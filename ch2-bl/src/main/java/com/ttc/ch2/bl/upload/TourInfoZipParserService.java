package com.ttc.ch2.bl.upload;

import java.io.File;
import java.util.List;

import com.ttc.ch2.bl.upload.common.OperationStatus;

public interface TourInfoZipParserService {
	
	/**
	 * @return list of Tourcode used to generate Itinerary raport
	 * */
	

	public List<String> parseZipStream(OperationStatus operationStatus, File file) throws TourInfoZipParserServiceException;
	
}
