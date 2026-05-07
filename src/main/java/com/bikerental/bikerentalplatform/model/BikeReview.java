package com.bikerental.bikerentalplatform.model;

public class BikeReview extends Review {
    private String bikeModel;
    private String bikeId;

    public BikeReview(String reviewId, String userId, String username,
                      int rating, String comment, String date,
                      String bikeId, String bikeModel) {
        super(reviewId, userId, username, rating, comment, date);
        this.bikeId = bikeId;
        this.bikeModel = bikeModel;
    }

    public String getBikeModel() { return bikeModel; }
    public String getBikeId() { return bikeId; }

    @Override
    public String display() {
        return "[Bike Review] Bike: " + bikeModel + " | Rating: " + getRating() + "/5 | " + getComment();
    }

    @Override
    public String toFileString() {
        return "BIKE|" + super.toFileString() + "|" + bikeId + "|" + bikeModel;
    }
}