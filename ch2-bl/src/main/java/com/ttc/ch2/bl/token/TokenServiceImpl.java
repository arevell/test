package com.ttc.ch2.bl.token;

import javax.inject.Inject;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.user.UserCCAPI;


@Service
public class TokenServiceImpl implements TokenService{
	
	@Inject
	private UserCCAPIDAO userCCAPIDAO;
		
	@Transactional(readOnly=false, noRollbackFor=UniqueTokenServiceException.class,propagation=Propagation.REQUIRED)
	public  String getGenerateToken() throws TokenServiceException{		
		try
		{
			String token=generateString();
			String encodedToken=new String(Base64.encode(token.getBytes()));
			
			UserCCAPI findedToken= userCCAPIDAO.findByToken(encodedToken);
			if(findedToken!=null)
			{
				throw new UniqueTokenServiceException("Token exist in system");
			}			
			return encodedToken;
		}
		catch(ConstraintViolationException e)
		{
				throw new UniqueTokenServiceException("Token exist in system",e);
		}
		catch (UniqueTokenServiceException e) {
			throw e;
		}
		catch(Exception e)
		{
			throw new TokenServiceException(e);
		}		
	}
	
	private String generateString()
	{
		String result=RandomStringUtils.random(27, true, true);
		return result;
	}
}
