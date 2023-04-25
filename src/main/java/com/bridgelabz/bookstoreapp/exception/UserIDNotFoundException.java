package com.bridgelabz.bookstoreapp.exception;

public class UserIDNotFoundException extends RuntimeException{
    public UserIDNotFoundException(String message) {
        super(message);
    }
}
