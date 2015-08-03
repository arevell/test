package com.ttc.ch2.ui.common.exceptions;

public class CH2Exception extends RuntimeException {
	public CH2Exception() {
		super();
	}

	public CH2Exception(String message) {
		super(message);
	}

	public CH2Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public CH2Exception(Throwable cause) {
		super(cause);
	}
}
