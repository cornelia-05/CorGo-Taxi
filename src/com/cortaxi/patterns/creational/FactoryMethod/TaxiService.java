package com.cortaxi.patterns.creational.FactoryMethod;

public interface TaxiService {

        String getServiceType();
        double calculatePrice(double distanceKm);
    }

