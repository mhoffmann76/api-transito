package com.hoffmanntecnologia.transito.domain.exception;

public class NegocioException extends RuntimeException{
    public NegocioException(final String message) {
        super(message);
    }
}
