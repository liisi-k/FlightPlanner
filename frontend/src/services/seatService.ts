import type { Seat, SeatPreferences } from '../types/seat';

class SeatService {
  static generateSeats(): Record<string, Seat> {
    const seats: Record<string, Seat> = {};
    const columns = ["A", "B", "C", "D", "E", "F"];
    
    for (let row = 1; row <= 15; row++) {
      for (let col of columns) {
        const id = `${row}${col}`;
        seats[id] = {
          id,
          type: "economy",
          row,
          col,
          occupied: Math.random() < 0.4,
          selected: false,
          recommended: false,
          isWindow: col === "A" || col === "F",
          isExtraLegroom: row === 1 || row === 15,
          isNearExit: row === 1 || row === 2 || row === 3 || row === 13 || row === 14 || row === 15,
        };
      }
    }
    return seats;
  }

  static findRecommendedSeats(
    seats: Record<string, Seat>, 
    preferences: SeatPreferences, 
    numberOfPassengers: number): Seat[] {
    
    Object.values(seats).forEach((seat) => {
      seat.recommended = false;
    });

    let availableSeats = Object.values(seats).filter(
      (seat) => !seat.occupied && !seat.selected
    );

    if (preferences.windowSeat) {
      availableSeats = availableSeats.filter((seat) => seat.isWindow);
    }
    if (preferences.extraLegroom) {
      availableSeats = availableSeats.filter((seat) => seat.isExtraLegroom);
    }
    if (preferences.nearExit) {
      availableSeats = availableSeats.filter((seat) => seat.isNearExit);
    }

    if (numberOfPassengers > 1) {
      const recommendedSeats = this.findSeatsTogether(availableSeats, numberOfPassengers);
      
      if (recommendedSeats.length > 0) {
        return recommendedSeats;
      }
    }

    availableSeats.sort((a, b) => {
      if (preferences.windowSeat && a.isWindow !== b.isWindow) 
        return a.isWindow ? -1 : 1;
      if (preferences.extraLegroom && a.isExtraLegroom !== b.isExtraLegroom) 
        return a.isExtraLegroom ? -1 : 1;
      if (preferences.nearExit && a.isNearExit !== b.isNearExit) 
        return a.isNearExit ? -1 : 1;
      return 0;
    });

    return availableSeats.slice(0, numberOfPassengers);
  }

  static findSeatsTogether(availableSeats: Seat[], getSeats: number): Seat[] {
    const seatsByRow: Record<number, Seat[]> = {};
    availableSeats.forEach((seat) => {
      if (!seatsByRow[seat.row]) seatsByRow[seat.row] = [];
      seatsByRow[seat.row].push(seat);
    });

    for (const [row, seats] of Object.entries(seatsByRow)) {
      if (seats.length >= getSeats) {
        const sortedSeats = seats.sort((a, b) => a.col.localeCompare(b.col));
        
        for (let i = 0; i <= sortedSeats.length - getSeats; i++) {
          const consecutive = sortedSeats.slice(i, i + getSeats);
          let isConsecutive = true;
          
          for (let j = 1; j < consecutive.length; j++) {
            if (consecutive[j].col.charCodeAt(0) !== consecutive[j - 1].col.charCodeAt(0) + 1) {
              isConsecutive = false;
              break;
            }
          }
          
          if (isConsecutive) {
            return consecutive;
          }
        }
      }
    }
    
    return [];
  }

//ChatGPT
  static async fetchFlights() {
    try {
      const response = await fetch("http://localhost:8080/api/flights");
      if (!response.ok) throw new Error(`Error ${response.status}`);
      return await response.json();
    } catch (error) {
      console.error("Failed to fetch flights:", error);
      return [];
    }
  }
}
//ChatGPT

export default SeatService;