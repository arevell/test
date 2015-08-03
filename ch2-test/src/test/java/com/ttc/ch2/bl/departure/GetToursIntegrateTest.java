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

import com.ttc.ch2.bl.departure.habs.HabsTourDepartureService;
import com.ttc.ch2.bl.departure.habs.HabsTourDepartureServiceException;
import com.ttc.ch2.common.BaseTest;
import com.wsout.habs.itropicsbuildws.WsToursOfBrandsVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/blCtx.xml" ,"classpath:/META-INF/spring/testCtx.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class GetToursIntegrateTest extends BaseTest {

	@Inject
	private ApplicationContext applicationContext;

	@Test
	public void getTourTest() throws TourDepartureServiceException, HabsTourDepartureServiceException {
		HabsTourDepartureService service = applicationContext.getBean(HabsTourDepartureService.class);
		WsToursOfBrandsVO result = service.getToursOfBrands(Arrays.asList("BV", "CH", "IV", "TT"));
		Assert.assertNotNull(result);
	}
}
