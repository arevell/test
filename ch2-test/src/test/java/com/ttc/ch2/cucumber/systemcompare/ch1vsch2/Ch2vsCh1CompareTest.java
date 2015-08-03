package com.ttc.ch2.cucumber.systemcompare.ch1vsch2;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;





import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.common.collect.Lists;
import org.junit.Assert;
import org.junit.Assume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.io.CharStreams;
import com.google.common.io.LineProcessor;
import com.ttc.ch2.cucumber.SpringHelper;
import com.ttc.ch2.cucumber.systemcompare.BaseCompareTest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Ch2vsCh1CompareTest extends BaseCompareTest{

	private static final Logger logger = LoggerFactory.getLogger(Ch2vsCh1CompareTest.class);

	public static String restEndpointCh1;
	public static String restEndpointCh2;
	private static CloseableHttpClient httpclient;
		
	static{		
		Properties properties=SpringHelper.applicationContext.getBean("myProperties",Properties.class);
		
		// endpoint to ch1
		String hostCh1=properties.getProperty("rest.test.ch1.host");
		Assert.assertTrue(StringUtils.isNotEmpty(hostCh1));
				
		restEndpointCh1=hostCh1+"content_repository/tour_departure/";
		
		// endpoint to ch2
		String hostCh2=properties.getProperty("rest.test.host");
		Assert.assertTrue(StringUtils.isNotEmpty(hostCh2));
		
		String appCh2=properties.getProperty("rest.test.app");
		Assert.assertTrue(StringUtils.isNotEmpty(appCh2));
		
		restEndpointCh2=hostCh2+appCh2+"/tour_departure/";
				
		httpclient = HttpClients.createDefault();
//		http://lonwk0412.corp.ttc:7777/ch2-ui/tour_info/BV/V3/ACANA14B02.xml?token=token-xxx&format=xml

	}
	
					
	@Given("^selling companies:(.*)")
	public void setSellingCompanyCode(String scCode){
//		scCode="TTSYDS";
		setupSellingCompany(scCode);
	}
	@Given("^percent for data:(.*)")
	public void setPercent(String percent){
		setupPercent(percent);
	}
	
	@And("^setting property (.*) as binding for field (.*)")
	public void set_binding_property_for_field(String propertyName, String fieldName) {
		bindingMap.put(fieldName, propertyName);
	}
	
		
	@Given("^appropriately configured test environment$")
	public void prepare(){		
		prepareDataBasedOnCh2();
	}
						
	@When("^the compare process is invoked$")
	public void execute() throws IOException, InterruptedException, JAXBException {					
//		 https://contenthub-dev.corp.ttc:8301/content_repository/tour_departure/";
//		 http://lonwk0412.corp.ttc:7777/ch2-ui/tour_info/BV/ACANA14B02.xml?token=token-xxx&format=xml
		
//		 ClientConfig config = new DefaultClientConfig();		 		
//		 Client client = Client.create(config);
//		 WebResource serviceCh2 = client.resource(restEndpointCh2);		
		 		
//		 choosedTour.clear();
//		 choosedTour.add("ACOPMA10");		 
		 
		for (String code : choosedTour) {					
			checkTourExist(code);
		}		
	}
	
	// check conditions	
	@Then("^the result is checked and possible differences are presented$")
	public void checkResults() throws Throwable {	
		Assert.assertFalse("Found differences\n"+Joiner.on("\n").join(compareResults),compareResults.size()>0);		
	}
		
			
	protected void checkTourExist(String tourCode) {		
		try{
		String response=getResponseFromCh2RestService(tourCode);
		boolean existFileInCh2=response!=null && response.contains("MarketVariationDepartureInfo") && response.contains(String.format("MarketVariationCode=\"%s\"", tourCode));
		if(existFileInCh2==false)
			compareResults.add(String.format("Tour %s->%s not found in Ch2 (probably has 'repository status' Empty or TourInfoOnly -> %s)",this.sellingCompanyCode,tourCode,getURLForCh2(tourCode)));
		}
		catch(Exception e){
			if(StringUtils.isNotEmpty(e.getMessage()) && e.getMessage().contains("401 Unauthorized"))
				compareResults.add(String.format("For Tour %s->%s Unauthorized Exception occurred in Ch2",this.sellingCompanyCode,tourCode));
			else{
				logger.error("",e);
			}
		}
	}	
	
	protected void prepareDataBasedOnCh2(){
		
		choosedTour=Lists.newArrayList();
		compareResults=Lists.newArrayList();
		
		List<String> tours=getAllToursFromCh1();
		
		double r=(((double)tours.size())*(percent*0.01d));		
		int count= r>1 ? (int) r: 1;
		
		Collections.shuffle(tours);
		while(count>0 && tours.isEmpty()==false){			
			choosedTour.add(tours.remove(0));			
			count--;						
		}	
		
		Assume.assumeTrue("No data to check",choosedTour.size()>0);		 
		logger.info(this.sellingCompanyCode+"-> Choosed tours:["+Joiner.on(",").join(choosedTour)+"] size:"+choosedTour.size());
	}
	
	private  List<String> getAllToursFromCh1(){
		 		 			
		 
		 try {
			 String response=getResponseFromCh1(brandCode);			 
			 List<String> tours=CharStreams.readLines(CharStreams.newReaderSupplier(response),new LineProcessor<List<String>>(){
				List<String> result=Lists.newArrayList();
				@Override
				public boolean processLine(String line) throws IOException {
					if(line.contains(".xml") && line.contains("<a href=\"/content_repository/tour_departure/")){						
						String t=line.substring(line.indexOf("<tt>")+"<tt>".length());
						t=t.substring(0,t.indexOf(".xml</tt>"));
						result.add(t);
					}
					return true;
				}
				@Override
				public List<String> getResult() {
					return result;
				}});			 
			 Assume.assumeTrue("Ch1 resource does not have tours",tours!=null && tours.isEmpty()==false);
			 
			 return tours;
			} catch (Exception e) {
				logger.error("",e);
				Throwables.propagate(e);
			}	
		 
		 return null;
	}
	
	private String getResponseFromCh2RestService(String tourCode) throws Exception{
			
		try{
			String result=null;
			HttpGet httpGet = new HttpGet(getURLForCh2(tourCode));
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
			    HttpEntity entity = response.getEntity();
			    result=EntityUtils.toString(entity);
			    return result;
			} finally {
			    response.close();
			}
		}
		catch(Exception e){
			logger.error(String.format("Problem with read data from rest service for tour code:%s and brand: %s",tourCode,brandCode),e);
			throw e;
		}	
	}
	
	private String getResponseFromCh1(String brandCode) throws Exception{
		
		try{
			String result=null;
			HttpGet httpGet = new HttpGet(getURLForCh1(brandCode));
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				result=EntityUtils.toString(entity);
				return result;
			} finally {
				response.close();
			}
		}
		catch(Exception e){
			logger.error(String.format("Problem with read data from CH1 for brand code:%s",brandCode),e);
			throw e;
		}	
	}
	
	private String getURLForCh2(String tourCode){
		return restEndpointCh2+brandCode+"/V1/"+tourCode+".xml?token=token-xxx";
	}
	
	private String getURLForCh1(String brandCode){
		return restEndpointCh1+brandCode;
	}
	
}




