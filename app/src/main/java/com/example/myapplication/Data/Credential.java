package com.example.myapplication.Data;

public class Credential {
    private int ID;
    private String username;
    private String password;
    private int accountID;

    public Credential() {
    }

    public Credential(int ID, String username, String password, int accountID) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.accountID = accountID;
    }

    public Credential(String username, String password, int accountID) {
        this.username = username;
        this.password = password;
        this.accountID = accountID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
