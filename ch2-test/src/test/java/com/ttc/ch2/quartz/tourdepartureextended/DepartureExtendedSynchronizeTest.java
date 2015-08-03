package com.ttc.ch2.quartz.tourdepartureextended;



import java.util.Date;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

import com.ttc.ch2.bl.departure.TourDepartureHistoryService;
import com.ttc.ch2.bl.departure.TropicExtendedDepartureSynchronizeService;
import com.ttc.ch2.bl.departure.TropicSynchronizeServiceException;
import com.ttc.ch2.bl.departure.common.LogOperationHelper;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.departure.TourDepartureHistory;
import com.ttc.ch2.domain.departure.TourDepartureHistory.TourDepartureStatus;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.jobs.QuartzJobHistory.JobHistoryStatus;
import com.ttc.ch2.quartz.executionlisteners.ScheduleInstancePreparer;
import com.ttc.ch2.scheduler.common.DepartureSynchronizeMessage;
import com.ttc.ch2.scheduler.common.JobParams;
import com.ttc.ch2.scheduler.jobs.departuresynch.DepartureExtendedSynchronizeJob;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.scheduler.service.departureextend.DepartureExtendedSynchronizeService;
import com.ttc.ch2.statistics.StatisticsBean;

/**Test configuration quartz jobs and scheduler mode*/
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class DepartureExtendedSynchronizeTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(DepartureExtendedSynchronizeTest.class);
	private String brandCode="BV";
	
	@Inject
	private DepartureExtendedSynchronizeService serviceToTest;
		
	@Inject
	private QuartzJobCh2Service quartzJobCh2Service;
	
	@Inject
	private TropicExtendedDepartureSynchronizeService tropicExtendedDepartureSynchronizeService;
	
	@Inject
	private StatisticsBean staticBean;
	
	
	// Check coverage exceptions
	@Test
	public void negativeRunJobTest() throws Exception {		
		//prepare
		boolean exceptionExist=false;
		try{
			JobExecutionContext mockCtx=PowerMockito.mock(JobExecutionContext.class);
			JobDetail mockJobDetail=PowerMockito.mock(JobDetail.class);
			JobDataMap mockJobDataMap=PowerMockito.mock(JobDataMap.class);
			PowerMockito.when(mockCtx.getJobDetail()).thenReturn(mockJobDetail);
			PowerMockito.when(mockJobDetail.getJobDataMap()).thenReturn(mockJobDataMap);	
			PowerMockito.when(mockJobDataMap.get(JobParams.BRAND_CODE.toString())).thenReturn(null);
			
			DepartureExtendedSynchronizeJob job=new DepartureExtendedSynchronizeJob();
			//test		
			Whitebox.invokeMethod(job, "executeInternal", mockCtx);
		}
		catch(Exception e){
			exceptionExist=true;
		}		
		//check
		Assert.assertFalse("QuickDepartureSynchronizeJob should catch all exceptions",exceptionExist);
	}

	// run  service without setup BrandCode
	@Test(expected=IllegalStateException.class)
	public void negativeRunServiceTest() throws Exception {
			serviceToTest.processing();			
	}
	

	@Test
	public void lockBrandTest() throws Exception {
		//prepare
		staticBean.registerBrand(brandCode);		
		QuartzJobCh2Service mockQuartzJobCh2Service=PowerMockito.mock(QuartzJobCh2Service.class);
		LockBrandService mockLockBrandService=PowerMockito.mock(LockBrandService.class);
		TropicExtendedDepartureSynchronizeService mockTropicQuickDepartureSynchronizeService=PowerMockito.mock(TropicExtendedDepartureSynchronizeService.class);
		PowerMockito.when(mockQuartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(), brandCode)).thenReturn(quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(), brandCode));
		PowerMockito.when(mockLockBrandService.lockBrand(brandCode,ProcessName.EXTENDED)).thenReturn(false);
				
		serviceToTest.setBrandCode(brandCode);
		Whitebox.setInternalState(serviceToTest, "lockBrandService", mockLockBrandService);
		Whitebox.setInternalState(serviceToTest, "quartzJobCh2Service", mockQuartzJobCh2Service);
		Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService", mockTropicQuickDepartureSynchronizeService);
			
		//test
		serviceToTest.processing();	
		
		//check
		ArgumentCaptor<QuartzJobHistory> quartzJobHistoryCaptor = ArgumentCaptor.forClass(QuartzJobHistory.class);
		Mockito.verify(mockQuartzJobCh2Service).saveNewQuartzJobHistory(quartzJobHistoryCaptor.capture());
		Assert.assertTrue("After unsuccesful lock  system should save QuartzJobHistory",quartzJobHistoryCaptor.getValue()!=null);
		Assert.assertTrue("QuartzJobHistory has incorrect status",JobHistoryStatus.Cancelled==quartzJobHistoryCaptor.getValue().getStatus());
		Assert.assertTrue("QuartzJobHistory has only one comment",quartzJobHistoryCaptor.getValue().getComments().size()==1);		
		String msg=String.format("Comment with incorrect messageCode expected: %s recived: %s", DepartureSynchronizeMessage.REGISTRED_PROCESS_EXCEPTION_V2.getCode(),quartzJobHistoryCaptor.getValue().getComments().get(0).getMessageCode());
		Assert.assertTrue(msg,DepartureSynchronizeMessage.REGISTRED_PROCESS_EXCEPTION_V2.getCode().equals(quartzJobHistoryCaptor.getValue().getComments().get(0).getMessageCode()));
		Assert.assertTrue("Field processingByAnotherThread value in process should  be setup on true",((Boolean)Whitebox.getInternalState(serviceToTest, "processingByAnotherThread"))==true);
	}
	
	@Test
	public void checkPositiveFlowTest() throws Exception {
		
		//prepare
		staticBean.registerBrand(brandCode);	
		QuartzJobCh2Service mockQuartzJobCh2Service=PowerMockito.mock(QuartzJobCh2Service.class);
		LockBrandService mockLockBrandService=PowerMockito.mock(LockBrandService.class);
		
		PowerMockito.when(mockQuartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(), brandCode)).thenReturn(quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(), brandCode));
		PowerMockito.when(mockLockBrandService.lockBrand(brandCode,ProcessName.EXTENDED)).thenReturn(true);

		TourDepartureHistoryService mockTourDepartureHistoryService=PowerMockito.mock(TourDepartureHistoryService.class);
		TropicExtendedDepartureSynchronizeService spyTropicQuickDepartureSynchronizeService=PowerMockito.spy(tropicExtendedDepartureSynchronizeService);	
						
		Whitebox.setInternalState(spyTropicQuickDepartureSynchronizeService, "tourDepartureHistoryService", mockTourDepartureHistoryService);
		PowerMockito.doNothing().when(spyTropicQuickDepartureSynchronizeService).executeOperation(Mockito.any(OperationStatus.class));

		
		serviceToTest.setBrandCode(brandCode);
		Whitebox.setInternalState(serviceToTest, "lockBrandService", mockLockBrandService);
		Whitebox.setInternalState(serviceToTest, "quartzJobCh2Service", mockQuartzJobCh2Service);
		Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService", spyTropicQuickDepartureSynchronizeService);
		
		
		
		//test
		serviceToTest.processing();	
		
		//check
		ArgumentCaptor<QuartzJobHistory> quartzJobHistoryCaptor = ArgumentCaptor.forClass(QuartzJobHistory.class);
		Mockito.verify(mockQuartzJobCh2Service).saveNewQuartzJobHistory(quartzJobHistoryCaptor.capture());

		
		Assert.assertTrue("System should save QuartzJobHistory",quartzJobHistoryCaptor.getValue()!=null);		
		Assert.assertTrue("QuartzJob.job status should be inactive recive:"+quartzJobHistoryCaptor.getValue().getQuartzJob().getJobStatus(),JobStatus.Inactive==quartzJobHistoryCaptor.getValue().getQuartzJob().getJobStatus());		
		// in process without mock object this assert will be equals 1
		Assert.assertTrue("QuartzJobHistory sholud not have comments",quartzJobHistoryCaptor.getValue().getComments().size()==0);		
		Assert.assertTrue("Field processingByAnotherThread value in process should  be setup on false",((Boolean)Whitebox.getInternalState(serviceToTest, "processingByAnotherThread"))==false);
		Assert.assertTrue("QuartzJobHistory has incorrect status expected:"+JobHistoryStatus.Success+" recieve:"+quartzJobHistoryCaptor.getValue().getStatus(),JobHistoryStatus.Success==quartzJobHistoryCaptor.getValue().getStatus());
		
		ArgumentCaptor<TourDepartureHistory> tourDepartureHistoryCaptor = ArgumentCaptor.forClass(TourDepartureHistory.class);
		Mockito.verify(mockTourDepartureHistoryService).saveTourDepartureHistory(tourDepartureHistoryCaptor.capture(),Mockito.anySetOf(Long.class));
		
		Assert.assertTrue("System should save TourDepartureHistory",tourDepartureHistoryCaptor.getValue()!=null);
		Assert.assertTrue("TourDepartureHistory should have operationStart",tourDepartureHistoryCaptor.getValue().getOperationStart()!=null);
		Assert.assertTrue("TourDepartureHistory should have operationEnd",tourDepartureHistoryCaptor.getValue().getOperationEnd()!=null);
		Assert.assertTrue("TourDepartureHistory should have QuartzJobHistory",tourDepartureHistoryCaptor.getValue().getQuartzJobHistory()!=null);
		Assert.assertTrue("TourDepartureHistory should have status:"+TourDepartureStatus.SUCCESS_OPERATION_END+" recived:"+tourDepartureHistoryCaptor.getValue().getStatus(),TourDepartureStatus.SUCCESS_OPERATION_END==tourDepartureHistoryCaptor.getValue().getStatus());
		Assert.assertTrue("TourDepartureHistory should have one comment",tourDepartureHistoryCaptor.getValue().getComments().size()==1);
		
		String msg=String.format("Comment with incorrect messageCode expected: %s recived: %s", TropicSynchronizeMessages.EXTENDED_STATUS.getCode(),tourDepartureHistoryCaptor.getValue().getComments().get(0).getMessageCode());
		Assert.assertTrue(msg,TropicSynchronizeMessages.EXTENDED_STATUS.getCode().equals(tourDepartureHistoryCaptor.getValue().getComments().get(0).getMessageCode()));
	}
	
	@Test
	public void throwException() throws Exception{
		
		
		//prepare
				staticBean.registerBrand(brandCode);		
				QuartzJobCh2Service mockQuartzJobCh2Service=PowerMockito.mock(QuartzJobCh2Service.class);
				LockBrandService mockLockBrandService=PowerMockito.mock(LockBrandService.class);
								
				
				PowerMockito.when(mockQuartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(), brandCode)).thenReturn(quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(), brandCode));
				PowerMockito.when(mockLockBrandService.lockBrand(brandCode,ProcessName.EXTENDED)).thenReturn(true);

				TropicExtendedDepartureSynchronizeService mockTropicQuickDepartureSynchronizeService=PowerMockito.mock(TropicExtendedDepartureSynchronizeService.class);						
				PowerMockito.when(mockTropicQuickDepartureSynchronizeService.departureSynchronizeOperation(Mockito.any(OperationStatus.class))).thenAnswer(new TropicSynchronizeServiceExceptionThrower());
								
				serviceToTest.setBrandCode(brandCode);
				Whitebox.setInternalState(serviceToTest, "lockBrandService", mockLockBrandService);
				Whitebox.setInternalState(serviceToTest, "quartzJobCh2Service", mockQuartzJobCh2Service);
				Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService", mockTropicQuickDepartureSynchronizeService);

				//test
				serviceToTest.processing();	
								
				//check
				ArgumentCaptor<QuartzJobHistory> quartzJobHistoryCaptor = ArgumentCaptor.forClass(QuartzJobHistory.class);
				Mockito.verify(mockQuartzJobCh2Service).saveNewQuartzJobHistory(quartzJobHistoryCaptor.capture());
				
				Assert.assertTrue("System should save QuartzJobHistory",quartzJobHistoryCaptor.getValue()!=null);		
				Assert.assertTrue("QuartzJob.job status should be inactive recive:"+quartzJobHistoryCaptor.getValue().getQuartzJob().getJobStatus(),JobStatus.Inactive==quartzJobHistoryCaptor.getValue().getQuartzJob().getJobStatus());		
				Assert.assertTrue("QuartzJobHistory sholud not have comments",quartzJobHistoryCaptor.getValue().getComments().size()==1);		
				Assert.assertTrue("Field processingByAnotherThread value in process should  be setup on false",((Boolean)Whitebox.getInternalState(serviceToTest, "processingByAnotherThread"))==false);
				Assert.assertTrue("QuartzJobHistory has incorrect status expected:"+JobHistoryStatus.Fail+" recieve:"+quartzJobHistoryCaptor.getValue().getStatus(),JobHistoryStatus.Fail==quartzJobHistoryCaptor.getValue().getStatus());	
	}
	
	
	private class TropicSynchronizeServiceExceptionThrower implements Answer<Object>{
		@Override
		public Object answer(InvocationOnMock invocation) throws Throwable {
			TropicSynchronizeServiceException e=new TropicSynchronizeServiceException("Example Exception");
			OperationStatus ops=(OperationStatus) invocation.getArguments()[0];						
			LogOperationHelper.logDefaultException(logger, ops, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);
			e.setOpStatus(ops);
			throw e;						
		}		
	}
	
}
