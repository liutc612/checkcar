package com.ltc.exception;
/**
 * 自定义异常：用于描述持久层SQLException
 * @author ltc
 *
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}
	
}
