package com.ttc.ch2.scheduler.service;



public interface SchedulerForUploadService {

	public static String jobGroupName="upload-job-group-ch2";
	public static String triggerGroupName="upload-trigger-group-ch2";
	public static String triggerUploadName="upload-trigger-ch2";	
	public static String JOB_DESC="Upload Tour Info Job Listener %s";
	
	/**Initialize Scheduler*/
	public void init() throws SchedulerServiceException;
}