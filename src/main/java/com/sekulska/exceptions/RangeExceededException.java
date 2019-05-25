package com.sekulska.exceptions;

public class RangeExceededException extends RuntimeException {
    public RangeExceededException(String message) {
        super(message);
    }
}
