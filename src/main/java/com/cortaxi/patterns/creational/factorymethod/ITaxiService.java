package com.cortaxi.patterns.creational.factorymethod;

public interface ITaxiService {

        String getServiceType();
        double calculatePrice(double distanceKm);
    }

