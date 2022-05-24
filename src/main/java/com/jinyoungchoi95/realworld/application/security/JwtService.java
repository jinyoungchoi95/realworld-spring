package com.jinyoungchoi95.realworld.application.security;

public interface JwtService {

    String createToken(String email);

    String resolveToken(String token);
}
