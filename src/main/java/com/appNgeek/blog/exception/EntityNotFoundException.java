package com.appNgeek.blog.exception;

public class EntityNotFoundException extends RuntimeException {
   
	private static final long serialVersionUID = -718416249404923558L;
	
	private Long id;
	
	private String entityType;

    public EntityNotFoundException(String entityType, Long id) {
        this.id = id;
        this.entityType =entityType;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return entityType + " with ID '" + id + "' not found";
    }
}