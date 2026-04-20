package com.cortaxi.patterns.creational.factorymethod;

public class StandardTaxi implements ITaxiService {
    @Override
    public String getServiceType() {
        return "Standard";
    }

    @Override
    public double calculatePrice(double distanceKm) {
        return 5 + distanceKm * 2.0;
    }
}
