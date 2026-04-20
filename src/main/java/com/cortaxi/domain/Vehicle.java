package com.cortaxi.domain;

public class Vehicle {
    private String type;
    private String color;
    private String plateNumber;
    private Driver driver;

    public Vehicle(String type, String licensePlate, Driver driver, String color) {
        this.type = type;
        this.color = color;
        this.plateNumber = licensePlate;
        this.driver = driver;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getType() { return type; }
    public String getColor() { return color; }
    public Driver getDriver() { return driver; }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

}
