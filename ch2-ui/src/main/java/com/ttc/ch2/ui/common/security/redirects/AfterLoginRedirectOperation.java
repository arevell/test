package com.ttc.ch2.ui.common.security.redirects;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AfterLoginRedirectOperation 
{
	public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}