package com.ttc.ch2.api.ccapi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import com.ttc.ch2.api.ccapi.v1.ConsolidatedContentAPIv1;

public class CCAPIv1SoapMessageFactory extends SaajSoapMessageFactory {
	
	Logger logger = LoggerFactory.getLogger(CCAPIv1SoapMessageFactory.class);
	
	@Override
	public SaajSoapMessage createWebServiceMessage(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		IOUtils.copy(inputStream, baos);
		String inputSoap = new String(baos.toByteArray(),"UTF-8");
		String out=inputSoap;
		Pattern p = Pattern.compile(".*<[a-zA-Z0-9]*:TourDetailsFull .*xmlns=.*",Pattern.MULTILINE| Pattern.DOTALL); 
		Matcher m =p.matcher(inputSoap);
		if(!m.matches()) {
			out=inputSoap.replaceAll("<([a-zA-Z0-9]*:TourDetailsFull)(.*)", "<$1 xmlns=\""+ConsolidatedContentAPIv1.TARGET_NAMESPACE+ "\"$2");
		}
		logger.debug(out);
		return super.createWebServiceMessage(new ByteArrayInputStream(out.getBytes("UTF-8")));
	}
	
}
