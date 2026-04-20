package com.cortaxi.patterns.creational.builder;

public class RideRequestDirector {

    public StandardRideRequest buildStandard(IRideRequestBuilder<StandardRideRequest> builder,
                                             String rider,
                                             String pickup,
                                             String dropout) {
        return builder
                .rider(rider)
                .from(pickup)
                .to(dropout)
                .vehicleType("Standard")
                .estimatedPrice(25.0)
                .build();
    }

    public StandardRideRequest buildPremiumPetFriendly(IRideRequestBuilder<StandardRideRequest> builder,
                                                       String rider,
                                                       String pickup,
                                                       String dropout) {
        return builder
                .rider(rider)
                .from(pickup)
                .to(dropout)
                .vehicleType("Premium")
                .petFriendly(true)
                .estimatedPrice(40.0)
                .notes("Handle with care: pet on board")
                .build();
    }

    public BusinessRideRequest buildBusinessExecutive(IRideRequestBuilder<BusinessRideRequest> builder,
                                                      String rider,
                                                      String pickup,
                                                      String dropout,
                                                      String invoiceEmail) {

        if (builder instanceof BusinessRideRequestBuilder b) {
                    b.prioritySupport(true)
                    .invoiceEmail(invoiceEmail);
        }
        return builder
                .rider(rider)
                .from(pickup)
                .to(dropout)
                .vehicleType("Business")
                .estimatedPrice(65.0)
                .notes("Includes priority support and refreshments")
                .build();
    }
}