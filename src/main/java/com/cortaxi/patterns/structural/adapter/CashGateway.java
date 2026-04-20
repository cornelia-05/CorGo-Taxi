package com.cortaxi.patterns.structural.adapter;

public class CashGateway implements IPaymentGateway {
    @Override
    public boolean authorize(double amount) {
        System.out.println("[Cash] pay on delivery " + amount);
        return true;
    }
    @Override
    public boolean capture(String transactionId) {
        System.out.println("[Cash] collected by driver");
        return true;
    }
    @Override
    public boolean refund(String transactionId, double amount) {
        System.out.println("[Cash] refund handled offline");
        return false; // sau true, după politica ta
    }
}
