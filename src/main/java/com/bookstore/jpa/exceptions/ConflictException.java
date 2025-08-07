package com.bookstore.jpa.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException() {
        super("A conflict exception has occurred.");
    }
    public ConflictException(String message) {
        super(message);
    }
}
