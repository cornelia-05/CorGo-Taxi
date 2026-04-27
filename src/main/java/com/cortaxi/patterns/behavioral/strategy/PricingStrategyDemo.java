package com.cortaxi.patterns.behavioral.strategy;

/**
 * Client – creates concrete strategy objects and passes them to the context.
 *
 * <p>Demonstrates runtime strategy switching: the same {@link PriceContext}
 * object is reused while the underlying algorithm is swapped via
 * {@link PriceContext#setStrategy(PricingStrategy)}.
 */
public class PricingStrategyDemo {

    public static void main(String[] args) {
        double distanceKm   = 10.0;
        double waitMinutes  = 5.0;

        // Client creates a context with an initial strategy.
        PriceContext context = new PriceContext(new StandardPrice());

        System.out.println("=== Strategy Pattern – Pricing Demo ===");

        // Standard pricing
        System.out.println("[Standard]  fare = " + context.executeStrategy(distanceKm, waitMinutes));

        // Switch to business strategy at runtime – no change to context internals.
        context.setStrategy(new BusinessPrice());
        System.out.println("[Business]  fare = " + context.executeStrategy(distanceKm, waitMinutes));

        // Switch to delivery strategy at runtime.
        context.setStrategy(new DeliveryPrice());
        System.out.println("[Delivery]  fare = " + context.executeStrategy(distanceKm, waitMinutes));
    }
}
