package com.ttc.ch2.bl.departure;

import com.ttc.ch2.bl.departure.common.OperationStatus;

/**
 * Main operation for synchronize data from tropics
 * */
public interface TropicDepartureMainSynchronizeService {	
	
	public OperationStatus departureSynchronizeOperation(OperationStatus op) throws TropicSynchronizeServiceException;
}