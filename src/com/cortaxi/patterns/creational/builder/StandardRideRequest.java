package com.cortaxi.patterns.creational.builder;

import java.util.Objects;

public class StandardRideRequest {
    private final String riderName;
    private final String pickup;
    private final String dropout;
    private final String vehicleType;
    private final boolean childSeat;
    private final boolean petFriendly;
    private final String notes;
    private final double estimatedPrice;

    StandardRideRequest(String riderName,
                        String pickup,
                        String dropout,
                        String vehicleType,
                        boolean childSeat,
                        boolean petFriendly,
                        String notes,
                        double estimatedPrice) {
        this.riderName = riderName;
        this.pickup = pickup;
        this.dropout = dropout;
        this.vehicleType = vehicleType;
        this.childSeat = childSeat;
        this.petFriendly = petFriendly;
        this.notes = notes;
        this.estimatedPrice = estimatedPrice;
    }

    public String getRiderName() { return riderName; }
    public String getPickup() { return pickup; }
    public String getDropout() { return dropout; }
    public String getVehicleType() { return vehicleType; }
    public boolean isChildSeat() { return childSeat; }
    public boolean isPetFriendly() { return petFriendly; }
    public String getNotes() { return notes; }
    public double getEstimatedPrice() { return estimatedPrice; }

    @Override
    public String toString() {
        return "RideRequest{" +
                "riderName='" + riderName + '\'' +
                ", pickup='" + pickup + '\'' +
                ", dropoff='" + dropout + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", childSeat=" + childSeat +
                ", petFriendly=" + petFriendly +
                ", notes='" + notes + '\'' +
                ", estimatedPrice=" + estimatedPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StandardRideRequest that)) return false;
        return childSeat == that.childSeat &&
                petFriendly == that.petFriendly &&
                Double.compare(that.estimatedPrice, estimatedPrice) == 0 &&
                Objects.equals(riderName, that.riderName) &&
                Objects.equals(pickup, that.pickup) &&
                Objects.equals(dropout, that.dropout) &&
                Objects.equals(vehicleType, that.vehicleType) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riderName, pickup, dropout, vehicleType, childSeat, petFriendly, notes, estimatedPrice);
    }
}
