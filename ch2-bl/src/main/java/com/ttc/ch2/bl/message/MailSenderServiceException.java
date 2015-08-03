package com.ttc.ch2.bl.message;

public class MailSenderServiceException extends Exception {

	private static final long serialVersionUID = -422304203061972503L;

	public MailSenderServiceException() {
		super();
	}

	public MailSenderServiceException(Exception e) {
		super(e);
	}

	public MailSenderServiceException(String message) {
		super(message);
	}

	public MailSenderServiceException(Throwable cause) {
		super(cause);
	}

	public MailSenderServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
