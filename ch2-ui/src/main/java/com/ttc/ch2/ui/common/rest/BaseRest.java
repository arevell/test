package com.ttc.ch2.ui.common.rest;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.PermissionException;
import com.ttc.ch2.ui.common.exceptions.SessionIncorrectException;
import com.ttc.ch2.ui.mvc.ExceptionXmlConverter;
import com.ttc.common.utils.HttpResponseHelper;
import com.ttc.util.messages.Severity;

public class BaseRest {
		
	private static final Logger logger = LoggerFactory.getLogger(BaseRest.class);
	
	protected void checkAuthenticated(){		
		if(SecurityHelper.isAuthenticated()==false){
			throw new PermissionException("Authenticated not exist");
		}
	}
	
	@ExceptionHandler({Exception.class,CH2Exception.class})
	public String unhandledException(HttpServletResponse response,Exception e) {	
		try {
			logger.error("",e);						
			String content=new ExceptionXmlConverter().convertToXmlByJaxb(e);
			writeOutput(response,content);			
		} catch (IOException e1) {
				throw new CH2Exception(e);
		} catch (Exception e1) {
			throw new CH2Exception(e);
		}
		return null;
	}
	
	@ExceptionHandler({PermissionException.class})
    public String permissionError(HttpServletResponse response,Exception e) {						
		try {
			logger.error("",e);			
			String content=new ExceptionXmlConverter().convertToXmlByJaxb(HttpServletResponse.SC_UNAUTHORIZED,"Access denied",Severity.ERROR);
			writeOutput(response,content);			
		} catch (IOException e1) {
				throw new CH2Exception(e);
		} catch (Exception e1) {
			throw new CH2Exception(e);
		}
		return null;
	}
	
	@ExceptionHandler({SessionIncorrectException.class})
	public String sessionIncorrectError(HttpServletResponse response,Exception e) {	
		try {
			logger.error("",e);
			String content=new ExceptionXmlConverter().convertToXmlByJaxb(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Invalid Session",Severity.ERROR);
			writeOutput(response,content);		
		} catch (IOException e1) {
				throw new CH2Exception(e);
		} catch (Exception e1) {
			throw new CH2Exception(e);
		}
		return null;
	}
	
	
	protected void writeOutput(HttpServletResponse response,String content) throws IOException{
		HttpResponseHelper.writeOutput(response, content,"text/xml");
	}
}
