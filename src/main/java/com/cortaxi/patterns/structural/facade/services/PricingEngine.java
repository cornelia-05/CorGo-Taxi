package com.cortaxi.patterns.structural.facade.services;

import com.cortaxi.patterns.behavioral.strategy.pricing.IPricingStrategy;
import com.cortaxi.patterns.behavioral.strategy.pricing.PricingContext;
import com.cortaxi.patterns.behavioral.strategy.pricing.StandardPricingStrategy;

/**
 * Facade service that delegates fare calculation to a {@link PricingContext}.
 * The active pricing strategy can be swapped at runtime via
 * {@link #setStrategy(IPricingStrategy)} without changing the facade interface.
 */
public class PricingEngine {

    private final PricingContext pricingContext;

    public PricingEngine() {
        this.pricingContext = new PricingContext(new StandardPricingStrategy());
    }

    /** Replace the active pricing strategy at runtime. */
    public void setStrategy(IPricingStrategy strategy) {
        pricingContext.setStrategy(strategy);
    }

    public double estimate(double distanceKm, double waitMinutes) {
        double total = pricingContext.executeStrategy(distanceKm, waitMinutes);
        System.out.println("[PricingEngine] price = " + total);
        return total;
    }
}
