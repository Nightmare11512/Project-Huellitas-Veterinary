package com.example.Proyect_DevOps.exception;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> manejarExcepcion(RuntimeException e) {
        return ResponseEntity
        .badRequest()
        .body(
            Map.of("success", false,
        "message",e.getMessage()
        ));
    }
}