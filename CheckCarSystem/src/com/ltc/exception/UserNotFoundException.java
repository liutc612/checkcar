package com.ltc.exception;
/**
 * 自定义异常：用于描述用户登录是否成功的状态
 */
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}
	
}
