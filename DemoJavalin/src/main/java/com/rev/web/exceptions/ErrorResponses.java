package com.rev.web.exceptions;

public class ErrorResponses {
    private final String error;
    private final long timestamp;

    public ErrorResponses(String error) {
        this.error = error;
        this.timestamp = System.currentTimeMillis();
    }

    public String getError() {
        return error;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
