package com.ttc.ch2.bl.user;

public class UserServiceException extends RuntimeException {

	public UserServiceException() {
		super();
	}
	
	public UserServiceException(Exception e) {
		super(e);
	}

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
