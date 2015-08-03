package com.ttc.ch2.scheduler.service;


import static org.mockito.Matchers.any;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.elasticsearch.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.departure.ImportStatusDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.departure.ImportStatus;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.domain.upload.UploadStatus;

@RunWith(PowerMockRunner.class)   
@PrepareForTest(SchedulerForImportServiceImpl.class)
public class StopJobConditionTest {

	private SchedulerForImportService schedulerServiceToTest=null;
	
	private Scheduler mockScheduler;
	
	private LockBrandService mockLockBrandService;
	
	private JobExecutionContext mockJobExecutionContext;
	
	private JobDetail mockJobDetail;
	
	private QuartzJobCh2Service mockQuartzJobCh2Service;
	
	private ImportStatusDAO mockImportStatusDAO;
	
	private BrandDAO mockBrandDAO;
	
	@Before
	public void init() throws SchedulerException{
	
           SchedulerFactoryBean mockScedulerFactory=PowerMockito.mock(SchedulerFactoryBean.class);
		   mockScheduler=PowerMockito.mock(Scheduler.class);		
		   mockLockBrandService=PowerMockito.mock(LockBrandService.class);		
		   mockJobExecutionContext=PowerMockito.mock(JobExecutionContext.class);		
		   mockJobDetail=PowerMockito.mock(JobDetail.class);
		   mockQuartzJobCh2Service=PowerMockito.mock(QuartzJobCh2Service.class);
		   mockImportStatusDAO=PowerMockito.mock(ImportStatusDAO.class);
		   mockBrandDAO=PowerMockito.mock(BrandDAO.class);
		  
		   PowerMockito.when(mockScedulerFactory.getScheduler()).thenReturn(mockScheduler);		   
		   		   
		   SchedulerForImportService spyElement=new SchedulerForImportServiceImpl();
		   schedulerServiceToTest = PowerMockito.spy(spyElement);
		   Whitebox.setInternalState(schedulerServiceToTest, "schedulerFactory",mockScedulerFactory);
		   Whitebox.setInternalState(schedulerServiceToTest, "lockBrandService",mockLockBrandService);
		   Whitebox.setInternalState(schedulerServiceToTest, "importStatusDAO",mockImportStatusDAO);
		   Whitebox.setInternalState(schedulerServiceToTest, "quartzJobCh2Service",mockQuartzJobCh2Service);
		   Whitebox.setInternalState(schedulerServiceToTest, "resetTimeMillisecoud",1000*60*15);
		   Whitebox.setInternalState(schedulerServiceToTest, "brandDAO",mockBrandDAO);
	}
	
	@After
	public void after(){

	}
	
	// active job - true
		// active lock - true
		// active import status  -  exist level 1 , log time -20 minute
		// job history - exist
		@Test
		public void stopJobAllowedOnlyPositiveTest() throws Exception
		{							
			setupActiveJob();
			PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(true);
			PowerMockito.when(mockQuartzJobCh2Service.getJobsHistoryList(any(QueryCondition.class), any(QuartzJobHistory.class))).thenReturn(Lists.newArrayList(new QuartzJobHistory()));
			ImportStatus importStatus=getImportStatus();
			importStatus.setDateUpdate(DateUtils.addMinutes(new Date(), -10));	
			importStatus.setProcessLevel(ProcessLevel.IMPORT);
			PowerMockito.when(mockImportStatusDAO.getImportStatusByBrandCode(any(String.class))).thenReturn(importStatus);
			Assert.assertTrue(schedulerServiceToTest.stopJobAllowedOnly("BV"));			
		}
		
	
	@Test
	public void stopJobAllowedOnlyBrandCodeNull() throws Exception
	{						
		//prepare
	   boolean exceptionExist=true;
	   //test
		try{
			schedulerServiceToTest.stopJobAllowedOnly(null);			
			exceptionExist=false;
		}
		catch (IllegalArgumentException e) {
			exceptionExist = true;
		}
		Assert.assertEquals("IllegalArgumentException was handled",true, exceptionExist);
	}
		
