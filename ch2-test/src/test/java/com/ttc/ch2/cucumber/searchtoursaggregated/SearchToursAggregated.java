package com.ttc.ch2.cucumber.searchtoursaggregated;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ttc.ch2.api.ccapi.v3.SearchAggregatedResults;
import com.ttc.ch2.api.ccapi.v3.SearchAggregatedSubResults;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedRequest;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedResponse;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Continent;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Country;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.RoomType;
import com.ttc.ch2.cucumber.CcapiCucumberHelper;
import com.ttc.ch2.cucumber.Ch2WSClient;
import com.ttc.ch2.cucumber.SearchLib;

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
	
	@When("^I request for the search tours aggregated")
	public void execute_search_tours() throws IOException {
		SearchToursAggregatedRequest request = new SearchToursAggregatedRequest();
	    request.setOrderBy("1");
	    request.setOrderDirection("ASC");
	    request.setFirstRecordNumber(new BigInteger("1"));
	    request.setNumberOfRecords(new BigInteger("30"));
	    request.setSecurityKey(token);
	    
	    NamedParameterJdbcTemplate jdbcTemplate = SearchLib.prepareTemplate();
	    MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("sccode",sccode);
	    paramSource.addValue("records", nrecords);
	    List<Map<String,Object>> result;
	    SearchToursAggregatedResponse response;
		switch(testParameter) {
			case "Continents":
				result = jdbcTemplate.queryForList(SearchLib.sqlForContinentsCodes, paramSource);
				if(result.size() == 0) {
					isResultsValid=false;
				}
				for(Map<String,Object> row: result) {
					request.getSellingCompanies().clear();
					request.getSellingCompanies().add((String)row.get("sellingcompanycode"));
					request.setContinentCodes((String)row.get("continentcode"));;
					response = wsClient.port.searchToursAggregated(request);
					if(!response.isSuccessful()) {
						isResultsValid=false;
						break;
					}
					
					for(SearchAggregatedResults sra: response.getSearchAggregatedResults()) {
						for(SearchAggregatedSubResults sr: sra.getSearchAggregatedSubResults()) { 
							boolean continentFound=false;
							for(Continent c: sr.getContinentsVisited().getContinent()) {
								if(c.getCode().value().equals((String)row.get("continentcode"))){
									continentFound=true;
									break;
								}
							}
							if(!continentFound) {
								isResultsValid=false;
								break;
							}
						}
					}
					if(!isResultsValid) {
						break;
					}
				}
				break;
			case "Countries":
				result = jdbcTemplate.queryForList(SearchLib.sqlForContryCodes, paramSource);
				if(result.size() == 0) {
					isResultsValid=false;
				}
				for(Map<String,Object> row: result) {
					request.getSellingCompanies().clear();
					request.getSellingCompanies().add((String)row.get("sellingcompanycode"));
					request.setCountryCodes((String)row.get("countrycode"));
					response = wsClient.port.searchToursAggregated(request);
					if(!response.isSuccessful()) {
						isResultsValid=false;
						break;
					}
					for(SearchAggregatedResults sra: response.getSearchAggregatedResults()) {
						for(SearchAggregatedSubResults sr: sra.getSearchAggregatedSubResults()) { 
							boolean continentFound=false;
							for(Country c: sr.getCountriesVisited().getCountry()) {
								if(c.getCode().value().equals((String)row.get("countrycode"))){
									continentFound=true;
									break;
								}
							}
							if(!continentFound) {
								isResultsValid=false;
								break;
							}
						}
					}
					if(!isResultsValid) {
						break;
					}
				}
				break;
			case "Durations":
				result = jdbcTemplate.queryForList(SearchLib.sqlForDurations, paramSource);
				if(result.size() == 0) {
					isResultsValid=false;
				}
				for(Map<String,Object> row: result) {
					request.getSellingCompanies().clear();
					request.getSellingCompanies().add((String)row.get("sellingcompanycode"));
					request.setDurationFrom(new BigInteger((String)row.get("duration")));
					request.setDurationTo(new BigInteger((String)row.get("duration")));
					response = wsClient.port.searchToursAggregated(request);
					if(!response.isSuccessful()) {
						isResultsValid=false;
						break;
					}
					for(SearchAggregatedResults sra: response.getSearchAggregatedResults()) {
						for(SearchAggregatedSubResults sr: sra.getSearchAggregatedSubResults()) { 
							if(!sr.getDuration().toString().equals((String)row.get("duration"))) {
								isResultsValid=false;
								break;
							}
						}
					}
					if(!isResultsValid) {
						break;
					}
				}
				break;
			case "Months":
				result = jdbcTemplate.queryForList(SearchLib.sqlForMonths, paramSource);
				if(result.size() == 0) {
					isResultsValid=false;
				}
				for(Map<String,Object> row: result) {
					request.getSellingCompanies().clear();
					request.getSellingCompanies().add((String)row.get("sellingcompanycode"));
					request.setMonths(((String)row.get("startdatetime")).replaceAll("^[0-9]+-0*([0-9]*)-.*", "$1"));
					
					response = wsClient.port.searchToursAggregated(request);
					if(!response.isSuccessful()) {
						isResultsValid=false;
						break;
					}
					for(SearchAggregatedResults sra: response.getSearchAggregatedResults()) {
						for(SearchAggregatedSubResults sr: sra.getSearchAggregatedSubResults()) { 
							int earliestMonth = Integer.parseInt(sr.getEarliestDepartureStartDate().toString().replaceAll("^[0-9]+-0*([0-9]*)-.*", "$1"));
							int latestMonth = Integer.parseInt(sr.getLatestDepartureStartDate().toString().replaceAll("^[0-9]+-0*([0-9]*)-.*", "$1"));
							int earliestYear = Integer.parseInt(sr.getEarliestDepartureStartDate().toString().replaceAll("^([0-9]+)-.*", "$1"));
							int latestYear = Integer.parseInt(sr.getLatestDepartureStartDate().toString().replaceAll("^([0-9]+)-.*", "$1"));
							int month = Integer.parseInt(request.getMonths());
							if(earliestYear == latestYear ) {
								if(!(earliestMonth <= month && latestMonth >= month)) {
									isResultsValid=false;
									break;
								}
							}
							else if(earliestYear+1 == latestYear){
								if(!(earliestMonth <= month || latestMonth >= month)) {
									isResultsValid=false;
									break;
								}
							}
						}
					}
					if(!isResultsValid) {
						break;
					}
				}
				break;
			case "PreferedRoomTypes":
				result = jdbcTemplate.queryForList(SearchLib.sqlForPreferedRoomType, paramSource);
				if(result.size() == 0) {
					isResultsValid=false;
				}
				for(Map<String,Object> row: result) {
					request.getSellingCompanies().clear();
					request.getSellingCompanies().add((String)row.get("sellingcompanycode"));
					request.setPreferedRoomType((String)row.get("rtype"));
					response = wsClient.port.searchToursAggregated(request);
					if(!response.isSuccessful()) {
						isResultsValid=false;
						break;
					}
					for(SearchAggregatedResults sra: response.getSearchAggregatedResults()) {
						for(SearchAggregatedSubResults sr: sra.getSearchAggregatedSubResults()) { 
							boolean isFound=false;
							for(RoomType c: sr.getSellableRoomTypes().getRoomType()) {
								if(c.getType().equals((String)row.get("rtype"))){
									isFound=true;
									break;
								}
							}
							if(!isFound) {
								isResultsValid=false;
								break;
							}
						}
					}
					if(!isResultsValid) {
						break;
					}
				}
				break;
			case "Prices":
				result = jdbcTemplate.queryForList(SearchLib.sqlForPrices, paramSource);
				if(result.size() == 0) {
					isResultsValid=false;
				}
				for(Map<String,Object> row: result) {
					request.getSellingCompanies().clear();
					request.getSellingCompanies().add((String)row.get("sellingcompanycode"));
					request.setPreferedRoomType((String)row.get("room_type"));
					Double priceFrom = Double.parseDouble((String)row.get("pmin"));
					request.setPriceFrom(priceFrom);
					Double priceTo = Double.parseDouble((String)row.get("pmax"));
					request.setPriceTo(priceTo);
					response = wsClient.port.searchToursAggregated(request);
					if(!response.isSuccessful()) {
						isResultsValid=false;
						break;
					}
					for(SearchAggregatedResults sra: response.getSearchAggregatedResults()) {
						for(SearchAggregatedSubResults sr: sra.getSearchAggregatedSubResults()) { 
							if(priceTo < sr.getPriceFrom()  || priceFrom > sr.getPriceTo()) {
								isResultsValid=false;
								break;
							}
							/*
	 						boolean isFound=false;
							for(RoomType c: sr.getSellableRoomTypes().getRoomType()) {
								if(c.getType().equals((String)row.get("room_type"))){
									isFound=true;
									break;
								}
							}
							if(!isFound) {
								isResultsValid=false;
								break;
							}
							*/
						}
					}
					if(!isResultsValid) {
						break;
					}
				}
				break;
			default:
				isResultsValid=false;
		}
	}
	
	@Then("^Test result is valid")
	public void is_successfull() throws Throwable {
		assertTrue(isResultsValid);
	}
}
