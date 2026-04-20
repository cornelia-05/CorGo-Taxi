package com.cortaxi.patterns.creational.abstractfactory.products;

public class CardPayment implements IPaymentMethod {
    @Override
    public String getType() {
        return "Credit/Debit Card";
    }

    @Override
    public double getTransactionFee() {
        return 0.5;
    }

    @Override
    public boolean requiresVerification() {
        return true;
    }
}