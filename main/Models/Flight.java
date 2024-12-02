package main.Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import main.DB.DB;

/**
 * @author henap
 *
 */

public class Flight {
    private int flightID;
    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
    private int seatsAvailable;

    public Flight(int flightID, String flightNumber, String departureCity, String destinationCity,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, double price, int seatsAvailable) {
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
    }

    // Getters
    public int getFlightID() {
        return flightID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    // Setters
    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public String getOrigin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDestination() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

