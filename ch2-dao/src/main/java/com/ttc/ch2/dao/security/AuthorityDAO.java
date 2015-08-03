package com.ttc.ch2.dao.security;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.auth.Authority;
import com.ttc.ch2.domain.user.User;


public interface AuthorityDAO extends GenericDAO<Authority, Long> {

	 Set<GrantedAuthority> getAllGrantedAuthoritiesForUser(Long userId);
	
}
