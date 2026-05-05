package com.bikerental.bikerental.model;

public abstract class Rental {

    // Encapsulation: all fields are private
    private String rentalId;
    private String userId;
    private String bikeId;
    private String startDate;
    private String endDate;
    private String status;
    private double totalFee;

    // Constructor
    public Rental(String rentalId, String userId, String bikeId,
                  String startDate, String endDate, String status) {
        this.rentalId = rentalId;
        this.userId = userId;
        this.bikeId = bikeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.totalFee = 0.0;
    }

    // Abstraction: subclasses must implement this
    public abstract double calculateFee(int duration);

    // Getters
    public String getRentalId()  { return rentalId; }
    public String getUserId()    { return userId; }
    public String getBikeId()    { return bikeId; }
    public String getStartDate() { return startDate; }
    public String getEndDate()   { return endDate; }
    public String getStatus()    { return status; }
    public double getTotalFee()  { return totalFee; }

    // Setters
    public void setEndDate(String endDate)   { this.endDate = endDate; }
    public void setStatus(String status)     { this.status = status; }
    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }

    // File storage
    public String toFileString() {
        return rentalId + "," + userId + "," + bikeId + "," +
                startDate + "," + endDate + "," + status + "," + totalFee;
    }
}