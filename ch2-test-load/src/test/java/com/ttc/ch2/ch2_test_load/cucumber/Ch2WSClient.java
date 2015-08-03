package com.ttc.ch2.ch2_test_load.cucumber;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.api.ccapi.v3.Ccapi;
import com.ttc.ch2.api.ccapi.v3.CcapiService;

public class Ch2WSClient {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch2WSClient.class);

	public final String token; 
	protected CcapiService service;
	public final Ccapi port;

	public Ch2WSClient() {
		try {
			Properties prop = new Properties();
			InputStream stream = System.getProperty("ch2.properties") != null ? new FileInputStream(System.getProperty("ch2.properties")) : DBClient.class.getResourceAsStream("/testdb.properties");
			prop.load(stream);
			URL baseUrl = com.ttc.ch2.api.ccapi.v3.CcapiService.class.getResource(".");
			URL url = new URL(baseUrl, prop.getProperty("ch2wsclient.endpoint"));
			token = prop.getProperty("ch2wsclient.token"); // "token-xxx";
			service = new CcapiService(url, new QName("http://www.ttc.com/ch2/api/ccapi/v3", "ccapiService"));
			port = service.getCcapiSoap11();
			logger.trace(port.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
