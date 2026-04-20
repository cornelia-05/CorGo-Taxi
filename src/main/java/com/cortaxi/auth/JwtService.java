package com.cortaxi.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private final Algorithm alg;
    private final String issuer;
    private final long ttlMinutes;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.issuer:corgo-taxi}") String issuer,
            @Value("${app.jwt.ttl-minutes:60}") long ttlMinutes
    ) {
        this.alg = Algorithm.HMAC256(secret);
        this.issuer = issuer;
        this.ttlMinutes = ttlMinutes;
    }

    public String createToken(Account acc) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(ttlMinutes * 60);

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(acc.getId().toString())
                .withClaim("email", acc.getEmail())
                .withClaim("role", acc.getRole())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(alg);
    }

    public DecodedJWT verify(String token) {
        return JWT.require(alg)
                .withIssuer(issuer)
                .build()
                .verify(token);
    }
}