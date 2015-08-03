package com.ttc.ch2.ui.common.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.ttc.ch2.domain.auth.UserCCAPIDetails;

public class TokenPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter  {

	private static final String TOKEN_PARAM_NAME="token";
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		
		String token=request.getParameter(TOKEN_PARAM_NAME);		
		UserCCAPIDetails u=new UserCCAPIDetails();
		u.setToken(token);
		return u;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "N/A";
	}
}
