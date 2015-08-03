package com.ttc.ch2.bl.departure;

import javax.inject.Inject;

import org.junit.Assert;
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

import com.ttc.ch2.common.BaseTest;

import facade.itropics.webservice.tropics.com.itropicsbuildws.ITropicsBuildWS;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@DirtiesContext
public class InitializeTropicsPortTest extends BaseTest{
	
	private static final Logger logger = LoggerFactory.getLogger(InitializeTropicsPortTest.class);
	@Inject
	private ApplicationContext applicationContext;
	
	@Value("${old.endpoint.tropics_build_ws}")
	private String endpointOld;
	
	@Value("${endpoint.tropics_build_ws}")
	private String endpoint; 
	
	@Test
	public void portInitializedTest() throws  TourDepartureServiceException{
		//prepare	
				try{
					TourDepartureService service =applicationContext.getBean(TourDepartureService.class);
					Whitebox.setInternalState(service, "port",(ITropicsBuildWS)null);
					Assert.assertTrue(service instanceof TourDepartureServiceImpl);		
					TourDepartureServiceImpl serivceImpl=(TourDepartureServiceImpl) service;		

					//Test						
						ITropicsBuildWS port=serivceImpl.getPort();
					// when
						Assert.assertNotNull("Test expected generate port",port);	
				}
				finally{
					TourDepartureService service =applicationContext.getBean(TourDepartureService.class);
					Whitebox.setInternalState(service, "port",(ITropicsBuildWS)null);
				}						
	}
	
	@Test
	public void portNotInitializeTest() throws TourDepartureServiceException
	{
		//prepare	
		try{
			TourDepartureService service =applicationContext.getBean(TourDepartureService.class);
			Whitebox.setInternalState(service, "endpoint","xxxxx");
			Whitebox.setInternalState(service, "port",(ITropicsBuildWS)null);
			Assert.assertTrue(service instanceof TourDepartureServiceImpl);		
			TourDepartureServiceImpl serivceImpl=(TourDepartureServiceImpl) service;		
			boolean exception=false;
			//Test	
			try {
				ITropicsBuildWS port=serivceImpl.getPort();
			} catch (TourDepartureServiceException e) {
				exception=true;
			} catch (Exception e) {
				exception = false;
				logger.error("Incorrect Exception Occured:"+e.getClass()+"("+e.getMessage()+")",e);
			}
			// when
			Assert.assertTrue("Test expected TourDepartureServiceException",exception);	
		}
		finally{
			TourDepartureService service =applicationContext.getBean(TourDepartureService.class);
			Whitebox.setInternalState(service, "endpoint",endpointOld);
		}
				
	}
	
	
	// initialize by endpoint to habs
	@Test
	public void portNotInitializeTestVer2() throws TourDepartureServiceException
	{
		//prepare	
		try{
			TourDepartureService service =applicationContext.getBean(TourDepartureService.class);
			Whitebox.setInternalState(service, "endpoint",endpoint);
			Whitebox.setInternalState(service, "port",(ITropicsBuildWS)null);
			Assert.assertTrue(service instanceof TourDepartureServiceImpl);		
			TourDepartureServiceImpl serivceImpl=(TourDepartureServiceImpl) service;		
			boolean exception=false;
			//Test	
			try {
				ITropicsBuildWS port=serivceImpl.getPort();
			} catch (TourDepartureServiceException e) {
				exception=true;
			} catch (Exception e) {
				exception = false;
				logger.error("Incorrect Exception Occured:"+e.getClass()+"("+e.getMessage()+")",e);
			}
			// when
			Assert.assertTrue("Test expected TourDepartureServiceException",exception);	
		}
		finally{
			TourDepartureService service =applicationContext.getBean(TourDepartureService.class);
			Whitebox.setInternalState(service, "endpoint",endpointOld);
		}
		
	}
	

}
