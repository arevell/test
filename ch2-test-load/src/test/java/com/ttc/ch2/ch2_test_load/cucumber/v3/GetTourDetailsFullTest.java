package com.ttc.ch2.ch2_test_load.cucumber.v3;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullResponse;
import com.ttc.ch2.ch2_test_load.cucumber.Ch2WSClient;

public class GetTourDetailsFullTest {
	private Ch2WSClient client;
	private GetTourDetailsFullRequest request = new GetTourDetailsFullRequest();
	private GetTourDetailsFullResponse response;
	private String sellingCompanyCode;
	private String tourCode;
	private String securityKey;
	
	public GetTourDetailsFullTest(String sellingCompanyCode, String tourCode) {
		client = new Ch2WSClient();
		this.sellingCompanyCode = sellingCompanyCode;
		this.tourCode = tourCode;
		this.securityKey = client.token; 
	}
	
	public void testCcapiV3GetTourDetailsFull() {
		request.setSellingCompany(sellingCompanyCode);
		request.setTourCode(tourCode);
		request.setSecurityKey(securityKey);
		response = client.port.getTourDetailsFull(request);
		assertTrue(response.isSuccessful());
	}
}
