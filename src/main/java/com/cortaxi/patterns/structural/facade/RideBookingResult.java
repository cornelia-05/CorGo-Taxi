package com.cortaxi.patterns.structural.facade;

public record RideBookingResult(boolean success, String message, double price, String driverId, String paymentTxId) {}
