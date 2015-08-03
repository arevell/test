package com.ttc.ch2.scheduler.service;

import java.util.Date;
import java.util.Map;

import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.domain.RecReportData;
import com.ttc.ch2.search.export.IndexSynchronizerVO;




public interface SchedulerForDepExtImportService {
		
	public static String jobGroupName="import-extended-job-group-ch2";
	public static String triggerGroupName="import-extended-trigger-group-ch2";
	public static String triggerName="import-extended-trigger-ch2";
	
	
	
	public static String JOB_DESC="Departure Extended Job %s";
	
	/**Initialize Scheduler*/
	public void init() throws SchedulerServiceException;
	
	public void setupCronJob(final boolean interrupt, final String brandCode) throws SchedulerServiceException;
	
	public void insertToContextData(String brandCode,IndexSynchronizerVO indexSynchronizerVO,FileCollectVO fileCollectVO);
	
	public void setupRecReportData(String brandCode,IndexSynchronizerVO indexSynchronizerVO,FileCollectVO fileCollectVO);
	
	
	public void clearRecReportData(String brandCode);
	
	public void clearRecReportData(String brandCode,RecReportData.Type type);
	
	public Map<Date, IndexSynchronizerVO> getIndexSynchronizerVOs(String brandCode);
		
	public Map<Date, FileCollectVO> getFileCollectVOs(String brandCode);
	
}


