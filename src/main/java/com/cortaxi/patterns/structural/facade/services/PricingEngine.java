package com.cortaxi.patterns.structural.facade.services;

public class PricingEngine {
    public double estimate(double distanceKm, double waitMinutes) {
        double base = 5.0;
        double perKm = 1.5 * distanceKm;
        double wait = 0.3 * waitMinutes;
        double total = base + perKm + wait;
        System.out.println("[PricingEngine] price = " + total);
        return total;
    }
}
