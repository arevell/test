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
import org.quartz.SchedulerException;

import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.upload.UploadStatusDAO;
import com.ttc.ch2.dao.upload.UploadTourInfoDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.upload.UploadStatus;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;

@RunWith(PowerMockRunner.class)   
@PrepareForTest(SchedulerForImportServiceImpl.class)
public class UploadResetConditionTest {

	private SchedulerForImportService schedulerServiceToTest=null;
	
	private LockBrandService mockLockBrandService;

	private UploadStatusDAO mockUploadStatusDAO;
	
	private UploadTourInfoDAO mockUploadTourInfoDAO;
	
	private BrandDAO mockBrandDAO;
	@Before
	public void init() throws SchedulerException{
	    		
		   mockLockBrandService=PowerMockito.mock(LockBrandService.class);		
		   mockUploadStatusDAO=PowerMockito.mock(UploadStatusDAO.class);		
		   mockUploadTourInfoDAO=PowerMockito.mock(UploadTourInfoDAO.class);
		   mockBrandDAO=PowerMockito.mock(BrandDAO.class);	
		   		   
		   SchedulerForImportService spyElement=new SchedulerForImportServiceImpl();
		   schedulerServiceToTest = PowerMockito.spy(spyElement);	
		   Whitebox.setInternalState(schedulerServiceToTest, "lockBrandService",mockLockBrandService);
		   Whitebox.setInternalState(schedulerServiceToTest, "uploadStatusDAO",mockUploadStatusDAO);
		   Whitebox.setInternalState(schedulerServiceToTest, "uploadTourInfoDAO",mockUploadTourInfoDAO);
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
			PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.UPLOAD)).thenReturn(true);
			UploadStatus uploadStatus=getUploadStatus();
			uploadStatus.setDateUpdate(DateUtils.addMinutes(new Date(), -20));	
			PowerMockito.when(mockUploadStatusDAO.getUploadStatusByBrandCode(any(String.class))).thenReturn(uploadStatus);
			Assert.assertTrue(schedulerServiceToTest.checkResetForUpload("BV"));			
	}
		
		// active lock - false
		// active upload status  - not exist
		// history with status procesing  exist
		@Test
	public void resetPermisionPositiveVer002() throws Exception
	{										
			PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.UPLOAD)).thenReturn(false);
			PowerMockito.when(mockUploadTourInfoDAO.getUploadTourInfoList(any(QueryCondition.class),any(UploadTourInfoFile.class))).thenReturn(Lists.newArrayList(new UploadTourInfoFile()));
			Assert.assertTrue(schedulerServiceToTest.checkResetForUpload("BV"));			
	}
		
		// active lock - true
		// active upload status  - not exist
		@Test
	public void resetPermisionPositiveVer003() throws Exception
	{										
			PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.UPLOAD)).thenReturn(true);
			Assert.assertTrue(schedulerServiceToTest.checkResetForUpload("BV"));			
	}
		
	
	@Test
	public void resetPermisionPositiveBrandCodeNull() throws Exception
	{						
		//prepare
	   boolean exceptionExist=true;
	   //test
		try{
			schedulerServiceToTest.checkResetForUpload(null);			
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
			PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.UPLOAD)).thenReturn(false);
			Assert.assertFalse(schedulerServiceToTest.checkResetForUpload("BV"));			
	}
	
	// active lock - true
	// active upload status  -  exist  log time -20 minute
	@Test
	public void resetPermisionVer002() throws Exception
	{										
		PowerMockito.when(mockLockBrandService.isLockBrand("BV", ProcessName.UPLOAD)).thenReturn(true);
		UploadStatus uploadStatus=getUploadStatus();
		uploadStatus.setDateUpdate(DateUtils.addMinutes(new Date(), -10));	
		PowerMockito.when(mockUploadStatusDAO.getUploadStatusByBrandCode(any(String.class))).thenReturn(uploadStatus);
		Assert.assertFalse(schedulerServiceToTest.checkResetForUpload("BV"));			
	}
	
	
	
	
		
	private UploadStatus getUploadStatus(){
		UploadStatus u=new UploadStatus();
		u.setDateUpdate(new Date());
		u.setBrandCode("BV");
		u.setId(-1l);
		u.setMessages("xxxx");
		u.setTypeMsg(TypeMsg.INF);
		return u;
	}
	
}
