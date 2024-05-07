package com.bfi.orabank.ExceptionHandler;

public class ElementExistException extends RuntimeException {
    public ElementExistException(String message) {
        super(message);
    }
}

