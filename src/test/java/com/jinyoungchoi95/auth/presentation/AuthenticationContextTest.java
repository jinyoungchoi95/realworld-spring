package com.jinyoungchoi95.auth.presentation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthenticationContextTest {

    @Test
    @DisplayName("principal이 없는데 get할 경우 예외 발생")
    void getNullPrinciapl_throwException() {
        assertThatThrownBy(() -> new AuthenticationContext().getPrincipal())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("can not return null principal");
    }

    @Test
    @DisplayName("principal이 있는데 set할 경우 예외 발생")
    void setExistPrincipal_throwException() {
        AuthenticationContext authenticationContext = new AuthenticationContext();
        authenticationContext.setPrincipal("principal");

        assertThatThrownBy(() -> authenticationContext.setPrincipal("principal"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("can not set exist principal");
    }
}
