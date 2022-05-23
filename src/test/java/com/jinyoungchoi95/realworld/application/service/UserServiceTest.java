package com.jinyoungchoi95.realworld.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jinyoungchoi95.realworld.application.dto.user.UserSaveRequest;
import com.jinyoungchoi95.realworld.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 저장 시 이메일 중복 예외")
    void saveExceptionByDuplicatedEmail() {
        // given
        UserSaveRequest request = new UserSaveRequest("username", "email@email.com", "password", "bio", "image");
        userRepository.save(request.toEntity());

        // when & then
        assertThatThrownBy(() -> userService.save(request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
