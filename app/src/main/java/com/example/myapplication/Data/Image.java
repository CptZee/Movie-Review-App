package com.example.myapplication.Data;

public class Image {
    private int ID;
    private String alt;
    private byte[] img;

    public Image() {
    }

    public Image(int ID, String alt, byte[] img) {
        this.ID = ID;
        this.alt = alt;
        this.img = img;
    }

    public Image(String alt, byte[] img) {
        this.alt = alt;
        this.img = img;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
