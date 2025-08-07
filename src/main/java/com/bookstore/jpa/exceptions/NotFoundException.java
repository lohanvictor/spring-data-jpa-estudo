package com.bookstore.jpa.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("A not found exception has occurred.");
    }
}
