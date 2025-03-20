<template>
    <div class="filters-panel">
      <h2 class="filter-title">Filter</h2>
      <div class="filter-group">
        <label>Destination</label>
        <input
          v-model="filters.destination"
          @input="emitFilters"
          placeholder="Where to?"
          class="filterInput"
        />
      </div>
      <div class="filter-group">
        <label>Travel Month</label>
        <input
          type="month"
          v-model="filters.month"
          @input="emitFilters"
          class="filterInput"
        />
      </div>
      <div class="filter-group">
        <label>Maximum Price</label>
        <input
          type="number"
          v-model="filters.maxPrice"
          @input="emitFilters"
          placeholder="Set your budget"
          class="filterInput"
        />
      </div>
      <div class="filter-group">
        <label>Time of Day</label>
        <select v-model="filters.timeOfDay" @change="emitFilters" class="filterInput">
          <option value="">Any Time</option>
          <option value="morning">Morning (6:00-11:59)</option>
          <option value="noon">Noon (12:00-15:59)</option>
          <option value="evening">Evening (16:00-20:59)</option>
          <option value="night">Night (21:00-5:59)</option>
        </select>
      </div>
      <button @click="resetFilters" class="reset-button">Reset Filters</button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        filters: {
          destination: '',
          month: '',
          maxPrice: null,
          timeOfDay: '',
        },
      };
    },
    methods: {
      emitFilters() {
        this.$emit('update-filters', this.filters);
      },
      resetFilters() {
        this.filters = { destination: '', month: '', maxPrice: null, timeOfDay: '' };
        this.emitFilters();
      },
    },
  };
  </script>
  
  <style scoped>
.filters-panel {
 display: flex;
  flex-direction: column;
  align-self: flex-start;
  position: absolute;
  width: 400px;
  height: 500px;
  left:70%;
  margin-top: 1%;
  background-color: rgba(255, 255, 255, 0.513);
  border-radius: 6%;
  border-left: 1px solid #e0e6ed;
  padding: 20px;
  z-index: 100;
}

.filter-title {
  font-size: 30px;
  color: #000000;
  margin-bottom: 2px;
  font-weight: bold;
}

.filter-group label {
  display: block;
  font-size: 20px;
  margin-top: 15px;
  color: #546e7a;
}

.filterInput {
  width: 90%;
  padding: 12px;
  border-radius: 8px;
  border: 1px solid #d0d9e1;
  background-color: white;
  font-size: 20px;
  transition: all 0.2s ease;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.03);
}

.filterInput:focus {
  outline: none;
  border-color: #1a73e8;
  box-shadow: 0 0 0 2px rgba(26, 115, 232, 0.2);
}

.reset-button {
 font-size: 18px;
  margin-top: 20px;
  padding: 12px;
  background-color: #395078;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.reset-button:hover {
  background-color: #517fc2;
}  
</style>
  