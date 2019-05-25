package com.sekulska.controller;

import com.sekulska.error.ApiError;
import com.sekulska.exceptions.CallsLimitExhaustedException;
import com.sekulska.exceptions.CurrencyUnavailableException;
import com.sekulska.exceptions.EndpointStructureChangedException;
import com.sekulska.exceptions.RangeExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error =  "Path not exist. Bad URL. " + ex.getRequestURL() ;
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,  error,  ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String error = ex.getParameterName() + " parameter is missing";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({EndpointStructureChangedException.class})
    public ResponseEntity<Object> handleEndpointStructureChangeException(EndpointStructureChangedException ex){
        String error = "Endpoint content was changed";
        ApiError apIerror = new ApiError(HttpStatus.NOT_FOUND, error, ex.getMessage());
        return new ResponseEntity<>(apIerror, apIerror.getStatus());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException (IllegalArgumentException ex){
        String error = "You can't use this value";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({CurrencyUnavailableException.class})
    public ResponseEntity<Object> handleCurrencyUnavailableException (Exception ex){
        String error = "Currency unavailable. Select other currency.";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({RangeExceededException.class})
    public ResponseEntity<Object> handleRangeExceededException (Exception ex){
        String error = "Select shorter range";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({CallsLimitExhaustedException.class})
    public ResponseEntity<Object> handleCallsLimitExhaustedException (Exception ex){
        String error = "Allowed amount: 5 per minute.";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
