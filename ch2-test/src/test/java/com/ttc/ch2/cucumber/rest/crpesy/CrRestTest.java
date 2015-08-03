package com.ttc.ch2.cucumber.rest.crpesy;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ttc.ch2.cucumber.rest.RestCucumberHelper;
import com.ttc.test.helpservice.UriBuilder;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CrRestTest {
	
	private static ClassPathXmlApplicationContext applicationContext;
	static
	{
		applicationContext =RestCucumberHelper.applicationContext;
	}
	private static String resultXmlFragment="<?xml";
	private Properties cfg;
	private CloseableHttpClient httpclient;
	private CloseableHttpResponse response;
	private HttpGet httpGet;
	private String token;	
	private String format;
	@Given("^User token (.*)")
	public void setToken(String token) throws Throwable {
		this.token=token;			
	}	
	
	
	@Given("^Format part (.*)")
	public void setFormat(String format) throws Throwable {
		this.format=format;
	}
	
	@Given("^Part path (.*)")
	public void setPath(String path) throws Throwable {
		String url=null;
		try{
			String hostAndApp=cfg.getProperty("rest.test.host")+cfg.getProperty("rest.test.app");			
			String query="";
			if(StringUtils.isNotEmpty(token)){
				query="?token="+token;
			
				if(StringUtils.isNotEmpty(format))
				query+="&format=xml";
			}
			
			url=hostAndApp+path+query;
		}
		catch (UnsupportedOperationException e) {
			Assert.assertTrue(e.getMessage(),false);
		}
					
		httpclient =  HttpClients.createDefault();
		httpGet=new HttpGet(url);			
	}
	
	@When("^Downolad file from contentRepository")
	public void mainTest() throws IOException {		
			 response = httpclient.execute(httpGet);		
	}
	
	@Then("^Response return content (.*)")
	public void checkContent(String content) throws Throwable {		
		HttpEntity resEntity=response.getEntity();
		String respContent = new String(EntityUtils.toByteArray(resEntity));						
		Assert.assertEquals("Invalid response content",true,respContent.contains(content.trim()));
	}
	
	@Then("^Response return status code (.*)")
	public void checkCode(int  statusCodeExpected) throws Throwable {
		int statusCode=response.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCodeExpected,statusCode);		
	}
	
	@Before
	public void before()
	{				
		 cfg=applicationContext.getBean("testPropertis",Properties.class);
	}
	
	@After
	public void after()  {				
	}
	
	
	
}
