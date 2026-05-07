package com.bikerental.bikerentalplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/submit-review")
    public String submitReview() { return "submit_review"; }

    @GetMapping("/reviews")
    public String reviewList() { return "review_list"; }

    @GetMapping("/admin/reviews")
    public String adminReviews() { return "admin_reviews"; }
}