package com.cortaxi.patterns.structural.proxy;
import java.util.Objects;

public class TaxiApp {
    protected final ITaxiService service;

    public TaxiApp(ITaxiService service) {
        this.service = Objects.requireNonNull(service);
    }

    public void showRide() {
        service.requestRide("Center");
    }

    public void showDriver(String id) {
        String info = service.getDriverInfo(id);
        System.out.println(info);
    }
}