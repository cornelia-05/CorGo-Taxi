package com.cortaxi.patterns.creational.abstractfactory.factories;

import com.cortaxi.patterns.creational.abstractfactory.products.*;

public class StandardFleetFactory implements IFleetFactory {
    @Override
    public IVehicle createVehicle() {
        return new StandardCar();
    }

    @Override
    public IDriver createDriver() {
        return new RegularDriver();
    }

    @Override
    public IPaymentMethod createPaymentMethod() {
        return new CashPayment();
    }
}