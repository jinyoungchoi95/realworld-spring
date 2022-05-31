package com.jinyoungchoi95.auth.presentation;

import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthenticationContext {

    private String principal;

    public String getPrincipal() {
        if (Objects.isNull(principal)) {
            throw new IllegalStateException("can not return null principal");
        }
        return principal;
    }

    public void setPrincipal(final String principal) {
        if (Objects.nonNull(this.principal)) {
            throw new IllegalStateException("can not set exist principal");
        }
        this.principal = principal;
    }
}
