package com.cortaxi.patterns.creational.abstractfactory.products;

public class LuxuryCar implements IVehicle {
    @Override
    public String getType() {
        return "Luxury Sedan";
    }

    @Override
    public String getFeatures() {
        return "Leather seats, Premium sound, WiFi, Water bottles";
    }

    @Override
    public double getBasePrice() {
        return 10.0;
    }
}