package com.ttc.ch2.bl.departure.habs;

public class HabsTourDepartureServiceException extends Exception {
	
	private static final long serialVersionUID = -1810565314301539487L;

	public HabsTourDepartureServiceException() {
		super();
	}
	
	public HabsTourDepartureServiceException(Exception e) {
		super(e);
	}

	public HabsTourDepartureServiceException(String message) {
		super(message);
	}

	public HabsTourDepartureServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
