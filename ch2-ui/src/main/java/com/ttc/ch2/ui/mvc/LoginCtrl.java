package com.ttc.ch2.ui.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.config.JspCh2URIs;

@Controller
public class LoginCtrl 
{	
	private static final Logger logger = LoggerFactory.getLogger(LoginCtrl.class);
	
	@Value("${common.version}")		
	private String version;
	@Value("${common.enviroment}")		
	private String enviroment;
	@Value("${common.deploy.time}")
	private String deployTime;
	
	public static final String LOG_PAGE_ID="logPageId";
	public static final String VERSION="version";
	public static final String ENV="enviroment";
	public static final String DEPLOY_DATE="deployDate";
	public static final String CURR_DATE="currentDate";
	
	
	
	
		
	@RequestMapping(value = "/login",  method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  prepareLoginPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession currentSession=request.getSession(false);
		String logPageId=null;
		if(currentSession!=null){
			logPageId=(String) currentSession.getAttribute(LOG_PAGE_ID);
		}
		
		//clear session
		if(org.springframework.util.StringUtils.hasText(logPageId)==false){			
			if(currentSession!=null){
				SessionHelper.removeSession(request, response);			
			}
			currentSession=request.getSession(true);	
			logPageId=RandomStringUtils.random(5, true, true);
			currentSession.setAttribute(LOG_PAGE_ID, logPageId);		
		}
				
		ModelAndView mav=new ModelAndView(JspCh2URIs.LOGIN.getName());					
		mav.addObject(LOG_PAGE_ID,currentSession.getAttribute(LOG_PAGE_ID));
		
		mav.addObject(VERSION,StringUtils.defaultIfBlank(version, "this value should be setup in ch2main.properties"));
		mav.addObject(ENV,StringUtils.defaultIfBlank(enviroment, "this value should be setup in ch2main.properties"));
		mav.addObject(CURR_DATE,DateHelper.getCurrentDateTimeAsString());
		mav.addObject(DEPLOY_DATE,StringUtils.defaultIfBlank(deployTime, "this value should be setup in ch2main.properties"));
		
		return mav;
	}
}