package com.jinyoungchoi95.realworld.presentation.user;

import com.jinyoungchoi95.realworld.application.user.UserResponse;
import com.jinyoungchoi95.realworld.application.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody final UserSaveRequest userSaveRequest) {
        UserResponse response = userService.save(userSaveRequest);
        return ResponseEntity.ok().body(response);
    }
}
