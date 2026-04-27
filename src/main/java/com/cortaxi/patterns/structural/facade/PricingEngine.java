package com.cortaxi.patterns.structural.facade;

import com.cortaxi.patterns.behavioral.strategy.PricingStrategy;
import com.cortaxi.patterns.behavioral.strategy.PriceContext;
import com.cortaxi.patterns.behavioral.strategy.StandardPrice;
import org.springframework.stereotype.Service;

/**
 * Facade service that delegates fare calculation to a {@link PriceContext}.
 * The active pricing strategy can be swapped at runtime via
 * {@link #setStrategy(PricingStrategy)} without changing the facade interface.
 */
@Service
public class PricingEngine {

    private final PriceContext pricingContext;

    public PricingEngine() {
        this.pricingContext = new PriceContext(new StandardPrice());
    }

    /**
     * Replace the active pricing strategy at runtime.
     */
    public void setStrategy(PricingStrategy strategy) {
        pricingContext.setStrategy(strategy);
    }

    public double estimate(double distanceKm, double waitMinutes) {
        double total = pricingContext.executeStrategy(distanceKm, waitMinutes);
        System.out.println("[PricingEngine] price = " + total);
        return total;
    }
}