package com.jinyoungchoi95.realworld.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jinyoungchoi95.realworld.application.response.UserResponse;
import com.jinyoungchoi95.realworld.domain.user.UserRepository;
import com.jinyoungchoi95.realworld.presentation.request.UserSaveRequest;
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
        userRepository.save(new UserSaveRequest("username1", "email@email.com", "password", "bio", "image").toEntity());
        UserSaveRequest request = new UserSaveRequest("username2", "email@email.com", "password", "bio", "image");

        // when & then
        assertThatThrownBy(() -> userService.save(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("user duplicated email");
    }

    @Test
    @DisplayName("유저 저장 시 유저 닉네임 중복 예외")
    void saveExceptionByDuplicatedUsername() {
        // given
        userRepository.save(new UserSaveRequest("username", "email1@email.com", "password", "bio", "image").toEntity());
        UserSaveRequest request = new UserSaveRequest("username", "email2@email.com", "password", "bio", "image");

        // when & then
        assertThatThrownBy(() -> userService.save(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("user duplicated username");
    }

    @Test
    @DisplayName("유저 저장")
    void save() {
        // given
        UserSaveRequest request = new UserSaveRequest("username", "email@email.com", "password", "bio", "image");

        // when
        UserResponse result = userService.save(request);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(new UserResponse("email@email.com", "token", "username", "bio", "image"));
    }
}
