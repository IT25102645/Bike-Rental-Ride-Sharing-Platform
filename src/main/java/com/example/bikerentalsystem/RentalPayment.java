package com.example.bikerentalsystem;

public class RentalPayment extends Payment {
    private String rentalID;
    private int rentalDays;

    public RentalPayment(String paymentID, String userID, double amount, String status, String rentalID, int rentalDays) {
        super(paymentID, userID, amount, status);
        this.rentalID = rentalID;
        this.rentalDays = rentalDays;
    }

    // Polymorphism - Different payment calculation
    public double calculatePayment() {
        return rentalDays * getAmount();
    }

    public String getRentalID() { return rentalID; }
    public int getRentalDays() { return rentalDays; }
}