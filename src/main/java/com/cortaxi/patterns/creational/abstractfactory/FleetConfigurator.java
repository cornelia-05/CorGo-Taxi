package com.cortaxi.patterns.creational.abstractfactory;

import com.cortaxi.patterns.creational.abstractfactory.factories.*;
import com.cortaxi.patterns.creational.abstractfactory.products.*;
import com.cortaxi.patterns.creational.abstractfactory.factories.IFleetFactory;
import com.cortaxi.patterns.creational.abstractfactory.products.IDriver;
import com.cortaxi.patterns.creational.abstractfactory.products.IPaymentMethod;
import com.cortaxi.patterns.creational.abstractfactory.products.IVehicle;

public class FleetConfigurator {
    private IVehicle vehicle;
    private IDriver driver;
    private IPaymentMethod paymentMethod;

    public FleetConfigurator(IFleetFactory factory) {
        this.vehicle = factory.createVehicle();
        this.driver = factory.createDriver();
        this.paymentMethod = factory.createPaymentMethod();
    }

    public void displayFleetInfo() {
        System.out.println("=== Fleet Configuration ===");
        System.out.println("Vehicle: " + vehicle.getType());
        System.out.println("Features: " + vehicle.getFeatures());
        System.out.println("Base Price: $" + vehicle.getBasePrice());
        System.out.println();
        System.out.println("Driver: " + driver.getLevel());
        System.out.println("Uniform: " + driver.getUniform());
        System.out.println("Hourly Rate: $" + driver.getHourlyRate());
        System.out.println();
        System.out.println("Payment: " + paymentMethod.getType());
        System.out.println("Fee: $" + paymentMethod.getTransactionFee());
        System.out.println("Verification: " + (paymentMethod.requiresVerification() ? "Required" : "Not required"));
        System.out.println("===========================\n");
    }
}