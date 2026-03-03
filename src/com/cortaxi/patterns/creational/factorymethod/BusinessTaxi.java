package com.cortaxi.patterns.creational.factorymethod;

public class BusinessTaxi implements ITaxiService {
    @Override
    public String getServiceType() {
        return "Business";
    }

    @Override
    public double calculatePrice(double distanceKm) {
        return 10 + distanceKm * 3.5;
    }
}