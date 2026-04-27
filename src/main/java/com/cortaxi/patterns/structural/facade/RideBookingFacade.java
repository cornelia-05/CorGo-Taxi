package com.cortaxi.patterns.structural.facade;

import com.cortaxi.patterns.behavioral.observer.RideEvent;
import com.cortaxi.patterns.behavioral.observer.RidePublisher;
import com.cortaxi.patterns.structural.adapter.IPaymentGateway;
import com.cortaxi.patterns.structural.facade.help.PaymentGatewayFactory;
import com.cortaxi.patterns.structural.facade.help.PaymentMethod;
import org.springframework.stereotype.Service;

@Service
public class RideBookingFacade {

    private final DriverMatcher driverMatcher;
    private final PricingEngine pricingEngine;
    private final RidePublisher ridePublisher;

    public RideBookingFacade(
            DriverMatcher driverMatcher,
            PricingEngine pricingEngine,
            RidePublisher ridePublisher
    ) {
        this.driverMatcher = driverMatcher;
        this.pricingEngine = pricingEngine;
        this.ridePublisher = ridePublisher;
    }

    public RideBookingResult bookRide(RideRequest request, PaymentMethod method) {

        // 1. driver
        String driverId = driverMatcher.findDriver(request.pickup());

        if (driverId == null) {
            return new RideBookingResult(false, "No drivers available", 0, null, null);
        }

        // 2. price
        double price = pricingEngine.estimate(
                request.distanceKm(),
                request.waitMinutes()
        );

        // 3. payment (Adapter + Factory)
        IPaymentGateway gateway = PaymentGatewayFactory.create(method);
        PaymentProcessor paymentProcessor = new PaymentProcessor(gateway);
        String txId = paymentProcessor.charge(request.riderName(), price);

        // 4. OBSERVER EVENT (în loc de NotificationService)
        ridePublisher.notifySubscribers(
                new RideEvent(
                        "RIDE-" + System.nanoTime(),
                        "BOOKED",
                        driverId,
                        price
                )
        );

        return new RideBookingResult(true, "Ride booked", price, driverId, txId);
    }
}