package com.ttc.ch2.bl.departure;

import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TourDepartureData;

public interface TourContentRepositoryService {

	public boolean persistData(OperationStatus opStatus, TourDepartureData tourDepartureData, String brandCode) throws TourContentRepositoryServiceException;

}
