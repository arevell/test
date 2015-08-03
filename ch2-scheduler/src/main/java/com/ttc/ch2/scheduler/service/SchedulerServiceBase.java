package com.ttc.ch2.scheduler.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ttc.ch2.common.enums.SystemDirection;
import com.ttc.ch2.common.predicates.Ch2ConfigValueFinder;
import com.ttc.ch2.dao.Ch2ConfigValueDAO;
import com.ttc.ch2.domain.Ch2ConfigValue;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.scheduler.common.JobParams;

public abstract class SchedulerServiceBase {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceBase.class);
	
	public static String SCHEDULER_USER="System";
	
	@Inject
	@Qualifier("schedulerFactoryBean")
	protected SchedulerFactoryBean schedulerFactory;
	
	@Inject
	protected QuartzJobCh2Service quartzJobCh2Service;
	
	@Inject
	protected Ch2ConfigValueDAO ch2ConfigValueDAO;
			
	@Inject
	protected ApplicationContext appCtx;
	
	
	
	protected CronTrigger createCronTrigger(String triggerName, String triggerGroupName, String cronExpression) throws SchedulerServiceException{
	    return createCronTrigger(triggerName, triggerGroupName, cronExpression, CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
	}
	
	protected CronTrigger createCronTrigger(String triggerName, String triggerGroupName, String cronExpression, int misfireInstruction) throws SchedulerServiceException{
		logger.trace("SchedulerServiceBase:createCronTrigger-start");
		CronTrigger trigger = new CronTrigger();
//		trigger.setVolatility(true);
		trigger.setGroup(triggerGroupName);
		trigger.setName(triggerName);
		trigger.setMisfireInstruction(misfireInstruction);		
//		trigger.getJobDataMap().put(DepartureSynchronizeJob.JobParams.USER.toString(),user);
		try {
			trigger.setCronExpression(cronExpression);
		} catch (ParseException e) {
			throw new SchedulerServiceException(e);
		}
		logger.trace("SchedulerServiceBase:createCronTrigger-end");
		return trigger;
	}
	
	
	protected SimpleTrigger createSimpleTrigger(String triggerName, String triggerGroupName, Date startTime) throws SchedulerServiceException{
		logger.trace("SchedulerServiceBase:createSimpleTrigger-start");
		SimpleTrigger trigger = new SimpleTrigger();
		trigger.setGroup(triggerGroupName);
		trigger.setName(triggerName);
//		trigger.setVolatility(true);
		trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
		trigger.setStartTime(startTime);
		trigger.setRepeatCount(0);
//		trigger.setRepeatInterval(1000*60*60);
		
//		trigger.getJobDataMap().put(DepartureSynchronizeJob.JobParams.USER.toString(),user);
		
		logger.trace("SchedulerServiceImpl:createSimpleTrigger-end");
		return trigger;
	}
	

	public List<JobVO> getAllJobList() throws SchedulerServiceException{
		
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		List<JobVO>data=Lists.newArrayList();				
		try {
			for (String jobGroupName : schedulerLocal.getJobGroupNames()) {
					for (String jobName : schedulerLocal.getJobNames(jobGroupName)) {					  													
					  Trigger[] triggers = schedulerLocal.getTriggersOfJob(jobName,jobGroupName);
					  Date nextFireTime=null;
					  if(triggers!=null && triggers.length>0){
						  nextFireTime = triggers[0].getNextFireTime();
					  }
					  
					  String brandCode=(String) schedulerLocal.getJobDetail(jobName, jobGroupName).getJobDataMap().get(JobParams.BRAND_CODE.toString());
					  String name=(String) schedulerLocal.getJobDetail(jobName, jobGroupName).getJobDataMap().get(JobParams.JOB_NAME_UI.toString());
					  

					  Preconditions.checkState(StringUtils.isNotBlank(name),String.format("Registred job %s should have extra parameter in context:%s",jobName,JobParams.JOB_NAME_UI.toString()));
					  
					  QuartzJob job=quartzJobCh2Service.findByName(jobNameMapper(jobName),brandCode);					  					   
					  String user=job.getUser()!=null ? job.getUser().getUsername() : "System";					  
					  data.add(new JobVO(job.getUiName(), jobGroupName, nextFireTime,job.getJobStatus().toString(),user,brandCode,false,false));					  
					  logger.debug("[jobName] : " + jobName + " [groupName] : " + jobGroupName + " - " + nextFireTime);					  				 
					}					
			}
		} catch (SchedulerException e) {
			throw new SchedulerServiceException(e);
		}
		logger.trace("SchedulerServiceImpl.getJobList-end");
		return data;									
	}
	
	
	public SchedulerVO getSchedulerData() throws SchedulerServiceException {
		logger.trace("SchedulerServiceImpl.getSchedulerName-start");
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		try {						
			SchedulerVO vo=new SchedulerVO();
			vo.setSchedulerName(schedulerLocal.getSchedulerName());
			vo.setStatus(schedulerLocal.isStarted() ? SchedulerVO.Status.STARTED : SchedulerVO.Status.STOPED);
					
			for (Iterator<Object> iterator = schedulerLocal.getSchedulerListeners().iterator(); iterator.hasNext();) {
				Object listener =  iterator.next();				
				if (listener instanceof StatisticListenerQuartz) {
					StatisticListenerQuartz statisticListenerQuartz = (StatisticListenerQuartz) listener;
					vo.setStartDate(schedulerLocal.isInStandbyMode() ? null : statisticListenerQuartz.getCurrentDateStart());					
				}				
			}
					
			if(schedulerLocal.isInStandbyMode())
				vo.setStatus(SchedulerVO.Status.STAND_BY_MODE);
			
			List<Ch2ConfigValue> cfgs=ch2ConfigValueDAO.findAll();
			Ch2ConfigValue value=Iterables.find(cfgs, new Ch2ConfigValueFinder(Ch2ConfigValue.PropName.SystemDirection));
			vo.setSystemDirection(value.getPropertyValue());
			
			logger.trace("SchedulerServiceImpl.getSchedulerName-end");
			return vo;
		} catch (SchedulerException e) {
			throw new SchedulerServiceException(e);
		}		
	}
	
	
	private String jobNameMapper(String jobNameFromQuartz){
		if(jobNameFromQuartz.contains(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString()))
			return QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString();
		else if(jobNameFromQuartz.contains(QuartzJob.JobName.UploadTourInfoJob.toString()))
			return QuartzJob.JobName.UploadTourInfoJob.toString();
		else if(jobNameFromQuartz.contains(QuartzJob.JobName.DepartureSynchronizeJob.toString()))
			return QuartzJob.JobName.DepartureSynchronizeJob.toString();
		else if(jobNameFromQuartz.contains(QuartzJob.JobName.AuditPurgeJob.toString()))
			return QuartzJob.JobName.AuditPurgeJob.toString();
		else
			throw new IllegalStateException("Scheduler has registred illegal job name:"+jobNameFromQuartz);
		
	}
	
	protected void interruptJob(String brandCode,QuartzJob.JobName jobEnum) throws SchedulerException {
		logger.trace("SchedulerServiceImpl:interruptJob-start");		
		if(isActiveJob(brandCode,jobEnum)){			
			QuartzJob job=quartzJobCh2Service.findByName(jobEnum.toString(),brandCode);
			job.setJobStatus(JobStatus.Cancelled);
			quartzJobCh2Service.saveQuartzJob(job);			
		}
		logger.trace("SchedulerServiceImpl:interruptJob-end");
	}
	
	protected boolean isActiveJob(String brandCode,QuartzJob.JobName jobEnum)
	{
		QuartzJob job=quartzJobCh2Service.findByName(jobEnum.toString(),brandCode);
		return JobStatus.Active==job.getJobStatus();
	}

}
