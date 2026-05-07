package com.bikerental.bikerentalplatform.model;

public class Review {
    private String reviewId;
    private String userId;
    private String username;
    private int rating;
    private String comment;
    private String date;

    public Review(String reviewId, String userId, String username, int rating, String comment, String date) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String getReviewId() { return reviewId; }
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public String getDate() { return date; }
    public void setComment(String comment) { this.comment = comment; }
    public void setRating(int rating) { this.rating = rating; }

    public String display() {
        return "[Review] User: " + username + " | Rating: " + rating + "/5 | " + comment;
    }

    public String toFileString() {
        return reviewId + "|" + userId + "|" + username + "|" + rating + "|" + comment + "|" + date;
    }
}