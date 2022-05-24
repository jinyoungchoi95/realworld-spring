package com.jinyoungchoi95.realworld.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(final String email);

    boolean existsByUsername(final String username);
}
