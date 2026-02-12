package com.cortaxi.patterns.creational.FactoryMethod;

public class StandardTaxi implements TaxiService {
    @Override
    public String getServiceType() {
        return "Standard";
    }

    @Override
    public double calculatePrice(double distanceKm) {
        return 5 + distanceKm * 2.0;
    }
}
