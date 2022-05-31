package com.jinyoungchoi95.auth.presentation;

import com.jinyoungchoi95.auth.application.JwtService;
import com.jinyoungchoi95.auth.presentation.exception.InvalidAuthException;
import com.jinyoungchoi95.auth.support.JwtAccessTokenExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;
    private final AuthenticationContext authenticationContext;

    public AuthInterceptor(final JwtService jwtService, final AuthenticationContext authenticationContext) {
        this.jwtService = jwtService;
        this.authenticationContext = authenticationContext;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        String accessToken = JwtAccessTokenExtractor.extract(request)
                .orElseThrow(() -> new InvalidAuthException("invalid auth requset"));
        String subject = jwtService.resolveToken(accessToken);
        authenticationContext.setPrincipal(subject);
        return true;
    }
}
