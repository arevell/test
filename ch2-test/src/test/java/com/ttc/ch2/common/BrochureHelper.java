package com.ttc.ch2.common;

import javax.inject.Inject;

import org.powermock.reflect.Whitebox;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.brox.service.BrochureService;
import com.ttc.ch2.brox.service.BrochureServiceImpl;

@Component
public class BrochureHelper {

	@Inject
	private BrochureService brochureService;
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public String getCommonCompany(String tourCode,String brandCode) throws Exception{
		BrochureService localBrochureService=getTargetObject(brochureService, BrochureServiceImpl.class);
		String result=Whitebox.invokeMethod(localBrochureService, "getCommonSellingCompany",new Object[]{tourCode,brandCode});
		return result;
	}
	
	
	
	@SuppressWarnings({"unchecked"})
	protected <T> T getTargetObject(Object proxy, Class<T> targetClass) throws Exception {
	  if (AopUtils.isJdkDynamicProxy(proxy)) {
	    return (T) ((Advised)proxy).getTargetSource().getTarget();
	  } else {
	    return (T) proxy; // expected to be cglib proxy then, which is simply a specialized class
	  }
	}	
	
}
