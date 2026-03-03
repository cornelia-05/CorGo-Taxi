package com.cortaxi.patterns.creational.builder;

public class BusinessRideRequestBuilder implements IRideRequestBuilder<BusinessRideRequest> {

    private String riderName = "Unknown rider";
    private String pickup = "Unknown pickup";
    private String dropout = "Unknown dropout";
    private String vehicleType = "Business";
    private boolean prioritySupport = true;
    private String invoiceEmail = "";
    private String notes = "";
    private double estimatedPrice = 0.0;

    @Override
    public IRideRequestBuilder<BusinessRideRequest> reset() {
        this.riderName = "Unknown rider";
        this.pickup = "Unknown pickup";
        this.dropout = "Unknown dropout";
        this.vehicleType = "Standard";
        this.notes = "";
        this.estimatedPrice = 0.0;
        return this;
    }

    @Override
    public IRideRequestBuilder<BusinessRideRequest> rider(String name) {
        this.riderName = name;
        return this;
    }

    @Override
    public IRideRequestBuilder<BusinessRideRequest> from(String pickup) {
        this.pickup = pickup;
        return this;
    }

    @Override
    public IRideRequestBuilder<BusinessRideRequest> to(String dropout) {
        this.dropout = dropout;
        return this;
    }

    @Override
    public IRideRequestBuilder<BusinessRideRequest> vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    @Override
    public IRideRequestBuilder<BusinessRideRequest> petFriendly(boolean petFriendly) {
        return this;
    }

    @Override
    public IRideRequestBuilder<BusinessRideRequest> notes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public IRideRequestBuilder<BusinessRideRequest> estimatedPrice(double price) {
        this.estimatedPrice = price;
        return this;
    }

    public BusinessRideRequestBuilder prioritySupport(boolean priority) {
        this.prioritySupport = priority;
        return this;
    }

    public void invoiceEmail(String email) {
        this.invoiceEmail = email;
    }

    @Override
    public BusinessRideRequest build() {
        return new BusinessRideRequest(
                riderName,
                pickup,
                dropout,
                vehicleType,
                prioritySupport,
                invoiceEmail,
                notes,
                estimatedPrice
        );
    }
}