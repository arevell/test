package com.ttc.ch2.quartz.executionlisteners;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.test.context.TestContext;

import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.common.Ch2TestExecutionListener;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.scheduler.service.WaitHelper;

public class ScheduleInstancePreparer extends Ch2TestExecutionListener {	
	@Override
	public void beforeClass(TestContext testContext) {
		 try {
			Scheduler sfb=SpringContext.getApplicationContext().getBean("schedulerFactoryBean",Scheduler.class);			 			
			sfb.standby();
			SpringContext.getApplicationContext().getBean(WaitHelper.class).waitOnStopScheduler();
		} catch (BeansException | SchedulerException e) {
			throw new UnsupportedOperationException(e);
		}	
	}	
}