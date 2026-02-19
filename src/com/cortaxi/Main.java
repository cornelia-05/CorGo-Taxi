package com.cortaxi;
import com.cortaxi.patterns.creational.abstractfactory.products.*;
import com.cortaxi.patterns.creational.abstractfactory.factories.*;
import com.cortaxi.patterns.creational.abstractfactory.*;
import com.cortaxi.patterns.creational.factorymethod.*;
import com.cortaxi.domain.*;

public class Main {
    static void main(String[] args) {

        //TaxiFactory standardFactory = new StandardTaxiFactory();
        //TaxiFactory businessFactory = new BusinessTaxiFactory();
        //TaxiFactory deliveryFactory = new DeliveryTaxiFactory();

        //standardFactory.showEstimate(10);
        //businessFactory.showEstimate(10);
        //deliveryFactory.showEstimate(10);

        System.out.println("\n--- ABSTRACT FACTORY DEMO ---\n");

        // Standard Fleet
        IFleetFactory standardFactory = new StandardFleetFactory();
        FleetConfigurator standardFleet = new FleetConfigurator(standardFactory);
        standardFleet.displayFleetInfo();

        // Business Fleet
        IFleetFactory businessFactory = new BusinessFleetFactory();
        FleetConfigurator businessFleet = new FleetConfigurator(businessFactory);
        businessFleet.displayFleetInfo();

        // Delivery Fleet
        IFleetFactory deliveryFactory = new DeliveryFleetFactory();
        FleetConfigurator deliveryFleet = new FleetConfigurator(deliveryFactory);
        deliveryFleet.displayFleetInfo();
    }
}