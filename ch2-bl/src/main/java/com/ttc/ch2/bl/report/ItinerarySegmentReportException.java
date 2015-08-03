package com.ttc.ch2.bl.report;

public class ItinerarySegmentReportException extends Exception {

	private static final long serialVersionUID = -7097348938770934300L;

	public ItinerarySegmentReportException() {
		super();
	}

	public ItinerarySegmentReportException(Exception e) {
		super(e);
	}

	public ItinerarySegmentReportException(String message) {
		super(message);
	}

	public ItinerarySegmentReportException(Throwable cause) {
		super(cause);
	}

	public ItinerarySegmentReportException(String message, Throwable cause) {
		super(message, cause);
	}
}
