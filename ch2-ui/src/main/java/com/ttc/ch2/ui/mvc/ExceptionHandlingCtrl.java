package com.ttc.ch2.ui.mvc;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.ui.common.config.JspCh2URIs;
import com.ttc.ch2.ui.common.exceptions.PermissionException;
import com.ttc.ch2.ui.common.exceptions.SessionIncorrectException;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.common.utils.ThrowablesHelper;

@Controller
public class ExceptionHandlingCtrl 
{	
	public static enum Param{TIME,CODE,READ_FROM_SESSION,RELOAD_PAGE,EXCEPTION_MSG,SOAP_ERROR_TITLE,REST_CONTENT};
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingCtrl.class);
	
	@Inject
	private MessageSource messageSource;
	
	@Value("${app.name}")
	private String appName;
	
	@RequestMapping(value = "/base/error",  method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView  handleException(HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		try{		
		Throwable exception=(Throwable) request.getAttribute("javax.servlet.error.exception");
		logger.error("",exception);
		
			ModelAndView mav=null;
			switch (checkTypyOfRequest(request)) {
				case UI:
					mav=uiHandle(request, response);
					break;
				case SOAP:
					mav=soapHandle(request, response);
					break;			
				case REST:				
					mav=restHandle(request, response);
				default:
					break;
			}
			return mav;
			}
		catch(Exception e){
			logger.error( messageSource.getMessage("errorCtrl.requesterror",new Object[]{}, Locale.getDefault()));
			throw e;
		}
    }

	
	private RequestType checkTypyOfRequest(HttpServletRequest request){		
		String requestUri=(String) request.getAttribute("javax.servlet.forward.request_uri");	
		String query=(String) request.getAttribute("javax.servlet.forward.query_string");
		
		if(requestUri.contains("ccapi")){
			return RequestType.SOAP;
		}
		if((requestUri.endsWith("zip") || requestUri.endsWith("xml") || requestUri.endsWith("pdf")) && request.getParameter("zk")==null){						
			return RequestType.REST;
		}
		else{
			return RequestType.UI;
		}
	}
		
	private ModelAndView restHandle(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav=new ModelAndView(JspCh2URIs.REST_ERROR.getName());
		Map<String,Object> data=Maps.newHashMap();
		data.put("description",  messageSource.getMessage("errorCtrl.resterrortitle",new Object[]{}, Locale.getDefault()));
		String code=ObjectUtils.toString(request.getAttribute("javax.servlet.error.status_code"), String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
		
		try{
			response.setStatus(Integer.parseInt(code));
		}
		catch(Exception e){
			logger.error("",e);
		}
		
		data.put("code",  code);		
		data.put("exception_msg",  ThrowablesHelper.getAllMessages((Throwable) request.getAttribute("javax.servlet.error.exception")));
		
				
		String content=new ExceptionXmlConverter().convert(data);
		mav.addObject(Param.REST_CONTENT.toString(), content);			
		return mav;
	}
	
	private ModelAndView soapHandle(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView(JspCh2URIs.SOAP_ERROR.getName());				
		mav.addObject(Param.SOAP_ERROR_TITLE.toString(), messageSource.getMessage("errorCtrl.soaperrortitle",new Object[]{}, Locale.getDefault()));		
		mav.addObject(Param.CODE.toString(), ObjectUtils.toString(request.getAttribute("javax.servlet.error.status_code"), "500"));		
		String msg=Joiner.on("\n").join(ThrowablesHelper.getAllMessages((Throwable) request.getAttribute("javax.servlet.error.exception")));		
		mav.addObject(Param.EXCEPTION_MSG.toString(), msg);	
		return mav;
	}
		
	private ModelAndView uiHandle(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav=null;
		if(ThrowablesHelper.throwableExist(SessionIncorrectException.class, (Throwable) request.getAttribute("javax.servlet.error.exception"))){
			mav=new ModelAndView("redirect:" + JspCh2URIs.SESSION_INVALIDATE.getPath());	
		}
		else if(ThrowablesHelper.throwableExist(PermissionException.class, (Throwable) request.getAttribute("javax.servlet.error.exception"))){
			mav=new ModelAndView("redirect:" + JspCh2URIs.ACCESS_DENIED.getPath());	
		}
		else{
			mav=errorUiHandle(request, response);
		}		
		return mav;
	}
			
	private ModelAndView errorUiHandle(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav=new ModelAndView(JspCh2URIs.ERROR.getName());
		String time=DateHelper.getCurrentDateTimeAsString();
		String code=ObjectUtils.toString(request.getAttribute("javax.servlet.error.status_code"), "500");
		String msg=ObjectUtils.toString(request.getAttribute("javax.servlet.error.message"), "No exist message exception. Contact the administrator, system registered an unexpected error");
		String exception=ObjectUtils.toString(request.getAttribute("javax.servlet.error.exception"), "Not found exception to present. Contact the administrator, system registered an unexpected error");
		
		mav.addObject(Param.TIME.toString(), time );
		mav.addObject(Param.CODE.toString(), code);
		mav.addObject(UserContextStaticName.EXCEPTION_MSG.toString(), StringEscapeUtils.escapeHtml(msg));
		mav.addObject(UserContextStaticName.EXCEPTION.toString(), exception);
		addReloadPage(request, mav);			
		return mav;
	}
		
		
	 private void addReloadPage(HttpServletRequest request,ModelAndView mav){		
		 	HttpSession session=request.getSession(false);	
			if(session!=null){
				boolean authenticated=com.ttc.ch2.common.SecurityHelper.isAuthenticatedSilent();
				if(authenticated){
					String uri=(String) request.getAttribute("javax.servlet.forward.request_uri");
					if(StringUtils.isNotEmpty(uri)){
						if(StringUtils.isNotEmpty(appName)){
						uri=uri.substring(appName.length()+1);
						}
						mav.addObject(UserContextStaticName.RELOAD_PAGE, uri);	
					}
				}
			}
		 
	 }
}