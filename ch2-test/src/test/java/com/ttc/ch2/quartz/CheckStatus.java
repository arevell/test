package com.ttc.ch2.quartz;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class CheckStatus extends BaseTest {

	private static final String brandCode="BV";
	
	@Inject
	SchedulerForImportService schedulerForImportService; 
		
	@Test
	public void checkStatus() throws SchedulerServiceException{
	
		Boolean needResetImport=schedulerForImportService.checkResetForImport(brandCode);
		System.out.println("Need reset import:"+needResetImport);
		Boolean needResetUpload=schedulerForImportService.checkResetForUpload(brandCode);
		System.out.println("Need reset upload:"+needResetUpload);
		
		Boolean needReset=schedulerForImportService.resetNeed(brandCode);
		System.out.println("Need reset :"+needReset);
		
		Boolean needAllowed=schedulerForImportService.resetAllowed(brandCode);
		System.out.println("Need allowed :"+needAllowed);
		
	}
}
