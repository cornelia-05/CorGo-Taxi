package com.cortaxi.patterns.structural.composite;

public class FareItem implements IFareComponent {
    private final String name;
    private final double price;

    public FareItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override public String getName() { return name; }
    @Override public double getPrice() { return price; }

    @Override
    public void print(String indent) {
        System.out.printf("%s- %s: %.2f%n", indent, name, price);
    }
}