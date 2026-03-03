package com.cortaxi.patterns.creational.factorymethod;

public abstract class TaxiFactory {

    public abstract ITaxiService createService();

    public void showEstimate(double distanceKm) {
        ITaxiService service = createService();
        double price = service.calculatePrice(distanceKm);
        System.out.println(service.getServiceType() + " price: " + price);
    }
}

