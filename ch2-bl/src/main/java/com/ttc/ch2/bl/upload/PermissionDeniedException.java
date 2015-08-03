package com.ttc.ch2.bl.upload;

public class PermissionDeniedException extends RuntimeException{

	
	public PermissionDeniedException() {
		super();
	}
	
	public PermissionDeniedException(Exception e) {
		super(e);
	}

	public PermissionDeniedException(String message) {
		super(message);
	}

	public PermissionDeniedException(String message, Throwable cause) {
		super(message, cause);
	}
}
