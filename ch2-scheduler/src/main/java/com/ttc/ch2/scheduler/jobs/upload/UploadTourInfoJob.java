package com.ttc.ch2.scheduler.jobs.upload;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.google.common.base.Preconditions;
import com.ttc.ch2.bl.upload.UploadTourInfoBatchService;
import com.ttc.ch2.bl.upload.common.JobExecutor;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.scheduler.jobs.departuresynch.DepartureSynchronizeJob.JobParams;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.scheduler.service.SchedulerForUploadService;

public class UploadTourInfoJob extends QuartzJobBean{

	private static final Logger logger = LoggerFactory.getLogger(UploadTourInfoJob.class);
		
	private String brandCode;
	@Override
	protected void executeInternal(JobExecutionContext ctx)	throws JobExecutionException {
		logger.trace("UploadTourInfoJob:executeInternal-start");

		
		try{
		 brandCode=(String) ctx.getJobDetail().getJobDataMap().get(JobParams.BRAND_CODE.toString());
		 Preconditions.checkState(StringUtils.isNotBlank(brandCode),"UploadTourInfoJob->executeInternal param brandCode is required");		 		 
		 UploadTourInfoBatchService service  = SpringContext.getApplicationContext().getBean(UploadTourInfoBatchService.class);
		 service.invokeProcess(brandCode,new StatusJobManager(SchedulerForUploadService.jobUploadName, brandCode));
		}
		catch(Exception e){
			logger.error("",e);
		}		
		logger.trace("UploadTourInfoJob:executeInternal-end");		
	}
	
	class StatusJobManager implements JobExecutor{
		
		private QuartzJobCh2Service quartzJobCh2Service;
		private QuartzJob job;
		
		
		StatusJobManager(String jobName,String brandCode){
			this.quartzJobCh2Service  = SpringContext.getApplicationContext().getBean(QuartzJobCh2Service.class);
			this.job=quartzJobCh2Service.findByName(jobName, brandCode);
		}

		@Override
		public void init() {		
			job.setJobStatus(JobStatus.Active);
			quartzJobCh2Service.saveQuartzJob(job);
		}

		@Override
		public void release() {
			job.setJobStatus(JobStatus.Inactive);
			quartzJobCh2Service.saveQuartzJob(job);			
		}

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
