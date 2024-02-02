package com.jbunce.userscrudhex.application.dtos.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class BaseResponse {
    private Object data;
    private Boolean success;
    private String message;
    private HttpStatus status;
    private Integer statusCode;

    public ResponseEntity<BaseResponse> toResponseEntity() {
        return new ResponseEntity<>(this, status);
    }
}
