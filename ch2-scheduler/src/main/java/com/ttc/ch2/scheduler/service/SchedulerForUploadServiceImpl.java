package com.ttc.ch2.scheduler.service;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.stereotype.Service;

import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.scheduler.common.JobParams;

@Service
public class SchedulerForUploadServiceImpl extends SchedulerServiceBase implements SchedulerForUploadService{

	private static final Logger logger = LoggerFactory.getLogger(SchedulerForUploadServiceImpl.class);
	
	public void init() throws SchedulerServiceException{
		logger.trace("SchedulerForUploadServiceImpl:init-start");		
		try {		
			logger.debug("SchedulerForUploadServiceImpl.init- configure job and trigger");
			 setupJobFromDbForUpload();
		} catch (SchedulerException e) {
			throw new SchedulerServiceException(e);
		}
		logger.trace("SchedulerForUploadServiceImpl:init-end");
	}	
	
	
	private void setupJobFromDbForUpload() throws SchedulerException, SchedulerServiceException{
		logger.trace("SchedulerForUploadServiceImpl:setupJobFromDbForUpload-start");
		for (QuartzJob job : quartzJobCh2Service.getJobsByGroupName(SchedulerForUploadService.jobGroupName)) {
			setupJobFromDbForUpload(job);			
		}
		logger.trace("SchedulerForUploadServiceImpl:setupJobFromDbForUpload-end");
	}
	
	private void setupJobFromDbForUpload(QuartzJob job) throws SchedulerException, SchedulerServiceException{
		logger.trace("SchedulerForUploadServiceImpl:setupJobFromDbForUpload-start");
			Scheduler schedulerLocal=schedulerFactory.getScheduler();
			String brandCode=job.getBrandCode();
			JobDetail jobDetail=schedulerLocal.getJobDetail(getUploadJobName(brandCode), jobGroupName);
			if(jobDetail==null){
				JobDetailBean jobDetails= appCtx.getBean(QuartzJob.JobName.UploadTourInfoJob.toString(),JobDetailBean.class);
				jobDetails.setName(getUploadJobName(brandCode));	
				jobDetails.getJobDataMap().put(JobParams.BRAND_CODE.toString(), brandCode);
				jobDetails.getJobDataMap().put(JobParams.JOB_NAME_UI.toString(), String.format(JOB_DESC, brandCode));
				jobDetails.setRequestsRecovery(false);
				jobDetails.setDurability(true);	
				jobDetails.setGroup(jobGroupName);								
				Trigger trigger=createCronTrigger(getUploadTriggerName(brandCode),triggerGroupName,job.getCronExpresion());
				trigger.setPriority(2);
				schedulerLocal.scheduleJob(jobDetails,trigger);
			}		
		logger.trace("SchedulerForUploadServiceImpl:setupJobFromDbForUpload-end");
	}

	private String getUploadJobName(String brandCode){
		return QuartzJob.JobName.UploadTourInfoJob.toString()+"_"+brandCode;
	}
	
	private String getUploadTriggerName(String brandCode){
		return triggerUploadName+"_"+brandCode;
	}
}
