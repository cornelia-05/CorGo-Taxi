package com.cortaxi.patterns.creational.factorymethod;

public class DeliveryTaxi implements ITaxiService {
    @Override
    public String getServiceType() {
        return "Delivery";
    }

    @Override
    public double calculatePrice(double distanceKm) {
        return 8 + distanceKm * 1.5;
    }
}
