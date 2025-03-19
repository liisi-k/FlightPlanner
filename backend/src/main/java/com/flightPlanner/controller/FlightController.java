package com.flightPlanner.controller;

import com.flightPlanner.model.Flight;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:5173")  
public class FlightController {

    private final List<Flight> flights = new ArrayList<>(List.of(
            new Flight("FL123", "London", "120€", LocalDate.of(2025, 3, 20), LocalTime.of(14, 30),
            "/images/imageLondon.jpg"),
            new Flight("FL456", "Paris", "150€", LocalDate.of(2025, 3, 25), LocalTime.of(15, 45),
            "/images/imageParis.jpg"),
            new Flight("FL789", "Santorini", "220", LocalDate.of(2025, 4, 5), LocalTime.of(6, 30),
            "/images/imageSantorini.jpg"),
            new Flight("FL999", "Rome", "180€", LocalDate.of(2025, 3, 22), LocalTime.of(10, 00),
            "/images/imageRome.jpg")
    ));

    @GetMapping
    public List<Flight> getFlights(
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String date
    ) {
        return flights.stream()
                .filter(flight -> destination == null || flight.getDestination().equalsIgnoreCase(destination))
                .filter(flight -> price == null || flight.getPrice().equals(price))
                .filter(flight -> date == null || flight.getDepartureDate().toString().equals(date))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Flight addFlight(@RequestBody Flight flight) {
        flights.add(flight);
        return flight;
    }
}
