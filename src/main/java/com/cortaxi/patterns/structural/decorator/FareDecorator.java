package com.cortaxi.patterns.structural.decorator;

import java.util.Objects;

public abstract class FareDecorator implements IFareComponent {
    protected final IFareComponent wrappee;

    protected FareDecorator(IFareComponent wrappee) {
        this.wrappee = Objects.requireNonNull(wrappee, "wrappee");
    }

    @Override
    public double execute() {
        return wrappee.execute();
    }
}
