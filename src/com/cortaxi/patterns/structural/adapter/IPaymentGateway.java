package com.cortaxi.patterns.structural.adapter;

public interface IPaymentGateway {
    boolean authorize(double amount);
    boolean capture(String transactionId);
    boolean refund(String transactionId, double amount);
}