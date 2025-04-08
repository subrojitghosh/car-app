package com.app.exception;

public class EmailExist extends RuntimeException {
    public EmailExist(String message) {

        super(message);
    }
}
