//ChatGPT
import { defineStore } from 'pinia';

interface Flight {
  flightNumber: string;
  destination: string;
  price: number;
}

export const useFlightStore = defineStore('flight', {
  state: () => ({
    flight: null as Flight | null,
  }),
  actions: {
    setFlight(flightData: Flight) {
      this.flight = flightData;
    }
  }
});
//ChatGPT
