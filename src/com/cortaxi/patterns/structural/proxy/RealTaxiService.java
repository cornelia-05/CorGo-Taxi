package com.cortaxi.patterns.structural.proxy;

public final class RealTaxiService implements ITaxiService {
    @Override
    public void requestRide(String location) {
        System.out.println("Ride requested at " + location);
    }

    @Override
    public String getDriverInfo(String id) {
        return "Driver info for " + id;
    }
}