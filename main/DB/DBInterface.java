package main.DB;

import main.Models.Booking;
import main.Models.Flight;
import main.Models.Transaction;
import main.Models.User;

/**
 * @author henap
 *
 */

public interface DBInterface {
	
    // Return TRUE or FALSE for CREATE, UPDATE, DELETE
    // Return Object for READ Methods

    // User Management Methods
    public User createUser(int userID, String username, String password, String role);

    public User getUser(int userID);

    public Boolean updateUser(int userID, String username, String password, String role);

    public Boolean deleteUser(int userID);

    // Flight Management Methods
    public Boolean createFlight(int flightID, String flightNumber, String departureCity, String destinationCity,
            String departureTime, String arrivalTime, double price, int seatsAvailable);

    public Flight getFlight(int flightID);

    public Boolean updateFlight(int flightID, String flightNumber, String departureCity, String destinationCity,
            String departureTime, String arrivalTime, double price, int seatsAvailable);

    public Boolean deleteFlight(int flightID);

    // Booking Methods
    public Boolean createBooking(int userID, int flightID, double price, String seatNumber, boolean travelInsurance);

    public Boolean updateBooking(int bookingId, double newPrice, String newSeatNumber, boolean newTravelInsurance);
    
    public Booking getBooking(int bookingID);

    public Boolean deleteBooking(int bookingID);

    // Transaction Methods
    public Boolean createTransaction(int transactionID, int userID, int bookingID, double amount);

    public Transaction getTransaction(int transactionID);

    // Utility Methods
    public Boolean checkFlightAvailability(int flightID, int numberOfSeats);

    public Boolean checkBookingExistence(int userID, int flightID);

}
