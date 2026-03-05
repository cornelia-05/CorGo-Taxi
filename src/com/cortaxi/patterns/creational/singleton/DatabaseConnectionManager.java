package com.cortaxi.patterns.creational.singleton;

public class DatabaseConnectionManager {
    private static volatile DatabaseConnectionManager instance;

    private DatabaseConnectionManager() {
        // Simulated expensive init
        System.out.println("Initializing DB connection manager...");
    }

    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionManager.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }

    public void execute(String sql) {
        System.out.println("Executing SQL on singleton connection: " + sql);
    }
}