package com.example.projekt.car.DTOs;

import java.io.Serializable;

public class Cars implements Serializable {
    private String model;
    private Integer faultID;//null if car has no fault
    private String registrationNumber;
    private boolean isOk;
    private boolean isTaken;
    private int id;
    private double latitude;
    private double longitude;
    private Integer userID;//null if car isn't taken

    public Cars(String model, Integer faultID, String registrationNumber, boolean isOk, boolean isTaken, int id, double latitude, double longitude, int userID) {
        this.model = model;
        this.faultID = faultID;
        this.registrationNumber = registrationNumber;
        this.isOk = isOk;
        this.isTaken = isTaken;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "model='" + model + '\'' +
                ", faultID=" + faultID +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", isOk=" + isOk +
                ", isTaken=" + isTaken +
                ", id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", userID=" + userID +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getFaultID() {
        return faultID;
    }

    public void setFaultID(Integer faultID) {
        this.faultID = faultID;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
