package com.example.techiteasy.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RecordNotFoundException() {
        super();
    }
    public RecordNotFoundException(String message) {
        super(message);
    }
}
