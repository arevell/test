package com.ttc.ch2.bl.departure.common;

import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;

/**
 *  implemantation this interface is in ch2-scheduler
 * */
public interface JobStatusChecker {

	public boolean isCancelProcess();
	
	public boolean isCancelOrInactiveProcess();
		
	public JobStatus getJobStatus();
	
}
