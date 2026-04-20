package com.cortaxi.patterns.creational.factorymethod;

public class StandardTaxiFactory extends TaxiFactory {
    @Override
    public ITaxiService createService() {
        return new StandardTaxi();
    }
}
