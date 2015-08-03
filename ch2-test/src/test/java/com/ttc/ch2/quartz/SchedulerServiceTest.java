package com.ttc.ch2.quartz;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.time.DateUtils;
import org.elasticsearch.common.collect.Sets;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.ttc.ch2.common.AuthenticatedExecutionPreparer;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.scheduler.service.JobVO;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerForImportServiceImpl;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;
import com.ttc.ch2.scheduler.service.SchedulerVO;

@Ignore // test need enviroment with quartz turn off 
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class,AuthenticatedExecutionPreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzTestCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class SchedulerServiceTest extends BaseTest {

	public static String brandCode="BV";
	public static String jobName="DepartureSynchronizeJob_"+brandCode;
	
	
	
	@Inject
	@Qualifier("schedulerFactoryBean")
	private SchedulerFactoryBean schedulerFactory;
	
	@Inject
	private SchedulerForImportService schedulerServiceToTest; 
	
	@Inject
	private QuartzJobCh2Service quartzJobCh2Service;
	
	@Inject 
	private  BrandDAO brandDAO;
	
	@Test
	public void positiveGetJobList() throws SchedulerServiceException, SchedulerException
	{		
		//prevalidation
		checkSchedulerConfigure();
		//prepare
		Set<String> results=Sets.newHashSet();
		for (Brand b : brandDAO.findAll()) {
			results.add(String.format(SchedulerForImportService.JOB_DESC,b.getCode()));
		}
		//test
		List<JobVO>list=schedulerServiceToTest.getJobList();
		//check
		Assert.assertTrue(list.size()==4);
		for (JobVO jobVO : list) {			
			Assert.assertTrue(results.contains(jobVO.getJobName()));	
		}
		
		
	}
	
	@Test
	public void positiveGetSchedulerData() throws SchedulerServiceException, SchedulerException
	{		
		//prevalidation
		checkSchedulerConfigure();
		//test
		SchedulerVO schedulerVO=schedulerServiceToTest.getSchedulerData();
		//check
		Assert.assertNotNull(schedulerVO);
	}
	
	@Test
	@Transactional
	public void positiveSetupCronJob() throws SchedulerServiceException, SchedulerException
	{		
		//prevalidation
		checkSchedulerConfigure();		
		Assert.assertFalse(schedulerFactory.isRunning());		
		//test
		schedulerServiceToTest.setupCronJob(false,brandCode);
		//check
		Trigger[] triggers=getTriggersForJob();
		Assert.assertTrue(triggers.length==1);
		Assert.assertTrue((triggers[0] instanceof CronTrigger));					
	}	
	
	@Test
	@Transactional
	public void positiveChangeJobTime() throws SchedulerServiceException, SchedulerException
	{		
		//prevalidation
		checkSchedulerConfigure();
		Assert.assertFalse(schedulerFactory.isRunning());
		
		//prepare
		Date time=DateUtils.addDays(new Date(),2);
		//test
		schedulerServiceToTest.changeJobTime(time,brandCode);
		//check
		Trigger[] triggers=getTriggersForJob();
		Assert.assertTrue(triggers.length==1);
		Assert.assertTrue((triggers[0] instanceof SimpleTrigger));
		Assert.assertTrue(triggers[0].getNextFireTime().getTime()==time.getTime());
		Assert.assertNotNull(quartzJobCh2Service.findByName(SchedulerForImportServiceImpl.jobImportName,brandCode).getNextFiringTime());
		Assert.assertTrue(quartzJobCh2Service.findByName(SchedulerForImportServiceImpl.jobImportName,brandCode).getNextFiringTime().getTime()==time.getTime());	
	}
	
	@Test
	public void negativeChangeJobTimeNull() throws SchedulerServiceException, SchedulerException
	{		
		//prevalidation
		checkSchedulerConfigure();
		Assert.assertFalse(schedulerFactory.isRunning());
		//prepare
		boolean exceptionExist=false;
		//test
		try{
			schedulerServiceToTest.changeJobTime(null,null);
		}catch(SchedulerServiceException e){					
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().startsWith("Start time of job is null"));
		}
		//check
	Assert.assertTrue("Test should have throw Exception",exceptionExist);
	}
	@Test
	public void negativeChangeJobTimePreviews() throws SchedulerServiceException, SchedulerException
	{		
		//prevalidation
		checkSchedulerConfigure();
		Assert.assertFalse(schedulerFactory.isRunning());
		//prepare
		boolean exceptionExist=false;
		//test
		try{
			schedulerServiceToTest.changeJobTime(DateUtils.addDays(new Date(), -2),null);
		}catch(SchedulerServiceException e){					
			exceptionExist=true;
			Assert.assertTrue(e.getMessage().startsWith("Start time of job is set in the past. Please correct it."));
		}
		//check
		Assert.assertTrue("Test should have throw Exception",exceptionExist);			
	}
	
	
	private void checkSchedulerConfigure() throws SchedulerException
	{
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		
		int countJobs=0;
		for (String groupName : schedulerLocal.getJobGroupNames()) {				 
			for (String jobName : schedulerLocal.getJobNames(groupName)) {				 						
				countJobs++;
			}
		}
		Assert.assertTrue("In system should by 4 job", countJobs==4);
	}
	
	private JobDetail getJob() throws SchedulerException
	{
		JobDetail job=null;
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		for (String groupName : schedulerLocal.getJobGroupNames()) {				 
			for (String jobName : schedulerLocal.getJobNames(groupName)) {				 		
				
				if (SchedulerServiceTest.jobName.equals(jobName)) {						
					job=schedulerLocal.getJobDetail(jobName, groupName);
				}
			}
		}
		Preconditions.checkState(job!=null, "cant find Job details for job name:"+(SchedulerServiceTest.jobName));
		return job;
	}
	
	private Trigger[] getTriggersForJob() throws SchedulerException
	{
		Trigger[] triggers=null;
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		for (String groupName : schedulerLocal.getJobGroupNames()) {				 
			for (String jobName : schedulerLocal.getJobNames(groupName)) {				 		
				
				if (SchedulerServiceTest.jobName.equals(jobName)) {
					triggers = schedulerLocal.getTriggersOfJob(jobName,groupName);								
				}
			}
		}
		Preconditions.checkState(triggers!=null, "cant find Job details for job name:"+(SchedulerServiceTest.jobName));
		return triggers;
	}
	
	private void removeAllJob() throws SchedulerException
	{
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		for (String groupName : schedulerLocal.getJobGroupNames()) {				 
			for (String jobName : schedulerLocal.getJobNames(groupName)) {				 						
				JobDetail jobDetails=schedulerLocal.getJobDetail(jobName, groupName);
				schedulerLocal.interrupt(jobDetails.getName(), jobDetails.getGroup());
				schedulerLocal.deleteJob(jobDetails.getName(), jobDetails.getGroup());
			}
		}
	}
	
	
}
