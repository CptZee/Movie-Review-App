package com.example.myapplication.Data;

public class Account {
    private int ID;
    private String email;
    private int credentialID;
    private int profileID;

    public Account() {

    }

    public Account(int ID, String email, int credentialID) {
        this.ID = ID;
        this.email = email;
        this.credentialID = credentialID;
    }

    public Account(String email, int credentialID) {
        this.email = email;
        this.credentialID = credentialID;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCredentialID() {
        return credentialID;
    }

    public void setCredentialID(int credentialID) {
        this.credentialID = credentialID;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }
}
