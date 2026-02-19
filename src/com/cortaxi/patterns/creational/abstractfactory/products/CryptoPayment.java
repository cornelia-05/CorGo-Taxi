package com.cortaxi.patterns.creational.abstractfactory.products;

public class CryptoPayment implements IPaymentMethod {
    @Override
    public String getType() {
        return "Cryptocurrency";
    }

    @Override
    public double getTransactionFee() {
        return 1.5;
    }

    @Override
    public boolean requiresVerification() {
        return true;
    }
}