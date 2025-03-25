export interface Seat {
    id: string;
    type: 'economy';
    row: number;
    col: string;
    occupied: boolean;
    selected: boolean;
    recommended: boolean;
    isWindow: boolean;
    isExtraLegroom: boolean;
    isNearExit: boolean;
  }
  
  export interface SeatPreferences {
    windowSeat: boolean;
    extraLegroom: boolean;
    nearExit: boolean;
  }