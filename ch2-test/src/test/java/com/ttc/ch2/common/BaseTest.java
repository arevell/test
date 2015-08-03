package com.ttc.ch2.common;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(locations={"classpath:/META-INF/spring/testCtx.xml","classpath:/META-INF/spring/daoCtx.xml"})
public class BaseTest  {


	
	@SuppressWarnings({"unchecked"})
	protected <T> T getTargetObject(Object proxy, Class<T> targetClass) throws Exception {
	  if (AopUtils.isJdkDynamicProxy(proxy)) {
	    return (T) ((Advised)proxy).getTargetSource().getTarget();
	  } else {
	    return (T) proxy; // expected to be cglib proxy then, which is simply a specialized class
	  }
	}	
	
	

	
}
