package com.jinyoungchoi95.realworld.application.user;

public interface JwtService {

    String createToken(String email);

    String resolveToken(String token);
}
