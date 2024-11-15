package main.DB;

import main.Models.Booking;
import main.Models.Flight;
import main.Models.Transaction;
import main.Models.User;

public interface DBInterface {
    // Return TRUE or FALSE for CREATE, UPDATE, DELETE
    // Return Object for READ Methods

    // User Management Methods
    public Boolean createUser(int userID, String username, String password, String role);

    public User getUser(int userID);

    public Boolean updateUser(int userID, String username, String password, String role);

    public Boolean deleteUser(int userID);

    // Flight Management Methods
    public Boolean createFlight(String flightNumber, String departureCity, String destinationCity,
            String departureTime, String arrivalTime, double price, int seatsAvailable);

    public Flight getFlight(String flightID);

    public Boolean updateFlight(String flightID, String flightNumber, String departureCity, String destinationCity,
            String departureTime, String arrivalTime, double price, int seatsAvailable);

    public Boolean deleteFlight(String flightID);

    // Booking Methods
    public Boolean createBooking(int userID, int flightID, double price, String seatNumber, boolean travelInsurance);

    public Booking getBooking(String bookingID);

    public Boolean cancelBooking(String bookingID);

    // Transaction Methods
    public Boolean createTransaction(int userID, int bookingID, double amount);

    public Transaction getTransaction(String transactionID);

    // Utility Methods
    public Boolean checkFlightAvailability(int flightID, int numberOfSeats);

    public Boolean checkBookingExistence(int userID, int flightID);

}
