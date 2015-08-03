package com.ttc.ch2.dao.auth;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Sets;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.domain.auth.Authority;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.auth.GroupAuthority;
import com.ttc.ch2.domain.user.User;

public class AuthorityTest {

	
	@Test
	public void checkAllGrantedAuthoritiesForUserTestWithOutGrups()
	{
		//prepare
		User u=new User();
		Authority a=new Authority();
		a.setId(1l);
		a.setAuthority("auth:1");
		Authority b=new Authority();
		b.setId(2l);
		b.setAuthority("auth:1");		
		u.setAuthorities(Sets.newHashSet(a,b));
				
		//test
		Set <GrantedAuthority> auths=AuthorityHelper.getAllGrantedAuthoritiesForUser(u);
		
		//checks
		Assert.assertTrue(auths.size()==1);
	}
	
	@Test
	public void checkAllGrantedAuthoritiesForUserTestWithGroups()
	{
		//prepare
		User u=new User();
		Authority a=new Authority();
		a.setId(1l);
		a.setAuthority("auth:1");
		Authority b=new Authority();
		b.setId(2l);
		b.setAuthority("auth:1");
		
		Group g=new Group();
		g.setId(1l);
		GroupAuthority ga=new GroupAuthority();
		ga.setId(1l);
		ga.setAuthority("auth:1");
		g.setGroupAuthorities(Sets.newHashSet(ga));
		u.setGroups(Sets.newHashSet(g));		
		u.setAuthorities(Sets.newHashSet(a,b));
		
		//test
		Set <GrantedAuthority> auths=AuthorityHelper.getAllGrantedAuthoritiesForUser(u);

		//checks
		Assert.assertTrue(auths.size()==1);
	}
}
