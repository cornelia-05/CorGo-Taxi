package com.cortaxi.patterns.structural.facade;

import com.cortaxi.patterns.behavioral.strategy.PricingStrategy;
import com.cortaxi.patterns.structural.decorator.*;
import org.springframework.stereotype.Service;

@Service
public class PricingEngine {

    private PricingStrategy strategy;

    public PricingEngine(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double distanceKm,
                            double waitMinutes,
                            boolean night,
                            boolean surge,
                            boolean discount) {

        // 1. BASE PRICE (Strategy)
        double base = strategy.calculate(distanceKm, waitMinutes);

        // 2. WRAP IN DECORATOR SYSTEM
        IFareComponent fare = new BaseFareWrapper(base);

        if (night) {
            fare = new NightTariffDecorator(fare, 1.2);
        }

        if (surge) {
            fare = new SurgeDecorator(fare, 1.5);
        }

        if (discount) {
            fare = new DiscountDecorator(fare, 5);
        }

        return fare.execute();
    }
}