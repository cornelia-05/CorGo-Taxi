package com.cortaxi.patterns.structural.facade.services;

public record RideRequest(String riderName, String pickup, String dropoff, double distanceKm, double waitMinutes) {}
