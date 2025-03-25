<template>
  <div class="all">
    <SeatSelectionPanels
      :preferences="preferences"
      :numberOfPassengers="numberOfPassengers"
      :selectedSeats="selectedSeats"
      :flight="flight"
      @update:preferences="updatePreferences"
      @update:numberOfPassengers="updateNumberOfPassengers"
      @find-recommended="findRecommendedSeats"
      @remove-seat="removeSeat"
      @confirm="confirmSelection"
    />    
    <PlaneLayout 
      :seats="seats"
      @selectSeat="selectSeat"
    />
  </div>
</template>

<script>
import { useFlightStore } from '../stores/flightStore';
import { useRoute } from 'vue-router';
import SeatSelectionPanels from '../components/SeatSelectionPanels.vue';
import PlaneLayout from '../components/PlaneLayout.vue';
import SeatService from '../services/seatService';

export default {
  name: "PlaneSeats",
  components: {
    SeatSelectionPanels,
    PlaneLayout,
  },
  setup() {
    const flightStore = useFlightStore();
    const route = useRoute();
    return { flightStore, route };
  },
  data() {
    return {
      flight: null,
      seats: {},
      selectedSeats: [],
      preferences: {
        window: false,
        extraLegroom: false,
        nearExit: false,
      },
      numberOfPassengers: 1,
    };
  },
  created() {
    this.loadFlightData();
    this.initializeSeats();
  },
  methods: {
    async loadFlightData() {
      try {
        const flightNumber = this.route.params.flightId;
        const data = await SeatService.fetchFlights();
        const matchingFlight = data.find((flight) => flight.flightNumber === flightNumber);

        if (matchingFlight) {
          this.flight = matchingFlight;
          this.flightStore.setFlight(matchingFlight);
        } else {
          throw new Error(`Flight ${flightNumber} not found`);
        }
      } catch (error) {
        console.error(error.message);
      }
    },

    initializeSeats() {
      this.seats = SeatService.generateSeats();
    },
    
    selectSeat(row, col) {
      const id = `${row}${col}`;
      const seat = this.seats[id];

      if (!seat || seat.occupied) return;

      if (seat.selected) {
        seat.selected = false;
        this.selectedSeats = this.selectedSeats.filter((s) => s.id !== id);
      } else {
        if (this.selectedSeats.length < this.numberOfPassengers) {
          seat.selected = true;
          this.selectedSeats.push(seat);
        } else {
          alert(`${this.numberOfPassengers} allowed`);
        }
      }
    },

    removeSeat(seat) {
      this.seats[seat.id].selected = false;
      this.selectedSeats = this.selectedSeats.filter((s) => s.id !== seat.id);
    },

    updatePreferences(newPreferences) {
      this.preferences = newPreferences;
    },

    updateNumberOfPassengers(newNumber) {
      this.clearSelectedSeats();
      this.numberOfPassengers = newNumber;
    },

    clearSelectedSeats() {
      this.selectedSeats.forEach((seat) => {
        this.seats[seat.id].selected = false;
      });
      this.selectedSeats = [];
    },

    findRecommendedSeats() {
      const recommendedSeats = SeatService.findRecommendedSeats(
        this.seats, 
        this.preferences, 
        this.numberOfPassengers
      );

      Object.values(this.seats).forEach((seat) => {
        seat.recommended = recommendedSeats.some(r => r.id === seat.id);
      });

      if (recommendedSeats.length === 0) {
        alert("No seats available that match your preferences!");
      } else if (recommendedSeats.length < this.numberOfPassengers) {
        alert(`Only ${recommendedSeats.length} seats available that match your preferences.`);
      }
    },

    confirmSelection() {
      if (this.selectedSeats.length === 0) {
        alert("Please select at least one seat.");
        return;
      }
      if (this.selectedSeats.length !== this.numberOfPassengers) {
        alert(`Please select exactly ${this.numberOfPassengers} seat(s).`);
        return;
      }

      const seatInfo = this.selectedSeats.map((seat) => `${seat.id} (${seat.type})`).join(", ");
      alert(`You've selected: ${seatInfo}`);
    },
  },
};
</script>

<style scoped>
.all {
  background-color: rgb(230, 233, 238);
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 98vw;
  max-width: auto;
  margin: 0 auto;
  min-height: 150vh;
}
</style>
