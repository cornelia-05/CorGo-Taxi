package com.cortaxi.patterns.behavioral.observer;

public class RideEvent {
    public final String rideId;
    public final String status;
    public final String driverId;
    public final double fare;

    public RideEvent(String rideId, String status, String driverId, double fare) {
        this.rideId = rideId;
        this.status = status;
        this.driverId = driverId;
        this.fare = fare;
    }
}
