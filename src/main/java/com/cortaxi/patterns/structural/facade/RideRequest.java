package com.cortaxi.patterns.structural.facade;

public record RideRequest(String riderName, String pickup, String dropoff, double distanceKm, double waitMinutes) {}
