package com.cortaxi.patterns.structural.bridge;

public final class AdvancedRideControl extends RideControl {

    public AdvancedRideControl(IVehicleB vehicle) {
        super(vehicle);
    }

    public void emergencyStop() {
        vehicle.stop();
        vehicle.setSpeed(0);
    }
}