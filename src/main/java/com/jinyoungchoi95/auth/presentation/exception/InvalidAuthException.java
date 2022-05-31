package com.jinyoungchoi95.auth.presentation.exception;

public class InvalidAuthException extends RuntimeException {

    public InvalidAuthException(final String message) {
        super(message);
    }
}
