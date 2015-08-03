package com.ttc.ch2.bl.departure;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.bl.departure.habs.HabsTourDepartureService;
import com.ttc.ch2.bl.departure.habs.HabsTourDepartureServiceException;
import com.ttc.ch2.bl.departure.habs.HabsTourDepartureServiceImpl;
import com.ttc.ch2.common.BaseTest;
import com.wsout.habs.itropicsbuildws.ITropicsBuildWS;


/**
 * Request from Habs api to tropisc is possible  
 * Request from Tropics api to hub is impossible 
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@DirtiesContext
public class InitializeHabsPortTest extends BaseTest{
	
	private static final Logger logger = LoggerFactory.getLogger(InitializeHabsPortTest.class);
	@Inject
	private ApplicationContext applicationContext;
	
	@Value("${endpoint.tropics_build_ws}")
	private String endpoint;
	
	@Value("${old.endpoint.tropics_build_ws}")
	private String endpointOld;
	
	
	@Test
	public void portInitializedTest() throws HabsTourDepartureServiceException{
		//prepare	
				try{
					HabsTourDepartureService service =applicationContext.getBean(HabsTourDepartureService.class);
					Whitebox.setInternalState(service, "port",(ITropicsBuildWS)null);
					Assert.assertTrue(service instanceof HabsTourDepartureServiceImpl);		
					HabsTourDepartureServiceImpl serivceImpl=(HabsTourDepartureServiceImpl) service;		

					//Test						
						ITropicsBuildWS port=serivceImpl.getPort();
					// when
						Assert.assertNotNull("Test expected generate port",port);	
				}
				finally{
					HabsTourDepartureService service =applicationContext.getBean(HabsTourDepartureService.class);
					Whitebox.setInternalState(service, "port",(ITropicsBuildWS)null);
				}						
	}
	
	@Test
	public void portNotInitializeTest() throws TourDepartureServiceException
	{
		//prepare	
		try{
			HabsTourDepartureService service =applicationContext.getBean(HabsTourDepartureService.class);
			Whitebox.setInternalState(service, "endpoint","xxxxx");
			Whitebox.setInternalState(service, "port",(ITropicsBuildWS)null);
			Assert.assertTrue(service instanceof HabsTourDepartureServiceImpl);		
			HabsTourDepartureServiceImpl serivceImpl=(HabsTourDepartureServiceImpl) service;		
			boolean exception=false;
			//Test	
			try {
				serivceImpl.getPort();
			} catch (HabsTourDepartureServiceException e) {
				exception=true;
			} catch (Exception e) {
				exception = false;				
				logger.error("Incorrect Exception Occured:"+e.getClass()+"("+e.getMessage()+")",e);
			}
			// when
			Assert.assertTrue("Test expected TourDepartureServiceException",exception);	
		}
		finally{
			HabsTourDepartureService service =applicationContext.getBean(HabsTourDepartureService.class);
			Whitebox.setInternalState(service, "endpoint",endpoint);
		}
				
	}
	
	// initialize by endpoint to tropics - success why ?
	@Ignore
	@Test
	public void portNotInitializeTestVer2() throws TourDepartureServiceException
	{
		//prepare	
		try{
			HabsTourDepartureService service =applicationContext.getBean(HabsTourDepartureService.class);
			Whitebox.setInternalState(service, "endpoint",endpointOld);
			Whitebox.setInternalState(service, "port",(ITropicsBuildWS)null);
			Assert.assertTrue(service instanceof HabsTourDepartureServiceImpl);		
			HabsTourDepartureServiceImpl serivceImpl=(HabsTourDepartureServiceImpl) service;		
			boolean exception=false;
			//Test	
			try {
				serivceImpl.getPort();
			} catch (HabsTourDepartureServiceException e) {
				exception=true;
			} catch (Exception e) {
				exception = false;				
				logger.error("Incorrect Exception Occured:"+e.getClass()+"("+e.getMessage()+")",e);
			}
			// when
			Assert.assertTrue("Test expected TourDepartureServiceException",exception);	
		}
		finally{
			HabsTourDepartureService service =applicationContext.getBean(HabsTourDepartureService.class);
			Whitebox.setInternalState(service, "endpoint",endpoint);
		}
		
	}
}
