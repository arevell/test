package com.ttc.ch2.bl.departure;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.common.BaseTest;

import facade.itropics.webservice.tropics.com.itropicsbuildws.WsDeparturesVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class GetDatesAndRatesIntegrateTest extends BaseTest{
	
	@Inject
	private ApplicationContext applicationContext;

	@Test
	public void getTourDatesAndRatesTest() throws TourDepartureServiceException
	{
		TourDepartureService service =applicationContext.getBean(TourDepartureService.class);
		WsDeparturesVO result=service.getTourDatesAndRates("14SWAM09", "CHUKLS", "apiKey");
		Assert.assertNotNull(result);
	}
		
	
}
