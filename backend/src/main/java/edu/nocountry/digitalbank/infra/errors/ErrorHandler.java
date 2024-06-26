package edu.nocountry.digitalbank.infra.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity<Map<String, String>> errorHandlerIntegrityValidation(IntegrityValidation e) {
        return ResponseEntity.badRequest()
                .body(Map.of("message", e.getMessage()));
    }
}
