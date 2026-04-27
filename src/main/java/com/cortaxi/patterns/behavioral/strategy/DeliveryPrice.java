package com.cortaxi.patterns.behavioral.strategy;

public class DeliveryPrice implements PricingStrategy {
    @Override
    public double calculate(double distanceKm, double waitMinutes) {
        return 8.0 + distanceKm * 1.5 + waitMinutes * 0.3;
    }
}
