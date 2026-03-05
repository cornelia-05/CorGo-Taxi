package com.cortaxi.patterns.creational.prototype;

public class Waypoint implements Cloneable {
    private final String name;
    private final double latitude;
    private final double longitude;

    public Waypoint(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // copy-constructor pentru deep copy controlat
    public Waypoint(Waypoint other) {
        this(other.name, other.latitude, other.longitude);
    }

    @Override
    public Waypoint clone() {
        // nu aruncăm checked exception; creăm un nou exemplar
        return new Waypoint(this);
    }

    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}