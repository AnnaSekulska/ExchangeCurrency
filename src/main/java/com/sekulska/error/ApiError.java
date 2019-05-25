package com.sekulska.error;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private String message;
    private String exceptionMessage;

    public ApiError(HttpStatus httpStatus, String message, String exceptionMessage) {
        this.status = httpStatus;
        this.message = message;
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
