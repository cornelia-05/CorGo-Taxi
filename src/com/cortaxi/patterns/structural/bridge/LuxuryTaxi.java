package com.cortaxi.patterns.structural.bridge;

public final class LuxuryTaxi implements IVehicleB {
    private int speed;

    @Override
    public void start() {
        System.out.println("Luxury taxi started");
    }

    @Override
    public void stop() {
        System.out.println("Luxury taxi stopped");
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
