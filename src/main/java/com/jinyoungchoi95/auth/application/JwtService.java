package com.jinyoungchoi95.auth.application;

public interface JwtService {

    String createToken(String email);

    String resolveToken(String token);
}
