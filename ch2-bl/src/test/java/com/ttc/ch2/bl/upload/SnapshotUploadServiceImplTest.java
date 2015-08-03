package com.ttc.ch2.bl.upload;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;

import org.elasticsearch.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.ttc.ch2.api.ccapi.v3.Message;
import com.ttc.ch2.api.ccapi.v3.GetTourDataUploadStatusRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourDataUploadStatusResponse;
import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.common.BlSampleGenerator;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.BrandLock;
import com.ttc.ch2.domain.upload.UploadStatus;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.user.UserCCAPI;


@RunWith(PowerMockRunner.class)   
@PrepareForTest(SnapshotUploadServiceImpl.class)
public class SnapshotUploadServiceImplTest {
	
	private static String TOKEN="token-xxx";
	
	private SnapshotUploadService snapshotUploadServiceSpy=null; 
	
	private LockBrandService lockBrandService;
		
	private UploadStatusService uploadStatusService;
		
	private UploadService uploadService;
	
	@Before
	  public void init() throws Exception
	  {
		lockBrandService=PowerMockito.mock(LockBrandService.class);	  
		uploadStatusService=PowerMockito.mock(UploadStatusService.class);	  
		uploadService=PowerMockito.mock(UploadService.class);	  
		 		 
		 snapshotUploadServiceSpy = PowerMockito.spy(new SnapshotUploadServiceImpl());
		 		 
		 Whitebox.setInternalState(snapshotUploadServiceSpy, "lockBrandService",lockBrandService);	
		 Whitebox.setInternalState(snapshotUploadServiceSpy, "uploadStatusService",uploadStatusService);	
		 Whitebox.setInternalState(snapshotUploadServiceSpy, "uploadService",uploadService);			 			 
	  }
	

	  @After
	  public void afterMethod() {
		reset(lockBrandService);
		reset(uploadStatusService);
	    reset(uploadService);	      
	 }
	
	@Test
	public void testRequestWithOutFileName() throws Exception{
		//prepare
		GetTourDataUploadStatusRequest request=new GetTourDataUploadStatusRequest();
		request.setBrandCode("BC");
		request.setSecurityKey(TOKEN);
		request.setFileName(null);
				
		UserCCAPI userCcapi=BlSampleGenerator.generateUser("Sample user ccapi");		
		PowerMockito.doReturn(userCcapi).when(snapshotUploadServiceSpy, "getLogedUser");
		
		PowerMockito.when(uploadService, "getUploadTourInfoFileByName", any()).thenReturn(null);
		PowerMockito.when(lockBrandService, "findBrandLockByCode", any()).thenReturn(null);
		//test
		GetTourDataUploadStatusResponse response=snapshotUploadServiceSpy.getSnapshotUploadTour(request);	
		//check
		Assert.assertTrue("Response should end successful",response.isSuccessful());
		Assert.assertNull("Response should not have Message context",response.getMessageContext());
		Assert.assertNull("Response should not have UploadFileStatus",response.getUploadFileStatus());	
		Assert.assertNotNull("Response should have CurrentBrandStatus",response.getCurrentBrandStatus());
		Assert.assertTrue("Status of brand is incorrect",Brand.Status.valueOf(response.getCurrentBrandStatus().getBrandStatus())==Brand.Status.InActive);
		Assert.assertNull("CurrentBrandStatus should not have Snapshot",response.getCurrentBrandStatus().getSnapshot());
	}
	
