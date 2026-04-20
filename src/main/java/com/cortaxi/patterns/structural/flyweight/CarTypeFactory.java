package com.cortaxi.patterns.structural.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Flyweight Factory = gestionează pool-ul de flyweights.
 */
public final class CarTypeFactory {

    private final Map<CarTypeKey, CarTypeFlyweight> pool = new ConcurrentHashMap<>();

    public CarTypeFlyweight getOrCreate(String brand, String model, String color, String fuelType) {
        CarTypeKey key = new CarTypeKey(brand, model, color, fuelType);
        return pool.computeIfAbsent(key, k -> new CarTypeFlyweight(
                k.brand(), k.model(), k.color(), k.fuelType()
        ));
    }

    public int poolSize() {
        return pool.size();
    }
}
