package main.Models;

public class Booking {
    private int bookingID;
    private int userID;
    private int flightID;
    private String bookingDate;
    private int seatsBooked;

    public Booking(int bookingID, int userID, int flightID, String bookingDate, int seatsBooked) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.flightID = flightID;
        this.bookingDate = bookingDate;
        this.seatsBooked = seatsBooked;
    }

    // Getters
    public int getBookingID() {
        return bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    // Setters
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    
}

