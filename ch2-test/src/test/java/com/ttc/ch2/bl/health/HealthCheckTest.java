package com.ttc.ch2.bl.health;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.bl.diag.Diagnostic;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:/META-INF/spring/testForSearchCtx.xml","classpath:/META-INF/spring/daoCtx.xml", "classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/searchCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class HealthCheckTest {

	@Inject
	private Diagnostic diagnostic;
		
	@Test
	public void isCH1DownloadConnectionOKTest() {
		boolean result = diagnostic.isCH1DownloadConnectionOK();
		assertTrue(result);
	}
	
	@Test 
	public void isCH1UploadConnectionOKTest() {
		boolean result = diagnostic.isCH1UploadConnectionOK();
		assertTrue(result);
	}
	
	@Test 
	public void isClusterStateOKTest() {
		boolean result = diagnostic.isClusterStateOK();
		assertTrue(result);
	}
	
	@Test 
	public void isDBCodingSetOKTest() {
		boolean result = diagnostic.isDBCodingSetOK();
		assertTrue(result);
	}
	
	@Test 
	public void isElasticSearchOKTest() {
		boolean result = diagnostic.isElasticSearchOK();
		assertTrue(result);
	}
	
	@Test 
	public void isTempDirOKTest() {
		boolean result = diagnostic.isTempDirOK();
		assertTrue(result);
	}
	
	@Test 
	public void isTropicsConnectivityOKTest() {
		boolean result = diagnostic.isTropicsConnectivityOK();
		assertTrue(result);
	}
	
	@Test 
	public void getMatchingTIandTDTest() {
		long result = diagnostic.getMatchingTIandTD();
		assertTrue(result > 0l);
	}
}
