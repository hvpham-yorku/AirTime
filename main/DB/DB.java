package main.DB;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.Models.Booking;
import main.Models.Flight;
import main.Models.Transaction;
import main.Models.User;

/**
 * @author henap
 *
 */

public class DB implements DBInterface {

    utility localUtils = new utility();
    private static Connection conn;
	public boolean connNull = true;
	public boolean connIsValid = false;
	public int rowsInserted = 0;
	
	// Initialize the database connection in the DB constructor
    public DB() throws IOException, InterruptedException {
		String url = "jdbc:mysql://127.0.0.1:3306/airtime_test?reconnect=true";
		String user = "AirTime_123";
		String password = "dqEn%rv@Up%$2?f";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				// initialize();
				connNull = conn == null;
				connIsValid = conn.isValid(5);
				
				System.out.println("Connected to the database");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		return conn;
	}
	
	public void setAutoCommit(boolean choice) {
		try {
			if (conn != null) {
                conn.setAutoCommit(choice);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
   /* public DB() {
        try {
            DBConnection = new JDBC(); // Handle the potential exception
        } catch (IOException | InterruptedException e) {
            System.out.println("Error initializing the JDBC connection.");
            e.printStackTrace();
        }
    }

    // Get connection using JDBC's getConn() method
    public static Connection getConn() {
        return JDBC.getConn(); 
    }
	*/
	
    // ========================================================================================================
    // Test Database Connection
    // ========================================================================================================
    public boolean testConnection() {
        try {
            Connection conn = getConn(); // Get the connection from JDBC
            if (conn != null && conn.isValid(5)) {
                System.out.println("Connection to the database is successful!");
                return true;  // Connection is valid
            } else {
                System.out.println("Failed to connect to the database.");
                return false; // Connection invalid
            }
        } catch (SQLException e) {
            System.out.println("Error testing the connection: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

	// Close the connection when done with all operations
	public void closeConnection() {
		try {
			if (getConn() != null && !getConn().isClosed()) {
				getConn().close();
				System.out.println("Database connection closed.");
			}
		} catch (SQLException e) {
			System.out.println("Error closing database connection.");
			e.printStackTrace();
		}
	}

	// ========================================================================================================
    // USER METHODS
    // ========================================================================================================

    // Create a new user
    @Override
    public User createUser(int userID, String username, String password, String role) {
    	User user = null;
        if (!localUtils.checkEmptyOrNullString(username, password)) {
            System.out.println("Required parameters to createUser were empty or null ( createUser() - DB.java )");
            //return false;
        }

        try {
            String SQL = "INSERT INTO users(user_id, username, password, role) VALUES(?, ?, ?, ?)";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userID);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, role);

            int rowsUpdated = pstmt.executeUpdate();
            //conn.close();

            if (rowsUpdated > 0) {
                System.out.println("User created successfully.");
                return user;
            }

        } catch (SQLException e) {
            System.out.println("Error creating user - ( createUser() - DB.java ) \n");
            e.printStackTrace();
        }
        //return false;
		return user;
    }
    
    // Verify user credentials
    public User verifyUser(String username, String password) throws SQLException {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            System.out.println("Invalid input parameters for user verification ( verifyUser() - DB.java )");
            return null;
        }

        try {
            String SQL = "SELECT * FROM users WHERE username = ? AND password = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Extract user details from the result set
                int userID = rs.getInt("user_id");
                String retrievedUsername = rs.getString("username");
                String retrievedPassword = rs.getString("password");
                String role = rs.getString("role");

                // Create and return a User object if the credentials are valid
                return new User(userID, retrievedUsername, retrievedPassword, role);
            } else {
                System.out.println("Invalid credentials ( verifyUser() - DB.java )");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error verifying user - ( verifyUser() - DB.java )");
            e.printStackTrace();
            throw e;
        }
    }


    // Get user by userID
    @Override
    public User getUser(int userID) {
        if (userID <= 0) {
            System.out.println("Required parameter userID is invalid ( getUser() - DB.java )");
            return null;
        }

        try {
            String SQL = "SELECT * FROM users WHERE user_id = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userID); 
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Retrieve the fields from the ResultSet
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                //conn.close();
                // Create and return a new User object
                return new User(userID, username, password, role); // No redeclaration of userID here
            }

        } catch (SQLException e) {
            System.out.println("Error getting user - ( getUser() - DB.java ) \n");
            e.printStackTrace();
        }
        return null;
    }

    // Update user information
    @Override
    public Boolean updateUser(int userID, String username, String password, String role) {
        if (userID <= 0 || username == null || username.isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Invalid input parameters ( updateUser() - DB.java )");
            return false;
        }

        try {
            // SQL query to update user details
            String SQL = "UPDATE users SET username = ?, password = ?, role = ? WHERE user_id = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            // Set parameters for the prepared statement
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role != null ? role : "customer"); // Set default role as "customer" if null
            pstmt.setInt(4, userID);

            
            int rowsUpdated = pstmt.executeUpdate();
            //conn.close();

            return rowsUpdated > 0; // Returns true if the update was successful

        } catch (SQLException e) {
            System.out.println("Error updating user - ( updateUser() - DB.java ) \n");
            e.printStackTrace();
        }
        return false; // Returns false if an error occurred
    }

    // Delete user by userID
    @Override
    public Boolean deleteUser(int userID) {
        if (userID <= 0) { 
            System.out.println("Required parameters to deleteUser were invalid ( deleteUser() - DB.java )");
            return false;
        }

        try {
            String SQL = "DELETE FROM users WHERE user_id = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userID); 

            int rowsDeleted = pstmt.executeUpdate();
            //conn.close();

            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error deleting user - ( deleteUser() - DB.java ) \n");
            e.printStackTrace();
        }
        return false;
    }

    // ========================================================================================================
    // FLIGHT METHODS
    // ========================================================================================================

    // Create flight
    @Override
    public Boolean createFlight(int flightID, String flightNumber, String departureCity, String destinationCity,
            String departureTime, String arrivalTime, double price, int seatsAvailable) {

        if (flightNumber == null || flightNumber.isEmpty() || departureCity == null || departureCity.isEmpty() ||
                destinationCity == null || destinationCity.isEmpty() || departureTime == null || arrivalTime == null) {
            System.out.println("Required parameters are empty or null ( createFlight() - DB.java )");
            return false;
        }

        try {
            String SQL = "INSERT INTO flights (flight_id, flight_number, departure_city, destination_city, " +
                    "departure_time, arrival_time, price, seats_available) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            pstmt.setInt(1, flightID);
            pstmt.setString(2, flightNumber);
            pstmt.setString(3, departureCity);
            pstmt.setString(4, destinationCity);
            pstmt.setString(5, departureTime);
            pstmt.setString(6, arrivalTime);
            pstmt.setDouble(7, price);
            pstmt.setInt(8, seatsAvailable);

            int rowsInserted = pstmt.executeUpdate();
            //conn.close();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error creating flight - ( createFlight() - DB.java ) \n");
            e.printStackTrace();
        }
        return false;
    }

    // Get flight by flightID
    @Override
    public Flight getFlight(int flightID) {
        if (flightID <= 0) {
            System.out.println("Invalid flight ID ( getFlight() - DB.java )");
            return null;
        }

        try {
            String SQL = "SELECT * FROM flights WHERE flight_id = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, flightID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String flightNumber = rs.getString("flight_number");
                String departureCity = rs.getString("departure_city");
                String destinationCity = rs.getString("destination_city");
                String departureTime = rs.getString("departure_time");
                String arrivalTime = rs.getString("arrival_time");
                double price = rs.getDouble("price");
                int seatsAvailable = rs.getInt("seats_available");

                //conn.close();
                return new Flight(flightID, flightNumber, departureCity, destinationCity, departureTime, arrivalTime,
                        price, seatsAvailable);
            }

        } catch (SQLException e) {
            System.out.println("Error getting flight - ( getFlight() - DB.java ) \n");
            e.printStackTrace();
        }
        return null;
    }

