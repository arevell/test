package com.ttc.ch2.ch2_test_load.cucumber.v3;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedRequest;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedResponse;
import com.ttc.ch2.ch2_test_load.cucumber.Ch2WSClient;

public class SearchToursAggregatedTest {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchToursAggregatedTest.class);
	private Ch2WSClient client;
	private String sellingComapnyCode;
	
	public SearchToursAggregatedTest(String sellingComapnyCode) {
		client = new Ch2WSClient();
		this.sellingComapnyCode = sellingComapnyCode;
	}
	
	public void testCcapiV3SearchToursAggregated() {
		SearchToursAggregatedRequest searchToursRequest = new SearchToursAggregatedRequest();
		searchToursRequest.setSecurityKey(client.token);
		searchToursRequest.setFirstRecordNumber(new BigInteger("1"));
		searchToursRequest.setNumberOfRecords(new BigInteger("10"));
		searchToursRequest.getSellingCompanies().add(sellingComapnyCode);
		SearchToursAggregatedResponse response = client.port.searchToursAggregated(searchToursRequest);
		assertTrue(response.isSuccessful());
		assertTrue(response.getNumberOfRecords().intValue() > 0);
	}
	
}
