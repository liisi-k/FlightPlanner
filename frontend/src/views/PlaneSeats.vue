<template>
  <div class="all">
      <div class="flightDetails">
      <h2>Flight number: {{ flight?.flightNumber }}</h2>
      <p>Destination: {{ flight?.destination }}</p>
      <p>Price: {{ flight?.price }}</p>
    </div>   
    <div class="planeContainer">   
      <PlaneLayout 
        :seats="seats"
        @selectSeat="selectSeat"/>
    </div>
  </div>
</template>

<script>
import PlaneLayout from '../components/PlaneLayout.vue';

export default {
  name: 'PlaneSeats',
  components: {
    PlaneLayout
  },
  props: {
    flightId: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      flight: null,
      seats: {},
      selectedSeats: [],
      preferences: {
        window: false,
        extraLegroom: false,
        nearExit: false
      },
      numberOfPassengers: 1
    };
  },
  created() {
    this.loadFlightData();
    this.initializeSeats();
  },
  methods: {
    loadFlightData() {
      fetch(`http://localhost:8080/api/flights`)
        .then(response => {
          if (!response.ok) {
            throw new Error(`Error ${response.status}`);
          }
          return response.json();
        })
        .then(data => {
          const matchingFlight = data.find(flight => flight.flightNumber === this.flightId);          
          if (matchingFlight) {
            this.flight = matchingFlight;
          } else {
            throw new Error(`Flight ${this.flightId} not found`);
          }
        });
    },
    
    initializeSeats() {
      const economyCols = ['A', 'B', 'C', 'D', 'E', 'F'];
      for (let row = 1; row <= 15; row++) {
        for (let col of economyCols) {
          const id = `${row}${col}`;
          this.seats[id] = {
            id,
            type: 'economy',
            row,
            col,
            occupied: Math.random() < 0.4,
            selected: false,
            recommended: false,
            isWindowSeat: col === 'A' || col === 'F',
            isExtraLegroom: row === 1 || row === 15,
            isNearExit: row === 1 || row === 2|| row === 3|| row === 13|| row === 14|| row === 15
          };
        }
      }
    },
    
    selectSeat(row, col) {
      const id = `${row}${col}`;
      const seat = this.seats[id];
      
      if (!seat || seat.occupied) return;
      
      if (seat.selected) {
        seat.selected = false;
        this.selectedSeats = this.selectedSeats.filter(s => s.id !== id);
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
    },
    
    getSeatTypeLabel(seat) {
      const features = [];      
      features.push('Economy Class');
      
      if (seat.isWindowSeat) features.push('Window');
      if (seat.isExtraLegroom) features.push('Extra Legroom');
      if (seat.isNearExit) features.push('Near Exit');
      
      return features.join(', ');
    },
  }
}
</script>

<style scoped>
.all {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 90vw;
  max-width: auto;
  margin: 0 auto;
}

.flightDetails {
  background-color: rgb(255, 255, 255);
  position: absolute;
  top:12%;
  left: 5%;
  width: 20%;
  min-width: 10%;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 20px;
}

.planeContainer {
  max-width: 70vh;
  padding: 20px;
  font-family: Arial, sans-serif;
}
</style>
