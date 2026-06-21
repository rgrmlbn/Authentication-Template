package com.project.main.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException( ) {
        super("Invalid token");
    }
}
