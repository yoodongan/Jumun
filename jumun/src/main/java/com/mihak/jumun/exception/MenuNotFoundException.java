package com.mihak.jumun.exception;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(String message) {
        super(message);
    }
}
