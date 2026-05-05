package com.bikerental.bikerental.model;

public class HourlyRental extends Rental {
    private static final double RATE = 150.0;

    public HourlyRental(String rentalId, String userId, String bikeId,
                        String startDate, String endDate, String status) {
        super(rentalId, userId, bikeId, startDate, endDate, status);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * RATE;
    }
}