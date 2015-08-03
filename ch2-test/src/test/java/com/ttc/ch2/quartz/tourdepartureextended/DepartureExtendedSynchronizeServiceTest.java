package com.ttc.ch2.quartz.tourdepartureextended;



import javax.inject.Inject;

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
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.quartz.executionlisteners.ScheduleInstancePreparer;
import com.ttc.ch2.scheduler.service.departureextend.DepartureExtendedSynchronizeService;
import com.ttc.ch2.search.export.IndexSynchronizerService;
import com.ttc.ch2.search.export.IndexSynchronizerVO;

@Ignore("This test create locks on brands")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class DepartureExtendedSynchronizeServiceTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(DepartureExtendedSynchronizeServiceTest.class);
	private String brandCode="BV";
	
	@Inject
	private DepartureExtendedSynchronizeService serviceToTest;
	
	@Inject
	private TropicExtendedDepartureSynchronizeService nestedService;
	
	
	@Test()
	public void runServiceTest() throws Exception {
			
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
}
