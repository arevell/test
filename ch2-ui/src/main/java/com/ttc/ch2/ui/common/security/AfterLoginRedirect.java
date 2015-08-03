package com.ttc.ch2.ui.common.security;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.collect.Maps;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.redirects.AfterLoginRedirectOperation;
import com.ttc.ch2.ui.common.security.redirects.BrochureRedirect;
import com.ttc.ch2.ui.common.security.redirects.CRRedirect;
import com.ttc.ch2.ui.common.security.redirects.OutgoingRedirect;
import com.ttc.ch2.ui.mvc.ExceptionXmlConverter;
import com.ttc.common.utils.HttpResponseHelper;
import com.ttc.util.messages.Severity;

public class AfterLoginRedirect extends OncePerRequestFilter{
			
	@Inject
	private ApplicationContext ctx;
	
	private Map<PathType,AfterLoginRedirectOperation> redirects=null;
	

	
	@PostConstruct
	public void init()
	{
		redirects=Maps.newHashMap();
		redirects.put(PathType.TOUR_DEPARTURE, ctx.getBean(CRRedirect.class));
		redirects.put(PathType.TOUR_INFO, ctx.getBean(CRRedirect.class));
		redirects.put(PathType.O_ARCHIVES, ctx.getBean(OutgoingRedirect.class));
		redirects.put(PathType.BROUCHURE, ctx.getBean(BrochureRedirect.class));
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		PathType type=PathType.getPathType(request);		
		AfterLoginRedirectOperation operation=redirects.get(type);
		if(operation==null){
			sendError(response, HttpServletResponse.SC_NOT_FOUND, "Source not found request without(tour_info,tour_departure,outgoing archive)");
			return;
		}
		operation.redirect(request, response);
	}
	
	protected void sendError(HttpServletResponse response,int code,String msg) throws ServletException{
		
		try{
		String content=new ExceptionXmlConverter().convertToXmlByJaxb(code,msg,Severity.ERROR);
		response.setStatus(code);
		HttpResponseHelper.writeOutput(HttpServletResponse.SC_UNAUTHORIZED,response, content,"text/xml");
		}
		catch(Exception e){
			throw new ServletException(e);
		}
	}
	
	

	
}
