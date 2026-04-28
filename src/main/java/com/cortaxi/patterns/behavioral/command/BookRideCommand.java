package com.cortaxi.patterns.behavioral.command;

import com.cortaxi.patterns.behavioral.memento.RideState;
import com.cortaxi.patterns.structural.facade.RideBookingFacade;
import com.cortaxi.patterns.structural.facade.RideRequest;
import com.cortaxi.patterns.structural.facade.help.PaymentMethod;

public class BookRideCommand implements ICommand {

    private final RideBookingFacade facade;
    private final RideRequest request;
    private final PaymentMethod method;
    private final RideState state;

    public BookRideCommand(
            RideBookingFacade facade,
            RideRequest request,
            PaymentMethod method,
            RideState state
    ) {
        this.facade = facade;
        this.request = request;
        this.method = method;
        this.state = state;
    }

    @Override
    public void execute() {
        state.pushState();
        facade.bookRide(request, method);
    }
}