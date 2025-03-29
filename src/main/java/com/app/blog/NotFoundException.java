package com.app.blog;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id, String entity) {
        super(entity+" not found with ID : "+id);
    }
}