package com.cortaxi.domain;

public class Client extends User implements IRideActions {

    public Client(String id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Client";
    }

    public Ride requestRide(Driver driver) {
        System.out.println("Client " + getName() + " requested a ride");
        return new Ride(this, driver);
    }

    @Override
    public void notify(String message) {
        System.out.println("Client notification: " + message);
    }
    @Override
    public void onRideAssigned(Ride ride) {
        notify("Your ride has been assigned");
    }
}
