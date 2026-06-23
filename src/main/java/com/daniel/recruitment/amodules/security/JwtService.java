package com.daniel.recruitment.amodules.security;

public interface JwtService {

    String generateToken(String email);

    String extractUsername(String token);

    boolean isTokenValid(String token);

}