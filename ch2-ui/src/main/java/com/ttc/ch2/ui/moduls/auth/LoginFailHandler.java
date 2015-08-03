package com.ttc.ch2.ui.moduls.auth;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import com.ttc.ch2.bl.user.UserService;
									  
public class LoginFailHandler extends ExceptionMappingAuthenticationFailureHandler {
	
private static final Logger logger = LoggerFactory.getLogger(LoginFailHandler.class);
	
	
	@Inject
	private UserService userService;
	
	@SuppressWarnings("deprecation")
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException{	
		logger.trace("LoginFailHandler:onAuthenticationFailure-start");
		
		super.onAuthenticationFailure(request, response, exception);
		
		if(exception.getAuthentication() !=null && exception.getAuthentication().getPrincipal()!=null)
		{
			if(exception.getAuthentication().getPrincipal() instanceof String){
				if(StringUtils.isNotEmpty((String)exception.getAuthentication().getPrincipal()))
				userService.invalidLoginOperation((String)exception.getAuthentication().getPrincipal());	
			}
			else{
				throw new UnsupportedOperationException("Unsupported type of Principal");
			}
		}
		logger.trace("LoginFailHandler:onAuthenticationFailure-end");
    }	
}
