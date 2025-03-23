<template>
  <div class="allBox">
    <h2>Available Flights</h2>
    <ul v-if="flights.length > 0">
      <li v-for="flight in flights" :key="flight.flightNumber">
        <button class="flight-box" @click="goToSeats(flight.flightNumber)">
          <img :src="'http://localhost:8080' + flight.imageUrl" :alt="flight.destination" class="flight-image" />
          <div class="flight-details">
            <span class="text dest">{{ flight.destination }}</span>
            <span class="text price">{{ flight.price }}</span>
            <span class="text">{{ flight.departureDate }} at {{ flight.departureTime }}</span>
          </div>
        </button>
      </li>
    </ul>
    <p v-else class="text no">No flights found.</p>
  </div>
</template>


<script>
export default {
  props: {
    flights: Array
  },
  methods: {
    goToSeats(flightId) {
      this.$router.push(`/seats/${flightId}`);
    }
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
  min-width: 800px;
  margin-top: -3%;
  height: 85vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  overflow-y: scroll;
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
  cursor: pointer;
}

.flight-box:hover {
  transform: scale(1.01);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
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
  height: 10vh;
  width: 27vw;
  display: flex;
  flex-direction: column;
  gap: 8px;
  text-align: center;
}

.text.dest{
  font-weight: bold;
  color: #333;
  font-size: 30px;
  text-align: left;
}

.text.price {
  color: #007bff;
  text-align: right;
  font-size: 30px;
  font-weight: bold;
}

.text {
  color: #888;
  font-size: 14px;
  margin-top: 5px;
}

.text.no {
  color: #000000;
  font-size: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
