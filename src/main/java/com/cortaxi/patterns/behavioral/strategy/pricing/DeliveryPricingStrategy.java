package com.cortaxi.patterns.behavioral.strategy.pricing;

/**
 * Concrete Strategy – delivery taxi pricing.
 * Formula: 8.0 base + 1.5 per km + 0.3 per wait minute.
 */
public class DeliveryPricingStrategy implements IPricingStrategy {
    @Override
    public double calculate(double distanceKm, double waitMinutes) {
        return 8.0 + distanceKm * 1.5 + waitMinutes * 0.3;
    }
}
