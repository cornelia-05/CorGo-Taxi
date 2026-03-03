package com.cortaxi.patterns.creational.factorymethod;

public class DeliveryTaxiFactory extends TaxiFactory {
    @Override
    public ITaxiService createService() {
        return new DeliveryTaxi();
    }
}