    // Update a flight
    @Override
    public Boolean updateFlight(int flightID, String flightNumber, String departureCity, String destinationCity,
            String departureTime, String arrivalTime, double price, int seatsAvailable) {

        if (flightNumber == null || flightNumber.isEmpty() || departureCity == null || departureCity.isEmpty() ||
                destinationCity == null || destinationCity.isEmpty() || departureTime == null || arrivalTime == null) {
            System.out.println("Required parameters are empty or null ( updateFlight() - DB.java )");
            return false;
        }

        try {
            String SQL = "UPDATE flights SET flight_number = ?, departure_city = ?, destination_city = ?, " +
                    "departure_time = ?, arrival_time = ?, price = ?, seats_available = ? WHERE flight_id = ?";

            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            pstmt.setString(1, flightNumber);
            pstmt.setString(2, departureCity);
            pstmt.setString(3, destinationCity);
            pstmt.setString(4, departureTime);
            pstmt.setString(5, arrivalTime);
            pstmt.setDouble(6, price);
            pstmt.setInt(7, seatsAvailable);
            pstmt.setInt(8, flightID);

            int rowsUpdated = pstmt.executeUpdate();
            //conn.close();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error updating flight - ( updateFlight() - DB.java ) \n");
            e.printStackTrace();
        }
        return false;
    }

