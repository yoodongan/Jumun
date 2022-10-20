package com.mihak.jumun.order.exception;

public class OrderProcessFailedException extends RuntimeException {
    public OrderProcessFailedException(String message) {
        super(message);
    }
}
