package com.ttc.ch2.quartz;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.quartz.executionlisteners.InitializeAllJobs;
import com.ttc.ch2.quartz.executionlisteners.ScheduleInstancePreparer;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerForImportServiceImpl;
import com.ttc.ch2.scheduler.service.SchedulerForDepExtImportService;
import com.ttc.ch2.scheduler.service.SchedulerForUploadService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;

/**Test configuration quartz jobs and scheduler mode*/
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class,InitializeAllJobs.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class InitializeQuartzTest extends BaseTest {

	@Inject
	@Qualifier("schedulerFactoryBean")
	private SchedulerFactoryBean schedulerFactory;
	
	@Inject
	private BrandDAO brandDAO; 
	
	
	@Test
	public void registredJobTest() throws SchedulerServiceException, SchedulerException {		
		JobDetail jobdetail=null;
		for (Brand brand : brandDAO.findAll()) {
			jobdetail=schedulerFactory.getScheduler().getJobDetail(QuartzJob.JobName.DepartureSynchronizeJob.toString()+"_"+brand.getCode(), SchedulerForImportServiceImpl.jobGroupName);		
			Assert.assertNotNull(QuartzJob.JobName.DepartureSynchronizeJob.toString()+"_"+brand.getCode()+" not registred in quartz",jobdetail);
			
			jobdetail=schedulerFactory.getScheduler().getJobDetail(QuartzJob.JobName.UploadTourInfoJob.toString()+"_"+brand.getCode(), SchedulerForUploadService.jobGroupName);		
			Assert.assertNotNull(QuartzJob.JobName.UploadTourInfoJob.toString()+"_"+brand.getCode()+" not registred in quartz",jobdetail);
			
			jobdetail=schedulerFactory.getScheduler().getJobDetail(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString()+"_"+brand.getCode(), SchedulerForDepExtImportService.jobGroupName);		
			Assert.assertNotNull(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString()+"_"+brand.getCode()+" not registred in quartz",jobdetail);
		}
	}
	
	@Test
	public void schedulerModeTest() throws SchedulerServiceException, SchedulerException {
		Assert.assertTrue("Scheduler should be work as Cluster Job Store mode",schedulerFactory.getScheduler().getMetaData().isJobStoreClustered());		
		Assert.assertTrue("Scheduler should be work as Job Store mode",LocalDataSourceJobStore.class.equals(schedulerFactory.getScheduler().getMetaData().getJobStoreClass()));
		
	}
	
}
