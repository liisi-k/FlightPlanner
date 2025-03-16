package com.flightPlanner.model;

import java.time.LocalDate;

public class Flight {
    private String flightNumber;
    private String destination;
    private String price;
    private LocalDate departureDate;

    public Flight(String flightNumber, String destination, String price, LocalDate departureDate) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.price = price;
        this.departureDate = departureDate;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}
