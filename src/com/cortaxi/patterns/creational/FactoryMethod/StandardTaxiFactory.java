package com.cortaxi.patterns.creational.FactoryMethod;

public class StandardTaxiFactory extends TaxiFactory {
    @Override
    public TaxiService createService() {
        return new StandardTaxi();
    }
}
