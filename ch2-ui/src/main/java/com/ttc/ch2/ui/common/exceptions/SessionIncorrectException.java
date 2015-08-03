package com.ttc.ch2.ui.common.exceptions;

public class SessionIncorrectException extends RuntimeException {
	public SessionIncorrectException() {
		super();
	}

	public SessionIncorrectException(String message) {
		super(message);
	}

	public SessionIncorrectException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionIncorrectException(Throwable cause) {
		super(cause);
	}
}
