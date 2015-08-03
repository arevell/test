package com.ttc.ch2.bl.contentrepository;

public class ContentRepositoryServiceException extends Exception {
	
	public ContentRepositoryServiceException() {
		super();
	}
	
	public ContentRepositoryServiceException(Exception e) {
		super(e);
	}

	public ContentRepositoryServiceException(String message) {
		super(message);
	}

	public ContentRepositoryServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
