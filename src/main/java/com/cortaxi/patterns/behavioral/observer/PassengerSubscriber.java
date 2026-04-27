package com.cortaxi.patterns.behavioral.observer;

public class PassengerSubscriber implements ISubscriber {

    private final String passengerId;

    public PassengerSubscriber(String passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public void update(RideEvent event) {

        if ("DRIVER_ASSIGNED".equals(event.status)) {
            System.out.println("[PASSENGER " + passengerId + "] Driver " + event.driverId + " assigned");
        }

        if ("COMPLETED".equals(event.status)) {
            System.out.println("[PASSENGER " + passengerId + "] Fare: " + event.fare);
        }
    }
}