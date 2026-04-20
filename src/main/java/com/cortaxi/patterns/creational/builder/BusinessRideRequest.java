package com.cortaxi.patterns.creational.builder;

import java.util.Objects;

public class BusinessRideRequest {
    private final String riderName;
    private final String pickup;
    private final String dropout;
    private final String vehicleType;
    private final boolean prioritySupport;
    private final String invoiceEmail;
    private final String notes;
    private final double estimatedPrice;

    BusinessRideRequest(String riderName,
                        String pickup,
                        String dropout,
                        String vehicleType,
                        boolean prioritySupport,
                        String invoiceEmail,
                        String notes,
                        double estimatedPrice) {
        this.riderName = riderName;
        this.pickup = pickup;
        this.dropout = dropout;
        this.vehicleType = vehicleType;
        this.prioritySupport = prioritySupport;
        this.invoiceEmail = invoiceEmail;
        this.notes = notes;
        this.estimatedPrice = estimatedPrice;
    }

    public String getRiderName() { return riderName; }
    public String getPickup() { return pickup; }
    public String getDropout() { return dropout; }
    public String getVehicleType() { return vehicleType; }
    public boolean isPrioritySupport() { return prioritySupport; }
    public String getInvoiceEmail() { return invoiceEmail; }
    public String getNotes() { return notes; }
    public double getEstimatedPrice() { return estimatedPrice; }

    @Override
    public String toString() {
        return "BusinessRideRequest{" +
                "riderName='" + riderName + '\'' +
                ", pickup='" + pickup + '\'' +
                ", dropoff='" + dropout+ '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", prioritySupport=" + prioritySupport +
                ", invoiceEmail='" + invoiceEmail + '\'' +
                ", notes='" + notes + '\'' +
                ", estimatedPrice=" + estimatedPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessRideRequest that)) return false;
        return  prioritySupport == that.prioritySupport &&
                Double.compare(that.estimatedPrice, estimatedPrice) == 0 &&
                Objects.equals(riderName, that.riderName) &&
                Objects.equals(pickup, that.pickup) &&
                Objects.equals(dropout, that.dropout) &&
                Objects.equals(vehicleType, that.vehicleType) &&
                Objects.equals(invoiceEmail, that.invoiceEmail) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riderName, pickup, dropout, vehicleType, prioritySupport, invoiceEmail, notes, estimatedPrice);
    }
}