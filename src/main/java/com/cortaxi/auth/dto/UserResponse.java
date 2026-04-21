package com.cortaxi.auth.dto;

import com.cortaxi.auth.Role;

import java.time.Instant;

public record UserResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phone,
        Role role,
        Instant createdAt
) {}