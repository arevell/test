package com.ttc.ch2.bl.upload.common;

import org.slf4j.Logger;

public class LogOperationHelper {

	
	
	public static void logException(Logger logger,OperationStatus opStatus,Throwable e,TourInfoMessages msg,Object ... params)
	{
		logger.error(TourInfoMessages.getMessage(msg,params),e);
		opStatus.addMessage(null,e,null,msg,params);		
	}
	
	public static void logExceptionForFile(Logger logger,OperationStatus opStatus,String productCode,Throwable e,String content,TourInfoMessages msg,Object ... params)
	{
		logger.error(TourInfoMessages.getMessage(msg,params),e);
		opStatus.addMessage(productCode,e,content,msg,params);
	}
		
	public static void logMessage(Logger logger,OperationStatus opStatus,TourInfoMessages msg,Object ... params)
	{		
		switch (msg) {
		case VALIDATION_FILE:
		case OPERATION_ON_FILE:
		case MAPPING_FILE:
		case CREATE_OLD_TOUR_INFO:
		case INSERT_TOUR_INFO:
		case UPDATE_TOUR_INFO:
				logger.trace(TourInfoMessages.getMessage(msg,params));	
			break;
			
		default:
				logger.info(TourInfoMessages.getMessage(msg,params));
			break;
		}
		
				
		opStatus.addMessage(null,null,null,msg,params);
	}
	
	public static void logMessageForFile(Logger logger,OperationStatus opStatus,String productCode,TourInfoMessages msg,Object ... params)
	{
		logger.info(TourInfoMessages.getMessage(msg,params));		
		opStatus.addMessage(productCode,null,null,msg,params);
	}
}
