package com.ttc.ch2.ch2_test_load.cucumber;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travcorp.contenthub.tour_data._2010._11._1.CcapiEnhancedServiceService;
import com.travcorp.contenthub.tour_data._2010._11._1.TourContentApi;


public class Ch2WSClientV1 {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch2WSClientV1.class);

	public final String token;
	protected CcapiEnhancedServiceService service;
	public TourContentApi port = null;

	public Ch2WSClientV1() {
		try {
			Properties prop = new Properties();
			InputStream stream = System.getProperty("ch2.properties") != null ? new FileInputStream(System.getProperty("ch2.properties")) : DBClient.class.getResourceAsStream("/testdb.properties");
			prop.load(stream);
			URL baseUrl = CcapiEnhancedServiceService.class.getResource(".");
        	URL url = new URL(baseUrl, prop.getProperty("ch2wsclientV1.endpoint")); //"http://localhost:8888/ch2-ui/ccapi/v1/CCAPIv1.wsdl"
        	service = new CcapiEnhancedServiceService(url, new QName("http://contenthub.travcorp.com/tour_data/2010/11/1.0", "CcapiEnhancedServiceService"));
        	token = prop.getProperty("ch2wsclientV1.token"); // "token-xxx";
        	port = service.getTourContentApiPort();
			logger.trace(port.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}				
	}
	
}
