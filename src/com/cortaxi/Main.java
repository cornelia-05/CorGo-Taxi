package com.cortaxi;

import com.cortaxi.domain.*;

public class Main {
    static void main(String[] args) {

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

        System.out.println("\n--- SCENARIO 2: Waiting (no driver) ---");
        Ride ride2 = new Ride(client, null);
        ride2.setStatus(RideStatus.WAITING);
        System.out.println("Ride status: " + ride2.getStatus());
        System.out.println("Driver assigned: " + (ride2.getDriver() == null ? "None" : ride2.getDriver().getName()));

        System.out.println("\n--- SCENARIO 3: Driver ON_LOCATION ---");
        Ride ride3 = client.requestRide(driver);
        driver.onRideAssigned(ride3);
        driver.acceptRide(ride3);
        ride3.setStatus(RideStatus.ON_LOCATION);
        System.out.println("Ride status: " + ride3.getStatus());
        driver.printVehicleDetails();

        System.out.println("\n--- SCENARIO 4: Driver CANCELED ---");
        Ride ride4 = client.requestRide(driver);
        driver.onRideAssigned(ride4);
        ride4.setStatus(RideStatus.CANCELED);
        System.out.println("Ride status: " + ride4.getStatus());
    }
}