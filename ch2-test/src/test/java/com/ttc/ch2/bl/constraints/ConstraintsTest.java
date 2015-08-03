package com.ttc.ch2.bl.constraints;

import java.util.Set;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.common.AuthenticatedExecutionPreparer;
import com.ttc.ch2.common.BaseTest;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, AuthenticatedExecutionPreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ConstraintsTest extends BaseTest{

	private static final Logger logger = LoggerFactory.getLogger(ConstraintsTest.class);
	
	
	@Inject
	private ConstraintService service;
	
		
	@Test
	public void testException() throws ConstraintServiceException
	{
			Set<String> twb=service.getToursWithoutBrand();		
			Assert.assertNotNull(twb);
			Assert.assertTrue(twb.isEmpty()==false);			
	}
	
	
}
