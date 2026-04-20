package com.cortaxi.patterns.creational.abstractfactory.factories;

import com.cortaxi.patterns.creational.abstractfactory.products.*;
import com.cortaxi.patterns.creational.abstractfactory.products.IDriver;
import com.cortaxi.patterns.creational.abstractfactory.products.IPaymentMethod;
import com.cortaxi.patterns.creational.abstractfactory.products.IVehicle;

public interface IFleetFactory {
    IVehicle createVehicle();
    IDriver createDriver();
    IPaymentMethod createPaymentMethod();
}