package com.cortaxi.patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class DriverList implements IDriverCollection {

    private final List<String> drivers = new ArrayList<>();

    public DriverList() {
        drivers.add("DRV-101");
        drivers.add("DRV-102");
        drivers.add("DRV-103");
    }

    @Override
    public Iterator<String> createIterator() {
        return new DriverIterator(drivers);
    }
}