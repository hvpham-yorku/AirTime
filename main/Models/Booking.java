package main.Models;

/**
 * @author henap
 *
 */

public class Booking {
	private int bookingID;
	private int userID;
	private int flightID;
	private String bookingDate;
	private int seatsBooked;
	private boolean travelInsurance;

	public Booking(int bookingID, int userID, int flightID, String bookingDate, int seatsBooked,
			boolean travelInsurance) {
		this.bookingID = bookingID;
		this.userID = userID;
		this.flightID = flightID;
		this.bookingDate = bookingDate;
		this.seatsBooked = seatsBooked;
		this.travelInsurance = travelInsurance;
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

	public boolean isTravelInsurance() {
		return travelInsurance;
	}

	public void setTravelInsurance(boolean travelInsurance) {
		this.travelInsurance = travelInsurance;
	}

}
