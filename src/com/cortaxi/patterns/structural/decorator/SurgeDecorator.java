package com.cortaxi.patterns.structural.decorator;

/**
 * Concrete Decorator #2 - surge pricing (înmulțește încă o dată).
 */
public final class SurgeDecorator extends FareDecorator {
    private final double surgeMultiplier; // ex: 1.5

    public SurgeDecorator(IFareComponent wrappee, double surgeMultiplier) {
        super(wrappee);
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public double execute() {
        return super.execute() * surgeMultiplier;
    }
}