package com.jinyoungchoi95.realworld.infrastructure.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jinyoungchoi95.realworld.application.auth.JwtService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JjwtServiceTest {

    @Value("${jwt.token.secret}")
    private String key;

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("토큰을 생성할 수 있다.")
    void createToken() {
        assertThat(jwtService.createToken("email@email.com")).isNotNull();
    }

    @Test
    @DisplayName("토큰을 복호화할 수 있다.")
    void resolveToken() {
        String subject = "email@email.com";
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, key)
                .setSubject(subject)
                .compact();

        assertThat(jwtService.resolveToken(token)).isEqualTo(subject);
    }

    @Test
    @DisplayName("이상한 토큰 복호화 시 예외 발생")
    void resolveTokenExceptionByErrorToken() {
        assertThatThrownBy(() -> jwtService.resolveToken("error token"))
                .isInstanceOf(JwtException.class)
                .hasMessage("invalid jwt token");
    }

    @Test
    @DisplayName("만료된 토큰 복호화 시 예외 발생")
    void resolveTokenExceptionByExpiredToken() {
        String expiredToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, key)
                .setSubject("email@email.com")
                .setExpiration(new Date((new Date()).getTime() - 1))
                .compact();

        assertThatThrownBy(() -> jwtService.resolveToken(expiredToken))
                .isInstanceOf(JwtException.class)
                .hasMessage("invalid jwt token");
    }
}
