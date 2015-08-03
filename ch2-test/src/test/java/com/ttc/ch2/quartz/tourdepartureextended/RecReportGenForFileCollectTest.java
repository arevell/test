package com.ttc.ch2.quartz.tourdepartureextended;



import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.bl.departure.TropicExtendedDepartureSynchronizeService;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.bl.report.ReconciliationReportService;
import com.ttc.ch2.bl.report.ReconciliationReportServiceException;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.quartz.executionlisteners.ScheduleInstancePreparer;
import com.ttc.ch2.scheduler.service.SchedulerForDepExtImportService;
import com.ttc.ch2.scheduler.service.departureextend.DepartureExtendedSynchronizeService;
import com.ttc.ch2.search.export.IndexSynchronizerService;
import com.ttc.ch2.search.export.IndexSynchronizerVO;

@Ignore("This test create locks on brands")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
//@PrepareForTest(TropicExtendedDepartureSynchronizeServiceImpl.class)
public class RecReportGenForFileCollectTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(RecReportGenForFileCollectTest.class);
	private String brandCode="BV";
		
//	@Rule
//    public PowerMockRule rule = new PowerMockRule();
	
	@Inject
	private DepartureExtendedSynchronizeService serviceToTest;
	
	@Inject
	private TropicExtendedDepartureSynchronizeService nestedService;
	
	@Inject
	private IndexSynchronizerService indexSynchronizerService;
	
	@Inject
	private SchedulerForDepExtImportService schedulerForDepExtImportService;
	
	@Inject
	@Named("HabsReconciliationReportServiceImpl")
	private ReconciliationReportService reconciliationReportService;
	
	//indexing warning 
	//None of the tours has been indexed
	//None of the tours has been added to the repository
	@Test()
	public void sendWarning001Test() throws Exception {
				
		TropicExtendedDepartureSynchronizeService spyNestedService=PowerMockito.spy(nestedService);
		
		IndexSynchronizerVO result=new IndexSynchronizerVO(); 
		result.setIndexingAll(false);
		result.setDocumentCount(0);
		result.setAggregatedCount(0);
		
		FileCollectVO resultFc=new FileCollectVO();
		resultFc.setStatusOperation(FileCollectVO.StatusOperation.Cleared);
					
		Mockito.when(indexSynchronizerService.getStateOfIndex(Mockito.anyMap(),Mockito.anyString())).thenReturn(result);
		Mockito.doAnswer(new SynchronizeAnswer()).when(indexSynchronizerService).synchronize(Mockito.any(ProcessName.class), Mockito.anyString(), Mockito.any(IndexSynchronizerVO.class));		
		PowerMockito.doReturn(resultFc).when(spyNestedService,"margeFileCollectVO",Mockito.anyMapOf(Date.class, FileCollectVO.class));
				
		String defValue=null;
		try{
		defValue=Whitebox.getInternalState(nestedService, "elasticSearchIndexing");
		Assert.assertNotNull(defValue);
		
		Whitebox.setInternalState(nestedService, "elasticSearchIndexing","true");
		Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",spyNestedService);
			serviceToTest.setBrandCode(brandCode);
			serviceToTest.processing();
		}
		finally{
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing",defValue);
			Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",nestedService);
		}
	}
	
	//indexing warning 
	//None of the tours has been indexed
	//None of the tours has been added to the repository
	@Test()
	public void sendWarning002Test() throws Exception {
		
		TropicExtendedDepartureSynchronizeService spyNestedService=PowerMockito.spy(nestedService);
		
		IndexSynchronizerVO result=new IndexSynchronizerVO(); 
		result.setIndexingAll(false);
		result.setDocumentCount(0);
		result.setAggregatedCount(0);
				
		Mockito.when(indexSynchronizerService.getStateOfIndex(Mockito.anyMap(),Mockito.anyString())).thenReturn(result);
		Mockito.doAnswer(new SynchronizeAnswer()).when(indexSynchronizerService).synchronize(Mockito.any(ProcessName.class), Mockito.anyString(), Mockito.any(IndexSynchronizerVO.class));		
		PowerMockito.doReturn(null).when(spyNestedService,"margeFileCollectVO",Mockito.anyMapOf(Date.class, FileCollectVO.class));
		
		String defValue=null;
		try{
			defValue=Whitebox.getInternalState(nestedService, "elasticSearchIndexing");
			Assert.assertNotNull(defValue);
			
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing","true");
			Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",spyNestedService);
			serviceToTest.setBrandCode(brandCode);
			serviceToTest.processing();
		}
		finally{
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing",defValue);
			Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",nestedService);
		}
	}
	
	//indexing warning 
	//None of the tours has been indexed
	@Test()
	public void sendSuccessTest() throws Exception {
		
		TropicExtendedDepartureSynchronizeService spyNestedService=PowerMockito.spy(nestedService);
		
		IndexSynchronizerVO result=new IndexSynchronizerVO(); 
		result.setIndexingAll(false);
		result.setDocumentCount(0);
		result.setAggregatedCount(0);
		
		
		FileCollectVO resultFc=new FileCollectVO();
		resultFc.setStatusOperation(FileCollectVO.StatusOperation.Executed);
		
		Mockito.when(indexSynchronizerService.getStateOfIndex(Mockito.anyMap(),Mockito.anyString())).thenReturn(result);
		Mockito.doAnswer(new SynchronizeAnswer()).when(indexSynchronizerService).synchronize(Mockito.any(ProcessName.class), Mockito.anyString(), Mockito.any(IndexSynchronizerVO.class));		
		PowerMockito.doReturn(resultFc).when(spyNestedService,"margeFileCollectVO",Mockito.anyMapOf(Date.class, FileCollectVO.class));
		
		String defValue=null;
		try{
			defValue=Whitebox.getInternalState(nestedService, "elasticSearchIndexing");
			Assert.assertNotNull(defValue);
			
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing","true");
			Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",spyNestedService);
			serviceToTest.setBrandCode(brandCode);
			serviceToTest.processing();
		}
		finally{
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing",defValue);
			Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",nestedService);
		}
	}
	
	
	//indexing warning 
	//None of the tours has been indexed
	@Test()
	public void sendError001Test() throws Exception {
		
		TropicExtendedDepartureSynchronizeService spyNestedService=PowerMockito.spy(nestedService);
		
		IndexSynchronizerVO result=new IndexSynchronizerVO(); 
		result.setIndexingAll(false);
		result.setDocumentCount(0);
		result.setAggregatedCount(0);
		
		
		FileCollectVO resultFc=new FileCollectVO();
		resultFc.setStatusOperation(FileCollectVO.StatusOperation.ExceptionAppeared);
		
		Mockito.when(indexSynchronizerService.getStateOfIndex(Mockito.anyMap(),Mockito.anyString())).thenReturn(result);
		Mockito.doAnswer(new SynchronizeAnswer()).when(indexSynchronizerService).synchronize(Mockito.any(ProcessName.class), Mockito.anyString(), Mockito.any(IndexSynchronizerVO.class));		
		PowerMockito.doReturn(resultFc).when(spyNestedService,"margeFileCollectVO",Mockito.anyMapOf(Date.class, FileCollectVO.class));
		
		String defValue=null;
		try{
			defValue=Whitebox.getInternalState(nestedService, "elasticSearchIndexing");
			Assert.assertNotNull(defValue);
			
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing","true");
			Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",spyNestedService);
			serviceToTest.setBrandCode(brandCode);
			serviceToTest.processing();
		}
		finally{
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing",defValue);
			Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",nestedService);
		}
	}
	
	//indexing warning 
	//None of the tours has been indexed
	@Test()
	public void sendError002Test() throws Exception {
		
		TropicExtendedDepartureSynchronizeService spyNestedService=PowerMockito.spy(nestedService);
		
		IndexSynchronizerVO result=new IndexSynchronizerVO(); 
		result.setIndexingAll(false);
		result.setDocumentCount(0);
		result.setAggregatedCount(0);
		
		
		FileCollectVO resultFc=new FileCollectVO();
		resultFc.setStatusOperation(FileCollectVO.StatusOperation.Executed);
		resultFc.getToursListNotAdded().add("xxxxx");
		
		Mockito.when(indexSynchronizerService.getStateOfIndex(Mockito.anyMap(),Mockito.anyString())).thenReturn(result);
		Mockito.doAnswer(new SynchronizeAnswer()).when(indexSynchronizerService).synchronize(Mockito.any(ProcessName.class), Mockito.anyString(), Mockito.any(IndexSynchronizerVO.class));		
		PowerMockito.doReturn(resultFc).when(spyNestedService,"margeFileCollectVO",Mockito.anyMapOf(Date.class, FileCollectVO.class));
		
		String defValue=null;
		try{
			defValue=Whitebox.getInternalState(nestedService, "elasticSearchIndexing");
			Assert.assertNotNull(defValue);
			
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing","true");
			Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",spyNestedService);
			serviceToTest.setBrandCode(brandCode);
			serviceToTest.processing();
		}
		finally{
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing",defValue);
			Whitebox.setInternalState(serviceToTest, "tropicExtendedDepartureSynchronizeService",nestedService);
		}
	}
	

	

	@Test
	public void sendNullToRaport() throws ReconciliationReportServiceException{
		
		reconciliationReportService.createReconciliationReport(ProcessName.IMPORT, brandCode, null, null);
		//None of the tours has been indexed
		//None of the tours has been added to the repository
	}
	
	class SynchronizeAnswer implements Answer<Object>{

		@Override
		public Object answer(InvocationOnMock invocation) throws Throwable {
			IndexSynchronizerVO result=(IndexSynchronizerVO) invocation.getArguments()[2];
			result.setIndexingAll(true);
			result.setAggregatedCount(100);
			result.setDocumentCount(100);
			result.getDocumentIDsListNotAdded().add("xxxx");
			result.getDocumentIDsListNotAdded().add("yyyy");
			result.getAggregatedIDsListNotAdded().add("xxxx");
			result.getAggregatedIDsListNotAdded().add("yyyy");
			return null;
		}		
	}
}
