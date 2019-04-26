package com.appNgeek.blog.exception;

public class PermissionDeniedException extends Exception {

	private static final long serialVersionUID = -948517976747652659L;

	public PermissionDeniedException(String message) {
		super(message);
	}

	public PermissionDeniedException(String message, Throwable ex) {
		super(message, ex);
	}

}
