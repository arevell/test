package com.ttc.ch2.bl.upload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.google.common.collect.Iterables;
import com.ttc.ch2.bl.upload.common.JobExecutor;
import com.ttc.ch2.bl.upload.common.MessageFinder;
import com.ttc.ch2.bl.upload.common.OperationStatus;
import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.common.AuthenticatedExecutionPreparer;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.dao.upload.UploadTourInfoDAO;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.test.common.UploadHelper;
import com.ttc.test.common.ZipHelper;


//This test need work in enviroment with quartz turn off content-Dev has enabled quartz 
@Ignore  
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, AuthenticatedExecutionPreparer.class})
@ContextConfiguration({"classpath:/META-INF/spring/blCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class UploadTourInfoBatchIntegTest extends BaseTest{
	
	
	private static final Logger logger = LoggerFactory.getLogger(UploadTourInfoBatchIntegTest.class);

	@Inject
	private ApplicationContext ctx;
	
	@Inject 
	private UploadTourInfoService uploadTourInfoService;
	
	@Inject 
	private UploadTourInfoDAO uploadTourInfoDAO;
	
	@Inject
	private UserService userService;
	
	@Inject
	private UploadService uploadService;
		
	@Test
	public void testUploadTourInfoSchemaValidateErrorVer1() throws  UploadServiceException {
						
		//prepare
		String path="tourinfo.zip";
		UploadTourInfoFile uti = new UploadTourInfoFile();
		uti.setDateUpload(new Date());
		uti.setName(UploadHelper.genFileName());
		InputStream is =  getClass().getResourceAsStream(path);		
		User user=userService.findUserByName("gui-usr-all-brand");
		Assert.assertTrue("Test cant prepare user",user != null && user instanceof UserGui);
		boolean exceptionExist=false;	
		try
		{
			uploadTourInfoService.addManualUploadTourInfoFile(uti, is,(UserGui) user);		
		}
		catch(Exception e)
		{
			logger.error("",e);
			exceptionExist=true;						
		}	
		finally{
			IOUtils.closeQuietly(is);
		}		
		Assert.assertFalse("Test cant prepare data",exceptionExist);
		
		UploadTourInfoBatchService batchTest=ctx.getBean(UploadTourInfoBatchService.class);
		//test
		exceptionExist=false;
		try{
			batchTest.invokeProcess("CH",PowerMockito.mock(JobExecutor.class));
		}catch(UploadServiceException e){
			exceptionExist=true;
			Assert.assertTrue(e.getOperationStatus().getStatus()==UploadTourInfoFileStatus.FAIL);
			Assert.assertTrue("Can't finde message :"+TourInfoMessages.INCORRECT_ZIP,Iterables.tryFind(e.getOperationStatus().getMessageManager().getMainMessages(), new MessageFinder(TourInfoMessages.INCORRECT_ZIP)).isPresent());
		}
		
		Assert.assertTrue(exceptionExist);
	}
	

//	@Test
	public void testUploadTourInfoSchemaValidateErrorVer2() throws  UploadServiceException {
		
		// prepare
		String path="tourinfo2.zip";		
		UploadTourInfoFile uti = new UploadTourInfoFile();
		uti.setDateUpload(new Date());
		uti.setName(UploadHelper.genFileName());
		InputStream is =  getClass().getResourceAsStream(path);				
		User user=userService.findUserByName("gui-usr-all-brand");
		Assert.assertTrue("Test cant prepare user",user != null && user instanceof UserGui);
		boolean exceptionExist=false;	
		try
		{
			uploadTourInfoService.addManualUploadTourInfoFile(uti, is,(UserGui) user);		
		}
		catch(Exception e)
		{
			logger.error("",e);
			exceptionExist=true;						
		}	
		finally{
			IOUtils.closeQuietly(is);
		}
		Assert.assertFalse("Test cant prepare data",exceptionExist);	
		
		
		UploadTourInfoBatchService batchTest=ctx.getBean(UploadTourInfoBatchService.class);
		//test
		exceptionExist=false;
		try{
			batchTest.invokeProcess("CH",PowerMockito.mock(JobExecutor.class));
		}catch(UploadServiceException e){
			exceptionExist=true;
			Assert.assertTrue(e.getOperationStatus().getStatus()==UploadTourInfoFileStatus.FAIL);
			Assert.assertTrue("Can't finde message :"+TourInfoMessages.INCORRECT_ZIP,Iterables.tryFind(e.getOperationStatus().getMessageManager().getMainMessages(), new MessageFinder(TourInfoMessages.INCORRECT_ZIP)).isPresent());
		}
		
		Assert.assertTrue(exceptionExist);		
	}
	
//	@Test
	public void testUploadTourInfoOK() throws  UploadServiceException, IOException {
		//prepare
		String path="ASAC14B02.xml";	
		String file=IOUtils.toString(getClass().getResourceAsStream(path));
		String content=file.replace("Worldwide Guided Vacations", "Worldwide Guided Vacations:"+RandomStringUtils.random(10,true,true));		
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		ZipHelper.createZip(out, IOUtils.toInputStream(content), path);		
		InputStream is=new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());		
		UploadTourInfoFile uti = new UploadTourInfoFile();
		uti.setDateUpload(new Date());
		uti.setName(UploadHelper.genFileName("BV"));
		User user=userService.findUserByName("gui-usr-all-brand");
		Assert.assertTrue("Test cant prepare user",user != null && user instanceof UserGui);
					
		// test upload
		OperationStatus op=null;
		try{
		op=uploadTourInfoService.addManualUploadTourInfoFile(uti, is,(UserGui) user);
		Assert.assertTrue(op.getStatus()==UploadTourInfoFileStatus.PRE_PROCESSING);
		}
		finally{
			IOUtils.closeQuietly(is);
		}		
		
		//test batch		
		UploadTourInfoBatchService batchTest=ctx.getBean(UploadTourInfoBatchService.class);
		boolean exceptionExist=false;
		try{
			batchTest.invokeProcess("BV",PowerMockito.mock(JobExecutor.class));
		}catch(UploadServiceException e){
			logger.error("",e);
			exceptionExist=true;
		}		
		Assert.assertFalse(exceptionExist);		
		UploadTourInfoFile result=uploadTourInfoDAO.find(op.getUploadTourInfoFile().getId());
		Assert.assertTrue("Operation end without success["+result.getStatus()+"]",result.getStatus()==UploadTourInfoFileStatus.SUCCESS || result.getStatus()==UploadTourInfoFileStatus.WARNING);		
	}
	
//	@Test
	public void testUploadTourInfoMD5Warn() throws  UploadServiceException, IOException {
		
		//prepare
		String tourCode="ASAC14B02";
		String path=tourCode+".xml";	
		String file=IOUtils.toString(getClass().getResourceAsStream(path));
		String content=file.replace("Worldwide Guided Vacations", "Worldwide Guided Vacations:"+RandomStringUtils.random(10,true,true));		
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		ZipHelper.createZip(out, IOUtils.toInputStream(content), path);		
		InputStream is=new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());		
		UploadTourInfoFile uti = new UploadTourInfoFile();
		uti.setDateUpload(new Date());
		uti.setName(UploadHelper.genFileName("BV"));
		User user=userService.findUserByName("gui-usr-all-brand");
		Assert.assertTrue("Test cant prepare user",user != null && user instanceof UserGui);
		
		
		// import part 1
		OperationStatus op=null;
		try{
		op=uploadTourInfoService.addManualUploadTourInfoFile(uti, is,(UserGui) user);
		Assert.assertTrue(op.getStatus()==UploadTourInfoFileStatus.PRE_PROCESSING);
		}
		finally{
			IOUtils.closeQuietly(is);
		}	
		
		
		//batch proccess part 1		
		UploadTourInfoBatchService batchTest=ctx.getBean(UploadTourInfoBatchService.class);
		boolean exceptionExist=false;
		try{
					batchTest.invokeProcess("BV",PowerMockito.mock(JobExecutor.class));
				}catch(UploadServiceException e){
					logger.error("",e);
					exceptionExist=true;
				}		
		Assert.assertFalse(exceptionExist);		
		UploadTourInfoFile result=uploadTourInfoDAO.find(op.getUploadTourInfoFile().getId());
		Assert.assertTrue("Operation end without success["+result.getStatus()+"]",result.getStatus()==UploadTourInfoFileStatus.SUCCESS || result.getStatus()==UploadTourInfoFileStatus.WARNING);	
							
		InputStream is2=new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
		UploadTourInfoFile uti2 = new UploadTourInfoFile();
		uti2.setDateUpload(new Date());
		uti2.setName(UploadHelper.genFileName("BV"));
		
		// import part 2
		OperationStatus op2=null;
		try{
			op2=uploadTourInfoService.addManualUploadTourInfoFile(uti2, is2,(UserGui) user);
			Assert.assertTrue(op.getStatus()==UploadTourInfoFileStatus.PRE_PROCESSING);			
			}
			finally{
				IOUtils.closeQuietly(is2);
			}
		
		//batch proccess part 2			
		//test warning
		exceptionExist=false;
		try{
					batchTest.invokeProcess("BV",PowerMockito.mock(JobExecutor.class));
				}catch(UploadServiceException e){
					logger.error("",e);
					exceptionExist=true;
				}		
		Assert.assertFalse(exceptionExist);		
		result=uploadService.getFullData(op2.getUploadTourInfoFile().getId());		
		Assert.assertTrue("Operation end without success",result.getStatus()==UploadTourInfoFileStatus.SUCCESS || result.getStatus()==UploadTourInfoFileStatus.WARNING);
		
		
		boolean md5WarningExist=false;
		for (TIComment comment : result.getComments()) {
			if(StringUtils.isNotEmpty(comment.getContent())){
				if(comment.getContent().contains("INF-4021")){
					md5WarningExist=true;
					break;
				}
			}			
		}
		
		Assert.assertTrue("Test cant find md5 warning",md5WarningExist);	
	}
	
  
	
}
