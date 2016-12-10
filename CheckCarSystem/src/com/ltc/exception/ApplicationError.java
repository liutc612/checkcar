package com.ltc.exception;
/**
 * 自定义异常：用于描述持久层的错误
 * @author ltc
 *
 */
public class ApplicationError extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public ApplicationError() {
		super();
	}

	public ApplicationError(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationError(String message) {
		super(message);
	}
	
}
