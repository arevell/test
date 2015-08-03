package com.ttc.ch2.ui.common.security;

import java.util.Collection;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import com.ttc.ch2.common.AuthenticationHelper;
import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.user.UserGui;

public class ChLdapUserDetailsMapper extends LdapUserDetailsMapper{

	private static final Logger logger = LoggerFactory.getLogger(ChLdapUserDetailsMapper.class);
	@Inject
	private UserGuiDAO userGuiDAO;
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx,String username, Collection<? extends GrantedAuthority> authorities) {	
		logger.trace("ChLdapUserDetailsMapper:mapUserFromContext-start");
		//UserDetails usrDetail=super.mapUserFromContext(ctx, username, authorities);

		// usrDetail- Ldap user details is replaced to UserGuiDetails
		UserGui uExemple=new UserGui();
        uExemple.setUsername(username);
		UserGui uGui=userGuiDAO.findByExample(uExemple);		 
        if (uGui == null) {
            throw new BadCredentialsException("Username is not registred in Ch2.[user:"+username+"]");
        }
                
        if (uGui.getEnabled() != null && uGui.getEnabled()==false) {
        	throw new BadCredentialsException("User is disabled.[user:"+username+"]");
        }
        	        
 	        
        logger.trace("ChLdapUserDetailsMapper:mapUserFromContext-end");
		return (UserDetails)AuthenticationHelper.buildAuthenticationForUserGui(uGui).getPrincipal(); 
	}
}
