package com.ttc.ch2.ch2_test_load.cucumber.v3;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedRequest;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedResponse;
import com.ttc.ch2.ch2_test_load.cucumber.Ch2WSClient;

public class GetContinentsAndCountriesVisitedTest {
	private Ch2WSClient client;
	private String continent;
	private String sellingCompanyCode;
	
	public GetContinentsAndCountriesVisitedTest(String continent, String sellingCompanyCode) {
		client = new Ch2WSClient(); 
		this.continent = continent;
		this.sellingCompanyCode = sellingCompanyCode;
	}
	
	public void testCcapiV3GetContinentsAndCountriesVisited() {
		GetContinentsAndCountriesVisitedRequest req = new GetContinentsAndCountriesVisitedRequest();
		req.setSecurityKey(client.token);
		req.setContinent(continent);
		req.getSellingCompanies().add(sellingCompanyCode);
		GetContinentsAndCountriesVisitedResponse resp = client.port.getContinentsAndCountriesVisited(req);
		assertTrue(resp.isSuccessful());
	}
}
