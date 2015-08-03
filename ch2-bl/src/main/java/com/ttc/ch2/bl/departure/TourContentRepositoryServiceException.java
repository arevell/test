package com.ttc.ch2.bl.departure;

public class TourContentRepositoryServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -6104329110949556571L;

	public TourContentRepositoryServiceException() {
		super();
	}
	
	public TourContentRepositoryServiceException(Exception e) {
		super(e);
	}

	public TourContentRepositoryServiceException(String message) {
		super(message);
	}

	public TourContentRepositoryServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
