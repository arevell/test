package com.ttc.ch2.bl.departure;

import com.ttc.ch2.bl.departure.common.OperationStatus;

public interface TropicExtendedDepartureSynchronizeService {
	
	public OperationStatus departureSynchronizeOperation(OperationStatus op) throws TropicSynchronizeServiceException;
	
	public void executeOperation(OperationStatus opStatus) throws TropicSynchronizeServiceException;
}
