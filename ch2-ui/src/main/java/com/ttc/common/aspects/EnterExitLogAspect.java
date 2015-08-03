package com.ttc.common.aspects;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class EnterExitLogAspect {

	private static final Logger logger=LoggerFactory.getLogger(EnterExitLogAspect.class);
			
	@Around("execution(public * com.ttc.ch2.ui.moduls.user.OperatorWiz.init(..))")
	public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
		String message = getMessage(joinPoint);		
		Logger logger=getLogger(joinPoint); 
        logInformation(logger,message +"start At: "+ new Date()+"[Piotr_U-start]");
        Object returnValue = null;
        try{
        	returnValue = joinPoint.proceed();
        }finally{        
        	logInformation(logger,message +"end At: "+ new Date()+"[Piotr_U-end]");
        }
        return returnValue;
	}

	private String getMessage(ProceedingJoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		String interceptedMethod = signature.getName();		
		String className=joinPoint.getTarget().getClass().getSimpleName();
		StringBuilder sb=new StringBuilder(className);
		sb.append(":").append(interceptedMethod).append("-");		
		return sb.toString();
	}
	
	private void logInformation(Logger logger,String msg)
	{
		logger.info(msg);
		EnterExitLogAspect.logger.info(msg);
	}
	
	private Logger getLogger(ProceedingJoinPoint joinPoint)
	{					
		Object o=joinPoint.getTarget();
		if (o != null) {
			return LoggerFactory.getLogger(o.getClass());
		}
		return logger;		
	}
}

