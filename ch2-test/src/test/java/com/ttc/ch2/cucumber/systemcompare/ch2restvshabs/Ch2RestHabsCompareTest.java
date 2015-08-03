package com.ttc.ch2.cucumber.systemcompare.ch2restvshabs;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.google.common.base.Joiner;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.SellingCompanyType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.cucumber.SpringHelper;
import com.ttc.ch2.cucumber.systemcompare.BaseCompareTest;
import com.ttc.ch2.cucumber.systemcompare.HabsDataVO;
import com.ttc.prodserv.mongo.domain.UniqueIdHelper;
import com.ttc.prodserv.mongo.domain.xmlrepo.OperatingProduct;
import com.ttc.prodserv.mongo.domain.xmlrepo.Product;
import com.ttc.prodserv.mongo.domain.xmlrepo.Tour;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Ch2RestHabsCompareTest extends BaseCompareTest{

	private static final Logger logger = LoggerFactory.getLogger(Ch2RestHabsCompareTest.class);
	
	
	public static String restEndpoint;
	private static CloseableHttpClient httpclient; 
//	private static WebResource service;
	static{		
		Properties properties=SpringHelper.applicationContext.getBean("myProperties",Properties.class);
		String host=properties.getProperty("rest.test.host");
		Assert.assertTrue(StringUtils.isNotEmpty(host));
		String app=properties.getProperty("rest.test.app");
		Assert.assertTrue(StringUtils.isNotEmpty(app));
		restEndpoint=host+app+"/tour_departure/";
		httpclient = HttpClients.createDefault();
//		http://lonwk0412.corp.ttc:7777/ch2-ui/tour_info/BV/V3/ACANA14B02.xml?token=token-xxx&format=xml	
	}
					
	@Given("^selling companies:(.*)")
	public void setSellingCompanyCode(String scCode){
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
		MongoTemplate mongoOperation=SpringHelper.applicationContext.getBean(MongoTemplate.class);
		JAXBContext jaxbContextV3 = JAXBContext.newInstance(TourDeparturesType.class);
			    
		
		for (String code : choosedTour) {			
			TourDeparturesType ch2Data=findChData(code,jaxbContextV3);
			HabsDataVO habsDataVO=findHabsData(code,mongoOperation);
			if(habsDataVO!=null){								
				if(ch2Data!=null && habsDataVO!=null)
					compare(ch2Data,habsDataVO);			
			}
		}		
	}
	
	// check conditions	
	@Then("^the result is checked and possible differences are presented$")
	public void checkResults() throws Throwable {	
		Assert.assertFalse("Found differences\n"+Joiner.on("\n").join(compareResults),compareResults.size()>0);		
	}
	
	
	private HabsDataVO findHabsData(String tourCode,MongoTemplate mongoOperation){
		HabsDataVO result=new HabsDataVO();
		
		Product product =  mongoOperation.findById(UniqueIdHelper.getDatesAndRatesId(tourCode, sellingCompanyCode), Product.class);
		if(product==null){
			compareResults.add(String.format("DatesAndRates not found in habs - tourCode %s, selling comapany %s",tourCode,sellingCompanyCode));
			return null;
		}
		
		result.product=product;
		
		Tour tour=mongoOperation.findById(UniqueIdHelper.getTourId(tourCode, sellingCompanyCode),Tour.class);
		if(tour==null){
			compareResults.add(String.format("Tour not found in habs - tourCode %s, selling comapany %s",tourCode,sellingCompanyCode));
			return null;
		}		
		result.tour = tour;		
		result.operatingProduct=mongoOperation.findById(String.valueOf(tour.getOperatingProductId()),OperatingProduct.class);
		result.sellingCompany=mongoOperation.findById(String.valueOf(tour.getSellingCompanyId()),com.ttc.prodserv.mongo.domain.xmlrepo.SellingCompany.class);
		
		if(result.operatingProduct==null){
			compareResults.add(String.format("OperatingProduct not found in habs - tourCode %s, selling comapany %s, opId %s",tourCode,sellingCompanyCode,tour.getOperatingProductId()));
			return null;
		}		
		if(result.sellingCompany==null){
			compareResults.add(String.format("SellingCompany not found in habs - tourCode %s, selling comapany %s, scId %s",tourCode,sellingCompanyCode,tour.getSellingCompanyId()));
			return null;
		}		
	
		return result;
	}
	
	
	private TourDeparturesType findChData(String tourCode,JAXBContext jaxbContextV3) throws ClientProtocolException, IOException{
		
		String response=getResponseFromRestService(tourCode);
		Assert.assertTrue("Xml Data not exist",StringUtils.isNotEmpty(response));  
		TourDeparturesType tourData=getTourDeparturesTypeV3(tourCode, response, jaxbContextV3);
		
		for (Iterator<SellingCompanyType> iterator = tourData.getSellingCompanies().getSellingCompany().iterator(); iterator.hasNext();) {
			SellingCompanyType sc= iterator.next();
			if (!this.sellingCompanyCode.equals(sc.getCode())) {
				iterator.remove();
			}			
			
		}
		
//		if (tourData.getSellingCompanies() != null) {
//			for (int i = 0; i < tourData.getSellingCompanies().getSellingCompany().size(); i++) {
//				if (!this.sellingCompanyCode.equals(tourData.getSellingCompanies().getSellingCompany().get(i).getCode())) {
//					tourData.getSellingCompanies().getSellingCompany().remove(i);
//					i--;
//				}
//			}
//		}
		
		return tourData;
	}
	
	
	private String getResponseFromRestService(String tourCode) throws ClientProtocolException, IOException{
				
		try{
			String result=null;
			HttpGet httpGet = new HttpGet(restEndpoint+brandCode+"/V3/"+tourCode+".xml?token=token-xxx");
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
			logger.error(String.format("Problem with read data from rest serwice for tour code:%s and brand: %s",tourCode,brandCode),e);
			throw e;
		}	
	}
}