package com.cortaxi.patterns.behavioral.observer;

public interface ISubscriber {
    void update(RideEvent event);
}
