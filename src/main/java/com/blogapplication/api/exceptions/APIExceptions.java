package com.blogapplication.api.exceptions;

public class APIExceptions extends RuntimeException {

    public APIExceptions(String message) {
        super(message);

    }

    public APIExceptions() {
        super();
    }
}
