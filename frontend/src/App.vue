<template>
  <header class="app-header">
    <div class="logo">
      <h3 v-if="!flightStore.flight">Flight Booking</h3>
      <h3 v-else>Flight: {{ flightStore.flight.flightNumber }}</h3>
    </div>
    
    <div v-if="flightStore.flight" class="flight-info">
      <div class="flight-detail">
        <span class="detail-label">Destination:</span>
        <span class="detail-value">{{ flightStore.flight.destination }}</span>
      </div>
      <div class="flight-detail">
        <span class="detail-label">Price:</span>
        <span class="detail-value">{{ flightStore.flight.price }}</span>
      </div>
    </div>
  </header>
  <router-view />
</template>

<script>
import { useFlightStore } from '@/stores/flightStore';
import { watchEffect } from 'vue';
import { useRoute } from 'vue-router';

export default {
  setup() {
    const flightStore = useFlightStore();
    const route = useRoute();

    watchEffect(() => {
      if (!route.path.startsWith('/seats/')) {
        flightStore.flight = null;
      }
    });

    return { flightStore };
  }
};
</script>

<style scoped>
.app-header {
  background: linear-gradient(90deg, #00478d, #0055a4);
  color: white;
  height: 5vh;
  min-height: 50px;
  max-height: 80px;
  padding: 0 15px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
  border-bottom: 2px solid #000000;
  font-size: 1rem;
  position: relative;
  overflow: hidden;
}

.logo h3 {
  font-size: 1rem;
  letter-spacing: 0.5px;
}

.flight-info {
  display: flex;
  align-items: center;
  gap: 20px; 
}

.flight-detail {
  display: flex;
  align-items: center;
  background: rgba(255, 255, 255, 0.1);
  padding: 6px 12px;
  border-radius: 8px;
}

.flight-detail:hover {
  background: rgba(255, 255, 255, 0.2);
  transform: scale(1.1);
}

.detail-label {
  margin-right: 8px;
  opacity: 0.7;
}

.detail-value {
  font-weight: bold;
}

.app-header::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 3px;
  background: linear-gradient(to right, #0004ff, #2dacf0);
  animation: gradient-flow 3s ease infinite;
  background-size: 200% 100%;
}

@keyframes gradient-flow {
  0%, 100% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
}
</style>
