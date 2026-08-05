package com.rev.web.exceptions;

public class NotFoundResponse extends RuntimeException {
    public NotFoundResponse(String message) {
        super(message);
    }
}
