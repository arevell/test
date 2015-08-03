package com.ttc.ch2.bl.constraints;

public class ConstraintServiceException extends Exception{

	
	private static final long serialVersionUID = -6104329110949556571L;

	public ConstraintServiceException() {
		super();
	}
	
	public ConstraintServiceException(Exception e) {
		super(e);
	}

	public ConstraintServiceException(String message) {
		super(message);
	}

	public ConstraintServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
