package com.jbunce.userscrudhex.infrastructure.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jbunce.userscrudhex.application.dtos.response.BaseResponse;

import io.micrometer.common.lang.NonNull;

public class ValidationHandlerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {

        BaseResponse baseResponse = BaseResponse.builder().build();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            baseResponse.setMessage(((FieldError) error).getField() + error.getDefaultMessage());
            baseResponse.setSuccess(false);
            baseResponse.setStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setStatusCode(400);
        });
        return new ResponseEntity<>(baseResponse, baseResponse.getStatus());
    }
}
