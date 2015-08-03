package com.ttc.ch2.bl.departure.common;

import java.util.Date;

import org.slf4j.Logger;

public class LogOperationHelper {
	
	public static TDMessage logDefaultException(Logger logger,OperationStatus opStatus,Throwable e,TropicSynchronizeMessages msg,Object ... params)
	{
		logger.error(TropicSynchronizeMessages.getMessage(msg,params),e);
		return opStatus.addException(null,null,null,e,null,null,msg,params);		
	}
	
	public static TDMessage logDefaultException(Logger logger,OperationStatus opStatus,Date time,Throwable e,TropicSynchronizeMessages msg,Object ... params)
	{
		logger.error(TropicSynchronizeMessages.getMessage(msg,params),e);
		return opStatus.addException(null,null,null,e,time,null,msg,params);		
	}

	public static TDMessage logExceptionForBrand(Logger logger, OperationStatus opStatus, Throwable e, TropicSynchronizeMessages msg, Object... params) {
		logger.error(TropicSynchronizeMessages.getMessage(msg, params), e);
		return opStatus.addException(opStatus.getCurrentBrand(), null, null, e, null, null, msg, params);
	}

	@Deprecated
	public static TDMessage logExceptionForSellingCompnies(Logger logger,String sellingCompanie,OperationStatus opStatus,Throwable e,TropicSynchronizeMessages msg,Object ... params)
	{
		logger.error(TropicSynchronizeMessages.getMessage(msg,params),e);
		return opStatus.addException(opStatus.getCurrentBrand(),sellingCompanie,null, e,null,null,  msg, params);		
	}

	public static TDMessage logExceptionForProduct(Logger logger,String productCode,OperationStatus opStatus,Throwable e,TropicSynchronizeMessages msg,Object ... params)
	{
		logger.error(TropicSynchronizeMessages.getMessage(msg,params),e);
		return opStatus.addException(opStatus.getCurrentBrand(),null,productCode, e,null,null, msg, params);		
	}
		
	public static TDMessage logDefaultMessage(Logger logger,OperationStatus opStatus,TropicSynchronizeMessages msg,Object ... params)
	{
		logInfo(logger, msg, TropicSynchronizeMessages.getMessage(msg,params));
		return opStatus.addMessage(null,null,null,msg,params);		
	}
	
	public static TDMessage logMessageForBrand(Logger logger,OperationStatus opStatus,TropicSynchronizeMessages msg,Object ... params)
	{
		logInfo(logger, msg, TropicSynchronizeMessages.getMessage(msg,params));
		return opStatus.addMessage(opStatus.getCurrentBrand(),null,null,new Date(),null,msg,params);		
	}
	
	public static TDMessage logMessageForSellingCompany(Logger logger,String sellingCompany,OperationStatus opStatus,TropicSynchronizeMessages msg,Object ... params)
	{
		logInfo(logger, msg, TropicSynchronizeMessages.getMessage(msg,params));
		return opStatus.addMessage(sellingCompany,null,msg,params);		
	}

	public static TDMessage logMessageForProduct(Logger logger,OperationStatus opStatus,String productCode,TropicSynchronizeMessages msg,Object ... params)
	{			
		logInfo(logger, msg, TropicSynchronizeMessages.getMessage(msg,params));		
		return opStatus.addMessage(opStatus.getCurrentBrand(),null,productCode,msg,params);		
	}
	
	private static void logInfo(Logger logger,TropicSynchronizeMessages msgE,String txt){
		switch (msgE) {
		case SAVE_CR_FOR_TOUR_CODE:
		case TD_CHECK_SUM_EXIST_IN_CR:
		case UPDATE_CR_FOR_TOUR_CODE:
		case OPERATION_FOR_PRODUCT_START:
		case OPERATION_FOR_PRODUCT_END:
				logger.trace(txt);	
			break;			
		default:
				logger.info(txt);
			break;
		}
	}

}
