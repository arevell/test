package com.ttc.ch2.bl.upload;

import java.io.InputStream;
import java.util.Date;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.bl.upload.validator.TiZipStreamValidator;
import com.ttc.ch2.common.AuthenticatedExecutionPreparer;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.dao.upload.UploadTourInfoDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileSource;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.ch2.domain.user.UserCCAPI;

@RunWith(SpringJUnit4ClassRunner.class)
//@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, AuthenticatedExecutionPreparer.class})
@ContextConfiguration({"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ZipBombCheckerTest extends BaseTest {

	@Inject
	private TiZipStreamValidator tiZipStreamValidator;
	
	@Inject
	private UploadTourInfoDAO uploadTourInfoDAO;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private UserCCAPIDAO userCCAPIDAO;
	
	@Value("${ch2.tizip.maxfiles}")
	private long zipMaxFiles;
	
	@Value("${ch2.tizip.maxzipsforbrand}")
	private int maxZipsForBrand;
	
	@Value("${ch2.ti.maxsize}")
	private long zipFileMaxSize;
	
	@Test
	public void checkZipBombTooManyXmlTest() {
		InputStream is = ZipBombCheckerTest.class.getResourceAsStream("Too-many-xml-in-zip.zip");
		try {
			tiZipStreamValidator.validZipExcludeZipBomb(is);
			Assert.fail("Validator failed");
		}catch(Exception e) {
			Assert.assertEquals(e.getMessage(), TourInfoMessages.getMessage(TourInfoMessages.ZIP_MAX_FILES_EXCEEDED, zipMaxFiles));
		}
		
	}
	
	@Test
	public void checkZipBombTooBigXmlTest() {
		InputStream is = ZipBombCheckerTest.class.getResourceAsStream("Too-big-xml-inside.zip");
		try {
			tiZipStreamValidator.validZipExcludeZipBomb(is);
			Assert.fail("Validator failed");
		}catch(Exception e) {
			Assert.assertEquals(e.getMessage(), TourInfoMessages.getMessage(TourInfoMessages.ZIP_XML_SIZE_EXCEEDED, zipFileMaxSize));
		}
	}
	
	@Test
	@Rollback(true)
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void checkToManyRejectedZips() {
		Brand brand=brandDAO.findByBrandCode("CH");
		Date now = new Date();
		UserCCAPI user = userCCAPIDAO.findByToken("token-xxx");
		for(int i=0; i <=  maxZipsForBrand; i++) {
			UploadTourInfoFile utif = new UploadTourInfoFile();
			Date utifdate = new Date(now.getTime()+(i*2000));
			utif.setBrand(brand);
			utif.setDateUpload(utifdate);
			utif.setStatus(UploadTourInfoFileStatus.REJECTED);
			utif.setSourceUploadFile(UploadTourInfoFileSource.API);
			utif.setName("CH-FAKE-"+i);
			utif.setUser(user);
			uploadTourInfoDAO.save(utif);
		}
		UploadTourInfoFile utif = new UploadTourInfoFile();
		utif.setName("CH-FAKE-main");
		try {
			tiZipStreamValidator.validateZipUplodPermission(utif);
			Assert.fail("Validator failed");
		}catch(Exception e) {
			Assert.assertEquals(e.getMessage(), TourInfoMessages.getMessage(TourInfoMessages.MAX_ZIPS_EXCEEDED, maxZipsForBrand));
		}
	}
	
}
