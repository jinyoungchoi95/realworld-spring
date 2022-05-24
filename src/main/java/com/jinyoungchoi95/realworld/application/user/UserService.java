package com.jinyoungchoi95.realworld.application.user;

import com.jinyoungchoi95.realworld.domain.user.User;
import com.jinyoungchoi95.realworld.domain.user.UserRepository;
import com.jinyoungchoi95.realworld.presentation.user.UserSaveRequest;
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
        checkDuplicateUsername(user.getUsername());

        return UserResponse.from(userRepository.save(user));
    }

    private void checkDuplicateEmail(final String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("user duplicated email");
        }
    }

    private void checkDuplicateUsername(final String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("user duplicated username");
        }
    }
}
