package com.ttc.ch2.scheduler.service;


import static org.mockito.Matchers.any;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.elasticsearch.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.quartz.SchedulerException;

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


@RunWith(PowerMockRunner.class)   
@PrepareForTest(SchedulerForImportServiceImpl.class)
public class ImportResetConditionTest {

	private SchedulerForImportService schedulerServiceToTest=null;
	
	private LockBrandService mockLockBrandService;

	private ImportStatusDAO mockImportStatusDAO;
	
	private QuartzJobCh2Service mockQuartzJobCh2Service;
	
	private BrandDAO mockBrandDAO;
		
	@Before
	public void init() throws SchedulerException{
	    		
		   mockLockBrandService=PowerMockito.mock(LockBrandService.class);		
		   mockImportStatusDAO=PowerMockito.mock(ImportStatusDAO.class);		
		   mockQuartzJobCh2Service=PowerMockito.mock(QuartzJobCh2Service.class);		
		   mockBrandDAO=PowerMockito.mock(BrandDAO.class);		
		   		   
		   SchedulerForImportService spyElement=new SchedulerForImportServiceImpl();
		   schedulerServiceToTest = PowerMockito.spy(spyElement);	
		   Whitebox.setInternalState(schedulerServiceToTest, "lockBrandService",mockLockBrandService);
		   Whitebox.setInternalState(schedulerServiceToTest, "importStatusDAO",mockImportStatusDAO);
		   Whitebox.setInternalState(schedulerServiceToTest, "quartzJobCh2Service",mockQuartzJobCh2Service);
		   Whitebox.setInternalState(schedulerServiceToTest, "resetTimeMillisecoud",1000*60*15);
		   Whitebox.setInternalState(schedulerServiceToTest, "brandDAO",mockBrandDAO);
	}
	
	@After
	public void after(){

	}
	
		// active lock - true
		// active upload status  -  exist  log time -20 minute
		@Test
	public void resetPermisionPositiveVer001() throws Exception
	{										
			PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(true);
			ImportStatus importStatus=getImportStatus();
			importStatus.setDateUpdate(DateUtils.addMinutes(new Date(), -20));	
			PowerMockito.when(mockImportStatusDAO.getImportStatusByBrandCode(any(String.class))).thenReturn(importStatus);
			Assert.assertTrue(schedulerServiceToTest.checkResetForImport("BV"));			
	}
		
		// active lock - false
		// active upload status  - not exist
		// history with status procesing  exist
		@Test
	public void resetPermisionPositiveVer002() throws Exception
	{										
			PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(false);
			QuartzJob job=getQuartzJob();			
			PowerMockito.when(mockQuartzJobCh2Service.findByName(any(String.class),any(String.class))).thenReturn(job);
			PowerMockito.when(mockQuartzJobCh2Service.getJobsHistoryList(any(QueryCondition.class),any(QuartzJobHistory.class))).thenReturn(Lists.newArrayList(new QuartzJobHistory()));
			Assert.assertTrue(schedulerServiceToTest.checkResetForImport("BV"));			
	}
		
		// active lock - true
		// active upload status  - not exist
		@Test
	public void resetPermisionPositiveVer003() throws Exception
	{										
			PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(true);
			Assert.assertTrue(schedulerServiceToTest.checkResetForImport("BV"));			
	}
		
	
	@Test
	public void resetPermisionPositiveBrandCodeNull() throws Exception
	{						
		//prepare
	   boolean exceptionExist=true;
	   //test
		try{
			schedulerServiceToTest.checkResetForImport(null);			
			exceptionExist=false;
		}
		catch (IllegalArgumentException e) {
			exceptionExist = true;
		}
		Assert.assertEquals("IllegalArgumentException was handled",true, exceptionExist);
	}
		
	// active lock - false
	// active upload status  - not exist
	// history with status procesing not exist
	@Test
	public void resetPermisionVer001() throws Exception
	{										
			PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(false);
			QuartzJob job=getQuartzJob();			
			PowerMockito.when(mockQuartzJobCh2Service.findByName(any(String.class),any(String.class))).thenReturn(job);
			Assert.assertFalse(schedulerServiceToTest.checkResetForImport("BV"));			
	}
	
	// active lock - true
	// active upload status  -  exist  log time -20 minute
	@Test
	public void resetPermisionVer002() throws Exception
	{										
		PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.IMPORT)).thenReturn(true);
		ImportStatus importStatus=getImportStatus();
		importStatus.setDateUpdate(DateUtils.addMinutes(new Date(), -10));	
		PowerMockito.when(mockImportStatusDAO.getImportStatusByBrandCode(any(String.class))).thenReturn(importStatus);
		Assert.assertFalse(schedulerServiceToTest.checkResetForImport("BV"));			
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
