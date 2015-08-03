package com.ttc.ch2.ui.moduls.user.common;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.domain.user.User;

public class RoleDecorator {

public  String getAllGrantedAuthoritiesForUserAsString(User user,String separator) {		
		Set<String> auths=Sets.newHashSet();
		for (GrantedAuthority auth : AuthorityHelper.getAllGrantedAuthoritiesForUser(user)) {
			auths.add(auth.getAuthority());			
		}		
		return Joiner.on(separator).join(auths);
	}
}
