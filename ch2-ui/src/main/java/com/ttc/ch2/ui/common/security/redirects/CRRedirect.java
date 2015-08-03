package com.ttc.ch2.ui.common.security.redirects;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.ttc.ch2.bl.security.SecurityService;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.common.predicates.FindBrandCodeInSellingCompanyByCodePredicate;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.ui.common.security.PathType;
import com.ttc.ch2.ui.common.validators.TourValidator;
import com.ttc.ch2.ui.moduls.tour.common.Format;

@Component
@Scope("prototype")
public class CRRedirect extends BaseRedirectOperation
{

	private static Pattern XML_PATTERN = Pattern.compile("(^/[A-Z]{2}/V[13]/.*xml$)|(^/[A-Z]{2}/.*xml$)");
	private static Pattern LIST_PATTERN = Pattern.compile("(^/[A-Z]{2}/list.htm$)|(^/[A-Z]{2}/V[13]/list.htm$)|(^/[A-Z]{2}/$)|(^/[A-Z]{2}/V[13]/$)");

	
	@Inject
	private SecurityService service;
	
	@Value("${app.name}")
	private String appName;
	
	
	@Override
	public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PathType type=PathType.getPathType(request);		
		if(validatePath(request, type)==false){
			sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied invalid path or parameter 'format' not exist:"+request.getRequestURL()+request.getQueryString());
		  return;
		}
				
		boolean hasPermissionToViewVersion1=com.ttc.ch2.common.AuthorityHelper.checkUserHasFunctionPermition(com.ttc.ch2.common.SecurityHelper.getUserCCAPIPrincipal().getUserDb(), com.ttc.ch2.common.FunctionType.CR_VIEW_V1);
		boolean hasPermissionToViewVersion3=com.ttc.ch2.common.AuthorityHelper.checkUserHasFunctionPermition(com.ttc.ch2.common.SecurityHelper.getUserCCAPIPrincipal().getUserDb(), com.ttc.ch2.common.FunctionType.CR_VIEW_V3);
		if(hasPermissionToViewVersion1==false && hasPermissionToViewVersion3==false){					
				sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied to functionality:"+type);
			  return;
		}
		
		FunctionType function=getFunctionTypeFromRequest(request,type);
		String brandCode=getBrandCode(request, type,function);
		
