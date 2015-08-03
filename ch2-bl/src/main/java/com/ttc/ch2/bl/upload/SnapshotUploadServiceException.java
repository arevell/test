package com.ttc.ch2.bl.upload;

public class SnapshotUploadServiceException extends RuntimeException {
	
	public SnapshotUploadServiceException() {
		super();
	}
	
	public SnapshotUploadServiceException(Exception e) {
		super(e);
	}

	public SnapshotUploadServiceException(String message) {
		super(message);
	}

	public SnapshotUploadServiceException(String message, Throwable cause) {
		super(message, cause);
	}	
}
