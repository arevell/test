package com.ttc.ch2.bl.departure;

import com.ttc.ch2.bl.departure.common.OperationStatus;


public class TropicSynchronizeServiceException extends Exception {

	private static final long serialVersionUID = -8238779367038299148L;
	
	private OperationStatus opStatus;
	
	public TropicSynchronizeServiceException() {
		super();
	}
	
	public TropicSynchronizeServiceException(Exception e) {
		super(e);
	}
	
	public TropicSynchronizeServiceException(Exception e,OperationStatus opStatus) {
		super(e);
		setOpStatus(opStatus);
	}

	public TropicSynchronizeServiceException(String message) {
		super(message);
	}

	public TropicSynchronizeServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationStatus getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(OperationStatus opStatus) {
		this.opStatus = opStatus;
	}
}
