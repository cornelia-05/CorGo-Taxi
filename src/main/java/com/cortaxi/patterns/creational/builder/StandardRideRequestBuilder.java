package com.cortaxi.patterns.creational.builder;

public class StandardRideRequestBuilder implements IRideRequestBuilder<StandardRideRequest> {

    private String riderName = "Unknown rider";
    private String pickup = "Unknown pickup";
    private String dropout = "Unknown dropout";
    private String vehicleType = "Standard";
    private boolean childSeat;
    private boolean petFriendly;
    private String notes = "";
    private double estimatedPrice = 0.0;

    @Override
    public IRideRequestBuilder<StandardRideRequest> reset() {
        this.riderName = "Unknown rider";
        this.pickup = "Unknown pickup";
        this.dropout = "Unknown dropout";
        this.vehicleType = "Standard";
        this.notes = "";
        this.estimatedPrice = 0.0;
        return this;
    }

    @Override
    public IRideRequestBuilder<StandardRideRequest> rider(String name) {
        this.riderName = name;
        return this;
    }

    @Override
    public IRideRequestBuilder<StandardRideRequest> from(String pickup) {
        this.pickup = pickup;
        return this;
    }

    @Override
    public IRideRequestBuilder<StandardRideRequest> to(String dropout) {
        this.dropout = dropout;
        return this;
    }

    @Override
    public IRideRequestBuilder<StandardRideRequest> vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    @Override
    public IRideRequestBuilder<StandardRideRequest> petFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
        return this;
    }

    @Override
    public IRideRequestBuilder<StandardRideRequest> notes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public IRideRequestBuilder<StandardRideRequest> estimatedPrice(double price) {
        this.estimatedPrice = price;
        return this;
    }

    @Override
    public StandardRideRequest build() {
        return new StandardRideRequest(
                riderName,
                pickup,
                dropout,
                vehicleType,
                childSeat,
                petFriendly,
                notes,
                estimatedPrice
        );
    }
}
