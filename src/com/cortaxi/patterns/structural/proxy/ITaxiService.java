package com.cortaxi.patterns.structural.proxy;

public interface ITaxiService {
    void requestRide(String location);
    String getDriverInfo(String id);
}
