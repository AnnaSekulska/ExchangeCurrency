package com.sekulska.exceptions;

public class CallsLimitExhaustedException extends RuntimeException {
    public CallsLimitExhaustedException(String message) {
        super(message);
    }
}
