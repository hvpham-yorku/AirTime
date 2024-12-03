package main.Models;

import java.util.ArrayList;

/**
 * @author henap
 *
 */

public class User {
    private int userID;
    private String username;
    private String password;
    private String role; // "admin" or "customer"
    private ArrayList<Transaction> transactions;
    private ArrayList<Flight> flights;
    private ArrayList<Flight> cart; // Cart for storing selected flights before payment

    // Constructor to initialize User object
    public User(int userID, String username, String password, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role != null ? role : "customer"; // Default to "customer" if role is null
        this.cart = new ArrayList<>(); // Initialize the cart
    }

    // Getter and Setter methods
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }


    public void setTransactions(ArrayList<Transaction> transactions){
        this.transactions = transactions;
    }

    public void setFlights(ArrayList<Flight> flights){
        this.flights = flights;
    }

    public ArrayList<Flight> getFlights(){
        return flights;
    }
    
    public void addToCart(Flight flight) {
        if (flight != null) {
            cart.add(flight);
        }
    }

    public void removeFromCart(Flight flight) {
        cart.remove(flight);
    }

    public ArrayList<Flight> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }

    @Override
    public String toString() {
        return "User [userID=" + userID + ", username=" + username + ", role=" + role + "]";
    }
}

