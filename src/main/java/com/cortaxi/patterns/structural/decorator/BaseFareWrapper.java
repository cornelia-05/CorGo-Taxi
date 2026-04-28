package com.cortaxi.patterns.structural.decorator;

/**
 * Adaptor simplu ca să conectăm Strategy output cu Decorator system
 */
public class BaseFareWrapper implements IFareComponent {

    private final double base;

    public BaseFareWrapper(double base) {
        this.base = base;
    }

    @Override
    public double execute() {
        return base;
    }
}