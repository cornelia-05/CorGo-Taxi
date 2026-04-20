package com.cortaxi.patterns.creational.abstractfactory.products;

public class CashPayment implements IPaymentMethod {
    @Override
    public String getType() {
        return "Cash";
    }

    @Override
    public double getTransactionFee() {
        return 0.0;
    }

    @Override
    public boolean requiresVerification() {
        return false;
    }
}