	@Test
	public void testRequestIncorrectFileName() throws Exception{
		//prepare
		GetTourDataUploadStatusRequest request=new GetTourDataUploadStatusRequest();
		request.setBrandCode("BC");
		request.setSecurityKey(TOKEN);
		request.setFileName(BlSampleGenerator.genFileName("BC"));
				
		
		UserCCAPI userCcapi=BlSampleGenerator.generateUser("Sample user ccapi");		
		PowerMockito.doReturn(userCcapi).when(snapshotUploadServiceSpy, "getLogedUser");

		PowerMockito.when(uploadService, "getUploadTourInfoFileByName", any()).thenReturn(null);
		PowerMockito.when(lockBrandService, "findBrandLockByCode", any()).thenReturn(null);
		//test
		GetTourDataUploadStatusResponse response=snapshotUploadServiceSpy.getSnapshotUploadTour(request);	
		//check
		Assert.assertTrue("Response should end successful",response.isSuccessful());
		Assert.assertNotNull("Response does not have Message context",response.getMessageContext());
		Assert.assertTrue("Response does not have Message context",response.getMessageContext().getMessage().size()==1);
		for (Message msg : response.getMessageContext().getMessage()) {
			Assert.assertTrue("InterpolatedMessage is incorrect",msg.getInterpolatedMessage().contains(request.getFileName()));			
		}
		Assert.assertNotNull("Response should have CurrentBrandStatus",response.getCurrentBrandStatus());
		Assert.assertTrue("Status of brand is incorrect",Brand.Status.valueOf(response.getCurrentBrandStatus().getBrandStatus())==Brand.Status.InActive);
		Assert.assertNull("CurrentBrandStatus should not have Snapshot",response.getCurrentBrandStatus().getSnapshot());
	}
	
	
	@Test
	public void testRequestCorrectFileName() throws Exception{
		//prepare
		
		UploadTourInfoFile sUfile=BlSampleGenerator.generateUploadTourInfoFile(1);
		
		GetTourDataUploadStatusRequest request=new GetTourDataUploadStatusRequest();
		request.setBrandCode(sUfile.getBrand().getCode());
		request.setSecurityKey(TOKEN);
		request.setFileName(sUfile.getName());
		
		UserCCAPI userCcapi=BlSampleGenerator.generateUser("Sample user ccapi");		
		PowerMockito.doReturn(userCcapi).when(snapshotUploadServiceSpy, "getLogedUser");


		PowerMockito.when(uploadService, "getUploadTourInfoFileByName", any()).thenReturn(sUfile);
		PowerMockito.when(lockBrandService, "findBrandLockByCode", any()).thenReturn(null);
		//test
		GetTourDataUploadStatusResponse response=snapshotUploadServiceSpy.getSnapshotUploadTour(request);	
		//check
		Assert.assertTrue("Response should end successful",response.isSuccessful());
		Assert.assertNotNull("Response should have CurrentBrandStatus",response.getCurrentBrandStatus());
		Assert.assertTrue("Status of brand is incorrect",Brand.Status.valueOf(response.getCurrentBrandStatus().getBrandStatus())==Brand.Status.InActive);
		Assert.assertNull("CurrentBrandStatus should not have Snapshot",response.getCurrentBrandStatus().getSnapshot());
		Assert.assertNotNull("Response should have UploadFileStatus",response.getUploadFileStatus());
		Assert.assertTrue("File name is incorect",response.getUploadFileStatus().getFileName().equals(sUfile.getName()));
		Assert.assertNull("Response should not have Message context",response.getMessageContext());
	
}
  
	
		
		@Test
		public void testRequestCorrectFileNameAndBrandLock() throws Exception{
			//prepare
			
			UploadTourInfoFile sUfile=BlSampleGenerator.generateUploadTourInfoFile(1);
			UploadStatus sUs=BlSampleGenerator.generateUploadStatus(1);
			BrandLock sBl=BlSampleGenerator.generateBrandLock(1);
			
			GetTourDataUploadStatusRequest request=new GetTourDataUploadStatusRequest();
			request.setBrandCode(sUfile.getBrand().getCode());
			request.setSecurityKey(TOKEN);
			request.setFileName(sUfile.getName());
			
			UserCCAPI userCcapi=BlSampleGenerator.generateUser("Sample user ccapi");		
			PowerMockito.doReturn(userCcapi).when(snapshotUploadServiceSpy, "getLogedUser");
	
			PowerMockito.when(uploadService, "getUploadTourInfoFileByName", any()).thenReturn(sUfile);
			PowerMockito.when(lockBrandService, "findBrandLockByCode", any()).thenReturn(sBl);
			PowerMockito.when(uploadStatusService, "getListOfProccess").thenReturn(Lists.newArrayList(sUs));
			//test
			GetTourDataUploadStatusResponse response=snapshotUploadServiceSpy.getSnapshotUploadTour(request);	
			//check
			Assert.assertTrue("Response should end successful",response.isSuccessful());
			Assert.assertNotNull("Response should have CurrentBrandStatus",response.getCurrentBrandStatus());
			Assert.assertTrue("Status of brand is incorrect",Brand.Status.valueOf(response.getCurrentBrandStatus().getBrandStatus())==Brand.Status.UploadTourInfo);
			Assert.assertNotNull("CurrentBrandStatus should  have Snapshot",response.getCurrentBrandStatus().getSnapshot());
			Assert.assertNotNull("Response should have UploadFileStatus",response.getUploadFileStatus());
			Assert.assertTrue("File name is incorect",response.getUploadFileStatus().getFileName().equals(sUfile.getName()));
			Assert.assertNull("Response should not have Message context",response.getMessageContext());
	}
		
		
		@Test
		public void testCantFindUser() throws Exception{
			//prepare
			
			UploadTourInfoFile sUfile=BlSampleGenerator.generateUploadTourInfoFile(1);
			UploadStatus sUs=BlSampleGenerator.generateUploadStatus(1);
			BrandLock sBl=BlSampleGenerator.generateBrandLock(1);
			
			GetTourDataUploadStatusRequest request=new GetTourDataUploadStatusRequest();
			request.setBrandCode(sUfile.getBrand().getCode());
			request.setSecurityKey(TOKEN);
			request.setFileName(sUfile.getName());
					
			PowerMockito.doThrow(new IllegalStateException("user not found")).when(snapshotUploadServiceSpy, "getLogedUser");
	
			PowerMockito.when(uploadService, "getUploadTourInfoFileByName", any()).thenReturn(sUfile);
			PowerMockito.when(lockBrandService, "findBrandLockByCode", any()).thenReturn(sBl);
			PowerMockito.when(uploadStatusService, "getListOfProccess").thenReturn(Lists.newArrayList(sUs));
			//test
			GetTourDataUploadStatusResponse response=snapshotUploadServiceSpy.getSnapshotUploadTour(request);	
			//check
			Assert.assertFalse("Response should not end successful",response.isSuccessful());
			Assert.assertNull("Response should not have CurrentBrandStatus",response.getCurrentBrandStatus());			
			Assert.assertNull("Response should not have UploadFileStatus",response.getUploadFileStatus());
			Assert.assertNotNull("Response should have Message context",response.getMessageContext());
			Assert.assertTrue("Response should have one Message",response.getMessageContext().getMessage().size()==1);
			Assert.assertTrue("Message should have exception content",response.getMessageContext().getMessage().get(0).getInterpolatedMessage().contains("system error occurred: Exception"));
	}
		
