<template>
    <div class="allBox">
      <h2>Available Flights</h2>
      <ul v-if="flights.length > 0">
        <li v-for="flight in flights" :key="flight.flightNumber">
          <div class="flight-box">
              <span class="destination">{{ flight.destination }}</span> 
              <span class="price">{{ flight.price }}</span> 
              <span class="departure-date">{{ flight.departureDate }}</span>
          </div>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      flights: Array
    }
  };
  </script>
  
  <style scoped>
  ul {
    list-style: none;
    padding: 0;
    margin: 0;
  }
  
  li {
    margin-bottom: 15px;
  }
  
  .allBox {
    padding: 30px;
    border: 2px solid #f1f1f1;
    background-color: rgba(0, 0, 0, 0.05);
    border-radius: 12px;
    color: #333;
    max-width: 800px;
  }

  .flight-box {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 10px;
    background-color: #f7faff;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }
  
  .destination {
    font-weight: bold;
    color: #333;
    font-size: 18px;
    width: 90px;
  }
  
  .price {
    font-weight: bold;
    color: #007bff;
    font-size: 20px;
    text-align: center;  
}
  
  .departure-date {
    color: #888;
    font-size: 14px;
    text-align: right;
  }
  </style>
  