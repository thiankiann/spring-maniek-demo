package com.example.demo.controller;

import com.example.demo.dto.ExceptionResponse;
import com.example.demo.exception.BookNotFoundException;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBookNotFoundException(Exception exception, HttpServletRequest request){
        //public ResponseEntity<?> handleBookNotFoundException(Exception exception){
        logger.warn("generic exception handler for some unexpected exception", exception);

        // return ResponseEntity.badRequest().body(exception.getMessage());
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(LocalDateTime.now(Clock.systemUTC()),
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getClass().getName(),
                        exception.getMessage(),
                        request.getServletPath()
                )
        );

    }
}
