package com.ttc.ch2.api.ccapi;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.xml.transform.dom.DOMSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapMessage;

import com.ttc.ch2.common.TransformHelper;

public class LogToDbInterceptor implements EndpointInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LogToDbInterceptor.class);

	@Value("${log.to.db.request.response.soap}")
	private String logToDbRequestRespone;

	@Value("${content.repository.path}")
	private String path;

	private ThreadLocal<String> fileSuffix = new ThreadLocal<>();

	@PostConstruct
	public void init() {
	}

	@Override
	public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {

		try{			
			if (BooleanUtils.toBoolean(logToDbRequestRespone)) {
				WebServiceMessage msg = messageContext.getRequest();
				if (msg instanceof SoapMessage) {
					SoapMessage msgSoap = (SoapMessage) msg;
					if (msgSoap.getSoapBody().getSource() instanceof DOMSource) {
						logger.debug(TransformHelper.documentToString(msgSoap.getDocument()));	
						fileSuffix.set(RandomStringUtils.random(5, true, true));	
						String fileName = path + "/soap-request-" + fileSuffix.get() + ".xml";
						FileUtils.writeStringToFile(new File(fileName), TransformHelper.documentToString(msgSoap.getDocument()));
					} else {
						throw new UnsupportedOperationException("Source of Body has incorrect type:" + msgSoap.getSoapBody().getSource().getClass().getName());
					}
				} else {
					throw new UnsupportedOperationException("Unsupported MessageContext type:" + messageContext.getClass().getName());
				}
			}
		}
		catch (Exception e) {
			logger.error("",e);
		}
		return true;
	}
	
	@Override
	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {

		try{
			if (BooleanUtils.toBoolean(logToDbRequestRespone)) {
				WebServiceMessage msg = messageContext.getResponse();
				if (msg instanceof SoapMessage) {
					SoapMessage msgSoap = (SoapMessage) msg;
					if (msgSoap.getSoapBody().getSource() instanceof DOMSource) {
						logger.debug(TransformHelper.documentToString(msgSoap.getDocument()));
						String fileName = path + "/soap-response-" + fileSuffix.get() + ".xml";
						FileUtils.writeStringToFile(new File(fileName), TransformHelper.documentToString(msgSoap.getDocument()));
					} else {
						throw new UnsupportedOperationException("Source of Body has incorrect type:" + msgSoap.getSoapBody().getSource().getClass().getName());
					}
				} else {
					throw new UnsupportedOperationException("Unsupported MessageContext type:" + messageContext.getClass().getName());
				}
			}
		}
		catch (Exception e) {
			logger.error("",e);
		}
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}
}
