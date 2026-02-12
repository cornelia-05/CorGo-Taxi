package com.cortaxi.patterns.creational.FactoryMethod;

public abstract class TaxiFactory {
    public abstract TaxiService createService();

    public void showEstimate(double distanceKm) {
        TaxiService service = createService();
        double price = service.calculatePrice(distanceKm);
        System.out.println(service.getServiceType() + " price: " + price);
    }
}

