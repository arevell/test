package com.ttc.ch2.hibernate.security;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.security.AuthorityDAO;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.auth.Authority;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.auth.GroupAuthority;
import com.ttc.ch2.domain.user.User;


@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class AuthorityDAOImpl extends BaseDao<Authority,  Long> implements  AuthorityDAO {

	@Inject
	private UserCCAPIDAO userCCAPIDAO;
	
	@Inject
	private UserGuiDAO userGuiDAO;
	
	@Override
	public Set<GrantedAuthority> getAllGrantedAuthoritiesForUser(Long userId) {
		Set<GrantedAuthority> result = new HashSet<GrantedAuthority>();
		User userDB = userGuiDAO.find(userId);
		if(userDB == null)
			userDB = userCCAPIDAO.find(userId);
		
		if(userDB != null) {
			if(userDB.getAuthorities() != null) {
				for(Authority a: userDB.getAuthorities()) {
					GrantedAuthority ga = new GrantedAuthorityImpl(a.getAuthority());
					result.add(ga);
				}
			}
			if(userDB.getGroups() != null ) {
				for(Group g: userDB.getGroups()) {
					for(GroupAuthority a: g.getGroupAuthorities()) {
						GrantedAuthority ga = new GrantedAuthorityImpl(a.getAuthority());
						result.add(ga);
					}
				}
			}
		}
		return result;
	}

}
