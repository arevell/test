package com.ttc.ch2.cucumber.ccapisecurity.gettourcategories;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.collect.Sets;
import com.ttc.ch2.AssertMessage;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesResponse;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.cucumber.CcapiCucumberHelper;
import com.ttc.ch2.cucumber.Ch2WSClient;
import com.ttc.ch2.cucumber.ccapisecurity.AssertType;
import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.user.UserCCAPI;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class GetTourCategoriesTest {

	private static ClassPathXmlApplicationContext applicationContext;
	private static String endPoint=null;
	static
	{
		applicationContext =CcapiCucumberHelper.applicationContext;
		endPoint = CcapiCucumberHelper.endPoint;
	}
	
	private Ch2WSClient wsClient = new Ch2WSClient(endPoint);
	private GetTourCategoriesRequest request = new GetTourCategoriesRequest();
	private GetTourCategoriesResponse response;	
	private AssertType checkTypeAsser;
	
	@Given("^Security key (.*)")
	public void setSecurityKey(String securityKey) throws Throwable {
		request.setSecurityKey(securityKey);			
	}
	
	@Given("^Company code (.*)")
	public void setSellingComapnyCode(String comapnyCode) throws Throwable {
		request.setSellingCompany(comapnyCode);	
	}
	
	@Given("^Check type assert (.*)")
	public void setAssertType(String asertType) throws Throwable {
		checkTypeAsser=AssertType.valueOf(asertType);
	}
	
	@When("^Check permision for request")
	public void mainTest() {
			response = wsClient.port.getTourCategories(request);
	}
	
	@Then("^Response has flag (.*)")
	public void is_checkFlag(boolean flag) throws Throwable {
		assertEquals(flag, response.isSuccessful());		
	}

	@And("^Response Message should have (.*)")
	public void check(String message) throws Throwable {
		
		switch (checkTypeAsser) {
		case EQUALS:
            AssertMessage.assertContains(message, response.getMessageContext());
			break;
		case CONTAINS:
            AssertMessage.assertContains(message, response.getMessageContext());
			break;
		default:
			Assert.assertTrue("Unsupported checkTypeAssert:"+checkTypeAsser.toString(),false);
			break;
		}
		
	}

	
	@Before
	public void before()
	{				
		after();
		SessionFactory sf=applicationContext.getBean(SessionFactory.class);
		Session s=null;
		Transaction t=null;
		try
		{
			s=sf.openSession();
			t=s.beginTransaction();

			{
				Function f =(Function) s.createQuery("from Function where name='"+FunctionType.SEARCH_TOURS_V3.getSimpleName()+"'").uniqueResult();
				SellingCompany sc =(SellingCompany) s.createQuery("from SellingCompany where code='CHCANS'").uniqueResult();
				
				UserCCAPI ccapiUser=SampleGenerator.generateUser("cucumber-user1");
				CCAPIAuthority a=new CCAPIAuthority();
				a.setFunction(f);	
				a.setSellingCompany(sc);	
				a.setUserCcapi(ccapiUser);
				ccapiUser.setCcapiAuthorities(Sets.newHashSet(a));	
				s.saveOrUpdate(ccapiUser);
			}
			
			{
				Function f =(Function) s.createQuery("from Function where name='"+FunctionType.GET_TOUR_CATEGORIES.getSimpleName()+"'").uniqueResult();
				SellingCompany sc =(SellingCompany) s.createQuery("from SellingCompany where code='CHCANS'").uniqueResult();
				
				UserCCAPI ccapiUser=SampleGenerator.generateUser("cucumber-user2");
				ccapiUser.setToken("token:2");
				CCAPIAuthority a=new CCAPIAuthority();
				a.setFunction(f);	
				a.setSellingCompany(sc);	
				a.setUserCcapi(ccapiUser);
				ccapiUser.setCcapiAuthorities(Sets.newHashSet(a));	
				s.saveOrUpdate(ccapiUser);
			}
		}
		finally
		{
			t.commit();
			s.flush();
			s.close();
			
		}
	}
	
	@After
	public void after()  {	
		
		CcapiCucumberHelper.after(applicationContext);
	}
}
