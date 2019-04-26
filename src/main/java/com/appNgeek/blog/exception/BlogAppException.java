package com.appNgeek.blog.exception;

public class BlogAppException extends Exception {

	private static final long serialVersionUID = -6561747157587815458L;

	public BlogAppException(String message) {
		super(message);
	}

	public BlogAppException(String message, Throwable ex) {
		super(message, ex);
	}

}
