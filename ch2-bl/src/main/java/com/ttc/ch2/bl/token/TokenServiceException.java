package com.ttc.ch2.bl.token;

public class TokenServiceException extends RuntimeException {

	public TokenServiceException() {
		super();
	}
	
	public TokenServiceException(Exception e) {
		super(e);
	}

	public TokenServiceException(String message) {
		super(message);
	}

	public TokenServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
