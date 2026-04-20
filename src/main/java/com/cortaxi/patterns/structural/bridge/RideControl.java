package com.cortaxi.patterns.structural.bridge;

public class RideControl {
    protected final IVehicleB vehicle;

    public RideControl(IVehicleB vehicle) {
        this.vehicle = vehicle;
    }

    public void startRide() {
        vehicle.start();
    }

    public void stopRide() {
        vehicle.stop();
    }

    public void increaseSpeed() {
        vehicle.setSpeed(vehicle.getSpeed() + 10);
    }
}
