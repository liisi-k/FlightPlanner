package com.flightPlanner.controller;

import com.flightPlanner.model.Flight;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final List<Flight> flights = new ArrayList<>(List.of(
            new Flight("FL123", "London", "120€", LocalDate.of(2025, 3, 20)),
            new Flight("FL456", "Paris", "150€", LocalDate.of(2025, 3, 25)),
            new Flight("FL789", "New York", "400€", LocalDate.of(2025, 4, 5)),
            new Flight("FL999", "Berlin", "180€", LocalDate.of(2025, 3, 22))
    ));

    @GetMapping
    public List<Flight> getFlights(
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String date
    ) {
        return flights.stream()
                .collect(Collectors.toList());
    }

    @PostMapping
    public Flight addFlight(@RequestBody Flight flight) {
        flights.add(flight);
        return flight;
    }
}
