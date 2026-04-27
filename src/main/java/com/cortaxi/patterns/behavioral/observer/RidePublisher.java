package com.cortaxi.patterns.behavioral.observer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RidePublisher implements IPublisher {

    private final List<ISubscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(RideEvent event) {
        for (ISubscriber s : subscribers) {
            s.update(event);
        }
    }

    // BUSINESS ACTIONS → emit events

    public void driverAssigned(String rideId, String driverId) {
        notifySubscribers(new RideEvent(
                rideId,
                "DRIVER_ASSIGNED",
                driverId,
                0
        ));
    }

    public void completed(String rideId, double fare) {
        notifySubscribers(new RideEvent(
                rideId,
                "COMPLETED",
                null,
                fare
        ));
    }
}