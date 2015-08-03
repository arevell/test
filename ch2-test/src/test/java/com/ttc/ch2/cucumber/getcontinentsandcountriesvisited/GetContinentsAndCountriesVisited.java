package com.ttc.ch2.cucumber.getcontinentsandcountriesvisited;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ttc.ch2.api.ccapi.v3.ContinentAndCountries;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedRequest;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedResponse;
import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullResponse;
import com.ttc.ch2.cucumber.CcapiCucumberHelper;
import com.ttc.ch2.cucumber.Ch2WSClient;
import com.ttc.ch2.cucumber.SearchLib;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetContinentsAndCountriesVisited {

	private static String endPoint=null;
	static
	{
		endPoint = CcapiCucumberHelper.endPoint;
	}
	
	
	private Ch2WSClient wsClient = new Ch2WSClient(endPoint);
	private String sccode;
	private int nrecords;
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
	
	
	@Given("^token is equal (.*)")
	public void populate_token(String token) throws Throwable {
		this.token = token;
	}
	
	@When("^I request for getContinentsAndCountriesVisited")
	public void execute_get_tour_details() throws IOException {
		GetContinentsAndCountriesVisitedRequest request = new GetContinentsAndCountriesVisitedRequest(); 
		request.setSecurityKey(token);
	    
	    NamedParameterJdbcTemplate jdbcTemplate = SearchLib.prepareTemplate();
	    MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("sccode",sccode);
	    paramSource.addValue("records", nrecords);
	    List<Map<String,Object>> result = jdbcTemplate.queryForList(SearchLib.sqlForContinentsCodes, paramSource);
	    paramSource.addValue("records", 1000);
	    List<Map<String,Object>> resultForConfirmation = jdbcTemplate.queryForList(SearchLib.sqlForContryCodes, paramSource);
	    GetContinentsAndCountriesVisitedResponse response;
	    if(result.size() == 0) {
	    	return;
	    }
		for(Map<String, Object> row: result) {
			request.getSellingCompanies().add((String)row.get("sellingcompanycode"));
			request.setContinent((String)row.get("continentcode"));
			List<Map<String,Object>> countriesForContinentList = new ArrayList<Map<String,Object>>();
			for(Map<String, Object> subrow: resultForConfirmation) {
				if(((String)row.get("continentcode")).equals((String)subrow.get("continentcode"))) {
					countriesForContinentList.add(subrow);
				}
			}
			response = wsClient.port.getContinentsAndCountriesVisited(request);
			if((!response.isSuccessful() && response.getContinentsAndCountries().size() !=1) || response.getContinentsAndCountries()==null) {
				isResultsValid=false;
				break;
			}
			if(response.getContinentsAndCountries().get(0).getCountries().size()!=countriesForContinentList.size()) {
				isResultsValid=false;
				break;
			}
			boolean isFound=false;
			for(Map<String, Object> subrow: countriesForContinentList) {
				isFound=false;
				for(String country: response.getContinentsAndCountries().get(0).getCountries()) {
					if(country.equals((String)subrow.get("countrycode"))) {
						isFound=true;
						break;
					}
				}
				if(!isFound) {
					break;
				}
			}
			if(!isFound) {
				isResultsValid=false;
				break;
			}
		}
	}
	
	@Then("^Test result is valid")
	public void is_successfull() throws Throwable {
		assertTrue(isResultsValid);
	}
	
}
