package com.ttc.ch2.bl.upload.common;

import org.apache.commons.lang.StringEscapeUtils;

import com.ttc.ch2.common.TypeMsg;

public class UploadMessage {

	private String message;	
	private TypeMsg typeMsg;	
	
	public UploadMessage(String message, TypeMsg typeMsg) {
		super();
//		this.message =  message;
		this.message =  StringEscapeUtils.escapeHtml(message);
		this.typeMsg = typeMsg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
//		this.message =  message;
		this.message = StringEscapeUtils.escapeHtml(message);;
	}

	public TypeMsg getTypeMsg() {
		return typeMsg;
	}

	public void setTypeMsg(TypeMsg typeMsg) {
		this.typeMsg = typeMsg;
	}
}
