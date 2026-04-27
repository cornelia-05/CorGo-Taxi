package com.cortaxi.patterns.behavioral.strategy.pricing;

/**
 * Strategy interface – declares the algorithm that all concrete pricing
 * strategies must implement.  The context communicates with concrete
 * strategies only through this interface.
 */
public interface IPricingStrategy {
    /**
     * Calculate the total fare for a ride.
     *
     * @param distanceKm  trip distance in kilometres
     * @param waitMinutes accumulated wait time in minutes
     * @return            total fare
     */
    double calculate(double distanceKm, double waitMinutes);
}
