package com.cortaxi.patterns.creational.FactoryMethod;

public class DeliveryTaxiFactory extends TaxiFactory {
    @Override
    public TaxiService createService() {
        return new DeliveryTaxi();
    }
}
