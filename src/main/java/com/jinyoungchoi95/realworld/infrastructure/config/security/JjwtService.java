package com.jinyoungchoi95.realworld.infrastructure.config.security;

import com.jinyoungchoi95.realworld.application.user.JwtService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JjwtService implements JwtService {

    private final String key;
    private final long accessTime;

    public JjwtService(@Value("${jwt.token.secret}") final String key,
                       @Value("${jwt.token.access-time}") final long accessTime) {
        this.key = key;
        this.accessTime = accessTime;
    }

    @Override
    public String createToken(final String email) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, key)
                .setSubject(email)
                .setExpiration(new Date((new Date()).getTime() + accessTime))
                .compact();
    }

    @Override
    public String resolveToken(final String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("invalid jwt token");
        }
    }
}
