package com.cortaxi.patterns.creational.abstractfactory.products;

public class DeliveryVan implements IVehicle {
    @Override
    public String getType() {
        return "Delivery Van";
    }

    @Override
    public String getFeatures() {
        return "Large cargo space, GPS tracking";
    }

    @Override
    public double getBasePrice() {
        return 8.0;
    }
}