package com.cortaxi.patterns.creational.factorymethod;

public class BusinessTaxiFactory extends TaxiFactory {
    @Override
    public ITaxiService createService() {
        return new BusinessTaxi();
    }
}
