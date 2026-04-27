package com.cortaxi.patterns.behavioral.observer;

public class DriverSubscriber implements ISubscriber {

    private final String driverId;

    public DriverSubscriber(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public void update(RideEvent event) {

        System.out.println("[DRIVER " + driverId + "] Ride " + event.rideId
                + " update: " + event.status);

        switch (event.status) {

            case "DRIVER_ASSIGNED" -> {
                System.out.println("[DRIVER " + driverId + "] You have been assigned a ride!");
                if (event.driverId != null && event.driverId.equals(driverId)) {
                    System.out.println("[DRIVER " + driverId + "] This ride is yours.");
                }
            }

            case "COMPLETED" ->
                    System.out.println("[DRIVER " + driverId + "] Ride completed. Fare earned: $" + event.fare);
        }
    }
}