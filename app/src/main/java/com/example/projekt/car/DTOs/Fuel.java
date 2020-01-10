package com.example.projekt.car.DTOs;

public class Fuel {
    private int carID;
    private double amount;
    private long timestamp;
    private double latitude;
    private double longitude;
    private double price;

    public Fuel(int carID, double amount, long timestamp, double latitude, double longitude, double price) {
        this.carID = carID;
        this.amount = amount;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
