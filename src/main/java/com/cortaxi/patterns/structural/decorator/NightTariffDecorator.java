package com.cortaxi.patterns.structural.decorator;

/**
 * Concrete Decorator #1 - adaugă un multiplicator (ex: noapte).
 */
public final class NightTariffDecorator extends FareDecorator {
    private final double multiplier; // ex: 1.2

    public NightTariffDecorator(IFareComponent wrappee, double multiplier) {
        super(wrappee);
        this.multiplier = multiplier;
    }

    @Override
    public double execute() {
        double base = super.execute();
        return base * multiplier;
    }

    // extra() din diagramă (opțional)
    public double extraAmount() {
        return execute() - wrappee.execute();
    }
}
