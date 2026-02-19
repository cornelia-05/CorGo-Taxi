package com.cortaxi.patterns.creational.factorymethod;

public class DeliveryTaxiFactory extends TaxiFactory {
    @Override
    public TaxiService createService() {
        return new DeliveryTaxi();
    }
}
