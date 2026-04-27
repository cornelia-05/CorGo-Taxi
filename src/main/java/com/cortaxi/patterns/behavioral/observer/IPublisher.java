package com.cortaxi.patterns.behavioral.observer;

public interface IPublisher {
    void subscribe(ISubscriber subscriber);
    void unsubscribe(ISubscriber subscriber);
    void notifySubscribers(RideEvent event);
}