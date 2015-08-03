package com.ttc.ch2.scheduler.service;

import java.util.List;

public interface SchedulerService {
	
	/**
	 * Job List used ch2-ui to presents  status of jobs
	 * @throws SchedulerServiceException
	 * */
	public List<JobVO> getAllJobList() throws SchedulerServiceException;
	

	/**
	 * Get Scheduler properties to present on Scheduler view
	 * @throws SchedulerServiceException 
	 * */
	public SchedulerVO getSchedulerData() throws SchedulerServiceException;

}