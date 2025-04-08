package com.app.exception;

public class UserExist extends RuntimeException {
    public UserExist(String massage){
        super(massage);
    }
}
