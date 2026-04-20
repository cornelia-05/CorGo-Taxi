package com.cortaxi.auth;

import com.cortaxi.auth.dto.AuthResponse;
import com.cortaxi.auth.dto.LoginRequest;
import com.cortaxi.auth.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthController(AccountRepository repo, PasswordEncoder encoder, JwtService jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest req) {
        repo.findByEmail(req.email()).ifPresent(a -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used");
        });

        Account acc = new Account();
        acc.setEmail(req.email());
        acc.setPasswordHash(encoder.encode(req.password()));
        acc.setRole(req.role());
        acc.setCreatedAt(Instant.now());

        repo.save(acc);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest req) {
        Account acc = repo.findByEmail(req.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!encoder.matches(req.password(), acc.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwt.createToken(acc);
        return new AuthResponse(token, acc.getRole());
    }
}