<template>
      <h2>Available Flights</h2>
      <ul v-if="flights.length > 0">
        <li v-for="flight in flights" :key="flight.flightNumber">
              <span class="text">{{ flight.destination }}</span> | 
              <span class="text">{{ flight.price }}</span> | 
              <span class="text">{{ flight.departureDate }}</span>
        </li>
      </ul>
  </template>
  
  <script>
  export default {
    props: {
      flights: Array
    }
  };
  </script>
  