package com.cortaxi.patterns.creational.abstractfactory.factories;

import com.cortaxi.patterns.creational.abstractfactory.products.*;

public class BusinessFleetFactory implements IFleetFactory {
    @Override
    public IVehicle createVehicle() {
        return new LuxuryCar();
    }

    @Override
    public IDriver createDriver() {
        return new ProfessionalDriver();
    }

    @Override
    public IPaymentMethod createPaymentMethod() {
        return new CardPayment();
    }
}