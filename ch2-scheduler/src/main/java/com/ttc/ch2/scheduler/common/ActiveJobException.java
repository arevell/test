package com.ttc.ch2.scheduler.common;

public class ActiveJobException extends RuntimeException {

	public ActiveJobException(Exception e) {
		super(e);
	}
	public ActiveJobException(String msg) {
		super(msg);
	}

}
