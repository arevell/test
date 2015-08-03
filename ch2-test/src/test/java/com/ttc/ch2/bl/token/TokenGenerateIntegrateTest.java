package com.ttc.ch2.bl.token;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TokenGenerateIntegrateTest extends BaseTest{
		
	@Inject
	private TokenService tokenService;

	@Test
	@Transactional
	public void generateTokenTest() throws TokenServiceException
	{
		Assert.assertNotNull(tokenService.getGenerateToken());
	}
}