    // Delete flight by flightID
    @Override
    public Boolean deleteFlight(int flightID) {
        if (flightID <= 0) {
            System.out.println("Invalid flight ID ( deleteFlight() - DB.java )");
            return false;
        }

        try {
            String SQL = "DELETE FROM flights WHERE flight_id = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, flightID);

            int rowsDeleted = pstmt.executeUpdate();
            //conn.close();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting flight - ( deleteFlight() - DB.java ) \n");
            e.printStackTrace();
        }
        return false;
    }

    // ========================================================================================================
    // BOOKING METHODS
    // ========================================================================================================

    // Create a new booking
    public Boolean createBooking(int userId, int flightId, double price, String seatNumber, boolean travelInsurance) {
        try {
            String SQL = "INSERT INTO bookings(user_id, flight_id, price, seat_number, travel_insurance) VALUES(?,?,?,?,?)";
            Connection DBConnection = DB.getConn();
            PreparedStatement pstmt = DBConnection.prepareStatement(SQL);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, flightId);
            pstmt.setDouble(3, price);
            pstmt.setString(4, seatNumber);
            pstmt.setBoolean(5, travelInsurance);

            int rowsInserted = pstmt.executeUpdate();
            //conn.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error creating booking - ( createBooking() - DB.java )");
            e.printStackTrace();
            return false;
        }
    }

    // Get booking by bookingID
    @Override
    public Booking getBooking(int bookingID) {
        if (bookingID <= 0) {
            System.out.println("Invalid booking ID ( getBooking() - DB.java )");
            return null;
        }

        try {
            String SQL = "SELECT * FROM bookings WHERE booking_id = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, bookingID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("user_id");
                int flightID = rs.getInt("flight_id");
                String bookingDate = rs.getString("booking_date");
                int seatsBooked = rs.getInt("seats_booked");

                //conn.close();
                return new Booking(bookingID, userID, flightID, bookingDate, seatsBooked);
            }

        } catch (SQLException e) {
            System.out.println("Error getting booking - ( getBooking() - DB.java ) \n");
            e.printStackTrace();
        }
        return null;
    }

    // Update a booking
    public Boolean updateBooking(int bookingId, double newPrice, String newSeatNumber, boolean newTravelInsurance) {
        try {
            String SQL = "UPDATE bookings SET price = ?, seat_number = ?, travel_insurance = ? WHERE booking_id = ?";
            Connection DBConnection = DB.getConn();
            PreparedStatement pstmt = DBConnection.prepareStatement(SQL);
            pstmt.setDouble(1, newPrice);
            pstmt.setString(2, newSeatNumber);
            pstmt.setBoolean(3, newTravelInsurance);
            pstmt.setInt(4, bookingId);

            int rowsUpdated = pstmt.executeUpdate();
            //conn.close();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating booking - ( updateBooking() - DB.java )");
            e.printStackTrace();
            return false;
        }
    }

    // Delete a booking
    public Boolean deleteBooking(int bookingId) {
        try {
            String SQL = "DELETE FROM bookings WHERE booking_id = ?";
            Connection DBConnection = DB.getConn();
            PreparedStatement pstmt = DBConnection.prepareStatement(SQL);
            pstmt.setInt(1, bookingId);

            int rowsDeleted = pstmt.executeUpdate();
            //conn.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting booking - ( deleteBooking() - DB.java )");
            e.printStackTrace();
            return false;
        }
    }

    // ========================================================================================================
    // TRANSACTION METHODS
    // ========================================================================================================

    // Create a new transaction
    @Override
    public Boolean createTransaction(int transactionID, int userID, int bookingID, double amount) {
        if (transactionID <= 0 || userID <= 0 || bookingID <= 0 || amount <= 0) {
            System.out.println("Invalid input parameters for transaction creation ( createTransaction() - DB.java )");
            return false;
        }

        try {
            String SQL = "INSERT INTO transactions (transaction_id, user_id, booking_id, amount) VALUES (?, ?, ?, ?)";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            pstmt.setInt(1, transactionID);
            pstmt.setInt(2, userID);
            pstmt.setInt(3, bookingID);
            pstmt.setDouble(4, amount);

            int rowsInserted = pstmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error creating transaction - ( createTransaction() - DB.java ) \n");
            e.printStackTrace();
        }
        return false;
    }


    // Get transaction by transactionID
    @Override
    public Transaction getTransaction(int transactionID) {
        if (transactionID <= 0) {
            System.out.println("Invalid transaction ID ( getTransaction() - DB.java )");
            return null;
        }

        try {
            String SQL = "SELECT * FROM transactions WHERE transaction_id = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, transactionID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("user_id");
                int bookingID = rs.getInt("booking_id");
                double amount = rs.getDouble("amount");
                String transactionDate = rs.getString("transaction_date");

                //conn.close();
                return new Transaction(transactionID, userID, bookingID, amount, transactionDate);
            }

        } catch (SQLException e) {
            System.out.println("Error getting transaction - ( getTransaction() - DB.java ) \n");
            e.printStackTrace();
        }
        return null;
    }

    // ========================================================================================================
    // UTILITY METHODS
    // ========================================================================================================

    // Check flight availability by flightID and # of seats
    @Override
    public Boolean checkFlightAvailability(int flightID, int numberOfSeats) {
        if (flightID <= 0 || numberOfSeats <= 0) {
            System.out.println("Invalid flight ID or number of seats ( checkFlightAvailability() - DB.java )");
            return false;
        }

        try {
            String SQL = "SELECT seats_available FROM flights WHERE flight_id = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, flightID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int availableSeats = rs.getInt("seats_available");
                //conn.close();

                return availableSeats >= numberOfSeats;
            }

        } catch (SQLException e) {
            System.out.println("Error checking flight availability - ( checkFlightAvailability() - DB.java ) \n");
            e.printStackTrace();
        }
        return false;
    }

    // Check Booking by userID and flightID
    @Override
    public Boolean checkBookingExistence(int userID, int flightID) {
        if (userID <= 0 || flightID <= 0) {
            System.out.println("Invalid user ID or flight ID ( checkBookingExistence() - DB.java )");
            return false;
        }

        try {
            String SQL = "SELECT COUNT(*) FROM bookings WHERE user_id = ? AND flight_id = ?";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, flightID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                //conn.close();

                return count > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error checking booking existence - ( checkBookingExistence() - DB.java ) \n");
            e.printStackTrace();
        }
        return false;
    }

}
