package com.ttc.ch2.scheduler.jobs.audit;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.ttc.ch2.common.SpringContext;

public class AuditPurgeJob extends AuditJob {
    private static Logger logger = LoggerFactory.getLogger(AuditPurgeJob.class);
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
        AuditPurger purger = getPurger();
        if (purger == null) {
            logger.info("The {} bean is not present, the purging job was not executed", AuditPurger.class.getName());
            return;
        }
        
        JobDataMap map = context.getMergedJobDataMap();
        int auditMaturityDays = toInt(map, "auditMaturityDays");
        int metricsMaturityDays = toInt(map, "metricsMaturityDays");
        int auditRecordsLimit = toInt(map, "auditRecordsLimit");
        int metricsRecordsLimit = toInt(map, "metricsRecordsLimit");
        
        purger.purge(auditMaturityDays, metricsMaturityDays, auditRecordsLimit, metricsRecordsLimit);
    }

    private AuditPurger getPurger () {
        try {
            return SpringContext.getApplicationContext().getBean(AuditPurger.class);
        } catch (BeansException e) {
        }
        return null;
    }
}
