package com.ttc.ch2.bl.departure;

import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.domain.Brand;

public interface TropicDepartureSynchronizeService {

	public OperationStatus departureSynchronize(OperationStatus opStatus) throws TropicSynchronizeServiceException;

	public void operationForBrand(OperationStatus opStatus, Brand brand) throws TourDepartureServiceException, TropicSynchronizeServiceException;
}
