package com.ttc.ch2.bl.upload;

import com.ttc.ch2.bl.upload.common.OperationStatus;

public class UploadServiceException extends RuntimeException {
	
	OperationStatus operationStatus;
	
	
	public UploadServiceException() {
		super();
	}
	
	public UploadServiceException(Exception e) {
		super(e);
	}

	public UploadServiceException(String message) {
		super(message);
	}

	public UploadServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationStatus getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(OperationStatus operationStatus) {
		this.operationStatus = operationStatus;
	}
}
