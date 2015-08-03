package com.ttc.ch2.ch2_test_load.cucumber.v1;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.travelcorp.ccapi.ArrayOfString;
import com.travelcorp.ccapi.SearchTourResults;
//
//import com.ttc.ch2.api.ccapi.v1.ArrayOfString;
//import com.ttc.ch2.api.ccapi.v1.SearchToursRequest;
//import com.ttc.ch2.api.ccapi.v1.SearchToursResponse;
//
//import com.ttc.ch2.api.ccapi.v1.ArrayOfString;
//import com.ttc.ch2.api.ccapi.v1.SearchToursRequest;
//import com.ttc.ch2.api.ccapi.v1.SearchToursResponse;
import com.ttc.ch2.ch2_test_load.cucumber.Ch2WSClientV1;


public class SearchToursTest {
	
	private Ch2WSClientV1 client;
	private String sellingComapnyCode;
	
	public SearchToursTest(String sellingComapnyCode) {
		client = new Ch2WSClientV1();
		this.sellingComapnyCode = sellingComapnyCode;
	}
	
	public void testCcapiV1SearchTours()  {
        
	    ArrayOfString sccodes = new ArrayOfString();
		sccodes.getString().add(sellingComapnyCode);
		SearchTourResults response = client.port.searchTours(client.token, sccodes, "", "", "", "", new ArrayOfString(), 1, 10, "", "");
		assertTrue(response.getHeader().isSuccessful());
		assertTrue(response.getHeader().getNumberOfRecords() > 0);
	}
}
