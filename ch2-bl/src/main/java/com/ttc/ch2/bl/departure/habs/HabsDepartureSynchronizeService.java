package com.ttc.ch2.bl.departure.habs;

import com.ttc.ch2.bl.departure.TropicSynchronizeServiceException;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.domain.Brand;

public interface HabsDepartureSynchronizeService {

	public OperationStatus departureSynchronize(OperationStatus opStatus) throws TropicSynchronizeServiceException;

	public void operationForBrand(OperationStatus opStatus, Brand brand) throws HabsTourDepartureServiceException, TropicSynchronizeServiceException;
}
