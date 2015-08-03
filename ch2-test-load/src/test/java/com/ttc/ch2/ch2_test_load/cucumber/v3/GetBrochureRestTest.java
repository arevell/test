package com.ttc.ch2.ch2_test_load.cucumber.v3;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class GetBrochureRestTest {
	private CloseableHttpClient httpclient;
	
	private HttpGet httpGet;
	
	public GetBrochureRestTest(String brandCode, String sellingCompanyCode, String tourCode) throws IOException {
		Properties props = new Properties();
		props.load(GetBrochureRestTest.class.getResourceAsStream("/testdb.properties"));
		httpclient = HttpClients.createDefault();
		String uri = props.getProperty("ch2ws.rest") + "/brochure_engine/brochure.pdf?token=" +
				props.getProperty("ch2ws.rest.token") + "&brandCode=" + brandCode + "&sellingCompanyCode=" + sellingCompanyCode + "&tours=" + tourCode;
	
        httpGet = new HttpGet(uri);
	}
	
	public void testCcapiV3GetBrochure() throws Exception {
		CloseableHttpResponse response=null;
		try {
			response = httpclient.execute(httpGet); 
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new Exception("Unable to get pdf Content");
			}
			else {
				HttpEntity resEntity = response.getEntity();
				byte[] thePdf = EntityUtils.toByteArray(resEntity); // reading all the content of 
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			response.close();
		}
		
	}
}
