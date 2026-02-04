package com.spring_payments_test.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String menssagem){
        super(menssagem);
    }
}
