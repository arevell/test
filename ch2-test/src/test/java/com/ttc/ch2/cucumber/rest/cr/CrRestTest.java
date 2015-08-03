package com.ttc.ch2.cucumber.rest.cr;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ttc.test.helpservice.UriBuilder;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CrRestTest {

	private static ApplicationContext applicationContext;
	static
	{
		applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/testCtx.xml","classpath:/META-INF/spring/daoCtx.xml","classpath:/META-INF/spring/blCtx.xml");		
	}
	private static String resultXmlFragment="<?xml";
	
	private UriBuilder uriBuilder;
	private CloseableHttpClient httpclient;
	private CloseableHttpResponse response;
	private HttpGet httpGet;
	private String token;
	private String version;
	
	
	@Given("^User token (.*)")
	public void setToken(String token) throws Throwable {
		this.token=token;			
	}	
	
	@Given("^File Version (.*)")
	public void setVersion(String version) throws Throwable {
		this.version=version;			
	}
	
	@Given("^Part path (.*)")
	public void setPath(String path) throws Throwable {
		String uriLocal=null;
		try{
			uriLocal=uriBuilder.getUriCr(path,token,version);	
		}
		catch (UnsupportedOperationException e) {
			Assert.assertTrue(e.getMessage(),false);
		}
					
		httpclient =  HttpClients.createDefault();
		httpGet=new HttpGet(uriLocal);			
	}
	
	

	@When("^Downolad file from contentRepository")
	public void mainTest() throws IOException {		
			 response = httpclient.execute(httpGet);		
	}
	
	@Then("^Response return xml file (.*)")
	public void is_checkFlag(boolean flag) throws Throwable {		
		HttpEntity resEntity=response.getEntity();
		String respContent = new String(EntityUtils.toByteArray(resEntity));						
		Assert.assertEquals(flag,respContent.contains(resultXmlFragment));
	}
	
	@Before
	public void before()
	{		
		uriBuilder=applicationContext.getBean(UriBuilder.class);		
	}
	
	@After
	public void after()  {				
	}
	
	
	
}
