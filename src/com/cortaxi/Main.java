package com.cortaxi;

import com.cortaxi.patterns.creational.factorymethod.*;
import com.cortaxi.domain.*;
import com.cortaxi.patterns.creational.builder.*;
import com.cortaxi.patterns.creational.prototype.*;
import com.cortaxi.patterns.creational.singleton.DatabaseConnectionManager;

public class Main {
    public static void main(String[] args) {

        TaxiFactory standardFactory = new StandardTaxiFactory();
        TaxiFactory businessFactory = new BusinessTaxiFactory();
        TaxiFactory deliveryFactory = new DeliveryTaxiFactory();

        standardFactory.showEstimate(10);
        businessFactory.showEstimate(10);
        deliveryFactory.showEstimate(10);

        Client client = new Client("C1", "Cornelia");
        Driver driver = new Driver("D1", "Alex");

        Vehicle car = new Vehicle("Sedan", "AB123CD", driver, "Red");
        driver.assignVehicle(car);

        System.out.println("\n--- SCENARIO 1: Driver ACCEPTED ---");
        Ride ride1 = client.requestRide(driver);
        driver.onRideAssigned(ride1);
        driver.acceptRide(ride1);
        ride1.setStatus(RideStatus.ARRIVING);
        System.out.println("Ride status: " + ride1.getStatus());
        driver.printVehicleDetails();

        System.out.println("\n--- Builder demo ---");
        RideRequestDirector director = new RideRequestDirector();
        StandardRideRequest standardRide = director.buildStandard(new StandardRideRequestBuilder(),
                "Cornelia", "Str. Aviatorilor 10", "Piata Unirii");
        StandardRideRequest petRide = director.buildPremiumPetFriendly(new StandardRideRequestBuilder(),
                "Alex", "Gara de Nord", "Aeroport OTP");
        System.out.println(standardRide);
        System.out.println(petRide);

        System.out.println("\n--- Prototype demo ---");
        RoutePlan basePlan = new RoutePlan(
                "Cornelia",
                java.util.List.of(
                        new Waypoint("Pickup", 44.44, 26.10),
                        new Waypoint("Dropoff", 44.50, 26.07)
                ),
                10.0,
                2.5,
                1.0,
                "Base route"
        );
        RoutePlan surged = basePlan.copyWithAdjustment(1.8, "(rainy evening)");
        System.out.println("Base plan: " + basePlan);
        System.out.println("Surged plan: " + surged);

        System.out.println("\n--- Singleton demo ---");
        DatabaseConnectionManager db1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager db2 = DatabaseConnectionManager.getInstance();
        System.out.println("Same instance? " + (db1 == db2));
        db1.execute("SELECT * FROM rides WHERE status = 'ARRIVING'");
    }
}