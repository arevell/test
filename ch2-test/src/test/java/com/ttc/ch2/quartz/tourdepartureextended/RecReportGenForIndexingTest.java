package com.ttc.ch2.quartz.tourdepartureextended;



import javax.inject.Inject;
import javax.inject.Named;

import org.elasticsearch.common.collect.Lists;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.Brand;
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
public class RecReportGenForIndexingTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(RecReportGenForIndexingTest.class);
	private String brandCode="BV";
	
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
	//File Collect process completed successfully
	@Test()
	public void sendWarningTest() throws Exception {
				
		IndexSynchronizerVO result=new IndexSynchronizerVO(); 
		result.setIndexingAll(false);
		result.setDocumentCount(0);
		result.setAggregatedCount(0);
			
		Mockito.when(indexSynchronizerService.getStateOfIndex(Mockito.anyMap(),Mockito.anyString())).thenReturn(result);
		Mockito.doAnswer(new SynchronizeAnswer()).when(indexSynchronizerService).synchronize(Mockito.any(ProcessName.class), Mockito.anyString(), Mockito.any(IndexSynchronizerVO.class));
		
		String defValue=null;
		try{
		defValue=Whitebox.getInternalState(nestedService, "elasticSearchIndexing");
		Assert.assertNotNull(defValue);
		
		Whitebox.setInternalState(nestedService, "elasticSearchIndexing","true");
			serviceToTest.setBrandCode(brandCode);
			serviceToTest.processing();
		}
		finally{
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing",defValue);
		}
	}
	
	//indexing success
	//Indexation completed successfully
	//File Collect process completed successfully
	@Test()
	public void sendSuccessTest() throws Exception {
		
		IndexSynchronizerVO result=new IndexSynchronizerVO(); 
		result.setIndexingAll(false);
		result.setDocumentCount(10);
		result.setAggregatedCount(10);
		
		Mockito.when(indexSynchronizerService.getStateOfIndex(Mockito.anyMap(),Mockito.anyString())).thenReturn(result);
		Mockito.doAnswer(new SynchronizeAnswer()).when(indexSynchronizerService).synchronize(Mockito.any(ProcessName.class), Mockito.anyString(), Mockito.any(IndexSynchronizerVO.class));
		
		String defValue=null;
		try{
			defValue=Whitebox.getInternalState(nestedService, "elasticSearchIndexing");
			Assert.assertNotNull(defValue);
			
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing","true");
			serviceToTest.setBrandCode(brandCode);
			serviceToTest.processing();
		}
		finally{
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing",defValue);
		}
	}
	
	// indexing error 
	//Elastic Search indexing: error
	//File Collect process completed successfully
	@Test()
	public void sendErrorTest() throws Exception {
		
		IndexSynchronizerVO iVo=new IndexSynchronizerVO();
		iVo.setDocumentCount(10);
		iVo.setAggregatedCount(10);
		iVo.setIndexingAll(true);
		iVo.setUnexpectedErrorOccurred(false);
		iVo.getAggregatedIDsListNotAdded().add("yyyyy");
		iVo.getAggregatedIDsListNotDeleted().add("yyyyy");
		iVo.getDocumentIDsListNotAdded().add("yyyyy");
		iVo.getDocumentIDsListNotDeleted().add("yyyyy");
		
		
		Mockito.when(indexSynchronizerService.getStateOfIndex(Mockito.anyMap(),Mockito.anyString())).thenReturn(iVo);
		Mockito.doAnswer(new SynchronizeAnswer()).when(indexSynchronizerService).synchronize(Mockito.any(ProcessName.class), Mockito.anyString(), Mockito.any(IndexSynchronizerVO.class));
		
		String defValue=null;
		try{
			defValue=Whitebox.getInternalState(nestedService, "elasticSearchIndexing");
			Assert.assertNotNull(defValue);
			
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing","true");
			serviceToTest.setBrandCode(brandCode);
			serviceToTest.processing();
		}
		finally{
			Whitebox.setInternalState(nestedService, "elasticSearchIndexing",defValue);
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
	
	private void insertRecReportData(){
		
		//prepare
				Brand b=new Brand();
				b.setCode("BV");
				b.setBrandName("BVUSAS");
				
				FileCollectVO fVo=new FileCollectVO();
				fVo.setBrand(b);
				fVo.setSellingCompanies(Lists.newArrayList(SampleGenerator.generateSellingCompany(b)));
				fVo.getToursListNotAdded().add("xxxx");
				fVo.getZipListNotAdded().add("xxxx");
				fVo.getZipListNotDeleted().add("xxxx");
						
				IndexSynchronizerVO iVo=new IndexSynchronizerVO();
				iVo.setIndexingAll(true);
				iVo.setUnexpectedErrorOccurred(false);
				iVo.getAggregatedIDsListNotAdded().add("yyyyy");
				iVo.getAggregatedIDsListNotDeleted().add("yyyyy");
				iVo.getDocumentIDsListNotAdded().add("yyyyy");
				iVo.getDocumentIDsListNotDeleted().add("yyyyy");
				
		
	
		schedulerForDepExtImportService.setupRecReportData(brandCode,iVo, fVo);
	}
}
