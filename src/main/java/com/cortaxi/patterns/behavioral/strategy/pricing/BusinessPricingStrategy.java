package com.cortaxi.patterns.behavioral.strategy.pricing;

/**
 * Concrete Strategy – business (premium) taxi pricing.
 * Formula: 10.0 base + 3.5 per km + 0.3 per wait minute.
 */
public class BusinessPricingStrategy implements IPricingStrategy {
    @Override
    public double calculate(double distanceKm, double waitMinutes) {
        return 10.0 + distanceKm * 3.5 + waitMinutes * 0.3;
    }
}
