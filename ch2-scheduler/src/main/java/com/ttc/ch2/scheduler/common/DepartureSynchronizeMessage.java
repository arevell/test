package com.ttc.ch2.scheduler.common;

import java.text.MessageFormat;

public enum DepartureSynchronizeMessage {

	
	//errors
	UNEXPECTED_EXCEPTION("ERR-1000","Unexpected problem occured"),
	REGISTRED_PROCESS_EXCEPTION("ERR-1001","Tour Departure Import process has found an another process with status: Processing"),
	COMMON_EXCEPTION("ERR-1002","Process has error:{0}"),

	//WRN
	LOCK_BY_ANOTHER_PROCESS("WRN-2006","Brand is lock process can't import tours for brand:{0}"),
	
	//info
	CANCEL_PROCESS("INF-4000","Process was cancelled"),
	ADDITIONAL_INF("INF-4001","Additional information: {0}"),
	;

	
	private String code;
	private String message;
	
	private DepartureSynchronizeMessage(String code,String msg)
	{
		this.code=code;
		this.message=msg;
	}
		
	public static DepartureSynchronizeMessage getByCode(String code)
	{
		DepartureSynchronizeMessage tab[]=values();
		for (int i = 0; i < tab.length; i++) {
			if(tab[i].getCode().equals(code))
				return tab[i];
		}		
		throw new IllegalArgumentException("Enum 'TourInfoMessagesErrorCodes' don't exist for code:"+code);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public  String getMessage(Object ... params)
	{
		return MessageFormat.format(getMessage(), params);
	}
	
	

}
