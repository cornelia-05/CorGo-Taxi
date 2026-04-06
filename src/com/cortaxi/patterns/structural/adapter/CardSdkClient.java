package com.cortaxi.patterns.structural.adapter;

public class CardSdkClient {
    public String createPaymentIntent(double amount) {
        System.out.println("[CardSdk] intent created for " + amount);
        return "pi_" + System.nanoTime();
    }
    public boolean capturePayment(String intentId) {
        System.out.println("[CardSdk] captured " + intentId);
        return true;
    }
    public boolean
    createRefund(String intentId, double amount) {
        System.out.println("[CardSdk] refunded " + amount + " on " + intentId);
        return true;
    }
}