package com.spring_payments_test.exceptions;

public class BadRequestEx extends RuntimeException {
    public BadRequestEx(String message) {
        super(message);
    }
}
