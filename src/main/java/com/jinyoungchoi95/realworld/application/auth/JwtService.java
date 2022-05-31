package com.jinyoungchoi95.realworld.application.auth;

public interface JwtService {

    String createToken(String email);

    String resolveToken(String token);
}
