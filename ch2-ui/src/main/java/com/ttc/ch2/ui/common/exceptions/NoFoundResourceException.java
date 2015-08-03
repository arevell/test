package com.ttc.ch2.ui.common.exceptions;

public class NoFoundResourceException extends RuntimeException {
	public NoFoundResourceException() {
		super();
	}

	public NoFoundResourceException(String message) {
		super(message);
	}

	public NoFoundResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoFoundResourceException(Throwable cause) {
		super(cause);
	}
}
