package com.cortaxi.controllers;

import com.cortaxi.patterns.behavioral.command.BookRideCommand;
import com.cortaxi.patterns.behavioral.command.ICommand;
import com.cortaxi.patterns.behavioral.command.RideInvoker;
import com.cortaxi.patterns.structural.facade.RideBookingFacade;
import com.cortaxi.patterns.structural.facade.RideRequest;
import com.cortaxi.patterns.structural.facade.help.PaymentMethod;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
public class RideApi {

    private final RideInvoker invoker;
    private final RideBookingFacade facade;

    public RideApi(RideInvoker invoker, RideBookingFacade facade) {
        this.invoker = invoker;
        this.facade = facade;
    }

    @PostMapping("/book")
    public String book(@RequestBody RideRequest request,
                       @RequestParam PaymentMethod method) {

        ICommand command = new BookRideCommand(
                facade,
                request,
                method
        );

        invoker.setCommand(command);
        invoker.executeCommand();

        return "OK";
    }
}