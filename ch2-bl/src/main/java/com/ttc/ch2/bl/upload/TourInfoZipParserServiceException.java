package com.ttc.ch2.bl.upload;

public class TourInfoZipParserServiceException extends Exception {
	
	private boolean loggedInHistory=false;
	
	public TourInfoZipParserServiceException() {
		super();
	}
	
	public TourInfoZipParserServiceException(Exception e) {
		super(e);
	}

	public TourInfoZipParserServiceException(String message) {
		super(message);
	}

	public TourInfoZipParserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public boolean isLoggedInHistory() {
		return loggedInHistory;
	}

	public void setLoggedInHistory(boolean loggedInHistory) {
		this.loggedInHistory = loggedInHistory;
	}
}
