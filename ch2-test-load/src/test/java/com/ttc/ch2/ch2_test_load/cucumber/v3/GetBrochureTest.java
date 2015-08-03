package com.ttc.ch2.ch2_test_load.cucumber.v3;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ttc.ch2.api.ccapi.v3.GetBrochureRequest;
import com.ttc.ch2.api.ccapi.v3.GetBrochureResponse;
import com.ttc.ch2.ch2_test_load.cucumber.Ch2WSClient;

public class GetBrochureTest {
	private Ch2WSClient client;
	private String sellingCompanyCode;
	private String brandCode;
	private String tourCode;
	
	public GetBrochureTest(String brandCode, String sellingCompanyCode, String tourCode) {
		client = new Ch2WSClient();
		this.sellingCompanyCode = sellingCompanyCode;
		this.tourCode = tourCode;
		this.brandCode = brandCode; 
	}
	
	public void testCcapiV3GetBrochure() {
		GetBrochureRequest req = new GetBrochureRequest();
		req.setSellingCompanyCode(sellingCompanyCode);
		req.setBrandCode(brandCode);
		req.getTour().add(tourCode);
		req.setSecurityKey(client.token);
		GetBrochureResponse resp = client.port.getBrochure(req);
		assertTrue(resp.isSuccessful());
	}
}
