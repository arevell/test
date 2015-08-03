package com.ttc.ch2.scheduler.service;

public class QuartzJobCh2ServiceException extends RuntimeException {
	
	public QuartzJobCh2ServiceException() {
		super();
	}
	
	public QuartzJobCh2ServiceException(Exception e) {
		super(e);
	}

	public QuartzJobCh2ServiceException(String message) {
		super(message);
	}

	public QuartzJobCh2ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
