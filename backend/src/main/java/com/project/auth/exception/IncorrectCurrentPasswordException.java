package com.project.auth.exception;

public class IncorrectCurrentPasswordException extends RuntimeException {
    public IncorrectCurrentPasswordException() {
        super("Incorrect current password");
    }
}
