package com.cortaxi.patterns.structural.flyweight;

import java.util.Objects;

/**
 * Context = starea extrinsecă (unique) + referință la Flyweight.
 * Împreună reprezintă "obiectul original complet".
 */
public final class TaxiContext {
    private final String plateNumber; // extrinsic
    private final String driverId;     // extrinsic
    private double x;                 // extrinsic (se schimbă)
    private double y;                 // extrinsic (se schimbă)
    private String status;            // extrinsic (AVAILABLE/ON_TRIP/OFFLINE etc.)

    private final CarTypeFlyweight carType; // intrinsic (shared)

    public TaxiContext(String plateNumber, String driverId, double x, double y, String status, CarTypeFlyweight carType) {
        this.plateNumber = Objects.requireNonNull(plateNumber, "plateNumber");
        this.driverId = Objects.requireNonNull(driverId, "driverId");
        this.status = Objects.requireNonNull(status, "status");
        this.carType = Objects.requireNonNull(carType, "carType");
        this.x = x;
        this.y = y;
    }

    public String plateNumber() { return plateNumber; }
    public String driverId() { return driverId; }
    public double x() { return x; }
    public double y() { return y; }
    public String status() { return status; }
    public CarTypeFlyweight carType() { return carType; }

    public void moveTo(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setStatus(String status) {
        this.status = Objects.requireNonNull(status, "status");
    }

    public String render() {
        // Context pasează extrinsic state către behavior-ul din flyweight
        return carType.render(plateNumber, driverId, x, y, status);
    }
}
