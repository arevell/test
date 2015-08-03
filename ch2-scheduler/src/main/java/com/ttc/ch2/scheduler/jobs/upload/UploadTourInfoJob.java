package com.ttc.ch2.scheduler.jobs.upload;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.google.common.base.Preconditions;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.scheduler.common.JobParams;
import com.ttc.ch2.scheduler.service.upload.UploadTourInfoBatchService;

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
		 service.invokeProcess(brandCode);
		}
		catch(Exception e){
			logger.error("",e);
		}		
		logger.trace("UploadTourInfoJob:executeInternal-end");		
	}
}
