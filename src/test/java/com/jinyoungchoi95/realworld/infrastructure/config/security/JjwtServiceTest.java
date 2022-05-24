package com.jinyoungchoi95.realworld.infrastructure.config.security;

import static org.assertj.core.api.Assertions.assertThat;

import com.jinyoungchoi95.realworld.application.user.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JjwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("토큰을 생성할 수 있다.")
    void createToken() {
        assertThat(jwtService.createToken("email@email.com")).isNotNull();
    }
}
