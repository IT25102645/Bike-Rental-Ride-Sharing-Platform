package com.example.bikerentalsystem;

public class Payment {
    private String paymentID;
    private String userID;
    private double amount;
    private String status;

    public Payment(String paymentID, String userID, double amount, String status) {
        this.paymentID = paymentID;
        this.userID = userID;
        this.amount = amount;
        this.status = status;
    }

    public String getPaymentID() { return paymentID; }
    public String getUserID() { return userID; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
}