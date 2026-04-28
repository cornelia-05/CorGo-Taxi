package com.cortaxi.patterns.behavioral.iterator;

import java.util.List;

public class DriverIterator implements Iterator<String> {

    private final List<String> drivers;
    private int position = 0;

    public DriverIterator(List<String> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean hasNext() {
        return position < drivers.size();
    }

    @Override
    public String next() {
        return drivers.get(position++);
    }

    @Override
    public void reset() {
        position = 0;
    }
}