package com.ttc.ch2.api.ccapi;

import org.springframework.ws.soap.security.WsSecurityValidationException;

@SuppressWarnings("serial")
public class TokenValidationException extends WsSecurityValidationException {

	public TokenValidationException(String msg) {
		super(msg);
	}

	public TokenValidationException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
