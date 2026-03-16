package com.cortaxi.patterns.structural.facade.services;

public class DriverMatcher {
    public String findDriver(String pickup) {
        System.out.println("[DriverMatcher] searching near " + pickup);
        return "DRV-" + Math.abs(pickup.hashCode() % 1000);
    }
}
