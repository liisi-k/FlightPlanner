<template>
  <div class="home">
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
  }
};
</script>
