package com.cortaxi.patterns.behavioral.command;

// Concrete Command - creates a new ride
public class CreateRideCommand implements ICommand {
    private RideReceiver ride;
    private String pickupLocation;
    private String dropoffLocation;

    public CreateRideCommand(RideReceiver ride, String pickup, String dropoff) {
        this.ride = ride;
        this.pickupLocation = pickup;
        this.dropoffLocation = dropoff;
    }

    @Override
    public void execute() {
        ride.createRide(pickupLocation, dropoffLocation);
    }

    @Override
    public void undo() {
        System.out.println("[COMMAND] Undoing create ride operation for " + ride.getRideId());
        // Reset ride to initial state
    }
}