package com.ttc.ch2.quartz.tourdepartureextended;



import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.quartz.executionlisteners.ClearAllJob;
import com.ttc.ch2.quartz.executionlisteners.ScheduleInstancePreparer;
import com.ttc.ch2.scheduler.service.SchedulerForDepExtImportService;
import com.ttc.ch2.scheduler.service.SchedulerForDepExtImportServiceImpl;
import com.ttc.ch2.scheduler.service.WaitHelper;

/**Test configuration quartz jobs and scheduler mode*/
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class,ClearAllJob.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzTestCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class DepartureExtendedSynchronizeJobTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(DepartureExtendedSynchronizeJobTest.class);
	private static String brandCode="BV";
	
	private static String triggerName=null;
	private static String jobName=null;
	
	static{
		//prepare
		try {
			 triggerName=Whitebox.invokeMethod(new SchedulerForDepExtImportServiceImpl(), "getTriggerName",new Object[]	{brandCode});
			 jobName=Whitebox.invokeMethod(new SchedulerForDepExtImportServiceImpl(), "getJobName",new Object[]	{brandCode});
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}			
	}
	
	@Inject
	@Qualifier("schedulerFactoryBean")
	private SchedulerFactoryBean schedulerFactory;
	
	@Inject
	private SchedulerForDepExtImportService schedulerForQuickImportService;
	
	@Inject
	private WaitHelper waitHelper;
	
	// Check coverage exceptions
	@Test
	public void deleteJobTest() throws Exception {		
		//prepare
		Date start=DateUtils.addMinutes(new Date(), 1);		
		QuartzJob job=new QuartzJob();
		job.setBrandCode(brandCode);
		job.setNextFiringTime(start);
		
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		Whitebox.invokeMethod(getTargetObject(schedulerForQuickImportService,SchedulerForDepExtImportServiceImpl.class), "setupJobFromDb",new Object[]{job});
		
		//test
		schedulerLocal.start();
		waitHelper.waitOnStartScheduler();
		
		schedulerLocal.deleteJob(jobName, SchedulerForDepExtImportService.jobGroupName);
		waitHelper.waitOnRefresh();
		schedulerLocal.standby();
		waitHelper.waitOnStopScheduler();
	}
	
	// Check coverage exceptions
	@Test
	public void interuptJobTest() throws Exception {		
		//prepare
		Date start=DateUtils.addMinutes(new Date(), 1);		
		QuartzJob job=new QuartzJob();
		job.setBrandCode(brandCode);
		job.setNextFiringTime(start);
		
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		Whitebox.invokeMethod(getTargetObject(schedulerForQuickImportService,SchedulerForDepExtImportServiceImpl.class), "setupJobFromDb",new Object[]{job});
		showListJobs();
		//test
		schedulerLocal.start();
		waitHelper.waitOnStartScheduler();
		
		showListJobs();
		schedulerLocal.interrupt(jobName, SchedulerForDepExtImportService.jobGroupName);
		waitHelper.waitOnRefresh();
		schedulerLocal.standby();
		waitHelper.waitOnStopScheduler();
	}
	
	
	private void showListJobs() throws SchedulerException{
		Scheduler schedulerLocal =schedulerFactory.getScheduler();
		
		for (String jobGroupName : schedulerLocal.getJobGroupNames()) {
			for (String jobName : schedulerLocal.getJobNames(jobGroupName)) {
				
				JobDetail jd=schedulerLocal.getJobDetail(jobName, jobGroupName);
				List<JobExecutionContext> activeJob=schedulerLocal.getCurrentlyExecutingJobs();
				boolean active=activeJob.size()>0 && activeJob.get(0).getJobDetail().getFullName().equals(jd.getFullName());
				System.out.println("jobGroupName:"+jobGroupName+" jobName:"+jobName+" active:"+active);
			}
		
		}

	}

	// 	
}
