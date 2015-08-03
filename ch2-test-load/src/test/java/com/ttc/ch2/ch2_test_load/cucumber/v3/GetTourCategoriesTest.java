package com.ttc.ch2.ch2_test_load.cucumber.v3;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesResponse;
import com.ttc.ch2.ch2_test_load.cucumber.Ch2WSClient;

public class GetTourCategoriesTest {
	private Ch2WSClient client;
	private String sellingCompanyCode;
	
	public GetTourCategoriesTest(String scCode) {
		client = new Ch2WSClient(); 
		this.sellingCompanyCode = scCode;
	}
	
	public void testCcapiV3GetTourCategories() {
		GetTourCategoriesRequest req = new GetTourCategoriesRequest();
		req.setSecurityKey(client.token);
		req.setSellingCompany(sellingCompanyCode);
		GetTourCategoriesResponse resp = client.port.getTourCategories(req);
		assertTrue(resp.isSuccessful());
	}
}
