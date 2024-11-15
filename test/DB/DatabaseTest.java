package test.DB;

import main.DB.DB;
import main.Models.User;

public class DatabaseTest {
    public static void main(String[] args) {
        DB db = new DB(); // Initialize the DB class

        // Test the database connection
        if (db.testConnection()) {
            System.out.println("Database is connected successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
        
        // Test creating user
        db.createUser(0, "janedoe", "password123", "admin");
        
        // Test retrieving user
        User user = db.getUser(1);
        System.out.println(user);
        
        // Test updating user
        db.updateUser(1, "janedoe_updated", "newpassword123", "customer");
        
        // Test deleting user
        db.deleteUser(1);
        
        // Test flight methods
        db.createFlight(101, "AI100", "Toronto", "New York", "2024-12-10 08:00", "2024-12-10 10:00", 300.00, 150);
        db.getFlight(101);
        db.updateFlight(101, "AI100", "Toronto", "Los Angeles", "2024-12-12 08:00", "2024-12-12 10:00", 350.00, 140);
        db.deleteFlight(101);
        
        // Test booking and transaction methods
        db.createTransaction(1, 101, 200.00);
    }
}

