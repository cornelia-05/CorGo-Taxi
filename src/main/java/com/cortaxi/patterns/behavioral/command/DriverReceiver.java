package com.cortaxi.patterns.behavioral.command;

// Receiver - contains business logic for driver operations
public class DriverReceiver {
    private String driverId;
    private String status;
    private double totalEarnings;
    private int completedRides;

    public DriverReceiver(String driverId) {
        this.driverId = driverId;
        this.status = "OFFLINE";
        this.totalEarnings = 0.0;
        this.completedRides = 0;
    }

    // Business logic methods
    public void goOnline() {
        this.status = "ONLINE";
        System.out.println("[RECEIVER] Driver " + driverId + " is now ONLINE");
    }

    public void goOffline() {
        this.status = "OFFLINE";
        System.out.println("[RECEIVER] Driver " + driverId + " is now OFFLINE");
    }

    public void acceptRide(String rideId) {
        System.out.println("[RECEIVER] Driver " + driverId + " accepted ride " + rideId);
    }

    public void completeRide(double earnings) {
        this.completedRides++;
        this.totalEarnings += earnings;
        System.out.println("[RECEIVER] Driver " + driverId + " completed a ride. "
                + "Total earnings: $" + totalEarnings + " | Rides completed: " + completedRides);
    }

    public void updateLocation(String latitude, String longitude) {
        System.out.println("[RECEIVER] Driver " + driverId + " location updated to ("
                + latitude + ", " + longitude + ")");
    }

    // Getters
    public String getDriverId() {
        return driverId;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public int getCompletedRides() {
        return completedRides;
    }
}