package com.cortaxi.patterns.behavioral.memento;

import java.util.Stack;

public class RideState {

    private String rideId;
    private String status;
    private String driverId;
    private double fare;

    private final Stack<RideMemento> history = new Stack<>();

    public RideState(String rideId) {
        this.rideId = rideId;
        this.status = "CREATED";
    }

    public RideMemento save() {
        return new RideMemento(rideId, status, driverId, fare);
    }

    public void restore(RideMemento memento) {
        this.rideId = memento.rideId;
        this.status = memento.status;
        this.driverId = memento.driverId;
        this.fare = memento.fare;
    }

    public void pushState() {
        history.push(save());
    }

    public void undo() {
        if (!history.isEmpty()) {
            restore(history.pop());
        }
    }

    // business updates

    public void assignDriver(String driverId) {
        pushState();
        this.driverId = driverId;
        this.status = "DRIVER_ASSIGNED";
    }

    public void completeRide(double fare) {
        pushState();
        this.status = "COMPLETED";
        this.fare = fare;
    }

    // getters (for demo)
    public String getStatus() { return status; }
    public String getDriverId() { return driverId; }
    public double getFare() { return fare; }

    // MEMENTO (nested class)
    public static class RideMemento {
        private final String rideId;
        private final String status;
        private final String driverId;
        private final double fare;

        private RideMemento(String rideId, String status, String driverId, double fare) {
            this.rideId = rideId;
            this.status = status;
            this.driverId = driverId;
            this.fare = fare;
        }
    }
}