package com.saraya.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    public String exceptionHandler(Exception ex){
        return ex.getMessage();
    }

    @ExceptionHandler(WecareException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(WecareException ex){
        ErrorMessage error = new ErrorMessage();
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
