<template>
  <div class="allBox">
    <h2>Available Flights</h2>
    <div class="flight-list-container">
      <ul v-if="flights.length > 0">
        <li v-for="flight in flights" :key="flight.flightNumber">
          <div class="flight-box">
            <img :src="'http://localhost:8080' + flight.imageUrl" :alt="flight.destination" class="flight-image" />
            <div class="flight-details">
                <span class="destination">{{ flight.destination }}</span>
                <span class="price">{{ flight.price }}</span>
              <span class="text">{{ flight.departureDate }} at {{ flight.departureTime }}</span>
            </div>
          </div>
        </li>
      </ul>
    </div>
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
.allBox {
  padding: 30px;
  border: 2px solid #f1f1f1;
  background-color: rgba(255, 255, 255, 0.513);
  border-radius: 12px;
  color: #333;
  max-width: 800px;
  margin-top: -2%;
  margin-left: center;
  height: 75%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.flight-list-container {
  overflow-y: auto;
  max-height: 70vh;
  padding-right: 10px;
}

.flight-box {
  display: flex;
  flex-direction: column;
  gap: 15px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 12px;
  background-color: #f7faff;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
  margin-bottom: 30px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.flight-image {
  width: 100%;
  max-width: 700px;
  height: 300px;
  object-fit: cover;
  border-radius: 12px;
  margin-bottom: 15px;
}

.flight-details {
  display: flex;
  flex-direction: column;
  gap: 8px;
  text-align: center;
}



.destination {
  font-weight: bold;
  color: #333;
  font-size: 30px;
  text-align: left;
}

.price {
  font-weight: bold;
  color: #007bff;
  font-size: 30px;
  text-align: right;
}

.text {
  color: #888;
  font-size: 14px;
  margin-top: 5px;
}
</style>
