package com.cortaxi.patterns.creational.abstractfactory.products;

public interface IPaymentMethod {
    String getType();
    double getTransactionFee();
    boolean requiresVerification();
}
