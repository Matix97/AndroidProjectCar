package com.example.projekt.car.DTOs;

public class Fault {
    private int carID;
    private boolean isCritical;
    private String description;
    private long timestamp;

    public Fault(int carID, boolean isCritical, String description, long timestamp) {
        this.carID = carID;
        this.isCritical = isCritical;
        this.description = description;
        this.timestamp = timestamp;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
