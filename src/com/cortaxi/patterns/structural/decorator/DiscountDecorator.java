package com.cortaxi.patterns.structural.decorator;

/**
 * Concrete Decorator #3 - aplică discount (scade o sumă fixă sau procent).
 */
public final class DiscountDecorator extends FareDecorator {
    private final double discountAmount; // sumă fixă

    public DiscountDecorator(IFareComponent wrappee, double discountAmount) {
        super(wrappee);
        this.discountAmount = discountAmount;
    }

    @Override
    public double execute() {
        double result = super.execute() - discountAmount;
        return Math.max(0.0, result);
    }
}