package com.example.roadrescue;

public class User {
    private String name;
    private String lastMassage;
    private String phoneNo;
    private String country;
    private int imageId;

    public User(String name, String lastMassage, String phoneNo, String country, int imageId) {
        this.name = name;
        this.lastMassage = lastMassage;
        this.phoneNo = phoneNo;
        this.country = country;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getLastMassage() {
        return lastMassage;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getCountry() {
        return country;
    }

    public int getImageId() {
        return imageId;
    }
}

