package com.ttc.ch2.scheduler.common;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.google.common.base.Preconditions;
import com.ttc.ch2.bl.departure.common.JobStatusChecker;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.dao.jobs.QuartzJobDAO;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.scheduler.service.SchedulerForUploadService;


public class UploadStatusChecker implements JobStatusChecker{

	private static final Logger logger = LoggerFactory.getLogger(UploadStatusChecker.class);
	private String brandCode;
	private QuartzJobDAO quartzJobDAO;
	
	public UploadStatusChecker(String brandCode,ApplicationContext ctx) {
		super();
		this.brandCode = brandCode;
//		this.sessionFactory=ctx.getBean("dataSource", SessionFactory.class);
		this.quartzJobDAO = SpringContext.getApplicationContext().getBean(QuartzJobDAO.class);
	}
	
	@Override
	public boolean isCancelProcess() {
		logger.trace("UploadStatusChecker:isCancelProcess-start");
		Preconditions.checkState(StringUtils.isNotEmpty(brandCode) , "UploadStatusChecker->isCancelProcess field brandCode is not initialize");	
		QuartzJob resultJob=getJob();	
		logger.trace("UploadStatusChecker:isCancelProcess-end");
		return JobStatus.Cancelled==resultJob.getJobStatus();
	}
	
	@Override
	public boolean isCancelOrInactiveProcess() {
		logger.trace("UploadStatusChecker:isCancelOrInactiveProcess-start");
		Preconditions.checkState(StringUtils.isNotEmpty(brandCode) , "UploadStatusChecker->isCancelProcess field brandCode is not initialize");				
		QuartzJob resultJob=getJob();
		logger.trace("UploadStatusChecker:isCancelOrInactiveProcess-end");
		return JobStatus.Cancelled==resultJob.getJobStatus() || JobStatus.Inactive==resultJob.getJobStatus();
	}


	@Override
	public JobStatus getJobStatus() {		
		Preconditions.checkState(StringUtils.isNotEmpty(brandCode) , "UploadStatusChecker->isCancelProcess field brandCode is not initialize");		
		return getJob().getJobStatus();
	}
	
	
	private QuartzJob getJob(){
		QuartzJob eJob=new QuartzJob();
		eJob.setJobName(QuartzJob.JobName.UploadTourInfoJob.toString());
		eJob.setBrandCode(brandCode);
		QuartzJob resultJob=quartzJobDAO.findByExample(eJob);
		quartzJobDAO.refresh(resultJob);
		return resultJob;
	}


	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
}
