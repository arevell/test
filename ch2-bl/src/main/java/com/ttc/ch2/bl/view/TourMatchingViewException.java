package com.ttc.ch2.bl.view;


public class TourMatchingViewException extends Exception {

	public TourMatchingViewException() {
		super();
	}

	public TourMatchingViewException(Exception e) {
		super(e);
	}

	public TourMatchingViewException(String message) {
		super(message);
	}

	public TourMatchingViewException(String message, Throwable cause) {
		super(message, cause);
	}
}
