package com.jinyoungchoi95.realworld.acceptance;

import com.jinyoungchoi95.realworld.presentation.request.UserSaveRequest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("유저 관련 기능")
public class UserAcceptanceTest extends AcceptanceTest {

    private ValidatableResponse postUsers(final UserSaveRequest request) {
        return RestAssured.given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/users")
                .then().log().all();
    }

    @Nested
    @DisplayName("회원가입은")
    class Registration extends AcceptanceTest {

        private UserSaveRequest request;

        @BeforeEach
        void prepare() {
            request = new UserSaveRequest("Jacob", "jake@jake.jake", "jakejake", null, null);
        }

        @Test
        @DisplayName("정상적으로 동작하여 성공")
        void success() {
            postUsers(request).statusCode(HttpStatus.OK.value());
        }

        @Test
        @DisplayName("이메일이 중복되면 실패")
        void fail_when_duplicated_email_is_exist() {
            postUsers(new UserSaveRequest("Jake", "jake@jake.jake", "jakejake", null, null));

            postUsers(request).statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("유저네임이 중복되면 실패")
        void fail_when_duplicated_username_is_exist() {
            postUsers(new UserSaveRequest("Jacob", "jacob@jacob.jacob", "jakejake", null, null));

            postUsers(request).statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
}
