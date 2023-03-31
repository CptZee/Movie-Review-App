package com.example.myapplication.Data;

public class Review {
    private int ID;
    private int movieID;
    private int reviewerID;
    private float review;
    private String comment;

    public Review() {
    }

    public Review(int reviewerID, float review, String comment) {
        this.reviewerID = reviewerID;
        this.review = review;
        this.comment = comment;
    }

    public Review(int ID, int reviewerID, int review, String comment) {
        this.ID = ID;
        this.reviewerID = reviewerID;
        this.review = review;
        this.comment = comment;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }

    public float getReview() {
        return review;
    }

    public void setReview(float review) {
        this.review = review;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
