package com.ttc.ch2.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.test.context.TestContext;

import com.ttc.ch2.common.Ch2TestExecutionListener;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;
import com.ttc.ch2.scheduler.service.WaitHelper;

public class ScheduleInstancePreparer extends Ch2TestExecutionListener {	
	@Override
	public void beforeClass(TestContext testContext) {
		 try {			 
			SpringContext.getApplicationContext().getBean("schedulerFactoryBean",Scheduler.class).standby();
			SpringContext.getApplicationContext().getBean(WaitHelper.class).waitOnStopScheduler();
			SpringContext.getApplicationContext().getBean(SchedulerForImportService.class).init();
		} catch (BeansException | SchedulerServiceException | SchedulerException e) {
			throw new UnsupportedOperationException(e);
		}	
	}	
}