<template>
  <div class="all">   
    <PlaneLayout 
      :seats="seats"
      @selectSeat="selectSeat"
    />
  </div>
</template>

<script>
import { useRoute } from 'vue-router';
import PlaneLayout from '../components/PlaneLayout.vue';
import SeatService from '../services/seatService';

export default {
  name: "PlaneSeats",
  components: {
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
