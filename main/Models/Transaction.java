package main.Models;

public class Transaction {
    private int transactionID;
    private int userID;
    private int bookingID;
    private double amount;
    private String transactionDate;

    public Transaction(int transactionID, int userID, int bookingID, double amount, String transactionDate) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.bookingID = bookingID;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getters
    public int getTransactionID() {
        return transactionID;
    }

    public int getUserID() {
        return userID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    // Setters
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    
}

