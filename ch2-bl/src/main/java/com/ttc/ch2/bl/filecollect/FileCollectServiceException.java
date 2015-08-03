package com.ttc.ch2.bl.filecollect;

public class FileCollectServiceException extends Exception {

	private static final long serialVersionUID = 5297260342092989357L;

	public FileCollectServiceException() {
		super();
	}

	public FileCollectServiceException(Exception e) {
		super(e);
	}

	public FileCollectServiceException(String message) {
		super(message);
	}

	public FileCollectServiceException(Throwable cause) {
		super(cause);
	}

	public FileCollectServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
