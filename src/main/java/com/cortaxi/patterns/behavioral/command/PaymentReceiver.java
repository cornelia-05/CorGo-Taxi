package com.cortaxi.patterns.behavioral.command;

// Receiver - contains business logic for payment operations
public class PaymentReceiver {
    private String paymentId;
    private double amount;
    private String status;
    private String paymentMethod;

    public PaymentReceiver(String paymentId) {
        this.paymentId = paymentId;
        this.status = "PENDING";
    }

    // Business logic methods
    public void processPayment(double amount, String method) {
        this.amount = amount;
        this.paymentMethod = method;
        this.status = "PROCESSING";
        System.out.println("[RECEIVER] Processing payment of $" + amount
                + " via " + method);
    }

    public void authorizePayment() {
        this.status = "AUTHORIZED";
        System.out.println("[RECEIVER] Payment " + paymentId + " authorized");
    }

    public void capturePayment() {
        this.status = "CAPTURED";
        System.out.println("[RECEIVER] Payment " + paymentId + " captured. Amount: $" + amount);
    }

    public void refundPayment() {
        this.status = "REFUNDED";
        System.out.println("[RECEIVER] Payment " + paymentId + " refunded. Amount: $" + amount);
    }

    // Getters
    public String getPaymentId() {
        return paymentId;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }
}