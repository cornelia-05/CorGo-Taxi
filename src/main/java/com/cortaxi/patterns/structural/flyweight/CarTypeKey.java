package com.cortaxi.patterns.structural.flyweight;

import java.util.Objects;

/**
 * Cheie imutabilă pentru pool-ul din factory.
 * Separăm Key de Flyweight ca să evităm bug-uri de equality/identity.
 */
public final class CarTypeKey {
    private final String brand;
    private final String model;
    private final String color;
    private final String fuelType;

    public CarTypeKey(String brand, String model, String color, String fuelType) {
        this.brand = Objects.requireNonNull(brand, "brand");
        this.model = Objects.requireNonNull(model, "model");
        this.color = Objects.requireNonNull(color, "color");
        this.fuelType = Objects.requireNonNull(fuelType, "fuelType");
    }

    public String brand() { return brand; }
    public String model() { return model; }
    public String color() { return color; }
    public String fuelType() { return fuelType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarTypeKey)) return false;
        CarTypeKey that = (CarTypeKey) o;
        return brand.equals(that.brand)
                && model.equals(that.model)
                && color.equals(that.color)
                && fuelType.equals(that.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color, fuelType);
    }

    @Override
    public String toString() {
        return brand + "|" + model + "|" + color + "|" + fuelType;
    }
}