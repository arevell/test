package com.ttc.ch2.ui.common.security;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ttc.ch2.common.AuthenticationHelper;
import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.mvc.LoginCtrl;

public class GuiAuthenticationProvider implements AuthenticationProvider
{
	@Inject
	private UserGuiDAO userGuiDAO;
	
	
	@Override
	public Authentication authenticate(Authentication authentication)throws AuthenticationException {
		
			validRequest();		
		    String username = authentication.getName();
	        String password = (String) authentication.getCredentials();
	        UserGui uExemple=new UserGui();
	        uExemple.setUsername(username);
	        UserGui uGui=userGuiDAO.findByExample(uExemple);
	 
	        if (uGui == null) {
	            throw new BadCredentialsException("Username was not found.[user:"+username+"]");
	        }
	        	        
	        if (uGui.getEnabled() != null && uGui.getEnabled()==false) {
	        	throw new BadCredentialsException("User is disabled.[user:"+username+"]");
	        }
	        	        
	        String hashPassword=password;	        
	        try {
				 hashPassword=new String(DigestUtils.md5DigestAsHex(password.getBytes("UTF-8")));
			} catch (UnsupportedEncodingException e) {
				throw new BadCredentialsException("Problem with md5 digest.");
			}
	        
	        if (!hashPassword.equals(uGui.getPassword())) {
	            throw new BadCredentialsException("Wrong password.");
	        }
	        
	        return AuthenticationHelper.buildAuthenticationForUserGui(uGui);
	}

	private void validRequest() {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession temporalySession=attr.getRequest().getSession(false);
			if(temporalySession==null){
				throw new BadCredentialsException("Temporaly session is requried");
			}
			
			String sessionLoginPageId=null;
			if(StringUtils.hasText((String) temporalySession.getAttribute(LoginCtrl.LOG_PAGE_ID))==false){
				throw new BadCredentialsException("login page is not present in session");
			}
			String requestLoginPageId=null;
			if(StringUtils.hasText((String) attr.getRequest().getParameter(LoginCtrl.LOG_PAGE_ID))==false){
				throw new BadCredentialsException("login page is not present in request");
			}
			requestLoginPageId=(String) attr.getRequest().getParameter(LoginCtrl.LOG_PAGE_ID);
			sessionLoginPageId=(String) temporalySession.getAttribute(LoginCtrl.LOG_PAGE_ID);
			
			if(sessionLoginPageId.equals(requestLoginPageId)==false){
				throw new BadCredentialsException("login page id is invalid");
			}	
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	} 

	

}
