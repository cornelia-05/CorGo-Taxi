package com.cortaxi.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/auth/");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring("Bearer ".length());
            try {
                DecodedJWT jwt = jwtService.verify(token);

                String email = jwt.getClaim("email").asString();
                String role = jwt.getClaim("role").asString(); // "DRIVER"/"PASSENGER"

                var authorities = (role == null || role.isBlank())
                        ? List.<SimpleGrantedAuthority>of()
                        : List.of(new SimpleGrantedAuthority("ROLE_" + role));

                var authentication =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);

                authentication.setDetails(
                        new org.springframework.security.web.authentication.WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );
                System.out.println("FILTER HIT: " + request.getRequestURI());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception ex) {
                ex.printStackTrace();
                SecurityContextHolder.clearContext();
            }
        }
        System.out.println("AUTH HEADER: " + auth);
        filterChain.doFilter(request, response);

    }
}