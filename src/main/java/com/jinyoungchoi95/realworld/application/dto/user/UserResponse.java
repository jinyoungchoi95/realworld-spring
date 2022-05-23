package com.jinyoungchoi95.realworld.application.dto.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.jinyoungchoi95.realworld.domain.user.User;

@JsonTypeName("user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class UserResponse {
    
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    private UserResponse() {
    }

    public UserResponse(final String email, final String token, final String username, final String bio,
                        final String image) {
        this.email = email;
        this.token = token;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getEmail(), "token", user.getUsername(), user.getBio(), user.getImage());
    }
}
