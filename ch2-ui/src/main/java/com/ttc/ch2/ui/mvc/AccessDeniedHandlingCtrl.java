package com.ttc.ch2.ui.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttc.ch2.ui.common.config.JspCh2URIs;
import com.ttc.util.messages.Severity;

@Controller
public class AccessDeniedHandlingCtrl 
{	
	private static final Logger logger = LoggerFactory.getLogger(AccessDeniedHandlingCtrl.class);
	public static enum Param{REST_CONTENT};
		
	@RequestMapping(value = "/base/access_denied",  method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  handleAccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{		
			ModelAndView mav=null;
			switch (checkTypyOfRequest(request)) {
			case UI:
				mav=uiHandle(request, response);
				break;
			case REST:				
				mav=restHandle(request, response);
			default:
				break;
			}
			return mav;
		}
		catch(Exception e){
			throw e;
		}
	}
	
	private RequestType checkTypyOfRequest(HttpServletRequest request){		
		String requestUri=StringUtils.defaultIfBlank((String) request.getAttribute("javax.servlet.forward.request_uri"),"");	
		String query=(String) request.getAttribute("javax.servlet.forward.query_string");
				
		if((requestUri.endsWith("zip") || requestUri.endsWith("xml") || requestUri.endsWith("pdf")) && request.getParameter("zk")==null){						
			return RequestType.REST;
		}
		else{
			return RequestType.UI;
		}
	}
		
	private ModelAndView restHandle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav=new ModelAndView("RestError");
		String content=new ExceptionXmlConverter().convertToXmlByJaxb(HttpServletResponse.SC_UNAUTHORIZED,"Access denied",Severity.ERROR);
		mav.addObject(Param.REST_CONTENT.toString(), content);			
		return mav;
	}
		
	private ModelAndView uiHandle(HttpServletRequest request, HttpServletResponse response){			
		ModelAndView mav=new ModelAndView(JspCh2URIs.ACCESS_DENIED.getName());
		return mav;
	}
			

	

}