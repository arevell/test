package com.ttc.ch2.cucumber.gettourcategories;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesResponse;
import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullResponse;
import com.ttc.ch2.api.ccapi.v3.TourCategories;
import com.ttc.ch2.cucumber.CcapiCucumberHelper;
import com.ttc.ch2.cucumber.Ch2WSClient;
import com.ttc.ch2.cucumber.SearchLib;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetTourCategories {

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
	
	@When("^I request for the getTourCategories")
	public void execute_get_tour_categories() throws IOException {
		GetTourCategoriesRequest request = new GetTourCategoriesRequest(); 
		request.setSecurityKey(token);
	    
	    NamedParameterJdbcTemplate jdbcTemplate = SearchLib.prepareTemplate();
	    MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("sccode",sccode);
	    paramSource.addValue("records", nrecords);
	    List<Map<String,Object>> result = jdbcTemplate.queryForList(SearchLib.sqlForTourCategories, paramSource);
	    request.setSellingCompany(sccode);
	    GetTourCategoriesResponse response = wsClient.port.getTourCategories(request);;
	    if(result.size() == 0) {
	    	return;
	    }
	    if(!response.isSuccessful() || response.getTourCategories().size() != result.size()) {
			isResultsValid=false;
			return;
		}
	    boolean isFound=false;
		for(Map<String, Object> row: result) {
			isFound=false;
			for(TourCategories subrow: response.getTourCategories() ) {
				if(subrow.getTourCategory().equals((String)row.get("categoryname"))) {
					List<String> dbValues = Arrays.asList(StringUtils.split((String)row.get("categoryvalues"), ","));
					if(dbValues.size() == subrow.getCategoryValue().size()) {
						int finded=0;
						for(String cvs: subrow.getCategoryValue()) {
							if(dbValues.indexOf(cvs)>=0) {
								finded++;
							}
						}
						if(finded==dbValues.size()) {
							isFound=true;
						}
					}
					break;
				}
			}
			if(!isFound) {
				break;
			}
		}
		if(!isFound) {
			isResultsValid=false;
		}
	}
	
	@Then("^Test result is valid")
	public void is_successfull() throws Throwable {
		assertTrue(isResultsValid);
	}
	
}
