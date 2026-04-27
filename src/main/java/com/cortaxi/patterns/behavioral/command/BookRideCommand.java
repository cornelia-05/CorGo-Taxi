package com.cortaxi.patterns.behavioral.command;

import com.cortaxi.patterns.structural.facade.RideBookingFacade;
import com.cortaxi.patterns.structural.facade.RideRequest;
import com.cortaxi.patterns.structural.facade.help.PaymentMethod;

public class BookRideCommand implements ICommand {

    private final RideBookingFacade facade;
    private final RideRequest request;
    private final PaymentMethod method;

    public BookRideCommand(
            RideBookingFacade facade,
            RideRequest request,
            PaymentMethod method
    ) {
        this.facade = facade;
        this.request = request;
        this.method = method;
    }

    @Override
    public void execute() {
        facade.bookRide(request, method);
    }
}