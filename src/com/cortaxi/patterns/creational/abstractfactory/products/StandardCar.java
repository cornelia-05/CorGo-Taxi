package com.cortaxi.patterns.creational.abstractfactory.products;

public class StandardCar implements IVehicle {
    @Override
    public String getType() {
        return "Standard Sedan";
    }

    @Override
    public String getFeatures() {
        return "AC, Radio, 4 seats";
    }

    @Override
    public double getBasePrice() {
        return 5.0;
    }
}