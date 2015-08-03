package com.ttc.ch2.cucumber.gettourdetailsfull;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullResponse;
import com.ttc.ch2.cucumber.CcapiCucumberHelper;
import com.ttc.ch2.cucumber.Ch2WSClient;
import com.ttc.ch2.cucumber.SearchLib;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetTourDetailsFull {

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
	
	@When("^I request for the getTourDetailsFull")
	public void execute_get_tour_details_full() throws IOException {
		GetTourDetailsFullRequest request = new GetTourDetailsFullRequest(); 
		request.setSecurityKey(token);
	    
	    NamedParameterJdbcTemplate jdbcTemplate = SearchLib.prepareTemplate();
	    MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("sccode",sccode);
	    paramSource.addValue("records", nrecords);
	    List<Map<String,Object>> result = jdbcTemplate.queryForList(SearchLib.sqlForTourCodes, paramSource);
	    GetTourDetailsFullResponse response;
	    if(result.size() == 0) {
	    	isResultsValid=false;
	    }
		for(Map<String, Object> row: result) {
			request.setSellingCompany((String)row.get("sellingcompanycode"));
			request.setTourCode((String)row.get("tour_code"));
			response = wsClient.port.getTourDetailsFull(request);
			if(!response.isSuccessful()) {
				isResultsValid=false;
				break;
			}
			if(!response.getTourInfo().getTourCode().equals((String)row.get("tour_code"))) {
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
