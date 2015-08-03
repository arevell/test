package com.ttc.ch2.bl.message;

@SuppressWarnings("serial")
public class CommentsServiceException extends RuntimeException {

	public CommentsServiceException(String message) {
		super(message);
	}

	public CommentsServiceException(Exception e) {
		super(e);
	}
}