	// active job - true
	// active lock - false
	// active import status  - not exist
	@Test
	public void stopJobAllowedOnlyVer001() throws Exception
	{							
			setupActiveJob();
			Assert.assertFalse(schedulerServiceToTest.stopJobAllowedOnly("BV"));			
	}
	// active job - true
	// active lock - true
	// active import status  - not exist
	@Test
	public void stopJobAllowedOnlyVer002() throws Exception
	{							
		setupActiveJob();
		PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(true);
		Assert.assertFalse(schedulerServiceToTest.stopJobAllowedOnly("BV"));			
	}

	// active job - true
	// active lock - true
	// active import status  -  exist level 5
	// job history not exist
	@Test
	public void stopJobAllowedOnlyVer003() throws Exception
	{							
		setupActiveJob();
		PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(true);		
		PowerMockito.when(mockImportStatusDAO.getImportStatusByBrandCode(any(String.class))).thenReturn(getImportStatus());		
		Assert.assertFalse(schedulerServiceToTest.stopJobAllowedOnly("BV"));			
	}

	// active job - true
	// active lock - true
	// active import status  -  exist level 5
	// job history - exist
	@Test
	public void stopJobAllowedOnlyVer004() throws Exception
	{							
		setupActiveJob();
		PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(true);
		PowerMockito.when(mockQuartzJobCh2Service.getJobsHistoryList(any(QueryCondition.class), any(QuartzJobHistory.class))).thenReturn(Lists.newArrayList(new QuartzJobHistory()));
		PowerMockito.when(mockImportStatusDAO.getImportStatusByBrandCode(any(String.class))).thenReturn(getImportStatus());
		Assert.assertFalse(schedulerServiceToTest.stopJobAllowedOnly("BV"));			
	}
	
     	// active job - true
		// active lock - true
		// active import status  -  exist level 1 , log time -20 minute
		// job history - exist
	@Test
	public void stopJobAllowedOnlyVer005() throws Exception
	{							
		setupActiveJob();
		PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(true);
		PowerMockito.when(mockQuartzJobCh2Service.getJobsHistoryList(any(QueryCondition.class), any(QuartzJobHistory.class))).thenReturn(Lists.newArrayList(new QuartzJobHistory()));
		ImportStatus importStatus=getImportStatus();
		importStatus.setDateUpdate(DateUtils.addMinutes(new Date(), -20));	
		importStatus.setProcessLevel(ProcessLevel.IMPORT);
		PowerMockito.when(mockImportStatusDAO.getImportStatusByBrandCode(any(String.class))).thenReturn(importStatus);
		Assert.assertFalse(schedulerServiceToTest.stopJobAllowedOnly("BV"));			
	}
	
		
	private void setupActiveJob() throws SchedulerException{
		   PowerMockito.when(mockJobExecutionContext.getJobDetail()).thenReturn(mockJobDetail);
		   PowerMockito.when(mockJobDetail.getGroup()).thenReturn(SchedulerForImportServiceImpl.jobGroupName);
		   PowerMockito.when(mockJobDetail.getName()).thenReturn(SchedulerForImportServiceImpl.jobImportName+"_BV");
		   PowerMockito.when(mockScheduler.getCurrentlyExecutingJobs()).thenReturn(Lists.newArrayList(mockJobExecutionContext,mockJobExecutionContext,mockJobExecutionContext));	
		   
			QuartzJob job=getQuartzJob();
			job.setJobStatus(JobStatus.Active);
			PowerMockito.when(mockQuartzJobCh2Service.findByName(any(String.class),any(String.class))).thenReturn(job);
		   
	}
	
	
	private ImportStatus getImportStatus(){
		ImportStatus i=new ImportStatus();
		i.setDateUpdate(new Date());
		i.setBrandCode("BV");
		i.setId(-1l);
		i.setMessages("xxxx");
		i.setProcessLevel(ProcessLevel.RECONCILIATION);
		i.setTypeMsg(TypeMsg.INF);
		return i;
	}
		
	private QuartzJob getQuartzJob(){
		QuartzJob quartzJob=new QuartzJob();
		quartzJob.setJobStatus(JobStatus.Inactive);
		return quartzJob;
	}
	
}
