package com.ttc.ch2.quartz.executionlisteners;

import org.junit.Assert;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.test.context.TestContext;

import com.ttc.ch2.common.Ch2TestExecutionListener;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.scheduler.service.SchedulerForDepExtImportService;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;

public class InitializeImportDepartureJob extends Ch2TestExecutionListener {	
	
	@Override
	public void beforeTestMethod(TestContext testContext) {
		 try {	
			 
			 Scheduler schedulerLocal=SpringContext.getApplicationContext().getBean("schedulerFactoryBean",Scheduler.class);			 
			 Assert.assertFalse("Scheduler should be work Ram Job Store mode",schedulerLocal.getMetaData().isJobStoreClustered());		

			 for (String  jobGrName : schedulerLocal.getJobGroupNames()) {
				 for (String jobName : schedulerLocal.getJobNames(jobGrName)) {
					 schedulerLocal.deleteJob(jobName, jobGrName);
				 }
			} 
			SpringContext.getApplicationContext().getBean(SchedulerForImportService.class).init();
			SpringContext.getApplicationContext().getBean(SchedulerForDepExtImportService.class).init();
		} catch (BeansException | SchedulerServiceException | SchedulerException e) {
			throw new UnsupportedOperationException(e);
		}	
	}
}