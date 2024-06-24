package com.dauphine.blogger.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.dauphine.blogger.services.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.services.exceptions.EventNotFoundByIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler({
            CategoryNotFoundByIdException.class,
            EventNotFoundByIdException.class
    })
    public ResponseEntity<String> handleNotFoundException(Exception ex){
        logger.warn("[NOT FOUND] {}", ex.getMessage());
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }
}