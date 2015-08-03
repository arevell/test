package com.ttc.ch2.ui.common.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.config.JspCh2URIs;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.mvc.ExceptionXmlConverter;
import com.ttc.ch2.ui.mvc.RequestType;
import com.ttc.common.utils.HttpResponseHelper;
import com.ttc.util.messages.Severity;


public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint{
	 
		
	@Value("${app.name}")
	private String appName;
	
	   @Override
	   public void commence( HttpServletRequest request, HttpServletResponse response,AuthenticationException authException ) throws IOException{		  
		   
		   
		   restResponse(response);
		   
		/*switch (checkTypyOfRequest(request)) {
		case REST:
			restResponse(response);
			break;
		case UI:
			uiResponse(response);
			break;			
		default:
			break;
		}*/
	   }
	   
	   
	   
	   private void restResponse(HttpServletResponse response){
		   try {
				String content;
				content = new ExceptionXmlConverter().convertToXmlByJaxb(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized",Severity.ERROR);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			    HttpResponseHelper.writeOutput(response, content,"text/xml");
		} catch (Exception e) {
			throw new CH2Exception(e);
		}  
	   }
	   
//	   private void uiResponse(HttpServletResponse response){		   
//		   response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
//		   if(StringUtils.isNotBlank(appName)){
//			   String path="/"+JspCh2URIs.PERMISSION.getPathWithAppName(appName);
//			   response.setHeader("Location", path);
//		   }else{
//			   String path=JspCh2URIs.PERMISSION.getPathWithAppName(appName);
//			   response.setHeader("Location", path);
//		   }
//		   
//	   }
//	   
//	   
//		private RequestType checkTypyOfRequest(HttpServletRequest request){		
//			String requestUri=(String) request.getRequestURI();			
//			if((requestUri.endsWith("zip") || requestUri.endsWith("xml") || requestUri.endsWith("pdf")) && request.getParameter("zk")==null){						
//				return RequestType.REST;
//			}
//			else{
//				return RequestType.UI;
//			}
//		}
	   
	
	}