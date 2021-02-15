package com.itau.api.renegociation.handle;

import com.itau.api.renegociation.dto.ErrorDTO;
import com.itau.api.renegociation.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ NotFoundException.class })
    private ResponseEntity<Object> handle(
            RuntimeException ex, WebRequest request) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
