package com.cortaxi.domain;

public class Driver extends User implements IRideActions {

    public Driver(String id, String name) {
        super(id, name);
    }
    private Vehicle vehicle;

    public Vehicle getVehicle() {return vehicle;}

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        System.out.println("Vehicle assigned: " + vehicle.getType() + " - " + vehicle.getPlateNumber());
    }

    public void printVehicleDetails() {
        if(vehicle != null) {
            System.out.println("Driver's vehicle: "
                    + vehicle.getType() + ", "
                    + vehicle.getColor() + ", Plate: "
                    + vehicle.getPlateNumber());
        } else {
            System.out.println("Driver has no vehicle assigned.");
        }
    }

    @Override
    public String getRole() {
        return "Driver";
    }

    public void acceptRide(Ride ride) {
        ride.setStatus(RideStatus.ARRIVING);
        notify("Ride accepted");
    }

    public void declineRide(Ride ride) {
        ride.setStatus(RideStatus.CANCELED);
        notify("Ride declined");
    }

    public void startRide(Ride ride) {
        ride.setStatus(RideStatus.ON_LOCATION);
        notify("Ride accepted");
    }

    @Override
    public void notify(String message) {
        System.out.println("Driver notification: " + message);
    }

    @Override
    public void onRideAssigned(Ride ride) {
        notify("New ride request received");
    }
}
