package com.ttc.ch2.scheduler.service;

import java.util.Date;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.ttc.ch2.common.enums.CronExpresion;

public interface SchedulerForImportService {	
	public static String jobGroupName="import-job-group-ch2";										
	public static String jobImportName="DepartureSynchronizeJob";
	public static String triggerGroupName="import-trigger-group-ch2";
	public static String triggerImportName="import-trigger-ch2";

	
	public static String SCHEDULER_USER="System";
	
	public static String JOB_DESC="Departure Import Job %s";
	
	/**
	 * Job List used ch2-ui to presents  status of jobs
	 * @throws SchedulerServiceException
	 * */
	List<JobVO> getJobList() throws SchedulerServiceException;

	/**
	 * Get Scheduler properties to present on Scheduler view
	 * @throws SchedulerServiceException 
	 * */
	SchedulerVO getSchedulerData() throws SchedulerServiceException;
		
	/**
	 * Method to change time of Job - presumption in system is only one job 
	 * @throws SchedulerServiceException 
	 * */
	void changeJobTime(Date date,String brandCode)  throws SchedulerServiceException;
	
//	public void setupCronJob() throws SchedulerServiceException;
	public void setupCronJob(boolean interrupt,String brandCode) throws SchedulerServiceException;
	
	public void resetStateOfProcessUplodImport(String brandCode) throws SchedulerServiceException;
	public void stopJob(final String brandCode) throws SchedulerServiceException;
		
	/**Initialize Scheduler*/
	public void init() throws SchedulerServiceException;
	
	
	public boolean stopJobAllowedOnly(String brandCode) throws SchedulerException;	
	
	/**
	 * @return true or false  - this process need or need not  reset semafor and status 
	 * */
	public boolean checkResetForUpload(String brandCode);
	/**
	 * @return true or false  - this process need or need not  reset semafor and status 
	 * */
	public boolean checkResetForImport(String brandCode);
	
	public boolean resetAllowed(String brandCode) throws SchedulerServiceException;
	
	public boolean resetNeed(String brandCode);
	
	public void setupNewCronExpression(CronExpresion expresion) throws SchedulerServiceException;
	
	public CronExpresion setupInconsistentCronExpresionIfNecessary() throws SchedulerServiceException;
}