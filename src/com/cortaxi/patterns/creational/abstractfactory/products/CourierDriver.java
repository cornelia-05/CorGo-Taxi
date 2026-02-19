package com.cortaxi.patterns.creational.abstractfactory.products;

public class CourierDriver implements IDriver {
    @Override
    public String getLevel() {
        return "Courier";
    }

    @Override
    public String getUniform() {
        return "Company vest";
    }

    @Override
    public double getHourlyRate() {
        return 18.0;
    }
}