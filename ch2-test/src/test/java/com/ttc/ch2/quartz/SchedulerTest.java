package com.ttc.ch2.quartz;

import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.common.AuthenticatedExecutionPreparer;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.quartz.executionlisteners.InitializeImportDepartureJob;
import com.ttc.ch2.quartz.executionlisteners.ScheduleInstancePreparer;
import com.ttc.ch2.scheduler.service.SchedulerForImportServiceImpl;
import com.ttc.ch2.scheduler.service.WaitHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class,InitializeImportDepartureJob.class,AuthenticatedExecutionPreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzTestCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class SchedulerTest extends BaseTest {

	public static String brandCode="BV";
		
	private static String triggerName=null;
	private static String jobName=null;
	
	static{
		//prepare
		try {
			 triggerName=Whitebox.invokeMethod(new SchedulerForImportServiceImpl(), "getImportTriggerName",new Object[]	{brandCode});
			 jobName=Whitebox.invokeMethod(new SchedulerForImportServiceImpl(), "getImportJobName",new Object[]	{brandCode});
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}			
	}
	
	@Inject
	@Qualifier("schedulerFactoryBean")
	private SchedulerFactoryBean schedulerFactory;
	
	@Inject
	private WaitHelper waitHelper;
			
	@Test
	public void changeTrigerSchedulerStop() throws Exception{

		Date start=DateUtils.addMinutes(new Date(), 2);
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		Trigger trigger=Whitebox.invokeMethod(new SchedulerForImportServiceImpl(), "createSimpleTrigger",new Object[]
				{triggerName, SchedulerForImportServiceImpl.triggerGroupName,start,brandCode});
		trigger.setJobName(jobName);
		trigger.setJobGroup(SchedulerForImportServiceImpl.jobGroupName);
		
		//test
		schedulerLocal.rescheduleJob(triggerName, SchedulerForImportServiceImpl.triggerGroupName, trigger);
		//check
		Assert.assertNotNull(schedulerLocal.getTrigger(triggerName, SchedulerForImportServiceImpl.triggerGroupName));
		Assert.assertTrue(schedulerLocal.getTrigger(triggerName, SchedulerForImportServiceImpl.triggerGroupName).getStartTime().getTime()==start.getTime());
	}
	
	@Test
	public void changeTrigerSchedulerStart() throws Exception{
		
		Date start=DateUtils.addMinutes(new Date(), 2);
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		try{
		Trigger trigger=schedulerLocal.getTrigger(triggerName, SchedulerForImportServiceImpl.triggerGroupName);
		Date startTimeBeforeChange=trigger.getStartTime();
		schedulerFactory.start();
		waitHelper.waitOnStartScheduler();		
		Trigger newTrigger=Whitebox.invokeMethod(new SchedulerForImportServiceImpl(), "createSimpleTrigger",new Object[]{
				triggerName, SchedulerForImportServiceImpl.triggerGroupName,start,brandCode});
		newTrigger.setJobName(jobName);
		newTrigger.setJobGroup(SchedulerForImportServiceImpl.jobGroupName);
		
		//test
		schedulerLocal.rescheduleJob(triggerName, SchedulerForImportServiceImpl.triggerGroupName, newTrigger);
		//check
		Assert.assertNotNull(schedulerLocal.getTrigger(triggerName, SchedulerForImportServiceImpl.triggerGroupName));
		
		Date startTimeAfterChange=schedulerLocal.getTrigger(triggerName, SchedulerForImportServiceImpl.triggerGroupName).getStartTime();		
		Assert.assertTrue(startTimeBeforeChange.getTime()!=startTimeAfterChange.getTime());
		}
		finally{
			schedulerFactory.stop();
			waitHelper.waitOnStopScheduler();	
		}
	}
	
	@Test
	public void changeTrigerJobActive() throws Exception{
		
		Date start=DateUtils.addMinutes(new Date(), 1);
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		try{
			
		Date startTimeBeforeChange=schedulerLocal.getTrigger(triggerName, SchedulerForImportServiceImpl.triggerGroupName).getStartTime();
		schedulerFactory.start();
		waitHelper.waitOnStartScheduler();		
		Trigger trigger=Whitebox.invokeMethod(new SchedulerForImportServiceImpl(), "createSimpleTrigger",new Object[]
				{triggerName, SchedulerForImportServiceImpl.triggerGroupName,start,brandCode});
		trigger.setJobName(jobName);
		trigger.setJobGroup(SchedulerForImportServiceImpl.jobGroupName);
		
		//test		
		schedulerLocal.rescheduleJob(triggerName, SchedulerForImportServiceImpl.triggerGroupName, trigger);
		Thread.sleep(15000);
		//check
		Assert.assertNotNull(schedulerLocal.getTrigger(triggerName, SchedulerForImportServiceImpl.triggerGroupName));
		Date startTimeAfterChange=schedulerLocal.getTrigger(triggerName, SchedulerForImportServiceImpl.triggerGroupName).getStartTime();		
		Assert.assertTrue(startTimeBeforeChange.getTime()!=startTimeAfterChange.getTime());
		}
		finally{
			schedulerFactory.stop();
			waitHelper.waitOnStopScheduler();	
		}
	}		
}
