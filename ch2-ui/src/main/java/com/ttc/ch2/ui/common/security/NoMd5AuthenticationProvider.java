package com.ttc.ch2.ui.common.security;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.ttc.ch2.common.AuthenticationHelper;
import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.user.UserGui;

public class NoMd5AuthenticationProvider implements AuthenticationProvider
{
	@Inject
	private UserGuiDAO userGuiDAO;
	
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {		
		    String username = authentication.getName();
	        String password = (String) authentication.getCredentials();
	        UserGui uExemple=new UserGui();
	        uExemple.setUsername(username);
	        UserGui uGui=userGuiDAO.findByExample(uExemple);
	 
	        if (uGui == null) {
	            throw new BadCredentialsException("Username not found.[user:"+username+"]");
	        }
	        
	        if (uGui.getEnabled() != null && uGui.getEnabled()==false) {
	        	throw new BadCredentialsException("User is disabled.[user:"+username+"]");
	        }
	        	        
	        
	        String hashPassword=password;
	
	        if (!hashPassword.equals(uGui.getPassword())) {
	            throw new BadCredentialsException("Wrong password.");
	        }
	 	        
	        return AuthenticationHelper.buildAuthenticationForUserGui(uGui);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	} 


}
