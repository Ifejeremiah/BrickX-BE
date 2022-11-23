package com.example.brickx.exceptions;

import org.springframework.http.HttpStatus;

public class BrickxAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public BrickxAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BrickxAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
