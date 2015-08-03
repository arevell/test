package com.ttc.ch2.bl.departure;

public class TourDepartureServiceException extends Exception {
	
	private static final long serialVersionUID = -1810565314301539487L;

	public TourDepartureServiceException() {
		super();
	}
	
	public TourDepartureServiceException(Exception e) {
		super(e);
	}

	public TourDepartureServiceException(String message) {
		super(message);
	}

	public TourDepartureServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