		@Test
		public void testPermisionDeniedForBrand() throws Exception{
			//prepare
			
			UploadTourInfoFile sUfile=BlSampleGenerator.generateUploadTourInfoFile(1);
			UploadStatus sUs=BlSampleGenerator.generateUploadStatus(1);
			BrandLock sBl=BlSampleGenerator.generateBrandLock(1);
			
			GetTourDataUploadStatusRequest request=new GetTourDataUploadStatusRequest();
			request.setBrandCode(sUfile.getBrand().getCode());
			request.setSecurityKey(TOKEN);
			request.setFileName(sUfile.getName());
			
			UserCCAPI userCcapi=BlSampleGenerator.generateUser("Sample user ccapi");
			userCcapi.getCcapiAuthorities().iterator().next().getSellingCompany().getBrand().setCode("NN");
			PowerMockito.doReturn(userCcapi).when(snapshotUploadServiceSpy, "getLogedUser");
			
			PowerMockito.when(uploadService, "getUploadTourInfoFileByName", any()).thenReturn(sUfile);
			PowerMockito.when(lockBrandService, "findBrandLockByCode", any()).thenReturn(sBl);
			PowerMockito.when(uploadStatusService, "getListOfProccess").thenReturn(Lists.newArrayList(sUs));
			//test
			GetTourDataUploadStatusResponse response=snapshotUploadServiceSpy.getSnapshotUploadTour(request);	
			//check
			Assert.assertFalse("Response should not end successful",response.isSuccessful());
			Assert.assertNull("Response should not have CurrentBrandStatus",response.getCurrentBrandStatus());			
			Assert.assertNull("Response should not have UploadFileStatus",response.getUploadFileStatus());
			Assert.assertNotNull("Response should have Message context",response.getMessageContext());
			Assert.assertTrue("Response should have one Message",response.getMessageContext().getMessage().size()==1);
			Assert.assertTrue("Message should have exception content",response.getMessageContext().getMessage().get(0).getInterpolatedMessage().contains("Permission denied for brand:"));
		}
		
		@Test
		public void testIncorectBrandCodeInFileName() throws Exception{
			//prepare
			
			UploadTourInfoFile sUfile=BlSampleGenerator.generateUploadTourInfoFile(1);
			UploadStatus sUs=BlSampleGenerator.generateUploadStatus(1);
			BrandLock sBl=BlSampleGenerator.generateBrandLock(1);
			
			GetTourDataUploadStatusRequest request=new GetTourDataUploadStatusRequest();
			request.setBrandCode(sUfile.getBrand().getCode());
			request.setSecurityKey(TOKEN);
			request.setFileName(BlSampleGenerator.genFileName("NN"));
			
			UserCCAPI userCcapi=BlSampleGenerator.generateUser("Sample user ccapi");
			PowerMockito.doReturn(userCcapi).when(snapshotUploadServiceSpy, "getLogedUser");
			
			PowerMockito.when(uploadService, "getUploadTourInfoFileByName", any()).thenReturn(sUfile);
			PowerMockito.when(lockBrandService, "findBrandLockByCode", any()).thenReturn(sBl);
			PowerMockito.when(uploadStatusService, "getListOfProccess").thenReturn(Lists.newArrayList(sUs));
			//test
			GetTourDataUploadStatusResponse response=snapshotUploadServiceSpy.getSnapshotUploadTour(request);	
			//check
			Assert.assertFalse("Response should not end successful",response.isSuccessful());
			Assert.assertNull("Response should not have CurrentBrandStatus",response.getCurrentBrandStatus());			
			Assert.assertNull("Response should not have UploadFileStatus",response.getUploadFileStatus());
			Assert.assertNotNull("Response should have Message context",response.getMessageContext());
			Assert.assertTrue("Response should have one Message",response.getMessageContext().getMessage().size()==1);
			Assert.assertTrue("Message should have exception content",response.getMessageContext().getMessage().get(0).getInterpolatedMessage().contains("Brand of request is different from the file name:"));
		}
	  
	  
}

