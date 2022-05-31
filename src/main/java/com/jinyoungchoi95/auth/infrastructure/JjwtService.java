package com.jinyoungchoi95.auth.infrastructure;

import com.jinyoungchoi95.auth.application.JwtService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JjwtService implements JwtService {

    private final SecretKey key;
    private final long accessTime;

    public JjwtService(@Value("${jwt.token.secret}") final String key,
                       @Value("${jwt.token.access-time}") final long accessTime) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        this.accessTime = accessTime;
    }

    @Override
    public String createToken(final String email) {
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512)
                .setSubject(email)
                .setExpiration(new Date((new Date()).getTime() + accessTime))
                .compact();
    }

    @Override
    public String resolveToken(final String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("invalid jwt token");
        }
    }
}
