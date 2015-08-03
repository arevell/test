package com.ttc.ch2.common.exceptions;

public class CompaniesAuthenticationException extends RuntimeException {

	private static final long serialVersionUID = -8885990103647977695L;

	private String firstInvalidSellingCompany;

	public CompaniesAuthenticationException(String msg) {
		super(msg);
	}

	public CompaniesAuthenticationException(String msg, String firstInvalidSellingCompany) {
		super(msg);
		this.firstInvalidSellingCompany = firstInvalidSellingCompany;
	}

	public CompaniesAuthenticationException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public String getFirstInvalidSellingCompany() {
		return firstInvalidSellingCompany;
	}
}
