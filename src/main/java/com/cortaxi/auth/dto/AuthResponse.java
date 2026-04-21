package com.cortaxi.auth.dto;

public record AuthResponse(String token, UserResponse user) {}