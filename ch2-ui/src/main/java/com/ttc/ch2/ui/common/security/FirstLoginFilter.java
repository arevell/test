package com.ttc.ch2.ui.common.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.ui.common.config.Ch2URIs;

public class FirstLoginFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if((request.getRequestURI().contains(Ch2URIs.AJAX.getPath())==true || request.getRequestURI().contains(Ch2URIs.CHGPSWD.getPath())==true))
				{
					super.doFilter(request, response, filterChain);
					return;
				}
					
			if (SecurityHelper.isUserGui() && SecurityHelper.getUserGuiPrincipal().getUserDb().getFirstLog()) {
				RequestDispatcher dispatcher=request.getRequestDispatcher(Ch2URIs.CHGPSWD.getPath());
				dispatcher.forward(request, response);
			}		
			else{
				super.doFilter(request, response, filterChain);
			}

	}

}


