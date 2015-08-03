package com.ttc.ch2.common.exceptions;

public class DaoException extends RuntimeException {

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
