package com.jinyoungchoi95.realworld.presentation;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinyoungchoi95.auth.application.JwtService;
import com.jinyoungchoi95.auth.presentation.AuthenticationContext;
import com.jinyoungchoi95.realworld.application.UserService;
import com.jinyoungchoi95.realworld.application.response.UserResponse;
import com.jinyoungchoi95.realworld.presentation.request.UserSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationContext authenticationContext;

    @Test
    @DisplayName("POST /api/users")
    void save() throws Exception {
        // setup & given
        UserSaveRequest request = new UserSaveRequest("username", "email@email.com", "password", "bio", null);
        when(userService.save(request))
                .thenReturn(new UserResponse("email@email.com", "token", "username", "bio", null));

        this.mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
