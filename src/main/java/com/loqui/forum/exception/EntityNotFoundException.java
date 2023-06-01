package com.loqui.forum.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long entityId) {
        super(String.format("%s with ID %d not found", entityName, entityId));
    }

    public EntityNotFoundException() {
        super();
    }
}
