package com.ttc.ch2.bl.token;

public class UniqueTokenServiceException extends TokenServiceException {

	public UniqueTokenServiceException() {
		super();
	}
	
	public UniqueTokenServiceException(Exception e) {
		super(e);
	}

	public UniqueTokenServiceException(String message) {
		super(message);
	}

	public UniqueTokenServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
