package com.cortaxi.patterns.behavioral.strategy;

public class BusinessPrice implements PricingStrategy {
    @Override
    public double calculate(double distanceKm, double waitMinutes) {
        return 10.0 + distanceKm * 3.5 + waitMinutes * 0.3;
    }
}
