package com.cortaxi.patterns.structural.facade.services;

public class NotificationService {
    public void notifyRider(String riderName, String message) {
        System.out.println("[Notify rider] " + riderName + ": " + message);
    }
    public void notifyDriver(String driverId, String message) {
        System.out.println("[Notify driver " + driverId + "] " + message);
    }
}
