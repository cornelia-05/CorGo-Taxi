package com.cortaxi.domain;

public class Admin extends User {

    public Admin(String id, String name) {
        super(id, name);
    }

    public void blockUser(User user) {
        System.out.println("User " + user.name + " has been blocked.");
    }

    public void viewAllRides() {
        System.out.println("Showing all rides...");
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public void notify(String message) {

    }
}