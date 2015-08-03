package com.ttc.ch2.ui.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.GenericFilterBean;

import com.ttc.ch2.ui.common.security.PathType;


/**
 * This filter is user for pseudo rest request to invalid session create default by DHtmlLayoutServlet in zkoss framework
 * */
public class InvalidSessionFromZkoss extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {		
		try{
			chain.doFilter(request, response);
		}
		finally{							
			invalidSessionIfNessecery(request);
		}
	}
	
	
	/**Invalid session in zkoss request under rest mode */
	private void invalidSessionIfNessecery(ServletRequest request){
				
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest=(HttpServletRequest) request;	
			String requestUri=httpRequest.getRequestURI();
			
			HttpSession session=httpRequest.getSession(false);
			if(session!=null){
				session.invalidate();
			}
			
			
//			PathType type=PathType.getPathType(httpRequest);
//			if(PathType.O_ARCHIVES==type || PathType.TOUR_DEPARTURE==type || PathType.TOUR_INFO==type){
//				if((requestUri.endsWith("zip") || requestUri.endsWith("xml") || requestUri.endsWith("pdf"))==false){				
//				HttpSession session=httpRequest.getSession(false);
//				if(session!=null){
//					session.invalidate();
//				}	
//			  }
//			}
		}
		
	}
}
