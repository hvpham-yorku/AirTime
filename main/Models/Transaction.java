// Transaction Class Example
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

    // Getters and setters

    
}

