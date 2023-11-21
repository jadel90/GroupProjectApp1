package com.example.groupprojectapp;

public class RatingAndReview {
    private float rating;
    private String feedback;

    // Default constructor (required for Firebase)
    public RatingAndReview() {
        // Default constructor is needed for Firebase to deserialize objects
    }

    public RatingAndReview(float rating, String feedback) {
        this.rating = rating;
        this.feedback = feedback;
    }

    public float getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }
}
