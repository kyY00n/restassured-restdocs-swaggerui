package com.rosie.restdocsswaggerui.controller;

import static org.springframework.http.ResponseEntity.badRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleException(Exception exception) {
        return badRequest().body(exception.getMessage());
    }

}
