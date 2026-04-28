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
            RidePublisher ridePublisher,
            PricingEngine pricingEngine
    ) {
        this.driverMatcher = driverMatcher;
        this.pricingEngine = pricingEngine;
        this.ridePublisher = ridePublisher;
    }

    public RideBookingResult bookRide(RideRequest request, PaymentMethod method) {

        // 1. DRIVER
        String driverId = driverMatcher.findDriver(request.pickup());

        if (driverId == null) {
            return new RideBookingResult(false, "No drivers available", 0, null, null);
        }

        // 2. PRICE (Strategy + Decorator via engine)
        double price = pricingEngine.calculate(
                request.distanceKm(),
                request.waitMinutes(),
                true,
                false,
                true
        );

        // 3. PAYMENT (Adapter + Factory)
        IPaymentGateway gateway = PaymentGatewayFactory.create(method);
        PaymentProcessor paymentProcessor = new PaymentProcessor(gateway);
        String txId = paymentProcessor.charge(request.riderName(), price);

        // 4. OBSERVER EVENT
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
