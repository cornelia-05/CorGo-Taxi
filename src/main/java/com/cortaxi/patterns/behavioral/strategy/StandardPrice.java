package com.cortaxi.patterns.behavioral.strategy;

public class StandardPrice implements PricingStrategy {
    @Override
    public double calculate(double distanceKm, double waitMinutes) {
        return 5.0 + distanceKm * 2.0 + waitMinutes * 0.3;
    }
}
