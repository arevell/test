package com.ttc.ch2.common.predicates;

import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Predicate;
import com.ttc.ch2.domain.auth.Role;

public class FindAuthorityPredicate implements Predicate<GrantedAuthority> {

	private Role roleToFind;
	public FindAuthorityPredicate(Role roleToFind)
	{
		this.roleToFind=roleToFind;
	}
	
	@Override
	public boolean apply(GrantedAuthority input) {
		return roleToFind.getName().equals(input.getAuthority());
	}	
}
