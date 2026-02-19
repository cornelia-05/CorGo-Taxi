package com.cortaxi.patterns.creational.factorymethod;

public class BusinessTaxi implements TaxiService {
    @Override
    public String getServiceType() {
        return "Business";
    }

    @Override
    public double calculatePrice(double distanceKm) {
        return 10 + distanceKm * 3.5;
    }
}