package com.cortaxi.patterns.behavioral.strategy.pricing;

/**
 * Context – maintains a reference to one concrete {@link IPricingStrategy}
 * and communicates with it only via the strategy interface.
 *
 * <p>The context does not know which concrete strategy is active or how the
 * fare is calculated.  Clients can replace the strategy at runtime via
 * {@link #setStrategy(IPricingStrategy)}.
 */
public class PricingContext {

    private IPricingStrategy strategy;

    public PricingContext(IPricingStrategy strategy) {
        this.strategy = strategy;
    }

    /** Replace the active strategy at runtime. Strategy must not be null. */
    public void setStrategy(IPricingStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy must not be null");
        }
        this.strategy = strategy;
    }

    /**
     * Delegate to the active strategy.  The context has no knowledge of
     * which algorithm is being applied.
     */
    public double executeStrategy(double distanceKm, double waitMinutes) {
        return strategy.calculate(distanceKm, waitMinutes);
    }
}
