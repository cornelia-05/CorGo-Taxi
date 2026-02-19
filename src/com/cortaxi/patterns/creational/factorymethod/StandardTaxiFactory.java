package com.cortaxi.patterns.creational.factorymethod;

public class StandardTaxiFactory extends TaxiFactory {
    @Override
    public TaxiService createService() {
        return new StandardTaxi();
    }
}
