package com.ttc.ch2.scheduler.common;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.jobs.QuartzJobHistory.JobHistoryStatus;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;

public class DepartureSynchronizeJobListener implements JobListener{

	private static final Logger logger = LoggerFactory.getLogger(DepartureSynchronizeJobListener.class);
	
	private QuartzJobCh2Service service;
	
	
	public DepartureSynchronizeJobListener(QuartzJobCh2Service quartzJobCh2Service) {
		this.service=quartzJobCh2Service;
	}

	@Override
	public String getName() {
		return DepartureSynchronizeJobListener.class.getSimpleName();
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		
		logger.trace("DepartureSynchronizeJobListener:jobToBeExecuted-start");
		
		logger.trace("DepartureSynchronizeJobListener:jobToBeExecuted-end");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		logger.trace("DepartureSynchronizeJobListener:jobExecutionVetoed-start");

		
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		logger.trace("DepartureSynchronizeJobListener:jobWasExecuted-start");
		
	}

	public QuartzJobCh2Service getService() {
		return service;
	}

	public void setService(QuartzJobCh2Service service) {
		this.service = service;
	}

}
