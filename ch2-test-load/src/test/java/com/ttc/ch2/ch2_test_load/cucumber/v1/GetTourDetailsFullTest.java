package com.ttc.ch2.ch2_test_load.cucumber.v1;

import static org.junit.Assert.*;

import org.junit.Test;

import com.travcorp.contenthub.tour_data._2010._11._1.TourDetailsFull;
import com.travcorp.contenthub.tour_data._2010._11._1.TourDetailsFullResponse;
import com.ttc.ch2.ch2_test_load.cucumber.Ch2WSClientV1;


public class GetTourDetailsFullTest {
	private Ch2WSClientV1 client;
	private TourDetailsFull request = new TourDetailsFull();
	private TourDetailsFullResponse response;
	private String sellingCompanyCode;
	private String tourCode;
	private String securityKey;
	
	public GetTourDetailsFullTest(String sellingCompanyCode, String tourCode) {
		client = new Ch2WSClientV1();
		this.sellingCompanyCode = sellingCompanyCode;
		this.tourCode = tourCode;
		this.securityKey = client.token; 
	}
	
	public void testCcapiV1GetTourDetailsFull() {
		request.setSellingCompanyCode(sellingCompanyCode);
		request.setMarketVariationCode(tourCode);
		request.setSecurityKey(securityKey);
		response = client.port.tourDetailsFull(request);
		assertNotNull(response);
		assertTrue(response.getMarketLocalisedTourData().getBrandCode().length() > 0);
	}
}
