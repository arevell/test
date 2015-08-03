package com.ttc.ch2.ch2_test_load.cucumber.v3;

import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.activation.DataHandler;

import org.junit.Test;

import com.ttc.ch2.api.ccapi.v3.UploadTourInfoRequest;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoResponse;
import com.ttc.ch2.ch2_test_load.cucumber.Ch2WSClient;

public class UploadTourInfoTest {
	private Ch2WSClient client;
	
	public UploadTourInfoTest() {
		client = new Ch2WSClient(); 
	}
	
	public void testCcapiV3UploadTourInfo() {
		URL url = UploadTourInfoTest.class.getResource("BV.zip");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss'Z'");
		String fileName = sdf.format(new Date());
		UploadTourInfoRequest req = new UploadTourInfoRequest();
		req.setFileName(fileName);
		req.setSecurityKey(client.token);
		DataHandler dh = new DataHandler(url);
		req.setFileData(dh);
		UploadTourInfoResponse resp = client.port.uploadTourInfo(req);
		assertTrue(resp.isSuccessful());
	}
}
