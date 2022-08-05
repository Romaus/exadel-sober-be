package com.exadel.sober.exceptions;

public class BadUserCredentialException extends RuntimeException {

    private String message;

    public BadUserCredentialException(String msg) {
        super(msg);
        this.message = msg;
    }
}
