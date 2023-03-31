package com.example.myapplication.Data;

public class Movie {
    private int ID;
    private String title;
    private int year;
    private String sypnosis;
    private float rating;
    private int posterID;

    public Movie() {
    }

    public Movie(int ID, String title, int year, String sypnosis, float rating, int posterID) {
        this.ID = ID;
        this.title = title;
        this.year = year;
        this.sypnosis = sypnosis;
        this.rating = rating;
        this.posterID = posterID;
    }

    public Movie(String title, int year, String sypnosis, float rating, int posterID) {
        this.title = title;
        this.year = year;
        this.sypnosis = sypnosis;
        this.rating = rating;
        this.posterID = posterID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSypnosis() {
        return sypnosis;
    }

    public void setSypnosis(String sypnosis) {
        this.sypnosis = sypnosis;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPosterID() {
        return posterID;
    }

    public void setPosterID(int posterID) {
        this.posterID = posterID;
    }
}
