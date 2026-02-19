package com.cortaxi.patterns.creational.factorymethod;

public class BusinessTaxiFactory extends TaxiFactory {
    @Override
    public TaxiService createService() {
        return new BusinessTaxi();
    }
}
