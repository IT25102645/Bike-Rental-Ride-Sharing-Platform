package com.bikerental.bikerentalplatform.controller;

import com.bikerental.bikerentalplatform.model.*;
import com.bikerental.bikerentalplatform.util.ReviewFileHandler;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @GetMapping
    public List<Map<String, Object>> getAllReviews() throws IOException {
        List<Review> reviews = ReviewFileHandler.readAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Review r : reviews) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("reviewId", r.getReviewId());
            map.put("username", r.getUsername());
            map.put("rating", r.getRating());
            map.put("comment", r.getComment());
            map.put("date", r.getDate());
            if (r instanceof BikeReview br) {
                map.put("type", "BIKE");
                map.put("bikeModel", br.getBikeModel());
            } else if (r instanceof RideReview rr) {
                map.put("type", "RIDE");
                map.put("routeName", rr.getRouteName());
            }
            result.add(map);
        }
        return result;
    }

    @PostMapping
    public Map<String, Object> createReview(@RequestParam Map<String, String> params) throws IOException {
        String type     = params.get("type");
        String userId   = params.get("userId");
        String username = params.get("username");
        int rating      = Integer.parseInt(params.get("rating"));
        String comment  = params.get("comment");
        String date     = new Date().toString();
        String reviewId = "R" + System.currentTimeMillis();

        Review review;
        if ("BIKE".equals(type)) {
            review = new BikeReview(reviewId, userId, username, rating, comment, date,
                    params.get("bikeId"), params.get("bikeModel"));
        } else {
            review = new RideReview(reviewId, userId, username, rating, comment, date,
                    params.get("rideId"), params.get("routeName"));
        }
        ReviewFileHandler.append(review);
        return Map.of("success", true, "message", "Review submitted successfully!");
    }

    @DeleteMapping("/{reviewId}")
    public Map<String, Object> deleteReview(@PathVariable String reviewId) throws IOException {
        List<Review> reviews = ReviewFileHandler.readAll();
        reviews.removeIf(r -> r.getReviewId().equals(reviewId));
        ReviewFileHandler.writeAll(reviews);
        return Map.of("success", true, "message", "Review deleted!");
    }
}