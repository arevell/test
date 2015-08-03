package com.ttc.ch2.ch2_test_load.cucumber.v3;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.api.ccapi.v3.SearchToursRequest;
import com.ttc.ch2.api.ccapi.v3.SearchToursResponse;
import com.ttc.ch2.ch2_test_load.cucumber.Ch2WSClient;

public class SearchToursTest {
	private static final Logger logger = LoggerFactory.getLogger(SearchToursTest.class);
	private Ch2WSClient client;
	private String sellingComapnyCode;
	
	public SearchToursTest(String sellingComapnyCode) {
		client = new Ch2WSClient();
		this.sellingComapnyCode = sellingComapnyCode;
	}
	
	public void testCcapiV3SearchTours()  {
		SearchToursRequest searchToursRequest = new SearchToursRequest();
		searchToursRequest.setSecurityKey(client.token);
		searchToursRequest.setFirstRecordNumber(new BigInteger("1"));
		searchToursRequest.setNumberOfRecords(new BigInteger("10"));
		searchToursRequest.getSellingCompanies().add(sellingComapnyCode);
		SearchToursResponse response = client.port.searchTours(searchToursRequest);
		assertTrue(response.isSuccessful());
		assertTrue(response.getNumberOfRecords().intValue() > 0);
	}
}
