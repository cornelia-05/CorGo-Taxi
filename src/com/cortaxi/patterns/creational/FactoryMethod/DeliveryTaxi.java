package com.cortaxi.patterns.creational.FactoryMethod;

public class DeliveryTaxi implements TaxiService {
    @Override
    public String getServiceType() {
        return "Delivery";
    }

    @Override
    public double calculatePrice(double distanceKm) {
        return 8 + distanceKm * 1.5;
    }
}
