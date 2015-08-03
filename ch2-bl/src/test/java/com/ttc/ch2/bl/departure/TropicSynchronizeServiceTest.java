package com.ttc.ch2.bl.departure;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.google.common.collect.Lists;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.common.BlSampleGenerator;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.departure.TourDepartureHistory.TourDepartureStatus;


@RunWith(PowerMockRunner.class)
@PrepareForTest(TropicDepartureSynchronizeServiceImpl.class)
public class TropicSynchronizeServiceTest {
		
	private TropicDepartureSynchronizeService tourServiceSpy =null;
	private TourDepartureService tourDepartureService;	
	private ContentRepositoryService contentRepositoryService;
	
	private BrandDAO brandDAO;
	@Before
	  public void init() throws Exception
	  {
		 tourDepartureService=PowerMockito.mock(TourDepartureService.class);	  
		 brandDAO=PowerMockito.mock(BrandDAO.class);	  
		 contentRepositoryService=PowerMockito.mock(ContentRepositoryService.class);	  
		 		 
		 tourServiceSpy = PowerMockito.spy(new TropicDepartureSynchronizeServiceImpl());
//		 tourServiceSpy = PowerMockito.mock(TropicDepartureSynchronizeServiceImpl.class);
		 
		 Whitebox.setInternalState(tourServiceSpy, "tourDepartureService",tourDepartureService);	
		 Whitebox.setInternalState(tourServiceSpy, "brandDAO",brandDAO);	
		 Whitebox.setInternalState(tourServiceSpy, "contentRepositoryService",contentRepositoryService);	
		 
		 
//		 PowerMockito.when(tourServiceSpy.departureSynchronize(any(OperationStatus.class))).thenCallRealMethod();
//		 PowerMockito.when(tourServiceSpy, "getApiKey").thenCallRealMethod();
//		 PowerMockito.when(tourServiceSpy, "generateTourDepartureForBrand", any(), any(), any()).thenCallRealMethod();
//		 PowerMockito.when(tourServiceSpy, "logException", any(OperationStatus.class), any(Throwable.class), any(TropicSynchronizeMessages.class),anyVararg()).thenCallRealMethod();
//		 PowerMockito.when(tourServiceSpy, "logMessage", any(OperationStatus.class), any(TropicSynchronizeMessages.class), anyVararg()).thenCallRealMethod();
//		 PowerMockito.when(tourServiceSpy, "logMessage", any(OperationStatus.class), any(TropicSynchronizeMessages.class)).thenCallRealMethod();
		 		 
//		 PowerMockito.when(tourServiceSpy, "logException", any(),any(),any(), anyVararg()).thenCallRealMethod();
	  }
	
	  @After
	  public void afterMethod() {
		reset(tourDepartureService);
		reset(brandDAO);
	    reset(tourServiceSpy);	   
	 }
	

	@Test
	public void getEmptyBrandListTest() throws Exception
	{
		//prepare
		OperationStatus opStatus=new OperationStatus();
		PowerMockito.doReturn(Lists.newArrayList()).when(tourServiceSpy, "getBrands", any());		
		
		//Test	
		opStatus=tourServiceSpy.departureSynchronize(opStatus);		
		//when
		Assert.assertTrue(opStatus.getStatus()==TourDepartureStatus.WARNING_OPERATION_END);
		//Assert.assertEquals(2,opStatus.getMessageManager().getMessages().size());
//		Assert.assertEquals(TropicSynchronizeMessages.NO_BRANDS,opStatus.getEnum(1));
	}
	
	
	@Test
	public void getBrandListExceptionTest() throws Exception
	{
		//prepare		
		PowerMockito.when(brandDAO, "findAll").thenThrow(Exception.class);
		OperationStatus opStatus=new OperationStatus();
		boolean exception=true;
		//Test	
		try
		{
		opStatus=tourServiceSpy.departureSynchronize(opStatus);
		exception=false;
		}
		catch (TropicSynchronizeServiceException e)
		{			
			Assert.assertTrue(e.getOpStatus().getStatus()==TourDepartureStatus.ERROR_OPERATION_END);
			//Assert.assertEquals(3,e.getOpStatus().getMessageManager().getMessages().size());
//			Assert.assertEquals(TropicSynchronizeMessages.READ_BRANDS_FROM_DB_EXCEPTION,e.getOpStatus().getEnum(1));
		}
		//when		
		Assert.assertTrue(exception);
	}
	
	@Test	
	public void getToursExceptionTest() throws Exception
	{
		//prepare	
		OperationStatus opStatus=new OperationStatus();
		PowerMockito.doReturn(Lists.newArrayList(BlSampleGenerator.generateBrand())).when(tourServiceSpy, "getBrands", any());		
//		PowerMockito.doThrow(new TourDepartureServiceException("Mock exception")).when(tourServiceSpy, "invokeServiceGetTours",  any(OperationStatus.class),anyString(),anyString());
//		 PowerMockito.doReturn(true).when(spy, "doTheGamble", anyString(), anyInt());
		PowerMockito.when(tourDepartureService, "getToursOfBrands", anyListOf(String.class)).thenThrow(TourDepartureServiceException.class);
		boolean exception=true;
		//Test	
		try
		{
			tourServiceSpy.departureSynchronize(opStatus);
			exception=false;
		}
		catch (TropicSynchronizeServiceException e)
		{			
			Assert.assertTrue(e.getOpStatus().getStatus()==TourDepartureStatus.ERROR_OPERATION_END);
			//Assert.assertEquals(4,e.getOpStatus().getMessageManager().getMessages().size());
//			Assert.assertEquals(TropicSynchronizeMessages.GET_TOURS_FROM_TROPIC_EXCEPTION,e.getOpStatus().getEnum(3));
		}
		//when		
		Assert.assertTrue("Test Without exception",exception);
	}
	
}
