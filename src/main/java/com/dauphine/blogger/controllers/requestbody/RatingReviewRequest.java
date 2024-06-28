package com.dauphine.blogger.controllers.requestbody;

public record RatingReviewRequest(int rating, String review, String reviewerName) {}
