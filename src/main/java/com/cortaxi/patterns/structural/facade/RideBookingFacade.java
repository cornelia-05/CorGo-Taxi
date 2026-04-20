package com.cortaxi.patterns.structural.facade;

import com.cortaxi.patterns.structural.facade.services.*;
import com.cortaxi.patterns.structural.facade.services.*;

public class RideBookingFacade {

    private final DriverMatcher driverMatcher = new DriverMatcher();
    private final PricingEngine pricingEngine = new PricingEngine();
    private final PaymentProcessor paymentProcessor = new PaymentProcessor();
    private final NotificationService notifier = new NotificationService();

    public RideBookingResult bookRide(RideRequest request) {
        // 1) găsește șofer
        String driverId = driverMatcher.findDriver(request.pickup());
        if (driverId == null) {
            return new RideBookingResult(false, "No drivers available", 0, null, null);
        }

        // 2) calculează prețul
        double price = pricingEngine.estimate(request.distanceKm(), request.waitMinutes());

        // 3) încasează plata
        String txId = paymentProcessor.chargeCard(request.riderName(), price);

        // 4) notifică părțile
        notifier.notifyRider(request.riderName(), "Driver " + driverId + " is on the way. Price: " + price);
        notifier.notifyDriver(driverId, "New ride for " + request.riderName() + ": " + request.pickup() + " -> " + request.dropoff());

        return new RideBookingResult(true, "Ride booked", price, driverId, txId);
    }
}