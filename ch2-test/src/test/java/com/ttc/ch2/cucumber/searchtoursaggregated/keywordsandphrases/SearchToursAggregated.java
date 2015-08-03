package com.ttc.ch2.cucumber.searchtoursaggregated.keywordsandphrases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedRequest;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedResponse;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Continent;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Country;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompany;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;
import com.ttc.ch2.cucumber.CcapiCucumberHelper;
import com.ttc.ch2.cucumber.Ch2WSClient;
import com.ttc.ch2.cucumber.SearchLib;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchToursAggregated {

	private static String endPoint=null;
	static
	{
		endPoint = CcapiCucumberHelper.endPoint;
	}
	
	private Ch2WSClient wsClient = new Ch2WSClient(endPoint);
	private String sccode;
	private int nrecords;
	private String testParameter;
	private String token;
	private boolean isResultsValid=true;
	private String keyword;
	private String tourCode;
	
	@Given("^Selling Company code (.*)")
	public void populate_sc_with_sellingcompanycode(String sellingCompanyCode) throws Throwable {
		sccode = sellingCompanyCode;
	}
	
	@Given("^number of records (.*)")
	public void populate_number_of_records(int numberOfRecords) throws Throwable {
		nrecords = numberOfRecords;
	}
	
	@Given("^search tours test parameter (.*)")
	public void populate_test_parameter(String testParam) throws Throwable {
		testParameter = testParam;
	}
	
	@Given("^token is equal (.*)")
	public void populate_token(String token) throws Throwable {
		this.token = token;
	}
	
	@Given("^keyword is equal (.*)")
	public void populate_tourcode(String keyword) throws Throwable {
		this.keyword = keyword;
	}
	
	@When("^I request for the search tours aggregated")
	public void execute_search_tours() throws IOException, JAXBException {
		SearchToursAggregatedRequest request = new SearchToursAggregatedRequest();
	    request.setOrderBy("1");
	    request.setOrderDirection("ASC");
	    request.setFirstRecordNumber(new BigInteger("1"));
	    request.setNumberOfRecords(new BigInteger("30"));
	    request.setSecurityKey(token);
	    request.getSellingCompanies().add(sccode);
	    tourCode="";
	    
	    TourInfo ti = null;
	    TourDeparturesType td=null;
	    if(nrecords > 0) {
		    NamedParameterJdbcTemplate jdbcTemplate = SearchLib.prepareTemplateForSearch();
		    MapSqlParameterSource paramSource = new MapSqlParameterSource();
		    paramSource.addValue("sccode",sccode);
		    paramSource.addValue("records", nrecords);
		    List<Map<String,Object>> result;
		    result = jdbcTemplate.queryForList(SearchLib.sqlForKeywordAndPhrases, paramSource);
		    String tixml = (String)result.get(0).get("tourinfo_xml");
		    String tdxml = (String)result.get(0).get("tourdeparture_xml");
		    JAXBContext jaxbRTIContext = JAXBContext.newInstance(TourInfo.class);
		    JAXBContext jaxbRTDContext = JAXBContext.newInstance(TourDeparturesType.class);
		    Unmarshaller jaxbRTIUnmarshaller = jaxbRTIContext.createUnmarshaller();
		    Unmarshaller jaxbRTDUnmarshaller = jaxbRTDContext.createUnmarshaller();
		    ti = (TourInfo) jaxbRTIUnmarshaller.unmarshal(new ByteArrayInputStream(tixml.getBytes(StandardCharsets.UTF_8)));
		    td = (TourDeparturesType) jaxbRTDUnmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(tdxml.getBytes(StandardCharsets.UTF_8))), TourDeparturesType.class).getValue();
	    }
	    SearchToursAggregatedResponse response;
	    //response = wsClient.port.searchToursAggregated(request);
		switch(testParameter) {
			case "TOUR_CODE" :
				request.getKeywordsAndPhrases().add(ti.getTourCode());
				response = wsClient.port.searchToursAggregated(request);
				assertTrue(response.isSuccessful());
				assertEquals(response.getTotalRecords().intValue(), 1);
				assertEquals(response.getSearchAggregatedResults().get(0).getSearchAggregatedSubResults().get(0).getTourCode(), ti.getTourCode());
				break;
			case "OP_CODE" :
				request.getKeywordsAndPhrases().add(td.getOperatingProductCode());
				response = wsClient.port.searchToursAggregated(request);
				assertTrue(response.isSuccessful());
				assertEquals(response.getTotalRecords().intValue(), 1);
				assertEquals(response.getSearchAggregatedResults().get(0).getSearchAggregatedSubResults().get(0).getOperatingProductCode(), td.getOperatingProductCode());
				break;
			case "BROCHURE_CODE":
				boolean isFound = false;
				for(SellingCompany sc: ti.getSellingCompanies().getSellingCompany()) {
					if(sc.getCode().equals(sccode)) {
						request.getKeywordsAndPhrases().add(sc.getBrochure().get(0).getCode());
						response = wsClient.port.searchToursAggregated(request);
						assertTrue(response.isSuccessful());
						//assertEquals(response.getTotalRecords().intValue(), 1);
						assertEquals(response.getSearchAggregatedResults().get(0).getSearchAggregatedSubResults().get(0).getBrochureCode(), sc.getBrochure().get(0).getCode());
						isFound=true;
					}
				}
				assertTrue(isFound);
				break;
			case "CATTOUR_CODE":
				request.getKeywordsAndPhrases().add(ti.getCataloguedTour().getCode());
				response = wsClient.port.searchToursAggregated(request);
				assertTrue(response.isSuccessful());
				assertEquals(response.getTotalRecords().intValue(), 1);
				assertEquals(response.getSearchAggregatedResults().get(0).getCataloguedTourCode(), ti.getCataloguedTour().getCode());
				
				break;
			case "COUNTRY_NAME":
				request.getKeywordsAndPhrases().add(ti.getCountriesVisited().getCountry().get(0).getName());
				response = wsClient.port.searchToursAggregated(request);
				assertTrue(response.isSuccessful());
				//assertEquals(response.getTotalRecords().intValue(), 1);
				isFound = false;
				for(Country ctr: response.getSearchAggregatedResults().get(0).getSearchAggregatedSubResults().get(0).getCountriesVisited().getCountry()) {
					if(ctr.getName().equals( ti.getCountriesVisited().getCountry().get(0).getName())) {
						isFound = true;
					}
				}
				assertTrue(isFound);
				break;
			case "CONTINENT_NAME":
				request.getKeywordsAndPhrases().add(ti.getContinentsVisited().getContinent().get(0).getName());
				response = wsClient.port.searchToursAggregated(request);
				assertTrue(response.isSuccessful());
				//assertEquals(response.getTotalRecords().intValue(), 1);
				isFound = false;
				for(Continent ctn: response.getSearchAggregatedResults().get(0).getSearchAggregatedSubResults().get(0).getContinentsVisited().getContinent()) {
					if(ctn.getName().equals( ti.getContinentsVisited().getContinent().get(0).getName())) {
						isFound = true;
					}
				}
				assertTrue(isFound);
				break;
			case "TOUR_NAME":       
			case "DESCRIPTION":     
			case "LOCATION_NAME":   
			case "BROCHURE_NAME":   
			case "LOCATION_CODE":   
			case "ITINERARY_TEXT":  
			case "ITINERARY_TITLE": 
			case "WHATSINCL_TEXT":  
			case "WHATSINCL_TITLE": 
			case "HIGHLIGHTS":      
			case "TOURCAT_NAME":    
			case "TOURCAT_CAT":
				request.getKeywordsAndPhrases().add(keyword);
				response = wsClient.port.searchToursAggregated(request);
				assertTrue(response.isSuccessful());
				assertEquals(response.getTotalRecords().intValue(),1);
				tourCode = response.getSearchAggregatedResults().get(0).getSearchAggregatedSubResults().get(0).getTourCode();
				break;
			default:
				isResultsValid=false;
		}
	}
	
	@Then("^Test result is valid")
	public void is_successfull() throws Throwable {
		assertTrue(isResultsValid);
	}
	
	@And("^TourCode is equal (.*)")
	public void tourCode(String tourCode) throws Throwable {
		//assertTrue(isResultsValid);
		assertEquals(tourCode,this.tourCode);
	}

	
	

}
