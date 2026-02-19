package com.cortaxi.patterns.creational.abstractfactory.factories;

import com.cortaxi.patterns.creational.abstractfactory.products.*;

public class DeliveryFleetFactory implements IFleetFactory {
    @Override
    public IVehicle createVehicle() {
        return new DeliveryVan();
    }

    @Override
    public IDriver createDriver() {
        return new CourierDriver();
    }

    @Override
    public IPaymentMethod createPaymentMethod() {
        return new CashPayment();
    }
}