package com.cortaxi;

import com.cortaxi.domain.*;
import com.cortaxi.patterns.creational.factorymethod.*;
import com.cortaxi.patterns.creational.builder.*;
import com.cortaxi.patterns.creational.prototype.*;
import com.cortaxi.patterns.creational.singleton.*;
import com.cortaxi.patterns.structural.adapter.*;

public class Main {
    static void main(String[] ignored) {
        IPaymentGateway cash = new CashGateway();
        IPaymentGateway card = new CardPaymentAdapter(new CardSdkClient()); // adaptor

        System.out.println("=== CASH ===");
        cash.authorize(15);
        cash.capture(null);
        cash.refund(null, 5);

        System.out.println("\n=== CARD ===");
        card.authorize(42.5);
        card.capture(null);
        card.refund(null, 10);
    }


}