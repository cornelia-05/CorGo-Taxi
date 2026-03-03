package com.cortaxi.patterns.creational.builder;

public interface IRideRequestBuilder<T> {
    IRideRequestBuilder<T> reset();
    IRideRequestBuilder<T> rider(String name);
    IRideRequestBuilder<T> from(String pickup);
    IRideRequestBuilder<T> to(String dropout);
    IRideRequestBuilder<T> vehicleType(String vehicleType);
    IRideRequestBuilder<T> petFriendly(boolean petFriendly);
    IRideRequestBuilder<T> notes(String notes);
    IRideRequestBuilder<T> estimatedPrice(double price);

    T build();
}