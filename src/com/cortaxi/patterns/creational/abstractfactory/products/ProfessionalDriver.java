package com.cortaxi.patterns.creational.abstractfactory.products;

public class ProfessionalDriver implements IDriver {
    @Override
    public String getLevel() {
        return "Professional";
    }

    @Override
    public String getUniform() {
        return "Suit and tie";
    }

    @Override
    public double getHourlyRate() {
        return 25.0;
    }
}