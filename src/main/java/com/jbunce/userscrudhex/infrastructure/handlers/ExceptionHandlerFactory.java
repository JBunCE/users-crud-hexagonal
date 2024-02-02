package com.jbunce.userscrudhex.infrastructure.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jbunce.userscrudhex.application.dtos.response.BaseResponse;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerFactory {

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException exception) {
        return BaseResponse.builder()
            .message(exception.getLocalizedMessage())
            .success(Boolean.FALSE)
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .statusCode(500).build().toResponseEntity();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<BaseResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        return BaseResponse.builder()
            .message(exception.getLocalizedMessage())
            .success(Boolean.FALSE)
            .status(HttpStatus.NOT_FOUND)
            .statusCode(404).build().toResponseEntity();
    }
    
}
