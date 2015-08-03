package com.ttc.ch2.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestContext;

import com.google.common.collect.Lists;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.auth.UserGuiDetails;
import com.ttc.ch2.domain.user.UserGui;

public class AuthenticatedExecutionPreparer extends Ch2TestExecutionListener {
	
	
	@Override
	public void beforeClass(TestContext testContext) {
		 authenticate();	
	}
	
	
	private void authenticate()
	{				
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/testOnlyCtx.xml","classpath:/META-INF/spring/dbCtx.xml");
		BrandDAO brandDAO=applicationContext.getBean(BrandDAO.class);
		
		UserGui ugui=SampleGenerator.generateUserUi("ui-test");
		ugui.getBrands().addAll(brandDAO.findAll());
				
		UserGuiDetails usr=new UserGuiDetails();
		usr.setUserDb(ugui);
		usr.setAuthorities(Lists.newArrayList(new SimpleGrantedAuthority("BRAND")));
		usr.setPassword(ugui.getPassword());
		usr.setUsername(ugui.getUsername());
		
		Authentication auth = new UsernamePasswordAuthenticationToken(usr, usr.getPassword(), Lists.newArrayList(new SimpleGrantedAuthority("BRAND")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
}