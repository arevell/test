package com.ttc.ch2.search.services;

public class SearchServiceException extends Exception {
	
	public SearchServiceException() {
		super();
	}
	
	public SearchServiceException(Exception e) {
		super(e);
	}

	public SearchServiceException(String message) {
		super(message);
	}

	public SearchServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
