package com.jinyoungchoi95.auth.support;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;

public class JwtAccessTokenExtractor {

    private static final String AUTHORIZATION_HEADER_TYPE = "Authrozation";
    private static final String TOKEN_TYPE = "Token";

    private JwtAccessTokenExtractor() {
        throw new AssertionError();
    }

    public static Optional<String> extract(final HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_TYPE);
        if (Strings.isEmpty(header)) {
            return Optional.empty();
        }
        return checkMatch(header.split(" "));
    }

    private static Optional<String> checkMatch(String[] parts) {
        if (parts.length == 2 && parts[0].equals(TOKEN_TYPE)) {
            return Optional.ofNullable(parts[1]);
        }
        return Optional.empty();
    }
}
