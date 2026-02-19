package com.cortaxi.patterns.creational.abstractfactory.products;

public class RegularDriver implements IDriver {
    @Override
    public String getLevel() {
        return "Regular";
    }

    @Override
    public String getUniform() {
        return "Casual attire";
    }

    @Override
    public double getHourlyRate() {
        return 15.0;
    }
}