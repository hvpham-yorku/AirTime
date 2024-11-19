package test.DB;

import java.io.IOException;
import main.DB.DB;
import main.Models.User;

public class DatabaseTest {
    public static void main(String[] args) {
    	try {
        DB db = new DB(); // Initialize the DB class, which should now handle connection setup internally

        // Test the database connection
        if (db.testConnection()) {
            System.out.println("Database is connected successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
            return; // Exit if the connection fails
        }

        // Test creating a user
        User newUser = db.createUser(1, "janedoe1", "password123", "admin");
        if (newUser != null) {
            System.out.println("User created successfully: " + newUser);
        } else {
            System.out.println("User creation failed.");
        }

        // Test retrieving the user
        User user = db.getUser(1);
        if (user != null) {
            System.out.println("Retrieved user: " + user);
        } else {
            System.out.println("Failed to retrieve user.");
        }

        // Test updating the user
        boolean userUpdated = db.updateUser(1, "janedoe_updated", "newpassword123", "customer");
        if (userUpdated) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Failed to update user.");
        }

        // Test deleting the user
        boolean userDeleted = db.deleteUser(1);
        if (userDeleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }

        // Test flight methods
        int flightID = 101;
        boolean flightCreated = db.createFlight(101, "AI100", "Toronto", "New York", "2024-12-10 08:00", "2024-12-10 10:00", 300.00, 150);
        if (flightCreated) {
            System.out.println("Flight created successfully.");
        } else {
            System.out.println("Failed to create flight.");
        }

        db.getFlight(flightID);  // Assuming getFlight prints the flight details if found

        boolean flightUpdated = db.updateFlight(101, "AI100", "Toronto", "Los Angeles", "2024-12-12 08:00", "2024-12-12 10:00", 350.00, 140);
        if (flightUpdated) {
            System.out.println("Flight updated successfully.");
        } else {
            System.out.println("Failed to update flight.");
        }

        boolean flightDeleted = db.deleteFlight(101);
        if (flightDeleted) {
            System.out.println("Flight deleted successfully.");
        } else {
            System.out.println("Failed to delete flight.");
        }

        // Test transaction methods
        // Change transaction_id each time for testing if unique is required
        boolean transactionCreated = db.createTransaction(1, 2, 2, 200.00);
        if (transactionCreated) {
            System.out.println("Transaction created successfully.");
        } else {
            System.out.println("Failed to create transaction.");
        }

        // Close the database connection
        db.closeConnection();
    	} catch (IOException | InterruptedException e) {
            System.out.println("Error initializing DB: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


