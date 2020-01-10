package com.example.projekt.car.DTOs;

public class TakeCar {
    private int carID;
    private boolean isTaken;
    private double longitude;
    private double latitude;
    private long timestamp;

    public TakeCar(int id, boolean isTaken, double longitude, double latitude, long timestamp) {
        this.carID = id;
        this.isTaken = isTaken;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return carID;
    }

    public void setId(int carID) {
        this.carID = carID;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}
