package com.cortaxi.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    // pentru demo; ideal din application.properties
    private final Algorithm algorithm = Algorithm.HMAC256("change-me-secret");
    private final JWTVerifier verifier = JWT.require(algorithm).withIssuer("cortaxi").build();

    public String createToken(Account acc) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(60 * 60 * 24); // 24h

        return JWT.create()
                .withIssuer("cortaxi")
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .withClaim("email", acc.getEmail())
                .withClaim("role", acc.getRole().name()) // IMPORTANT: enum -> String
                .sign(algorithm);
    }

    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }
}