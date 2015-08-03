package com.ttc.ch2.ui.common.security;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.ttc.ch2.common.AuthenticationHelper;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.auth.UserCCAPIDetails;
import com.ttc.ch2.domain.user.UserCCAPI;



public class TokenAuthenticationProvider implements AuthenticationProvider
{	
	@Inject
	private UserCCAPIDAO userCCAPIDAO;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {		   
	       	UserCCAPIDetails usrDetails = (UserCCAPIDetails) authentication.getPrincipal();
	       	
	       	if(StringUtils.isEmpty(usrDetails.getToken()))
	       		return null;
	       	
	        UserCCAPI user=userCCAPIDAO.findByToken(usrDetails.getToken());
	        if (user != null) {	      
	        		        	
	        	 if (user.getEnabled() != null && user.getEnabled()==false) {
	 	        	throw new BadCredentialsException("User is disabled.[user:"+user.getUsername()+"]");
	 	        }
	        	
		            return AuthenticationHelper.buildAuthenticationForUserCcapi(user, usrDetails);
	        }
	        return null;	        	        
	}
	   	   
	@Override
	public boolean supports(Class<?> authentication) {
	        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
	}
}