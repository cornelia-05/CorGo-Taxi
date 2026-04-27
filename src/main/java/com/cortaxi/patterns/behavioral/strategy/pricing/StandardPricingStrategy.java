package com.cortaxi.patterns.behavioral.strategy.pricing;

/**
 * Concrete Strategy – standard taxi pricing.
 * Formula: 5.0 base + 2.0 per km + 0.3 per wait minute.
 */
public class StandardPricingStrategy implements IPricingStrategy {
    @Override
    public double calculate(double distanceKm, double waitMinutes) {
        return 5.0 + distanceKm * 2.0 + waitMinutes * 0.3;
    }
}
