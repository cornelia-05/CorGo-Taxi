package com.cortaxi.patterns.structural.facade.services;

public class PaymentProcessor {
    public String chargeCard(String riderName, double amount) {
        System.out.println("[Payment] charging " + riderName + " amount " + amount);
        return "tx_" + System.nanoTime();
    }
}
