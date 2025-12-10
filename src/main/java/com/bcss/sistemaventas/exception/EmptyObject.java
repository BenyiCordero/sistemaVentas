package com.bcss.sistemaventas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyObject extends RuntimeException {
    
    public EmptyObject(String message) {
        super(message);
    }
    
    public EmptyObject(String message, Throwable cause) {
        super(message, cause);
    }
}