package com.cortaxi.patterns.creational.FactoryMethod;

public class BusinessTaxiFactory extends TaxiFactory {
    @Override
    public TaxiService createService() {
        return new BusinessTaxi();
    }
}
