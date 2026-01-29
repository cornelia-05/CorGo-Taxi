package com.cortaxi.domain;

public abstract class User {
    protected String id;
    protected String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }

    public abstract String getRole();
    public String getId() { return id; }
    public String getName() { return name; }

    public abstract void notify(String message);
}
