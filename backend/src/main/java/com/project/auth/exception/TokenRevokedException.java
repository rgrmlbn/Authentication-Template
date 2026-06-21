package com.project.auth.exception;

public class TokenRevokedException extends RuntimeException {
    public TokenRevokedException() {
        super("Token has been revoked");
    }
}
