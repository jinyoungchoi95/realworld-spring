package com.jinyoungchoi95.realworld.application.service;

import com.jinyoungchoi95.realworld.application.dto.user.UserResponse;
import com.jinyoungchoi95.realworld.application.dto.user.UserSaveRequest;
import com.jinyoungchoi95.realworld.domain.user.User;
import com.jinyoungchoi95.realworld.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse save(final UserSaveRequest userSaveRequest) {
        User user = userSaveRequest.toEntity();
        checkDuplicateEmail(user.getEmail());

        return UserResponse.from(userRepository.save(user));
    }

    private void checkDuplicateEmail(final String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("user duplicated email");
        }
    }
}
