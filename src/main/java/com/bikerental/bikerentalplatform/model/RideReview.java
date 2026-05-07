package com.bikerental.bikerentalplatform.model;

public class RideReview extends Review {
    private String rideId;
    private String routeName;

    public RideReview(String reviewId, String userId, String username,
                      int rating, String comment, String date,
                      String rideId, String routeName) {
        super(reviewId, userId, username, rating, comment, date);
        this.rideId = rideId;
        this.routeName = routeName;
    }

    public String getRideId() { return rideId; }
    public String getRouteName() { return routeName; }

    @Override
    public String display() {
        return "[Ride Review] Route: " + routeName + " | Rating: " + getRating() + "/5 | " + getComment();
    }

    @Override
    public String toFileString() {
        return "RIDE|" + super.toFileString() + "|" + rideId + "|" + routeName;
    }
}