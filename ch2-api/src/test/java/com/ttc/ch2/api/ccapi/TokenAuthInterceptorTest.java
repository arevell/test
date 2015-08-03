package com.ttc.ch2.api.ccapi;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;

import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ttc.ch2.api.ccapi.TokenAuthInterceptor;
import com.ttc.ch2.api.ccapi.TokenValidationException;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.user.UserCCAPI;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TokenAuthInterceptor.class)
public class TokenAuthInterceptorTest {

	private TokenAuthInterceptor testedBean;
	private UserCCAPIDAO userDao;
	
	   @Before
	  public  void init() throws Exception
	  {
		  TokenAuthInterceptor localTestedBean=new TokenAuthInterceptor();
		  testedBean=PowerMockito.spy(localTestedBean);
		  userDao=PowerMockito.mock(UserCCAPIDAO.class);
		  Whitebox.setInternalState(testedBean, "userCCAPIDAO",userDao);
	  }
	
	  @After
	  public void afterMethod() {
		reset(testedBean);			   
	  } 
	
	@Test
	public void testInvalidSecurityKey() throws Exception
	{
		//prepare
		String key="xxxx";
		FunctionType fTyp=FunctionType.UPLOAD_TOUR_INFO;	
		List<String> companies=Lists.newArrayList();
		PowerMockito.doReturn(null).when(userDao, "findByToken", any());
				
		boolean expectedException=false;
		//test
		try
		{
			Whitebox.invokeMethod(testedBean, "authentication",new Object[]{key,fTyp,companies});
			expectedException=false;		
		}
		catch(TokenValidationException e)
		{
			expectedException=true;
			Assert.assertTrue("Invalid securityKey permission denied".equals(e.getMessage()));
		}
				
		Assert.assertEquals(true, expectedException);		
	}
	
	@Test
	public void testInvalidFunction() throws Exception
	{
		//prepare
		String key="xxxx";
		FunctionType fTyp=FunctionType.UPLOAD_TOUR_INFO;	
		List<String> companies=Lists.newArrayList();
		UserCCAPI u=new UserCCAPI();
		u.setToken(key);
		u.setEnabled(true);
		PowerMockito.doReturn(u).when(userDao, "findByToken", any());
				
		boolean expectedException=false;
		//test
		try
		{
			Whitebox.invokeMethod(testedBean, "authentication",new Object[]{key,fTyp,companies});
			expectedException=false;		
		}
		catch(TokenValidationException e)
		{
			expectedException=true;
			Assert.assertTrue("Invalid function permission denied".equals(e.getMessage()));
		}
				
		Assert.assertEquals(true, expectedException);		
	}
	
	@Test
	public void testInvalidSellingCompanyWithOutCompanies() throws Exception
	{
		//prepare
		String key="xxxx";
		FunctionType fTyp=FunctionType.GET_BROCHURE;	
		List<String> companies=Lists.newArrayList();
		
		Function f=new Function();
		f.setName(fTyp.getSimpleName());
		
		UserCCAPI u=new UserCCAPI();
		u.setToken(key);
		u.setEnabled(true);
		CCAPIAuthority a=new CCAPIAuthority();
		a.setFunction(f);		
		u.getCcapiAuthorities().add(a);
		PowerMockito.doReturn(u).when(userDao, "findByToken", any());
		
		boolean expectedException=false;
		//test
		try
		{
			Whitebox.invokeMethod(testedBean, "authentication",new Object[]{key,fTyp,companies});
			expectedException=false;		
		}
		catch(TokenValidationException e)
		{
			expectedException=true;
			Assert.assertTrue("Invalid companies permission denied".equals(e.getMessage()));			
		}
		
		Assert.assertEquals(true, expectedException);		
	}
	
	@Test
	public void testInvalidSellingCompanyWithCompanies() throws Exception
	{
		//prepare
		String key="xxxx";
		FunctionType fTyp=FunctionType.GET_BROCHURE;	
		List<String> companies=Lists.newArrayList("Code:A");
		
		Function f=new Function();
		f.setName(fTyp.getSimpleName());
		
		SellingCompany s=new SellingCompany();
		s.setName("Company:B");
		s.setCode("Code:B");
		
		UserCCAPI u=new UserCCAPI();
		u.setToken(key);
		u.setEnabled(true);
		CCAPIAuthority a=new CCAPIAuthority();
		a.setFunction(f);		
		a.setSellingCompany(s);
		u.getCcapiAuthorities().add(a);
		PowerMockito.doReturn(u).when(userDao, "findByToken", any());
		
		boolean expectedException=false;
		//test
		try
		{
			Whitebox.invokeMethod(testedBean, "authentication",new Object[]{key,fTyp,companies});
			expectedException=false;		
		}
		catch(TokenValidationException e)
		{
			expectedException=true;			
		}
		
		Assert.assertEquals(true, expectedException);		
	}
	
	@Test
	public void testAuthentication() throws Exception
	{
		//prepare
		String key="xxxx";
		FunctionType fTyp=FunctionType.UPLOAD_TOUR_INFO;	
		List<String> companies=Lists.newArrayList("Code:A");
		
		Function f=new Function();
		f.setName(fTyp.getSimpleName());
		
		SellingCompany s=new SellingCompany();
		s.setName("Company:A");
		s.setCode("Code:A");
		
		UserCCAPI u=new UserCCAPI();
		u.setToken(key);
		u.setEnabled(true);
		CCAPIAuthority a=new CCAPIAuthority();
		a.setFunction(f);		
		a.setSellingCompany(s);
		u.getCcapiAuthorities().add(a);
		PowerMockito.doReturn(u).when(userDao, "findByToken", any());
		
		//test				
		Whitebox.invokeMethod(testedBean, "authentication",new Object[]{key,fTyp,companies});
			
		Assert.assertNotNull(SecurityContextHolder.getContext().getAuthentication());
		
		Class cl=(SecurityContextHolder.getContext().getAuthentication()).getClass();
		Assert.assertEquals(true,UsernamePasswordAuthenticationToken.class.equals(cl));		
	}
}
