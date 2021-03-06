package com.java.products.exception;

public class InvalidOrderException extends IllegalArgumentException {

    public InvalidOrderException(String msg) {
        super(msg);
    }

    public InvalidOrderException(String msg, Throwable err) {
        super(msg, err);
    }
}
