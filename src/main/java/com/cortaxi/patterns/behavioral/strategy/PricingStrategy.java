package com.cortaxi.patterns.behavioral.strategy;

/**
 * Strategy interface – declares the algorithm that all concrete pricing
 * strategies must implement.  The context communicates with concrete
 * strategies only through this interface.
 */
public interface PricingStrategy {
    double calculate(double distanceKm, double waitMinutes);
}
