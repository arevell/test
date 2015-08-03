package com.ttc.ch2.bl.download.ch1download;

public class Ch1DownloadServiceException extends RuntimeException {

	private static final long serialVersionUID = -1248274754217790534L;

	public Ch1DownloadServiceException() {
		super();
	}

	public Ch1DownloadServiceException(Exception e) {
		super(e);
	}

	public Ch1DownloadServiceException(String message) {
		super(message);
	}

	public Ch1DownloadServiceException(Throwable cause) {
		super(cause);
	}

	public Ch1DownloadServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
