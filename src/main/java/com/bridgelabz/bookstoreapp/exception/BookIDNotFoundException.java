package com.bridgelabz.bookstoreapp.exception;

public class BookIDNotFoundException extends RuntimeException{
    public BookIDNotFoundException(String message) {
        super(message);
    }
}
