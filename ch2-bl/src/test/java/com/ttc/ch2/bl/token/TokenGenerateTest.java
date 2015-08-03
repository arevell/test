package com.ttc.ch2.bl.token;

import static org.mockito.Matchers.anyString;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.user.UserCCAPI;

@RunWith(PowerMockRunner.class)   
@PrepareForTest(TokenServiceImpl.class)
public class TokenGenerateTest {
		

	@Test
	public void generateTokenExceptionTest() throws Exception
	{				
		UserCCAPIDAO dao = PowerMockito.mock(UserCCAPIDAO.class);	    
	    TokenService tokenServiceMock = PowerMockito.mock(TokenServiceImpl.class);	    
	    Whitebox.setInternalState(tokenServiceMock, "userCCAPIDAO",dao);	         
	    PowerMockito.when(dao.findByToken(anyString())).thenReturn(new UserCCAPI());	
		PowerMockito.when(tokenServiceMock, "generateString").thenCallRealMethod();
		PowerMockito.when(tokenServiceMock.getGenerateToken()).thenCallRealMethod();
		boolean exceptionExist=true;		
		try
		{
			Assert.assertNotNull(tokenServiceMock.getGenerateToken());			
			exceptionExist=false;
		}
		catch(UniqueTokenServiceException e)
		{
			exceptionExist=true;
		}
		Assert.assertEquals("TokenServiceException was handled",true, exceptionExist);
	}
}


