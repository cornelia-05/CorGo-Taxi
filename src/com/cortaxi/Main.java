package com.cortaxi;

import com.cortaxi.domain.*;
import com.cortaxi.patterns.creational.factorymethod.*;
import com.cortaxi.patterns.creational.builder.*;
import com.cortaxi.patterns.creational.prototype.*;
import com.cortaxi.patterns.creational.singleton.*;
import com.cortaxi.patterns.structural.adapter.*;
import com.cortaxi.patterns.structural.composite.*;

public class Main {
    static void main(String[] ignored) {
        IPaymentGateway cash = new CashGateway();
        IPaymentGateway card = new CardPaymentAdapter(new CardSdkClient()); // adaptor

        System.out.println("=== CASH ===");
        boolean cashAuth = cash.authorize(15);
        boolean cashCap  = cash.capture(null);
        boolean cashRef  = cash.refund(null, 5);
        System.out.printf("auth=%s, capture=%s, refund=%s%n", cashAuth, cashCap, cashRef);

        System.out.println("\n=== CARD ===");
        boolean cardAuth = card.authorize(42.5);
        boolean cardCap  = card.capture(null);
        boolean cardRef  = card.refund(null, 10);
        System.out.printf("auth=%s, capture=%s, refund=%s%n", cardAuth, cardCap, cardRef);




        IFareComponent baseRide   = new FareItem("Ride km + time", 25.0);
        IFareComponent airportFee = new FareItem("Airport surcharge", 5.5);
        IFareComponent toll       = new FareItem("Highway toll", 3.0);
        IFareComponent waiting    = new FareItem("Waiting 10 min", 4.0);

        FareBundle airportTransfer = new FareBundle("Airport Transfer");
        airportTransfer.add(baseRide);
        airportTransfer.add(airportFee);
        airportTransfer.add(toll);

        FareBundle vipPackage = new FareBundle("VIP Package");
        vipPackage.add(airportTransfer);
        vipPackage.add(waiting);

        System.out.println("VIP children before remove: " + vipPackage.getChildren().size());
        vipPackage.remove(waiting);
        System.out.println("VIP children after remove:  " + vipPackage.getChildren().size());
        vipPackage.add(waiting);

        vipPackage.print("");
        System.out.printf("%nTotal VIP: %.2f%n", vipPackage.getPrice());
    }


}