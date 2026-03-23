package com.example.bikerentalsystem;

public class Rental {

    // Encapsulation: Private variables (Information Hiding)
    private String rentalID;
    private String bikeID;
    private String status;

    // Constructor
    public Rental(String rentalID, String bikeID, String status) {
        this.rentalID = rentalID;
        this.bikeID = bikeID;
        this.status = status;
    }

    // Public Getters (Standard practice for Encapsulation)
    public String getRentalID() { return rentalID; }
    public String getBikeID() { return bikeID; }
}
