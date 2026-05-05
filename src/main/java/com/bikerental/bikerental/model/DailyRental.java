package com.bikerental.bikerental.model;

public class DailyRental extends Rental {
    private static final double RATE = 1000.0;

    public DailyRental(String rentalId, String userId, String bikeId,
                       String startDate, String endDate, String status) {
        super(rentalId, userId, bikeId, startDate, endDate, status);
    }

    @Override
    public double calculateFee(int days) {
        return days * RATE;
    }
}
