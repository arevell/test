package com.ttc.ch2.bl.departure.habs;

import org.elasticsearch.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import com.wsout.habs.itropicsbuildws.ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO;
import com.wsout.habs.itropicsbuildws.ITropicsBuildWS;
import com.wsout.habs.itropicsbuildws.WsDeparturesVO;
import com.wsout.habs.itropicsbuildws.WsInGetTourDatesAndRatesVO;
import com.wsout.habs.itropicsbuildws.WsInGetToursOfBrandsVO;
import com.wsout.habs.itropicsbuildws.WsInGetToursWithSCListVO;
import com.wsout.habs.itropicsbuildws.WsToursOfBrandsVO;
import com.wsout.habs.itropicsbuildws.WsToursWithSCListVO;




public class HabsTourDepartureServiceTest {


	private HabsTourDepartureService service=new HabsTourDepartureServiceImpl();
	
	@Test
	public void getToursWithSCListTestVer001(){
		
		//prepare
		ITropicsBuildWS wsPort=Mockito.mock(ITropicsBuildWS.class);			
		Whitebox.setInternalState(service, "port", wsPort);
		
		WsToursWithSCListVO response=new WsToursWithSCListVO();
		response.setSuccessful(false);
		response.setErrorMessage("My main error");
		response.getErrorMessagesArray().add(getComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO());
				
		Mockito.when(wsPort.getToursWithSCList(Mockito.any(WsInGetToursWithSCListVO.class))).thenReturn(response);
		
		boolean errorExist=false;
		//test
		try{
			service.getToursWithSCList("BV");
		}
		catch(HabsTourDepartureServiceException e){
			errorExist=true;
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Tropics return error from service: 'getToursWithSCList'"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Main error:"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("List errors:"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my code:1"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my error message :1"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my response message error"));
		}		
		Assert.assertTrue("Test expected exception HabsTourDepartureServiceException",errorExist);
	}
	
	@Test
	public void getToursWithSCListTestVer002(){
		
		//prepare
		ITropicsBuildWS wsPort=Mockito.mock(ITropicsBuildWS.class);			
		Whitebox.setInternalState(service, "port", wsPort);
		
		WsToursWithSCListVO response=new WsToursWithSCListVO();
		response.setSuccessful(false);
//		response.setErrorMessage("My main error");
//		response.getErrorMessagesArray().add(getComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO());
		
		Mockito.when(wsPort.getToursWithSCList(Mockito.any(WsInGetToursWithSCListVO.class))).thenReturn(response);
		
		boolean errorExist=false;
		//test
		try{
			service.getToursWithSCList("BV");
		}
		catch(HabsTourDepartureServiceException e){
			errorExist=true;
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Tropics return error from service: 'getToursWithSCList'"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("Main error:"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("List errors:"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("my code:1"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("my error message :1"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("my response message error"));
		}		
		Assert.assertTrue("Test expected exception HabsTourDepartureServiceException",errorExist);
	}
	
	@Test
	public void getToursWithSCListTestVer003(){
		
		//prepare
		ITropicsBuildWS wsPort=Mockito.mock(ITropicsBuildWS.class);			
		Whitebox.setInternalState(service, "port", wsPort);
		
		WsToursWithSCListVO response=new WsToursWithSCListVO();
		response.setSuccessful(false);
		response.setErrorMessage("My main error");
//		response.getErrorMessagesArray().add(getComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO());
		
		Mockito.when(wsPort.getToursWithSCList(Mockito.any(WsInGetToursWithSCListVO.class))).thenReturn(response);
		
		boolean errorExist=false;
		//test
		try{
			service.getToursWithSCList("BV");
		}
		catch(HabsTourDepartureServiceException e){
			errorExist=true;
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Tropics return error from service: 'getToursWithSCList'"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Main error:"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("List errors:"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("my code:1"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("my error message :1"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("my response message error"));
		}		
		Assert.assertTrue("Test expected exception HabsTourDepartureServiceException",errorExist);
	}
	@Test
	public void getToursWithSCListTestVer004(){
		
		//prepare
		ITropicsBuildWS wsPort=Mockito.mock(ITropicsBuildWS.class);			
		Whitebox.setInternalState(service, "port", wsPort);
		
		WsToursWithSCListVO response=new WsToursWithSCListVO();
		response.setSuccessful(false);
		response.getErrorMessagesArray().add(getComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO());
		
		Mockito.when(wsPort.getToursWithSCList(Mockito.any(WsInGetToursWithSCListVO.class))).thenReturn(response);
		
		boolean errorExist=false;
		//test
		try{
			service.getToursWithSCList("BV");
		}
		catch(HabsTourDepartureServiceException e){
			errorExist=true;
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Tropics return error from service: 'getToursWithSCList'"));
			Assert.assertFalse("Incorrect error message",e.getMessage().contains("Main error:"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("List errors:"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my code:1"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my error message :1"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my response message error"));
		}		
		Assert.assertTrue("Test expected exception HabsTourDepartureServiceException",errorExist);
	}
	
	@Test()
	public void getToursWithSCListTestVer005(){
		
		//prepare
		ITropicsBuildWS wsPort=Mockito.mock(ITropicsBuildWS.class);			
		Whitebox.setInternalState(service, "port", wsPort);	
		Mockito.when(wsPort.getToursWithSCList(Mockito.any(WsInGetToursWithSCListVO.class))).thenReturn(null);
		
		boolean errorExist=false;
		//test
		try{
			service.getToursWithSCList("BV");
		}
		catch(HabsTourDepartureServiceException e){
			errorExist=true;
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("System expected response from service 'getToursWithSCList'"));
		}		
		Assert.assertTrue("Test expected exception HabsTourDepartureServiceException",errorExist);
	}
	
	
	@Test
	public void getToursOfBrandsTestVer001(){
		
		//prepare
		ITropicsBuildWS wsPort=Mockito.mock(ITropicsBuildWS.class);			
		Whitebox.setInternalState(service, "port", wsPort);
		
		WsToursOfBrandsVO response=new WsToursOfBrandsVO();
		response.setSuccessful(false);
		response.setErrorMessage("My main error");
		response.getErrorMessagesArray().add(getComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO());
				
		Mockito.when(wsPort.getToursOfBrands(Mockito.any(WsInGetToursOfBrandsVO.class))).thenReturn(response);
		
		boolean errorExist=false;
		//test
		try{
			service.getToursOfBrands(Lists.newArrayList("BV"));
		}
		catch(HabsTourDepartureServiceException e){
			errorExist=true;
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Tropics return error from service: 'getToursOfBrands'"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Main error:"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("List errors:"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my code:1"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my error message :1"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my response message error"));
		}		
		Assert.assertTrue("Test expected exception HabsTourDepartureServiceException",errorExist);
	}
	
	
	@Test
	public void getTourDatesAndRatesTestVer001(){
		
		//prepare
		ITropicsBuildWS wsPort=Mockito.mock(ITropicsBuildWS.class);			
		Whitebox.setInternalState(service, "port", wsPort);
		
		WsDeparturesVO response=new WsDeparturesVO();
		response.setSuccessful(false);
		response.setErrorMessage("My main error");
		response.getErrorMessagesArray().add(getComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO());
				
		Mockito.when(wsPort.getTourDatesAndRates(Mockito.any(WsInGetTourDatesAndRatesVO.class))).thenReturn(response);
		
		boolean errorExist=false;
		//test
		try{
			service.getTourDatesAndRates("tourCode","BV","apikeys");
		}
		catch(HabsTourDepartureServiceException e){
			errorExist=true;
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Tropics return error from service: 'getTourDatesAndRates'"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("Main error:"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("List errors:"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my code:1"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my error message :1"));
			Assert.assertTrue("Incorrect error message",e.getMessage().contains("my response message error"));
		}		
		Assert.assertTrue("Test expected exception HabsTourDepartureServiceException",errorExist);
	}
	
	
	private ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO getComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO(){
		ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO err=new ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO();
		err.setErrorCode("my code:1");
		err.setErrorMessage("my error message :1");
		err.getResponseMessages().add("my response message error");
		return err;
	}
}
