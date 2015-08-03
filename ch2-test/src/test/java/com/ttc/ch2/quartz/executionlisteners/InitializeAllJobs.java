package com.ttc.ch2.quartz.executionlisteners;

import org.springframework.beans.BeansException;
import org.springframework.test.context.TestContext;

import com.ttc.ch2.common.Ch2TestExecutionListener;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerForDepExtImportService;
import com.ttc.ch2.scheduler.service.SchedulerForUploadService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;

public class InitializeAllJobs extends Ch2TestExecutionListener {	
	@Override
	public void beforeClass(TestContext testContext) {
		 try {			 			 			 
			SpringContext.getApplicationContext().getBean(SchedulerForImportService.class).init();
			SpringContext.getApplicationContext().getBean(SchedulerForDepExtImportService.class).init();
			SpringContext.getApplicationContext().getBean(SchedulerForUploadService.class).init();
		} catch (BeansException | SchedulerServiceException e) {
			throw new UnsupportedOperationException(e);
		}	
	}	
}