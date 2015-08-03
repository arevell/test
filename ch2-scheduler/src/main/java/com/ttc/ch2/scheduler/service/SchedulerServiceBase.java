package com.ttc.ch2.scheduler.service;

import java.text.ParseException;
import java.util.Date;

import javax.inject.Inject;

import org.quartz.CronTrigger;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public abstract class SchedulerServiceBase {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceBase.class);
	
	@Inject
	@Qualifier("schedulerFactoryBean")
	protected SchedulerFactoryBean schedulerFactory;
	
	@Inject
	protected QuartzJobCh2Service quartzJobCh2Service;
			
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

}
