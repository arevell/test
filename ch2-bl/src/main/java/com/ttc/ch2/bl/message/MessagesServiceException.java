package com.ttc.ch2.bl.message;

@SuppressWarnings("serial")
public class MessagesServiceException extends RuntimeException {

	public MessagesServiceException(String message) {
		super(message);
	}

	public MessagesServiceException(Exception e) {
		super(e);
	}
}
