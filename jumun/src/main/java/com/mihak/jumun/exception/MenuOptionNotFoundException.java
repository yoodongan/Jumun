package com.mihak.jumun.exception;

public class MenuOptionNotFoundException extends RuntimeException {
    public MenuOptionNotFoundException(String message) {
        super(message);
    }
}
