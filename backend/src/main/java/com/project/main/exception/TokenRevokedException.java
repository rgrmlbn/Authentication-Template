package com.project.main.exception;

public class TokenRevokedException extends RuntimeException {
    public TokenRevokedException() {
        super("Token has been revoked");
    }
}
