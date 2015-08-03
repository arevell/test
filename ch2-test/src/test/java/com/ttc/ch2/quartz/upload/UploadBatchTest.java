package com.ttc.ch2.quartz.upload;



import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.quartz.executionlisteners.ScheduleInstancePreparer;
import com.ttc.ch2.scheduler.common.JobParams;
import com.ttc.ch2.scheduler.jobs.upload.UploadTourInfoJob;

/**Test configuration quartz jobs and scheduler mode*/
@Ignore("This test create locks on brands")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class UploadBatchTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(UploadBatchTest.class);
	private String brandCode="BV";
		
	
	// Check coverage exceptions
	@Test
	public void runJobTest() throws Exception {		
		//prepare
		boolean exceptionExist=false;
		try{
			JobExecutionContext mockCtx=PowerMockito.mock(JobExecutionContext.class);
			JobDetail mockJobDetail=PowerMockito.mock(JobDetail.class);
			JobDataMap mockJobDataMap=PowerMockito.mock(JobDataMap.class);
			PowerMockito.when(mockCtx.getJobDetail()).thenReturn(mockJobDetail);
			PowerMockito.when(mockJobDetail.getJobDataMap()).thenReturn(mockJobDataMap);	
			PowerMockito.when(mockJobDataMap.get(JobParams.BRAND_CODE.toString())).thenReturn(brandCode);
			
			UploadTourInfoJob job=new UploadTourInfoJob();
			//test		
			Whitebox.invokeMethod(job, "executeInternal", mockCtx);
		}
		catch(Exception e){
			logger.error("",e);
			exceptionExist=true;
		}		
		//check
		Assert.assertFalse("DepartureSynchronizeJob should catch all exceptions",exceptionExist);
	}

	
	
}
