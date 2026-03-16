package com.cortaxi.patterns.structural.adapter;

public class CardPaymentAdapter implements IPaymentGateway {
    private final CardSdkClient card;

    public CardPaymentAdapter(CardSdkClient card) {
        this.card = card;
    }

    private String lastIntentId; // mic buffer pentru demo

    @Override
    public boolean authorize(double amount) {
        lastIntentId = card.createPaymentIntent(amount);
        return lastIntentId != null;
    }

    @Override
    public boolean capture(String transactionId) {
        String id = transactionId != null ? transactionId : lastIntentId;
        return card.capturePayment(id);
    }

    @Override
    public boolean refund(String transactionId, double amount) {
        String id = transactionId != null ? transactionId : lastIntentId;
        return card.createRefund(id, amount);
    }
}