package com.ttc.ch2.ui.moduls.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.ttc.ch2.audit.annotations.Audit;
import com.ttc.ch2.ui.common.SessionHelper;

//import com.ttc.ch2.audit.ChAudit;

public class LogoutHandler extends SimpleUrlLogoutSuccessHandler  
{
	private static final Logger logger=LoggerFactory.getLogger(LogoutHandler.class);
	
	private String urlLogoutSucces=null;
	
	@Override
	@Audit(object="Logout Explicit")
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {	
				
		SessionHelper.removeSession(request, response);
		DefaultRedirectStrategy dfRedirect=new DefaultRedirectStrategy();
				
		if(urlLogoutSucces!=null)
		{
			logger.debug("logout - redirect to :{} ",urlLogoutSucces);
			dfRedirect.sendRedirect(request, response, urlLogoutSucces);
		}
		else
		{
			logger.debug("logout - redirect to :{} ",getDefaultTargetUrl());
			dfRedirect.sendRedirect(request, response, getDefaultTargetUrl());
		}			
	}
	
	public String getUrlLogoutSucces() {
		return urlLogoutSucces;
	}


	public void setUrlLogoutSucces(String urlLogoutSucces) {
		this.urlLogoutSucces = urlLogoutSucces;
	}


}
