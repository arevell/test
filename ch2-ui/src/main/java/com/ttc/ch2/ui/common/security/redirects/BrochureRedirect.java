package com.ttc.ch2.ui.common.security.redirects;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.ttc.ch2.brox.service.BrochureServiceException;
import com.ttc.ch2.brox.service.BrochureServiceImpl;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.ui.common.config.Ch2URIs;

@Component
@Scope("prototype")
public class BrochureRedirect extends BaseRedirectOperation
{
	@Value("${app.name}")
	private String appName;
	
	@Inject
	private SellingCompanyDAO sellingCompanyDAO;
	
	@Override
	public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
//		http://localhost:8080/ch2-ui/brochure_engine/Brochure?brandCode=CH&sellingCompanyCode=CHUKLS&tours=Tour1		
		String sellingCompanyCode= StringUtils.defaultIfEmpty(request.getParameter("sellingCompanyCode"),"");
		if (SecurityHelper.isUserCcapi()) {			
			if (AuthorityHelper.hasAuthorityUserCcapi(FunctionType.EBROCHURE_V1, sellingCompanyCode) == false) {
				sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied for this function:"+
			     FunctionType.EBROCHURE_V1.getSimpleName() +" and for resource:"+sellingCompanyCode);
				return;
			}
		}
		else {
			sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied for this function:"+ 
					FunctionType.EBROCHURE_V1.getSimpleName()+" and for resource:"+sellingCompanyCode);
			return;
		}
		Set<String> missingParams=validateParams( request);
		if(missingParams.size()>0){
		sendError(response, HttpServletResponse.SC_NOT_FOUND, String.format(BrochureServiceImpl.ERROR_NO_REQUESTED_PARAMETER_DEFINED,Joiner.on(",").join(missingParams)));
		return;
		}
		
		if(StringUtils.isNotEmpty(request.getParameter("brandCode")) && StringUtils.isNotEmpty(request.getParameter("sellingCompanyCode"))){
			SellingCompany sc=sellingCompanyDAO.findBySellingCompanyCode(request.getParameter("sellingCompanyCode"));
			if(sc==null){
				sendError(response, HttpServletResponse.SC_NOT_FOUND, String.format(BrochureServiceImpl.ERROR_INCORRECT_COMPANY,request.getParameter("sellingCompanyCode")));
				return;
			}
			else{
				if(sc.getBrand().getCode().equals(request.getParameter("brandCode"))==false){
					sendError(response, HttpServletResponse.SC_NOT_FOUND, String.format(BrochureServiceImpl.ERROR_INCORRECT_BRAND,request.getParameter("brandCode"),request.getParameter("sellingCompanyCode")));
					return;
				}
			}
		}
		
		
		
		fileRequestOperation(request, response);									
	}
		
	private void fileRequestOperation(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {							
		String uri="";
		if(StringUtils.isNotEmpty(appName))
			  uri=request.getRequestURI().substring(("/"+appName).length());
		else{
				uri=request.getRequestURI();
			}
		
		
		StringBuilder extraParam=new StringBuilder();
		if(StringUtils.isEmpty(request.getParameter("title")))
			extraParam.append("&title=");
		if(StringUtils.isEmpty(request.getParameter("agent_text")))
			extraParam.append("&agent_text=");
		if(StringUtils.isEmpty(request.getParameter("agent_image")))
			extraParam.append("&agent_image=");		
		RequestDispatcher dispatcher=request.getRequestDispatcher(uri+"?"+extraParam.toString());
		dispatcher.forward(request, response);
	}
	
	private Set<String> validateParams(HttpServletRequest request) {
		
		
		Set<String> missingParams=Sets.newHashSet();
		if (StringUtils.isEmpty(request.getParameter("brandCode"))) {
			missingParams.add("brandCode");
		}				
		if (StringUtils.isEmpty(request.getParameter("sellingCompanyCode"))) {
			missingParams.add("sellingCompanyCode");
		}		
		if (StringUtils.isEmpty(request.getParameter("tours"))) {
			missingParams.add("tours");
		}
		
		return missingParams;
	}
}