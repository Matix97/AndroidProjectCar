package com.example.projekt.car.data;

import java.io.Serializable;


public class Car implements Serializable {
    Double xCoordinate;
    Double yCoordinate;
    Boolean isTaken;
    Double fuel;
    String carsID;
    Boolean criticalFault;
    Boolean lightFault;
    int image;

    public Car(Double xCoordinate, Double yCoordinate, Boolean isTaken, Double fuel, String carsID, Boolean criticalFault, Boolean lightFault, int image) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.isTaken = isTaken;
        this.fuel = fuel;
        this.carsID = carsID;
        this.criticalFault = criticalFault;
        this.lightFault = lightFault;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Boolean getTaken() {
        return isTaken;
    }

    public void setTaken(Boolean taken) {
        isTaken = taken;
    }

    public Double getFuel() {
        return fuel;
    }

    public void setFuel(Double fuel) {
        this.fuel = fuel;
    }

    public Boolean getCriticalFault() {
        return criticalFault;
    }

    public void setCriticalFault(Boolean criticalFault) {
        this.criticalFault = criticalFault;
    }

    public Boolean getLightFault() {
        return lightFault;
    }

    public void setLightFault(Boolean lightFault) {
        this.lightFault = lightFault;
    }

    public String getCarsID() {
        return carsID;
    }
}
