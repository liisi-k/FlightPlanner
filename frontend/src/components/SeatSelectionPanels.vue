<template>
  <div class="panel">
    <div class="preferencesContainer">
      <h3 class="heading">Seat Preferences</h3>
      <label>
        <input 
          type="checkbox" 
          :checked="preferences.window"
          @change="updatePreference('window', $event.target.checked)"
        > Window Seat
      </label>
      <label>
        <input 
          type="checkbox" 
          :checked="preferences.extraLegroom"
          @change="updatePreference('extraLegroom', $event.target.checked)"
        > Extra Legroom
      </label>
      <label>
        <input 
          type="checkbox" 
          :checked="preferences.nearExit"
          @change="updatePreference('nearExit', $event.target.checked)"
        > Near Exit
      </label>
      <label>
        <input 
          type="number" 
          :value="numberOfPassengers" 
          @input="updatePassengerCount($event.target.value)"
          min="1" 
          max="9"
        > Number of Passengers
      </label>
      <button @click="findRecommended" class="btn confirm">Find Recommended</button>
    </div>

    <div class="selectedSeatsContainer">
      <h3 class="heading">Selected Seats</h3>
      <ul class="selectedSeatsList">
        <li v-for="seat in selectedSeats" :key="seat.id">
          {{ seat.id }} - {{ getSeatTypeLabel(seat) }}
          <button @click="removeSeat(seat)" class="btn remove">Remove</button>
        </li>
      </ul>
      <h4>Total Price: {{ totalPrice }} €</h4>
      <button @click="confirm" class="btn confirm" :disabled="selectedSeats.length === 0">
      Confirm Selection
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SeatSelectionPanels',
  props: {
    preferences: {
      type: Object,
      required: true
    },
    numberOfPassengers: {
      type: Number,
      required: true
    },
    selectedSeats: {
      type: Array,
      required: true
    },
    flight: {
      type: Object,
      required: true
    }
  },
computed: {
  totalPrice() {
    if (!this.flight || !this.flight.price) return 0;

    const priceNumber = parseFloat(this.flight.price.replace(/[^0-9.]/g, ''));
    this.$emit('update:totalPrice', this.selectedSeats.length * (isNaN(priceNumber) ? 0 : priceNumber));

    return this.selectedSeats.length * (isNaN(priceNumber) ? 0 : priceNumber);
  },
},

  watch: {
    totalPrice(newValue) {
      this.$emit('update:totalPrice', newValue); 
    }
  },
  methods: {
    updatePreference(key, value) {
      const newPreferences = { ...this.preferences };
      newPreferences[key] = value;
      this.$emit('update:preferences', newPreferences);
    },
    
    updatePassengerCount(value) {
      const count = Math.max(1, parseInt(value) || 1);
      this.$emit('update:numberOfPassengers', count);
    },

    getSeatTypeLabel(seat) {
      const features = [];
      
      features.push('Economy Class');
      
      if (seat.isWindowSeat) features.push('Window');
      if (seat.isExtraLegroom) features.push('Extra Legroom');
      if (seat.isNearExit) features.push('Near Exit');
      
      return features.join(', ');
    },
    
    findRecommended() {
      this.$emit('findRecommended');
    },

    removeSeat(seat) {
      this.$emit('removeSeat', seat);
    },
    
    confirm() {
      this.$emit('confirm');
    }
  }
}
</script>

<style scoped>
.panel {
  position: absolute;
  top:11%;
  width: 25%;
  height: 80vh;
  max-height: 100%;
  padding: 15px;
}
.preferencesContainer {
  width: 83%;
  height: auto;
  background: linear-gradient(135deg, #ffffff, #f3f3f3);
  padding: 8px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
  color: #000000;
}

.preferencesContainer label {
  display: flex;
  align-items: center;
  gap: 5px;
  font-size: 13px;
  margin-bottom: 5px;
  cursor: pointer;
  transition: 0.3s;
}

.preferencesContainer input[type="checkbox"] {
  width: 18px;
  height: 18px;
  accent-color: #1b1da5;
  cursor: pointer;
}

.preferencesContainer input[type="number"] {
  width: 30px;
  padding: 5px;
  border: 2px solid #ddd;
  border-radius: 6px;
  text-align: center;
  font-size: 13px;
  transition: 0.3s;
}

.preferencesContainer input[type="number"]:focus {
  border-color: #00478d;
  outline: none;
}

.selectedSeatsContainer {
  background-color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background: linear-gradient(135deg, #ffffff, #f3f3f3);
  position: absolute;
  left: 5%;
  top: 50%;
  width: 72%;
  height: auto;
  max-height: 40%;
  overflow-y: auto;
  padding: 15px;
  border-radius: 8px;
}

.selectedSeatsList li {
  width: 110%;
  margin-left: -12%;
  display: flex;
  justify-content: space-between;
  padding: 8px 0;
  font-size: 13px;
  border-bottom: 1px solid #2e2793;
}

.heading {
  text-align: left;
  font-size: 14px;
}

.btn {
  color: white;
  border: none;
  padding: 2px 8px;
  border-radius: 4px;
  cursor: pointer;
}

.btn.remove {
  background-color: #a32727;
}

.btn.confirm {
  background-color: #00478d;
  padding: 5px 15px;
  font-weight: bold;
  margin-top: 8px;
  width: 100%;
}
</style>
