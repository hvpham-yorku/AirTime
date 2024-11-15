package main.Models;

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

    // Getters and setters

    
}

