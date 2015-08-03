package com.ttc.ch2.bl.departure;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
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
	
	@Inject
	private ApplicationContext applicationContext;
	
	@Value("${endpoint.tropics_build_ws}")
	private String endpoint; 
	
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
				System.out.println("Incorrect Exception Occured:"+e.getClass()+"("+e.getMessage()+")");
			}
			// when
			Assert.assertTrue("Test expected TourDepartureServiceException",exception);	
		}
		finally{
			TourDepartureService service =applicationContext.getBean(TourDepartureService.class);
			Whitebox.setInternalState(service, "endpoint",endpoint);
		}
				
	}
}
