package com.ttc.ch2.ui.common.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.ttc.ch2.common.predicates.FindAuthorityPredicate;
import com.ttc.ch2.domain.auth.Role;


public class ZkauVoter implements AccessDecisionVoter<Object> {

		
	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		
		if (object instanceof FilterInvocation) {
			FilterInvocation f=(FilterInvocation) object;
			String fullreq=f.getFullRequestUrl();
			String u=f.getRequestUrl();		
		}
		
//		if (!(authentication.getDetails() instanceof WebAuthenticationDetails)) {
//			return ACCESS_DENIED;
//		}						
//		Optional<? extends GrantedAuthority> findedAuthority= Iterables.tryFind(authentication.getAuthorities(),new FindAuthorityPredicate(Role.CCAPI));
//		
//		return findedAuthority.isPresent()  ? ACCESS_GRANTED : ACCESS_DENIED;
		return  ACCESS_GRANTED ;
	}
	
	
	@Override
	public boolean supports(ConfigAttribute attribute) {				
		return attribute.getAttribute() != null && "isAuthenticated()".equals(attribute.getAttribute());	
	}

	@Override
	public boolean supports(Class clazz) {
		return clazz.getName().equals("org.springframework.security.web.FilterInvocation");
	}
}


