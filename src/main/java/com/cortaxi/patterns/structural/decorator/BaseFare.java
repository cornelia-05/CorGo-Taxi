package com.cortaxi.patterns.structural.decorator;

/**
 * ConcreteComponent - implementarea de bază.
 */
public final class BaseFare implements IFareComponent {
    private final double distanceKm;
    private final double pricePerKm;
    private final double pickupFee;

    public BaseFare(double distanceKm, double pricePerKm, double pickupFee) {
        this.distanceKm = distanceKm;
        this.pricePerKm = pricePerKm;
        this.pickupFee = pickupFee;
    }

    @Override
    public double execute() {
        return pickupFee + distanceKm * pricePerKm;
    }
}
