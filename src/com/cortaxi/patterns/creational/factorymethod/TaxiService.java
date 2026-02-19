package com.cortaxi.patterns.creational.factorymethod;

public interface TaxiService {

        String getServiceType();
        double calculatePrice(double distanceKm);
    }

