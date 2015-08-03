package com.ttc.ch2.bl.upload.ch1upload;

import com.ttc.ch2.bl.upload.common.OperationStatus;

public class Ch1UploadServiceException extends RuntimeException {
	
	OperationStatus operationStatus;
	
	
	public Ch1UploadServiceException() {
		super();
	}
	
	public Ch1UploadServiceException(Exception e) {
		super(e);
	}

	public Ch1UploadServiceException(String message) {
		super(message);
	}

	public Ch1UploadServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationStatus getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(OperationStatus operationStatus) {
		this.operationStatus = operationStatus;
	}
}
