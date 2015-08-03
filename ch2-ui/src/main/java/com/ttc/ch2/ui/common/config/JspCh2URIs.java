package com.ttc.ch2.ui.common.config;

public enum JspCh2URIs {
	
	ERROR("error","/base/error.zul"),
	LOGIN("login","/login.htm"),	
	LOGOUT("logout","/modules/auth/j_spring_security_logout"),	
	ACCESS_DENIED("access_denied","/base/access_denied.htm"),
	SESSION_INVALIDATE("invalid_session","/invalid_session.htm"),	
	PERMISSION("permision","/permision.htm"),
	SOAP_ERROR("SoapError","/soapError.htm"),
	REST_ERROR("RestError","/restError.htm");
	
	private String name = null;
	private String path = null;
	
	private JspCh2URIs(String name,String path) {
		this.name=name;
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	public String getPathWithAppName(String appName) {
		return appName+path;
	}

	public String getName() {
		return name;
	}
}
