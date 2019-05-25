package com.sekulska.exceptions;

public class CurrencyUnavailableException extends RuntimeException{
    public CurrencyUnavailableException(String message) {
        super(message);
    }
}
