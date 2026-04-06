package com.cortaxi.patterns.structural.flyweight;

import java.util.Objects;

/**
 * Flyweight = starea intrinsecă (shared).
 * Obiectele de tip CarTypeFlyweight sunt reutilizate între multe taxi-uri.
 */
public final class CarTypeFlyweight {
    private final String brand;
    private final String model;
    private final String color;
    private final String fuelType; // optional, dar util ca exemplu

    public CarTypeFlyweight(String brand, String model, String color, String fuelType) {
        this.brand = Objects.requireNonNull(brand, "brand");
        this.model = Objects.requireNonNull(model, "model");
        this.color = Objects.requireNonNull(color, "color");
        this.fuelType = Objects.requireNonNull(fuelType, "fuelType");
    }

    public String brand() { return brand; }
    public String model() { return model; }
    public String color() { return color; }
    public String fuelType() { return fuelType; }

    /**
     * Behavior rămâne în Flyweight, dar primește extrinsic state ca parametri.
     */
    public String render(String plateNumber, String driverId, double x, double y, String status) {
        return "Taxi[" +
                "plate=" + plateNumber +
                ", driver=" + driverId +
                ", status=" + status +
                ", pos=(" + x + "," + y + ")" +
                ", carType=" + brand + " " + model + " " + color + " " + fuelType +
                "]";
    }

    @Override
    public String toString() {
        return "CarTypeFlyweight{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}