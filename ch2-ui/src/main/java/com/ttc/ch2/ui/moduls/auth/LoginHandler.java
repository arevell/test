package com.ttc.ch2.ui.moduls.auth;

import java.io.IOException;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.ttc.ch2.audit.annotations.Audit;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.auth.UserGuiDetails;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.security.UserContext;
import com.ttc.ch2.ui.mvc.LoginCtrl;

public class LoginHandler extends SimpleUrlAuthenticationSuccessHandler {
	 
	private static final Logger logger = LoggerFactory.getLogger(LoginHandler.class);
	
	@Inject
	private UserService userService;
	
	@Audit(object="Login")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {	   	         	         	    		 	
		 	 logger.trace("LoginHandler:onAuthenticationSuccess-start");
			 	 UserGuiDetails principal=(UserGuiDetails) authentication.getPrincipal();		 	
		         UserContext ctx=new UserContext();	         	         	         
		         ctx.putObj(UserContext.UserContextStaticName.LOGED_USER, principal.getUserDb());	         
		         HttpSession session=SessionHelper.getSession(request);
		         session.setAttribute(UserContext.class.getName(), ctx);
		         
		         // removed unnessesery value
		         session.removeAttribute(LoginCtrl.LOG_PAGE_ID);
		         
		         /*
		         try {
		        	 Random generator = new Random();
		        	 BlowfishCoder.initCoder(session, ("66633"+principal.getUserDb().getUsername()+generator.nextInt(100000)).getBytes());
		         }catch(Exception e) {
		        	 throw new IOException(e);
		         }
		         */
		         if(principal.getUserDb().getCountInvalidLog()>0)
		        	 userService.resetInvalidCountLogin(principal.getUserDb().getUsername());
		         
		         if(principal.getUserDb().getFirstLog()==true)
		        	 getRedirectStrategy().sendRedirect(request, response, Ch2URIs.CHGPSWD.getPath());		         
		         else if(SecurityHelper.hasAuthority(Role.ADMINISTRATOR))	        
		        	 getRedirectStrategy().sendRedirect(request, response, Ch2URIs.SCHEDULER.getPath());		        	
		         else if(SecurityHelper.hasAuthority(Role.BRAND))
		        	 getRedirectStrategy().sendRedirect(request, response, Ch2URIs.TOUR_MATCH_STATUS_HTML.getPath());
		         
		         super.onAuthenticationSuccess(request, response, authentication);
	         logger.trace("LoginHandler:onAuthenticationSuccess-end");
	      }
	}