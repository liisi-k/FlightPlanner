<template>
  <div class="home">
    <input
      v-model="destination"
      @input="filterFlights"
      @keyup.enter="searchFlights"
      placeholder="Enter your destination"
      class="destinationSearch"
    />
    <FlightList :flights="filteredFlights" />
  </div>
</template>

<script>
import axios from 'axios';
import FlightList from '../components/FlightList.vue';

export default {
  components: { FlightList },
  data() {
    return {
      destination: '',
      flights: [],
      filteredFlights: []
    };
  },
  async mounted() {
    await this.fetchAllFlights();
  },
  methods: {
    async fetchAllFlights() {
      try {
        const response = await axios.get('http://localhost:8080/api/flights');
        this.flights = response.data;
        this.filteredFlights = response.data;
      } catch (error) {
        console.error('Error fetching flights:', error);
      }
    },
    filterFlights() {
      this.filteredFlights = this.flights.filter(flight =>
        flight.destination.toLowerCase().includes(this.destination.toLowerCase())
      );
    },
    async searchFlights() {
      try {
        const response = await axios.get(`http://localhost:8080/api/flights?destination=${this.destination}`);
        this.filteredFlights = response.data;
      } catch (error) {
        console.error('Error fetching flights:', error);
      }
    }
  }
};
</script>

<style scoped>
.destinationSearch {
  margin-bottom: 20px;
  background-color: rgb(255, 255, 255);
  padding: 8px;
  border-radius: 10px;
  width: 250px;
  height: 30px;
  font-size: 16px;
  border: 1px solid #ccc;
}
</style>
