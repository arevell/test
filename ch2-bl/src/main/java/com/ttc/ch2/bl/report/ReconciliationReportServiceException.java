package com.ttc.ch2.bl.report;

public class ReconciliationReportServiceException extends Exception {

	private static final long serialVersionUID = 3232737053840885786L;

	public ReconciliationReportServiceException() {
		super();
	}

	public ReconciliationReportServiceException(Exception e) {
		super(e);
	}

	public ReconciliationReportServiceException(String message) {
		super(message);
	}

	public ReconciliationReportServiceException(Throwable cause) {
		super(cause);
	}

	public ReconciliationReportServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
