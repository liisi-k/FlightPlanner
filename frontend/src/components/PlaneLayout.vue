<template>
  <div class="planeContainer">
    <div class="exit">FRONT EXIT</div>
    <h4 class="h4">Economy Class</h4>
    <div>
      <div class="row header">
        <div class="seatLabel">A</div>
        <div class="seatLabel">B</div>
        <div class="seatLabel">C</div>
        <div class="aisleSpacer"></div>
        <div class="seatLabel">D</div>
        <div class="seatLabel">E</div>
        <div class="seatLabel">F</div>
      </div>

      <div v-for="row in 15" :key="`economy-${row}`" class="row">
        <div class="rowNumber">{{ row }}</div>
        <div v-for="col in ['A', 'B', 'C']" :key="`${row}${col}`"
             class="seat"
             :class="getSeatClasses(row, col)"
             @click="$emit('selectSeat', row, col)">
          {{ row }}{{ col }}
        </div>
        <div class="aisleSpacer"></div>
        <div v-for="col in ['D', 'E', 'F']" :key="`${row}${col}`"
             class="seat"
             :class="getSeatClasses(row, col)"
             @click="$emit('selectSeat', row, col)">
          {{ row }}{{ col }}
        </div>
      </div>
    </div>
    <div class="exit">REAR EXIT</div>

    <div class="legendContainer">
      <div class="legendItem">
        <div class="seat example"></div>
        <span>Available</span>
      </div>
      <div class="legendItem">
        <div class="seat example occupied"></div>
        <span>Occupied</span>
      </div>
      <div class="legendItem">
        <div class="seat example selected"></div>
        <span>Selected</span>
      </div>
      <div class="legendItem">
        <div class="seat example extraLegroom"></div>
        <span>Extra Legroom</span>
      </div>
      <div class="legendItem">
        <div class="seat example window"></div>
        <span>Window</span>
      </div>
      <div class="legendItem">
        <div class="seat example nearExit"></div>
        <span>Near Exit</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PlaneLayout',
  props: {
    seats: {
      type: Object,
      required: true
    }
  },
  emits: ['selectSeat'],
  methods: {
    getSeatClasses(row, col) {
      const id = `${row}${col}`;
      const seat = this.seats[id];

      if (!seat) return {};

      return {
        'window': seat.isWindow,
        'extraLegroom': seat.isExtraLegroom,
        'nearExit': seat.isNearExit,
        'occupied': seat.occupied,
        'selected': seat.selected,
        'recommended': seat.recommended
      };
    }
  }
}
</script>

<style scoped>
.h4 {
  font-size: 11px;
  text-align: center;
}

.planeContainer {
  position: absolute;
  top: 13%;
  left: 40%;
  display: flex;
  flex-direction: column;
  width: 45vw;
  max-width: 450px;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 8px;
  box-sizing: border-box;
}

.exit {
  background-color: #1d86b3;
  color: white;
  text-align: center;
  width: 100%;
  font-weight: bold;
  font-size: 12px;
}

.row {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
  justify-content: center;
}

.row.header {
  margin-left: 5%;
}

.rowNumber {
  width: 20px;
  text-align: center;
  font-weight: bold;
  font-size: 12px;
  margin-right: 5px;
}

.seatLabel {
  width: 24px;
  min-width: 24px;
  height: 24px;
  min-height: 24px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 12px;
  margin: 0 6px;
}

.seat {
  width: clamp(24px, 3vw, 28px);
  height: clamp(24px, 3vw, 28px);
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #ccc;
  cursor: pointer;
  border-radius: 5px;
  font-size: clamp(10px, 1vw, 12px);
  transition: all 0.2s;
  margin: 0 2px;
}

.seat:hover:not(.occupied) {
  transform: scale(1.1);
  box-shadow: 0 0 5px rgb(15, 255, 7);
}

.seat.occupied {
  background-color: rgb(61, 60, 60);
  color: white;
  cursor: not-allowed;
}

.seat.selected {
    background-color: #3cc140;
  color: white;
    border-color: #2d8636;
}

  .seat.recommended:not(.selected) {
    background-color: #00478d;
  color: white;
  border-color: #2980b9;
  animation: pulse 1.5s infinite;
}

  .seat.window {
  border-color: #87CEEB;
  border-width: 2px;
}

.seat.extraLegroom:not(.occupied):not(.recommended):not(.selected) {
  background-color: #b5d3e7;
}

  .seat.nearExit {
  border-style: dashed;
  border-width: 1.4px;
  border-color: #f76700;
}

.aisleSpacer {
  width: 20px;
}

@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.05); }
  100% { transform: scale(1); }
}

.legendContainer {
  display: flex;
  flex-direction: row;
  justify-content: center;
  gap: 5px;
  width: auto;
  border-radius: 8px;
  font-size: 11px;
  align-items: center;
  margin-top: 15px;
}

.legendItem {
  display: flex;
  align-items: center;
  gap: 2px;
}

.seat.example {
  width: 15px;
  height: 15px;
  border-radius: 2px;
  display: inline-block;
  vertical-align: middle;
}
</style>
