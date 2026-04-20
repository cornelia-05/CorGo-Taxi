package com.cortaxi.domain;

public class Ride {
    private Client client;
    private Driver driver;
    private String startLocation;
    private String destination;
    private double price;
    private RideStatus status;

    public Ride(Client client, Driver driver, String start, String destination, Double price) {
        this.client = client;
        this.driver = driver;
        this.startLocation = start;
        this.destination = destination;
        this.price = price;
        this.status = RideStatus.WAITING;
    }

    public Ride(Client client, Driver driver) {
        this(client, driver, "Unknown", "Unknown", 0.0);
    }

    public Driver getDriver() {
        return driver;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
}
