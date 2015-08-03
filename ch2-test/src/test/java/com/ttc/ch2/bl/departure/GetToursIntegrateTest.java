package com.ttc.ch2.bl.departure;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.common.BaseTest;

import facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursOfBrandsVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/blCtx.xml" ,"classpath:/META-INF/spring/testCtx.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class GetToursIntegrateTest extends BaseTest {

	@Inject
	private ApplicationContext applicationContext;

	@Test
	public void getTourTest() throws TourDepartureServiceException {
		TourDepartureService service = applicationContext.getBean(TourDepartureService.class);
		WsToursOfBrandsVO result = service.getToursOfBrands(Arrays.asList("BV", "CH", "IV", "TT"));
		Assert.assertNotNull(result);
	}
}
