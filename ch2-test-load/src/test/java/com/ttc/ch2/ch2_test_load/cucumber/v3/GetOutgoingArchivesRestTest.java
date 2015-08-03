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

public class GetOutgoingArchivesRestTest {
	private CloseableHttpClient httpclient;
	
	private HttpGet httpGet;
	
	public GetOutgoingArchivesRestTest(String sellingCompanyCode) throws IOException {
		Properties props = new Properties();
		props.load(GetOutgoingArchivesRestTest.class.getResourceAsStream("/testdb.properties"));
		httpclient = HttpClients.createDefault();
		httpGet = new HttpGet(props.getProperty("ch2ws.rest") + "/outgoing_archives/V3/" + sellingCompanyCode + ".zip?token=" + props.getProperty("ch2ws.rest.token") );
	}
	
	public void execute() throws Exception {
		CloseableHttpResponse response=null;
		try {
			response = httpclient.execute(httpGet); 
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new Exception("Unable to get ZIP Content");
			}
			else {
				HttpEntity resEntity = response.getEntity();
				byte[] theZIP = EntityUtils.toByteArray(resEntity); // reading all the content of 
			}
		}catch(Exception e) {
//			e.printStackTrace();
			throw e;
		}finally {
			response.close();
		}
		
	}
}
