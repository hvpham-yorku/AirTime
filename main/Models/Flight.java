package main.Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private String departureTime;
    private String arrivalTime;
    private double price;
    private int seatsAvailable;

    public Flight(int flightID, String flightNumber, String departureCity, String destinationCity,
                  String departureTime, String arrivalTime, double price, int seatsAvailable) {
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

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
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

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
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

    public ArrayList<Flight> getAllFlights() {
    ArrayList<Flight> flights = new ArrayList<>();
    String SQL = "SELECT * FROM flights";

    try (PreparedStatement stmt = DB.getConn().prepareStatement(SQL);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Flight flight = new Flight(
                rs.getInt("flight_id"),
                rs.getString("flight_number"),
                rs.getString("departure_city"),
                rs.getString("destination_city"),
                rs.getString("departure_time"),
                rs.getString("arrival_time"),
                rs.getDouble("price"),
                rs.getInt("seats_available")
            );
            flights.add(flight);
        }
    } catch (SQLException e) {
        System.out.println("Error fetching all flights: " + e.getMessage());
        e.printStackTrace();
    }

    return flights;
}

    
}

