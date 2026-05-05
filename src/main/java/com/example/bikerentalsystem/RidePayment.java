package com.example.bikerentalsystem;

public class RidePayment extends Payment {
    private String rideID;
    private double distance;

    public RidePayment(String paymentID, String userID, double amount, String status, String rideID, double distance) {
        super(paymentID, userID, amount, status);
        this.rideID = rideID;
        this.distance = distance;
    }

    public double calculatePayment() {
        return distance * getAmount();
    }

    public String getRideID() { return rideID; }
    public double getDistance() { return distance; }
}