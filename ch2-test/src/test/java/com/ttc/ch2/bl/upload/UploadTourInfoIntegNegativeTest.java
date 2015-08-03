package com.ttc.ch2.bl.upload;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.google.common.collect.Iterables;
import com.ttc.ch2.bl.upload.common.MessageFinder;
import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.common.AuthenticatedExecutionPreparer;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.test.common.UploadHelper;


 
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, AuthenticatedExecutionPreparer.class})
@ContextConfiguration({"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class UploadTourInfoIntegNegativeTest extends BaseTest{
	
	@Inject 
	private UploadTourInfoService uploadService;		
	
	@Test
	public void testUploadTourInfoNoZipFile() throws  UploadServiceException, FileNotFoundException {
		
		UploadTourInfoFile uti = new UploadTourInfoFile();
		uti.setDateUpload(new Date());
		uti.setName(UploadHelper.genFileName());
		byte [] data=new byte[1];
		InputStream is =  new ByteArrayInputStream(data);
		boolean exceptionExist=false;	
		try
		{
			uploadService.addManualUploadTourInfoFile(uti, is,SampleGenerator.generateUserUi("ui-test"));		
		}
		catch(UploadServiceException e)
		{
			exceptionExist=true;			
			Assert.assertTrue(e.getOperationStatus().getStatus()==UploadTourInfoFileStatus.FAIL);			
			Assert.assertTrue("Can't finde message :"+TourInfoMessages.INCORRECT_ZIP,Iterables.tryFind(e.getOperationStatus().getMessageManager().getMainMessages(), new MessageFinder(TourInfoMessages.INCORRECT_ZIP)).isPresent());
		}	
		finally{
			IOUtils.closeQuietly(is);
		}
		Assert.assertTrue(exceptionExist);
	}
	
	@Test
	public void testUploadTourInfoIncorrectZip() throws  UploadServiceException {
		
		String path="touri.zip";
		UploadTourInfoFile uti = new UploadTourInfoFile();
		uti.setDateUpload(new Date());
		uti.setName(UploadHelper.genFileName());
		InputStream is =  getClass().getResourceAsStream(path);		
		is = getClass().getResourceAsStream(path);
	
		boolean exceptionExist=false;	
		try
		{
		uploadService.addManualUploadTourInfoFile(uti, is,SampleGenerator.generateUserUi("ui-test"));
		}
		catch(UploadServiceException e)
		{
			exceptionExist=true;			
			Assert.assertTrue(e.getOperationStatus().getStatus()==UploadTourInfoFileStatus.FAIL);
			Assert.assertTrue("Can't finde message :"+TourInfoMessages.INCORRECT_ZIP,Iterables.tryFind(e.getOperationStatus().getMessageManager().getMainMessages(), new MessageFinder(TourInfoMessages.INCORRECT_ZIP)).isPresent());
		}
		finally{
			IOUtils.closeQuietly(is);
		}
		Assert.assertTrue(exceptionExist);
	}
	

	
}
