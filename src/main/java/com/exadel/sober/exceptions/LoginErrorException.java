package com.exadel.sober.exceptions;

public class LoginErrorException extends RuntimeException {

    private String message;

    public LoginErrorException(String msg) {
        super(msg);
        this.message = msg;
    }
}
