package main.DB;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

import main.Models.User;

public class DB implements DBInterface {

    JDBC DBConnection;
    utility localUtils = new utility();

    public DB() {
        try {
            DBConnection = new JDBC(); // Handle the potential exception
        } catch (IOException | InterruptedException e) {
            System.out.println("Error initializing the JDBC connection.");
            e.printStackTrace();
        }
    }

    // Get connection using JDBC's getConn() method
    public static Connection getConn() {
        return JDBC.getConn(); // Access JDBC's getConn() method
    }

    // ========================================================================================================
    // USER METHODS
    // ========================================================================================================

    // Create a new user
    @Override
    public Boolean createUser(int userID, String username, String password, String role) {
        if (!localUtils.checkEmptyOrNullString(username, password)) {
            System.out.println("Required parameters to createUser were empty or null ( createUser() - DB.java )");
            return false;
        }

        try {
            String SQL = "INSERT INTO users(userID, username, password, role) VALUES(?, ?, ?, ?)";
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userID);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, role);

            int rowsUpdated = pstmt.executeUpdate();
            conn.close();

            if (rowsUpdated > 0) {
                System.out.println("User created successfully.");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error creating user - ( createUser() - DB.java ) \n");
            e.printStackTrace();
        }
        return false;
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
            pstmt.setInt(1, userID); // Use setInt for userID since it's an integer
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Retrieve the fields from the ResultSet
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                conn.close();
                // Create and return a new User object
                return new User(userID, username, password, role); // No redeclaration of userID here
            }

        } catch (SQLException e) {
            System.out.println("Error getting user - ( getUser() - DB.java ) \n");
            e.printStackTrace();
        }
        return null;
    }

    // Delete user by userID
    @Override
    public Boolean deleteUser(int userID) {
        if (userID <= 0) { // Ensure userID is a valid positive integer
            System.out.println("Required parameters to deleteUser were invalid ( deleteUser() - DB.java )");
            return false;
        }

        try {
            String SQL = "DELETE FROM users WHERE user_id = ?";
            Connection conn = getConn(); // Ensure the connection is valid
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userID); // Set userID as an integer

            int rowsDeleted = pstmt.executeUpdate();
            conn.close();

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
            DBConnection.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error creating booking - ( createBooking() - DB.java )");
            e.printStackTrace();
            return false;
        }
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
            DBConnection.close();

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
            DBConnection.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting booking - ( deleteBooking() - DB.java )");
            e.printStackTrace();
            return false;
        }
    }
}
