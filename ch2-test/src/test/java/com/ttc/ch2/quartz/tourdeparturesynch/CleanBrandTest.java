package com.ttc.ch2.quartz.tourdeparturesynch;



import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.google.common.collect.Lists;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.departure.habs.HabsTourDepartureService;
import com.ttc.ch2.bl.departure.habs.HabsTourDepartureServiceImpl;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.quartz.executionlisteners.ScheduleInstancePreparer;
import com.ttc.ch2.scheduler.common.JobParams;
import com.ttc.ch2.scheduler.jobs.departuresynch.DepartureSynchronizeJob;
import com.wsout.habs.itropicsbuildws.ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO;
import com.wsout.habs.itropicsbuildws.ITropicsBuildWS;
import com.wsout.habs.itropicsbuildws.WsDeparturesVO;
import com.wsout.habs.itropicsbuildws.WsInGetToursOfBrandsVO;
import com.wsout.habs.itropicsbuildws.WsTourBrandCompanyInfoVO;
import com.wsout.habs.itropicsbuildws.WsTourBrandInfoVO;
import com.wsout.habs.itropicsbuildws.WsTourOfBrandVO;
import com.wsout.habs.itropicsbuildws.WsToursOfBrandsVO;
import com.wsout.habs.itropicsbuildws.WsToursWithSCListVO;

/**Test configuration quartz jobs and scheduler mode*/
@Ignore("This test create locks on brands")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class CleanBrandTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(CleanBrandTest.class);
	private String brandCode="BV";
		
	@Inject
	private ApplicationContext ctx;
	
	// Check prevents for clear tours	
	@Inject
	private ContentRepositoryService repositoryService;
	
	@Inject
	private BrandDAO brandDao;
	
	
	@Test
	public void runJobTest() throws Exception {		
		//prepare
		boolean exceptionExist=false;
		
		HabsTourDepartureService service=ctx.getBean(HabsTourDepartureService.class);		
		((HabsTourDepartureServiceImpl)service).getPort();
		
		ITropicsBuildWS port=Whitebox.getInternalState(service,"port");
		Assert.assertNotNull(port);
		
		ContentRepository filter=new ContentRepository();
		filter.getBrands().add(brandDao.findByBrandCode("BV"));
		int countBefore=repositoryService.getCountentRepositoryTourDepartureCount(filter,RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly);		
		try{			
			ITropicsBuildWS mockPort=Mockito.mock(ITropicsBuildWS.class);
			Whitebox.setInternalState(service, "port", mockPort);
							
			Mockito.when(mockPort.getToursOfBrands(Mockito.any(WsInGetToursOfBrandsVO.class))).thenReturn(createWsToursOfBrandsVO(true));

			JobExecutionContext mockCtx=PowerMockito.mock(JobExecutionContext.class);
			JobDetail mockJobDetail=PowerMockito.mock(JobDetail.class);
			JobDataMap mockJobDataMap=PowerMockito.mock(JobDataMap.class);
			PowerMockito.when(mockCtx.getJobDetail()).thenReturn(mockJobDetail);
			PowerMockito.when(mockJobDetail.getJobDataMap()).thenReturn(mockJobDataMap);	
			PowerMockito.when(mockJobDataMap.get(JobParams.BRAND_CODE.toString())).thenReturn(brandCode);
			
			DepartureSynchronizeJob job=new DepartureSynchronizeJob();
			//test		
			Whitebox.invokeMethod(job, "executeInternal", mockCtx);
		}
		catch(Exception e){
			logger.error("",e);
			exceptionExist=true;
		}
		finally{
			if(port!=null)
				Whitebox.setInternalState(service, "port", port);
		}
		//check
		Assert.assertFalse("DepartureSynchronizeJob should catch all exceptions",exceptionExist);
		
		int countAfter=repositoryService.getCountentRepositoryTourDepartureCount(filter,RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly);		
		Assert.assertThat("Count of tours were changed", countAfter, equalTo(countBefore));
	}

private WsToursOfBrandsVO createWsToursOfBrandsVO(boolean createExcetpion){
		
		WsToursOfBrandsVO response=new WsToursOfBrandsVO();
		
		if(createExcetpion){			
			response.setSuccessful(false);	
			response.setErrorMessage("my error message");
			response.getErrorMessagesArray().addAll(createComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO(10));		
		}else{
			WsTourBrandCompanyInfoVO sc=new WsTourBrandCompanyInfoVO(); 
			sc.setBrochureCode("xxxx");
			sc.setCompanyCode("BVUSAS");
			
			WsTourBrandInfoVO brand=new WsTourBrandInfoVO();
			brand.setBrandCode("BV");
			brand.getCompanyInfo().add(sc);
			
			WsTourOfBrandVO tour=new WsTourOfBrandVO();
			tour.setTourCode("IREXN10");
			tour.getBrandInfo().add(brand);
						
			response.setSuccessful(true);
			response.getTour().add(tour);
		}
		return response;
	}
	
	private WsDeparturesVO createWsDeparturesVO(boolean createExcetpion){
		
		WsDeparturesVO response=new WsDeparturesVO();
		if(createExcetpion){
			response.setSuccessful(false);	
			response.setErrorMessage("my error message");
			response.getErrorMessagesArray().addAll(createComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO(10));			
		}else{
			throw new UnsupportedOperationException("to - do");
		}
		return response;
	}
	
	private WsToursWithSCListVO createWsToursWithSCListVO(boolean createExcetpion){
		
		WsToursWithSCListVO response=new WsToursWithSCListVO();
		if(createExcetpion){
			response.setSuccessful(false);	
			response.setErrorMessage("my error message");
			response.getErrorMessagesArray().addAll(createComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO(10));			
		}else{
			throw new UnsupportedOperationException("to - do");
		}
		return response;
	}
	
	private List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> createComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO(int count){
		
		List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> result=Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO vo=new ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO();
			vo.setErrorCode("code:"+i);
			vo.setErrorMessage("message details:"+i);
			result.add(vo);
		}
		return result;
	}
	
}
