package com.cortaxi.patterns.structural.bridge;

public class StandartTaxiB implements IVehicleB {
    private int speed;

    @Override
    public void start() {
        System.out.println("Standard taxi started");
    }

    @Override
    public void stop() {
        System.out.println("Standard taxi stopped");
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}