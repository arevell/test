package com.ttc.ch2.brox.service;

public class BrochureServiceException extends Exception {

	private static final long serialVersionUID = 3170191420036018664L;

	public BrochureServiceException() {
		super();
	}

	public BrochureServiceException(Exception e) {
		super(e);
	}

	public BrochureServiceException(String message) {
		super(message);
	}

	public BrochureServiceException(Throwable cause) {
		super(cause);
	}

	public BrochureServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
