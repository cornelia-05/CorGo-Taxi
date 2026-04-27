package com.cortaxi.patterns.structural.facade;

import com.cortaxi.patterns.structural.adapter.IPaymentGateway;

public class PaymentProcessor {

    private final IPaymentGateway gateway;

    public PaymentProcessor(IPaymentGateway gateway) {
        this.gateway = gateway;
    }

    public String charge(String riderName, double amount) {
        System.out.println("[PaymentProcessor] charging " + riderName);

        boolean ok = gateway.authorize(amount);
        if (!ok) return null;

        String txId = "tx_" + System.nanoTime();
        gateway.capture(txId);

        return txId;
    }
}