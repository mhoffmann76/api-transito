package com.hoffmanntecnologia.transito.api.exceptionhandler;

import com.hoffmanntecnologia.transito.domain.exception.NegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e){
        return ResponseEntity.badRequest().body(e.getMessage());

    }
}
