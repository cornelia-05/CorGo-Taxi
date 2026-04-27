package com.cortaxi.patterns.structural.facade.help;

import com.cortaxi.patterns.structural.adapter.CardPaymentAdapter;
import com.cortaxi.patterns.structural.adapter.CardSdkClient;
import com.cortaxi.patterns.structural.adapter.CashGateway;
import com.cortaxi.patterns.structural.adapter.IPaymentGateway;

public class PaymentGatewayFactory {
    public static IPaymentGateway create(PaymentMethod method) {
        return switch (method) {
            case CARD -> new CardPaymentAdapter(new CardSdkClient());
            case CASH -> new CashGateway();
        };
    }
}