		UserCCAPI user=SecurityHelper.getUserCCAPIPrincipal().getUserDb();
		Collection<SellingCompany> companies=AuthorityHelper.transformAuthorityforUserCcapi(user).get(function);				
		if(StringUtils.isBlank(brandCode) || (companies==null || companies.size()==0 || Iterables.filter(companies, new FindBrandCodeInSellingCompanyByCodePredicate(brandCode)).iterator().hasNext()==false))
		{
				sendError(response,HttpServletResponse.SC_UNAUTHORIZED, "Access denied invalid brand code for function:"+function);
			  return; 
		}
		
		
		if(request.getRequestURI().toLowerCase().endsWith(".xml")){
			fileRequestOperation(brandCode,type, request, response);
			return;
		}
		
		
		// html list
		if((StringUtils.isNotEmpty(request.getParameter("format")))|| request.getRequestURI().contains("list.htm")){						
			String uri="";
			if(StringUtils.isNotEmpty(appName))
				  uri=request.getRequestURI().substring(("/"+appName).length());
			else{
					uri=request.getRequestURI();
				}
			
			if(uri.contains("list.htm")==false){
				uri+="list.htm";
			}
			RequestDispatcher dispatcher=request.getRequestDispatcher(uri);
//			RequestDispatcher dispatcher=request.getRequestDispatcher(uri+"?"+request.getQueryString());
			dispatcher.forward(request, response);
			return;			
		}
		
		
//		// zul list
//		String param=ParamsUtils.getInstance().addParam("brandCode", brandCode).addParam("function",function.toString()).encodeAllParams();		
//		if(PathType.TOUR_INFO==type){
//			
//			if(function==FunctionType.CR_VIEW_V1)
//			{
//			RequestDispatcher dispatcher=request.getRequestDispatcher(Ch2URIs.TOUR_INFO_REPOSITORY_OLDEXT.getPath()+"?param="+param);
//			dispatcher.forward(request, response);
//			}
//			if(function==FunctionType.CR_VIEW_V3)
//			{
//				RequestDispatcher dispatcher=request.getRequestDispatcher(Ch2URIs.TOUR_INFO_REPOSITORY_NEWEXT.getPath()+"?param="+param);
//				dispatcher.forward(request, response);
//			}
//		}
//		else if(PathType.TOUR_DEPARTURE==type){
//			
//			if(function==FunctionType.CR_VIEW_V1)
//			{
//			RequestDispatcher dispatcher=request.getRequestDispatcher(Ch2URIs.TOUR_DEPARTURE_REPOSITORY_OLDEXT.getPath()+"?param="+param);
//			dispatcher.forward(request, response);
//			}
//			else if(function==FunctionType.CR_VIEW_V3)
//			{
//			RequestDispatcher dispatcher=request.getRequestDispatcher(Ch2URIs.TOUR_DEPARTURE_REPOSITORY_NEWEXT.getPath()+"?param="+param);
//			dispatcher.forward(request, response);
//			}
//		}
		
	}
	
	
	private String getBrandCode(HttpServletRequest request,PathType type, FunctionType functionType)
	{
		String result=null;
		String uri=request.getRequestURI();
		 if(type==PathType.TOUR_DEPARTURE ){
			 String partPath=uri.substring(uri.toLowerCase().indexOf(PathType.TOUR_DEPARTURE.getPartPath()));
			 Iterator<String> itr=Splitter.on("/").split(partPath).iterator();
			 if(itr.hasNext())
			 {
				itr.next();
				String brandCode=itr.next();
				if(service.verifiPermisionToBrand(SecurityHelper.getUserCCAPIPrincipal().getUserDb(), brandCode,functionType))
					result=brandCode;
			 }
		 }	
		 else if(type==PathType.TOUR_INFO ){
			 String partPath=uri.substring(uri.toLowerCase().indexOf(PathType.TOUR_INFO.getPartPath()));
			 Iterator<String> itr=Splitter.on("/").split(partPath).iterator();
			 if(itr.hasNext())
			 {
				itr.next();
				String brandCode=itr.next();
				if(service.verifiPermisionToBrand(SecurityHelper.getUserCCAPIPrincipal().getUserDb(), brandCode,functionType))
				result=brandCode;
			 }
		 }
		 return result;
	}
	
	private void fileRequestOperation(String brandCode,PathType type,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		if (type != PathType.OTHER) {		
			String fileName= getLastPathOfRequest(request);
			String tourCode= fileName.substring(0,fileName.indexOf(".xml"));
			TourValidator.validate(tourCode);
			FunctionType functionType=getFunctionTypeFromRequest(request,type);
			if(service.verifyPermissionsToFile(SecurityHelper.getUserCCAPIPrincipal().getUserDb(), tourCode,brandCode,functionType))
	    	{					
				String uri="";
				if(StringUtils.isNotEmpty(appName))
					  uri=request.getRequestURI().substring(("/"+appName).length());
				else{
						uri=request.getRequestURI();
					}
				RequestDispatcher dispatcher=request.getRequestDispatcher(uri+"?"+request.getQueryString());
				dispatcher.forward(request, response);
				
	    	}else{
	    		sendError(response,HttpServletResponse.SC_UNAUTHORIZED, "Access denied for file:"+fileName);
	    	}
    	}
		else{
			sendError(response,HttpServletResponse.SC_NOT_FOUND, "Source not found request without(tour_info,tour_departure)");
		}
	}

	private FunctionType getFunctionTypeFromRequest(HttpServletRequest request,PathType type)
	{		
		FunctionType result=null;		
		String uri=request.getRequestURI();
		 if(type==PathType.TOUR_DEPARTURE || type==PathType.TOUR_INFO){
			result= uri.toUpperCase().contains("/V3/") ? FunctionType.CR_VIEW_V3:FunctionType.CR_VIEW_V1;
		}		
		return result;
	}
	
	private boolean validatePath(HttpServletRequest request,PathType type){
		
		boolean result=true;
		String uri=request.getRequestURI();
		uri = StringUtils.substringBefore(uri, ";jsessionid");
		String pathToCheck="";
		 if(type==PathType.TOUR_DEPARTURE ){
			 pathToCheck=uri.substring(uri.toLowerCase().indexOf(PathType.TOUR_DEPARTURE.getPartPath())+PathType.TOUR_DEPARTURE.getPartPath().length());					 
		 }
		 else if(type==PathType.TOUR_INFO ){
			 pathToCheck=uri.substring(uri.toLowerCase().indexOf(PathType.TOUR_INFO.getPartPath())+PathType.TOUR_INFO.getPartPath().length());
		 }
		 		 
		 if(uri.toLowerCase().endsWith(".xml"))
			 result&=XML_PATTERN.matcher(pathToCheck).find();
		 else{
			 result&=LIST_PATTERN.matcher(pathToCheck).find();		 
			 result&=StringUtils.isNotEmpty(request.getParameter("format"));			 
			try {
				Format.getFormatByParamName(request.getParameter("format"));
			} catch (Exception e) {
				result &= false;
			}
		 }
		 		 
		 return result;	
	}
	
	
//	public static void main(String[] args) {
//		String txt="";
//		String patern="(^/[A-Z]{2}/list.htm$)|(^/[A-Z]{2}/V[13]/list.htm$)|(^/[A-Z]{2}/$)|(^/[A-Z]{2}/V[13]/$)";
//		
//		 txt="/CH/V1/list.htm";
//		Assert.assertTrue(txt.matches(patern));
//		txt="/CH/V3/list.htm";
//		Assert.assertTrue(txt.matches(patern));
//		txt="/CH/V4/list.htm";
//		Assert.assertFalse(txt.matches(patern));
//		 txt="/CH/list.htm";
//		Assert.assertTrue(txt.matches(patern));
//		
//		txt="/CH/";
//		Assert.assertTrue(txt.matches(patern));
//		txt="/CH/V3/";
//		Assert.assertTrue(txt.matches(patern));
//		
//		
//		
////		String patern="(^/[A-Z]{2}/V[13]/list.htm$)";
////		String patern="(^/[A-Z]{2}/[V(1|3)]{2}/.*htm$)";
////		String patern="((^/[A-Z]{2}/V[13]{2}/$)|(^/[A-Z]{2}/$))|((^/[A-Z]{2}/V[13]{2}/*.htm$)|(^/[A-Z]{2}/*.htm$))";
//		
//		boolean result=txt.matches(patern);
//		
//		System.out.println("end");
//		
//	}
	
	
}
