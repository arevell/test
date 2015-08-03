package com.ttc.ch2.cucumber.ccapisecurity.upload;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Random;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.collect.Sets;
import com.ttc.ch2.AssertMessage;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoRequest;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoResponse;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.cucumber.CcapiCucumberHelper;
import com.ttc.ch2.cucumber.Ch2WSClient;
import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.test.common.UploadHelper;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class UploadTest {
	
	private static ClassPathXmlApplicationContext applicationContext;
	private static String endPoint=null;
	private static Random rnd;
	static
	{
		applicationContext =CcapiCucumberHelper.applicationContext;
		endPoint = CcapiCucumberHelper.endPoint;
		rnd=new Random();
	}
	
	private Ch2WSClient wsClient = new Ch2WSClient(endPoint);
	private UploadTourInfoRequest request = new UploadTourInfoRequest();
	private UploadTourInfoResponse response;
	private UserCCAPI ccapiUser=null;
	
	@Given("^Security key (.*)")
	public void setAllData(String securityKey) throws Throwable {
		request.setSecurityKey(securityKey);				
		request.setFileName(UploadHelper.genFileName());		
		String fileName="CH-2014-05-14T17-02-32Z-1.zip";
		ByteArrayDataSource rawData= new ByteArrayDataSource(this.getClass().getResourceAsStream(fileName),"application/zip");
		DataHandler data= new DataHandler(rawData);		
		request.setFileData(data);
	}
	
	@When("^Check permision for request")
	public void mainTest() {
			response = wsClient.port.uploadTourInfo(request);
	}
	
	@Then("^Response has flag (.*)")
	public void is_checkFlag(boolean flag) throws Throwable {
		assertEquals(flag, response.isSuccessful());		
	}

	@And("^Response Message should have (.*)")
	public void check(String message) throws Throwable {
		if(request.getSecurityKey().equals("token:2"))
            AssertMessage.assertContains(message, response.getMessageContext());
		else
            AssertMessage.assertContains(message, response.getMessageContext());
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
				Function f =(Function) s.createQuery("from Function where name='"+FunctionType.SEARCH_TOURS_AGGREGATED.getSimpleName()+"'").uniqueResult();
				SellingCompany sc =(SellingCompany) s.createQuery("from SellingCompany where code='CHCANS'").uniqueResult();
				
				ccapiUser=SampleGenerator.generateUser("cucumber-user1");
				CCAPIAuthority a=new CCAPIAuthority();
				a.setFunction(f);	
				a.setSellingCompany(sc);	
				a.setUserCcapi(ccapiUser);
				ccapiUser.setCcapiAuthorities(Sets.newHashSet(a));	
				s.saveOrUpdate(ccapiUser);
			}
			{
				Function f =(Function) s.createQuery("from Function where name='"+FunctionType.UPLOAD_TOUR_INFO.getSimpleName()+"'").uniqueResult();
				SellingCompany sc =(SellingCompany) s.createQuery("from SellingCompany where code='CHCANS'").uniqueResult();
				
				ccapiUser=SampleGenerator.generateUser("cucumber-user2");
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
