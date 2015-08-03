package com.ttc.ch2.bl.report;

import java.util.List;

import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;
import com.ttc.ch2.common.enums.ProcessName;

public interface ItinerarySegmentReportService {

	public String createItinerarySegmentValidationReport(ProcessName processName, String brandCode) throws ItinerarySegmentReportException;


	/**
	 * @deprecated  use {@link #createItinerarySegmentValidationReport(ProcessName, String, List)} 
	 */
	public String createItinerarySegmentValidationReport(ProcessName processName, String brandCode, List<TourInfo> tourInfoList) throws ItinerarySegmentReportException;
	
	
	public String createItinerarySegmentReport(ProcessName processName, String brandCode, List<String> tourCodeListForItineraryRaport) throws ItinerarySegmentReportException;

}
