package com.pet.pethaven.exceptionHandler;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
            super(message);
        }
};
