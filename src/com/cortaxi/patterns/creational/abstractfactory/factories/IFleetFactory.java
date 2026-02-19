package com.cortaxi.patterns.creational.abstractfactory.factories;

import com.cortaxi.patterns.creational.abstractfactory.products.*;

public interface IFleetFactory {
    IVehicle createVehicle();
    IDriver createDriver();
    IPaymentMethod createPaymentMethod();
}