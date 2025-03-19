package com.flightPlanner.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private String flightNumber;
    private String destination;
    private String price;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String imageUrl;

    public Flight(String flightNumber, String destination, String price, LocalDate departureDate, LocalTime departureTime, String imageUrl) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.price = price;
        this.departureDate = departureDate;
        this.departureTime=departureTime;
        this.imageUrl= imageUrl;
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
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
