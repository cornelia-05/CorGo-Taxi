package com.cortaxi.patterns.creational;

import com.cortaxi.domain.Client;
import com.cortaxi.domain.Driver;
import com.cortaxi.domain.User;

public class UserFactory {
    public static User createUser(String type, String id, String name) {
        return switch (type.toLowerCase()) {
            case "client" -> new Client(id, name) {
                @Override
                public String getRole() {
                    return "";
                }
            };
            case "driver" -> new Driver(id, name) {
                @Override
                public String getRole() {
                    return "";
                }
            };
            default -> throw new IllegalArgumentException("Unknown user type");
        };
    }
}
