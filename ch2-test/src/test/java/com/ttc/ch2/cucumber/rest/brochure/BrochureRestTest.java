package com.ttc.ch2.cucumber.rest.brochure;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Assume;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;




import com.ttc.ch2.common.BrochureHelper;
import com.ttc.ch2.cucumber.SearchLib;
import com.ttc.test.helpservice.UriBuilder;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class BrochureRestTest {

	private static ApplicationContext applicationContext;	
	static {
		applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/testCtx.xml","classpath:/META-INF/spring/daoCtx.xml","classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/broxTestCtx.xml");
	}
	private UriBuilder uriBuilder;
	private CloseableHttpClient httpclient;
	private CloseableHttpResponse response;
	private HttpGet httpGet;
	private String token;
	private String brandCode;
	private String companieCode;
	private String tourCode;
//	http://localhost:7070/ch2-ui/brochure_engine/brochure.pdf?brandCode=CH&sellingCompanyCode=CHUKLS&tours=Tour1&token=token-br
	
	
	@Given("^User token (.*)")
	public void setToken(String token) throws Throwable {
		this.token=token;			
	}
	
//	@Given("^Brand code (.*)")
//	public void setBrandCode(String brandCode) throws Throwable {
//		this.brandCode=brandCode;			
//	}	
//	
//	@Given("^Selling companies code (.*)")
//	public void setComapniesCode(String companieCode) throws Throwable {
//		this.companieCode=companieCode;			
//	}	
//	@Given("^Tour code (.*)")
//	public void setTourCode(String tourCode) throws Throwable {
//		this.tourCode=tourCode;			
//	}	
	
	
	
	@Given("^Part path (.*)")
	public void setPath(String path) throws Throwable {	
				
		
		    NamedParameterJdbcTemplate jdbcTemplate = SearchLib.prepareTemplate();		    
		    List<Map<String,Object>> result = jdbcTemplate.queryForList(SearchLib.sqlForGetBrouchureRest, new MapSqlParameterSource());		    
		    Assume.assumeTrue("No data in Db to test eBrouchure rest",result.size()>0);

		    BrochureHelper bHelper=applicationContext.getBean(BrochureHelper.class);
		    for(Map<String, Object> row: result) {	
		    	brandCode=(String)row.get("BCODE");		    	
		    	tourCode=(String)row.get("TOUR_CODE");
		    	
		    	String company=bHelper.getCommonCompany(tourCode, brandCode);
		    	if(StringUtils.hasText(company)){
			    	companieCode=company;
			    	break;	
		    	}		    	
		    }		    
		    Assume.assumeTrue("No found common selling company in Db to test eBrouchure rest",StringUtils.hasText(companieCode));
		
		StringBuilder sb=new StringBuilder();
		sb.append(uriBuilder.getHostApp());
		sb.append(path);
		sb.append("?");
		sb.append("token=").append(token);
		sb.append("&brandCode=").append(brandCode);
		sb.append("&sellingCompanyCode=").append(companieCode);
		sb.append("&tours=").append(tourCode);
		
		String uriLocal=sb.toString();	
		System.out.println(uriLocal);
		httpclient =  HttpClients.createDefault();
		httpGet=new HttpGet(uriLocal);			
	}
		
	@When("^Downolad file brochure")
	public void mainTest() throws IOException {		
			 response = httpclient.execute(httpGet);		
	}
	
	@Then("^Response return pdf file (.*)")
	public void is_checkFlag(boolean flag) throws Throwable {		
		HttpEntity resEntity=response.getEntity();								
		Assert.assertEquals(flag,isPDF(resEntity.getContent()));
	}
	
	@Before
	public void before()
	{		
		uriBuilder=applicationContext.getBean(UriBuilder.class);		
	}
	
	@After
	public void after()  {				
	}
	
	public boolean isPDF(InputStream in){
		    Scanner input = new Scanner(in);
		    while (input.hasNextLine()) {
		        final String checkline = input.nextLine();
		        if(checkline.contains("%PDF-")) { 
		            // a match!
		        return true;
		        }
		        return false;
		    }
		    return false;
		}
	
}
