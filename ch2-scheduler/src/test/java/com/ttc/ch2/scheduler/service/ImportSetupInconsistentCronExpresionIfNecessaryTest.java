package com.ttc.ch2.scheduler.service;


import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.quartz.SchedulerException;

import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.common.enums.CronExpresion;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.departure.ImportStatusDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;


@RunWith(PowerMockRunner.class)   
public class ImportSetupInconsistentCronExpresionIfNecessaryTest {

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
	
	@Test(expected=IllegalStateException.class)
	public void incorrectCronconfiguration() throws Exception
	{						
		//prepare
		List<Brand> emptyList=Lists.newArrayList();
		PowerMockito.when(mockBrandDAO.findAll()).thenReturn(emptyList);		
		//test
		schedulerServiceToTest.setupInconsistentCronExpresionIfNecessary();
	}
	
	@Test()
	public void positiveWithoutModification() throws Exception
	{						
		//prepare
		PowerMockito.when(mockBrandDAO.findAll()).thenReturn(this.getBrand());
		PowerMockito.when(mockQuartzJobCh2Service.findByName(Matchers.anyString(), Matchers.anyString())).thenReturn(getQuartzJob(CronExpresion.TDI_HOUR06.getExpresion(5)),getQuartzJob(CronExpresion.TDI_HOUR06.getExpresion(10)));
		Mockito.doNothing().when(schedulerServiceToTest).setupNewCronExpression(Matchers.any(CronExpresion.class));
		
		//test
		CronExpresion ce=schedulerServiceToTest.setupInconsistentCronExpresionIfNecessary();
		
		//check
		Assert.assertTrue("Expected cron expresion:"+CronExpresion.TDI_HOUR06,ce==CronExpresion.TDI_HOUR06);
		Mockito.verify(schedulerServiceToTest, Mockito.times(0)).setupNewCronExpression(Matchers.any(CronExpresion.class));
	}
	
	@Test()
	public void positiveWithModification() throws Exception
	{						
		//prepare
		PowerMockito.when(mockBrandDAO.findAll()).thenReturn(this.getBrand());
		PowerMockito.when(mockQuartzJobCh2Service.findByName(Matchers.anyString(), Matchers.anyString())).thenReturn(getQuartzJob(CronExpresion.TDI_HOUR06.getExpresion(5)),getQuartzJob(CronExpresion.TDI_HOUR12.getExpresion(10)));
		Mockito.doNothing().when(schedulerServiceToTest).setupNewCronExpression(Matchers.any(CronExpresion.class));
				
		//test
		CronExpresion ce=schedulerServiceToTest.setupInconsistentCronExpresionIfNecessary();
		
		//check
		Assert.assertTrue("Expected cron expresion:"+CronExpresion.TDI_HOUR06,ce==CronExpresion.TDI_HOUR06);
		Mockito.verify(schedulerServiceToTest, Mockito.times(1)).setupNewCronExpression(Matchers.any(CronExpresion.class));
	}
				
	private QuartzJob getQuartzJob(String cronExpresion){
		QuartzJob quartzJob=new QuartzJob();
		quartzJob.setJobStatus(JobStatus.Inactive);
		quartzJob.setCronExpresion(cronExpresion);
		return quartzJob;
	}
	
	private List<Brand> getBrand(){		
		String [] codes={"BV","CH","IV","TT"};
		List<Brand> brands=Lists.newArrayList();
		for (int i = 0; i < codes.length; i++) {
			Brand brand=new Brand();
			brand.setBrandName("xxxx:"+i);
			brand.setCode(codes[i]);			
			brands.add(brand);
		}
		return brands;
	}	
}
