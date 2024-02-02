package com.jbunce.userscrudhex.infrastructure.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("The entity was not found");
    }
}