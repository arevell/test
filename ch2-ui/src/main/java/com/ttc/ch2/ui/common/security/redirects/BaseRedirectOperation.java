package com.ttc.ch2.ui.common.security.redirects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.ttc.ch2.ui.mvc.ExceptionXmlConverter;
import com.ttc.common.utils.HttpResponseHelper;
import com.ttc.util.messages.Severity;

public abstract class BaseRedirectOperation implements AfterLoginRedirectOperation {

	protected String getLastPathOfRequest(HttpServletRequest request) {
		String uri=request.getRequestURI();	
		uri = StringUtils.substringBefore(uri, ";jsessionid");
		return Lists.newLinkedList(Splitter.on("/").split(uri)).getLast();
	}
	
	protected void sendError(HttpServletResponse response,int code,String msg) throws ServletException{
		
		try{
		String content=new ExceptionXmlConverter().convertToXmlByJaxb(code,msg,Severity.ERROR);
		response.setStatus(code);
		HttpResponseHelper.writeOutput(response, content,"text/xml");	
		}
		catch(Exception e){
			throw new ServletException(e);
		}
	}
	
}
