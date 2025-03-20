<template>
  <div class="home">
      <FlightList :flights="filteredFlights" />
    <FlightFilters @update-filters="applyFilters" />
  </div>
</template>

<script>
import axios from 'axios';
import FlightList from '../components/FlightList.vue';
import FlightFilters from '../components/FlightFilters.vue';

export default {
  components: { FlightList, FlightFilters },
  data() {
    return {
      flights: [],
      filteredFlights: [],
      filters: {
        destination: '',
        month: '',
        maxPrice: null,
        timeOfDay: '',
      },
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
        this.filteredFlights = this.flights;
      } catch (error) {
        console.error('Error fetching flights:', error);
      }
    },
    applyFilters(newFilters) {
      this.filters = newFilters;
      this.filteredFlights = this.flights.filter(flight => {
        const matchesDestination = !newFilters.destination || 
          flight.destination.toLowerCase().includes(newFilters.destination.toLowerCase());
        const matchesMonth = !newFilters.month || flight.departureDate?.startsWith(newFilters.month);
        const matchesPrice = !newFilters.maxPrice || parseFloat(flight.price) <= newFilters.maxPrice;
        const matchesTime = !newFilters.timeOfDay || this.getTimeCategory(flight) === newFilters.timeOfDay;

        return matchesDestination && matchesMonth && matchesPrice && matchesTime;
      });
    },
    getTimeCategory(flight) {
      const hour = parseInt(flight.departureTime?.split(':')[0], 10);
      if (hour >= 6 && hour < 12) return 'morning';
      if (hour >= 12 && hour < 16) return 'noon';
      if (hour >= 16 && hour < 21) return 'evening';
      return 'night';
    }
  }
};
</script>
  
<style scoped>
.home {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
}
</style>
