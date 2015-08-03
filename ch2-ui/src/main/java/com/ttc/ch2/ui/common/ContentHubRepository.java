package com.ttc.ch2.ui.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ttc.ch2.ui.common.config.Ch2URIs;

/**
 * Servlet Filter implementation class ContentHubRepository
 */
public class ContentHubRepository implements Filter {

    /**
     * Default constructor. 
     */
    public ContentHubRepository() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

				
//		HttpServletRequest req = (HttpServletRequest) request;
//	    if (SecurityContextHolder.getContext().getAuthentication() == null) {
//	        // in here, get your principal, and populate the auth object with 
//	        // the right authorities
//	        Authentication auth = doAuthentication(req); 
//	        SecurityContextHolder.getContext().setAuthentication(auth);
//	    }
		
//		RequestDispatcher dispatcher=request.getRequestDispatcher(Ch2URIs.DATA_VIEW_EXTERNAL_CLIENT.getPath());
//		dispatcher.forward(request, response);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
