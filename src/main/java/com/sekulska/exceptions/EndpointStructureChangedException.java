package com.sekulska.exceptions;

public class EndpointStructureChangedException extends RuntimeException {
    public EndpointStructureChangedException(String message) {
        super(message);
    }
}
