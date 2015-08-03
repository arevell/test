package com.ttc.ch2.cucumber;

import java.net.URL;

import javax.xml.namespace.QName;

import com.ttc.ch2.api.ccapi.v3.Ccapi;
import com.ttc.ch2.api.ccapi.v3.CcapiService;

public class Ch2WSClient {
	
	protected CcapiService service;
	public Ccapi port = null;
	
	public Ch2WSClient() {
		try {
			
			URL baseUrl = com.ttc.ch2.api.ccapi.v3.CcapiService.class.getResource(".");
			URL url = new URL(baseUrl, "http://lonwk0411.corp.ttc:7777/ch2-ui/ccapi/v3/CCAPIv3.wsdl");
			service = new CcapiService(url, new QName("http://www.ttc.com/ch2/api/ccapi/v3", "ccapiService"));
			
			//service = new CcapiService();
			port = service.getCcapiSoap11();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Ch2WSClient(String endpoint) {
		
		try {
			URL baseUrl = com.ttc.ch2.api.ccapi.v3.CcapiService.class.getResource(".");
			URL url = new URL(baseUrl, endpoint+".wsdl");
			service = new CcapiService(url, new QName("http://www.ttc.com/ch2/api/ccapi/v3", "ccapiService"));
			port = service.getCcapiSoap11();
		}catch (Exception e) {
			e.printStackTrace();
		}				
	}
}
