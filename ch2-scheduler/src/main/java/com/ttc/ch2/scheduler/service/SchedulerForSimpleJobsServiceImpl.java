package com.ttc.ch2.scheduler.service;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.stereotype.Service;

import com.ttc.ch2.domain.jobs.QuartzJob;

@Service
public class SchedulerForSimpleJobsServiceImpl extends SchedulerServiceBase  implements SchedulerForSimpleJobsService {

    private static Logger logger = LoggerFactory.getLogger(SchedulerForSimpleJobsServiceImpl.class); 
    
    
    @Override
    public void init() throws SchedulerServiceException {

        logger.trace("initialize.init-start");
        try {       
            logger.debug("configure job and trigger for {} group", SIMPLE_GROUP);
             initialize();
        } catch (SchedulerException e) {
            throw new SchedulerServiceException(e);
        }
        logger.trace("SchedulerServiceImpl.init-end");
    }

     protected void initialize() throws SchedulerException, SchedulerServiceException{
        logger.trace("initialize-start");
        for (QuartzJob job : quartzJobCh2Service.getJobsByGroupName(SIMPLE_GROUP)) {
            try {
                initializeJob(job);
            } catch (Exception ex) {
                logger.error("Failed job initialize", ex);
            }
        }
        logger.trace("initialize-end");
    }
    
    protected void initializeJob(QuartzJob job) throws SchedulerException, SchedulerServiceException{
        logger.trace("initializeJob-start");
        
        Scheduler schedulerLocal=schedulerFactory.getScheduler();
        String jobName = job.getJobName();
        JobDetail detail = schedulerLocal.getJobDetail(jobName, SIMPLE_GROUP);
        if (detail == null) {
            Object bean = appCtx.getBean(jobName);
            if (bean instanceof JobDetailBean) {
                JobDetailBean jobDetails = (JobDetailBean) bean;
                jobDetails.setName(jobName);
                jobDetails.setGroup(SIMPLE_GROUP);
                String cronExpression = job.getCronExpresion();
                CronTrigger trigger = createCronTrigger(jobName + "-trigger", SIMPLE_GROUP + "-trigger", cronExpression/*, CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW*/);
                trigger.setPriority(7);
                schedulerLocal.scheduleJob(jobDetails, trigger);
            }
        }
        logger.trace("initializeJob-end");
    }
}
