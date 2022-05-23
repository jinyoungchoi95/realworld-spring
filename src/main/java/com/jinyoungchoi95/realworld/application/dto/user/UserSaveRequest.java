package com.jinyoungchoi95.realworld.application.dto.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.jinyoungchoi95.realworld.domain.user.User;

@JsonTypeName(value = "user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class UserSaveRequest {

    private String username;
    private String email;
    private String password;
    private String bio;
    private String image;

    private UserSaveRequest() {
    }

    public UserSaveRequest(final String username, final String email, final String password, final String bio,
                           final String image) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .bio(bio)
                .image(image)
                .build();
    }
}
