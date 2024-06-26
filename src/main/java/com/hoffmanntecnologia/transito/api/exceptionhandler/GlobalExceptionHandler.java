package com.hoffmanntecnologia.transito.api.exceptionhandler;

import com.hoffmanntecnologia.transito.domain.exception.EntidadeNaoEcontradaException;
import com.hoffmanntecnologia.transito.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.hibernate.query.sql.spi.ParameterOccurrence;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;
@AllArgsConstructor
@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final  MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatusCode status,
                                                                  final WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos então inválidos");

                Map<String, String> fields =  ex.getBindingResult().getAllErrors()
                .stream()
                         .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                                 objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
                problemDetail.setProperty("fields", fields);


        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ProblemDetail handleNegocio (NegocioException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }



    @ExceptionHandler(EntidadeNaoEcontradaException.class)
    public ProblemDetail handleEntidadeNaoEncontrada (EntidadeNaoEcontradaException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }


    @ExceptionHandler(DataIntegrityViolationException.class)

    public ProblemDetail handleDataIntegrity(DataIntegrityViolationException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso está em uso");

        return problemDetail;

    }
}
