package com.ttc.ch2.common;

import com.ttc.ch2.bl.upload.common.OperationStatus;

public class StopBatchException extends RuntimeException {
	
	public StopBatchException() {
		super();
	}
	
	public StopBatchException(Exception e) {
		super(e);
	}

	public StopBatchException(String message) {
		super(message);
	}

	public StopBatchException(String message, Throwable cause) {
		super(message, cause);
	}
}
