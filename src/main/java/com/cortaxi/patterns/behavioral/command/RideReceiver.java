package com.cortaxi.patterns.behavioral.command;
// Receiver - contains business logic for ride operations
public class RideReceiver {
    private String rideId;
    private String passengerId;
    private String pickupLocation;
    private String dropoffLocation;
    private double fare;
    private String status;

    public RideReceiver(String rideId, String passengerId) {
        this.rideId = rideId;
        this.passengerId = passengerId;
        this.status = "PENDING";
    }

    // Business logic methods
    public void createRide(String pickup, String dropoff) {
        this.pickupLocation = pickup;
        this.dropoffLocation = dropoff;
        this.status = "CREATED";
        System.out.println("[RECEIVER] Ride " + rideId + " created from "
                + pickup + " to " + dropoff);
    }

    public void confirmRide() {
        this.status = "CONFIRMED";
        System.out.println("[RECEIVER] Ride " + rideId + " confirmed");
    }

    public void assignDriver(String driverId) {
        this.status = "DRIVER_ASSIGNED";
        System.out.println("[RECEIVER] Driver " + driverId + " assigned to ride " + rideId);
    }

    public void startRide() {
        this.status = "IN_PROGRESS";
        System.out.println("[RECEIVER] Ride " + rideId + " started");
    }

    public void completeRide(double finalFare) {
        this.fare = finalFare;
        this.status = "COMPLETED";
        System.out.println("[RECEIVER] Ride " + rideId + " completed. Fare: $" + finalFare);
    }

    public void cancelRide() {
        this.status = "CANCELLED";
        System.out.println("[RECEIVER] Ride " + rideId + " cancelled");
    }

    // Getters
    public String getRideId() {
        return rideId;
    }

    public String getStatus() {
        return status;
    }

    public double getFare() {
        return fare;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }
}