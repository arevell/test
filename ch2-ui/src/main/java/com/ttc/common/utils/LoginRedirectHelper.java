package com.ttc.common.utils;

import org.zkoss.zk.ui.Executions;

import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.auth.UserGuiDetails;
import com.ttc.ch2.ui.common.config.Ch2URIs;


public class LoginRedirectHelper{
	
	public void redirect() {		
		if(SecurityHelper.isAuthenticatedSilent() && SecurityHelper.isUserGui()){	
			 UserGuiDetails principal=SecurityHelper.getUserGuiPrincipal();			
			 if(principal.getUserDb().getFirstLog()==true)
				 Executions.sendRedirect(Ch2URIs.CHGPSWD.getPath());		         
	         else if(SecurityHelper.hasAuthority(Role.ADMINISTRATOR))
	        	 Executions.sendRedirect(Ch2URIs.AUDIT.getPath());
	         else if(SecurityHelper.hasAuthority(Role.BRAND))
	        	 Executions.sendRedirect(Ch2URIs.TOUR_MATCH_STATUS_HTML.getPath());
		}		
	}
}
