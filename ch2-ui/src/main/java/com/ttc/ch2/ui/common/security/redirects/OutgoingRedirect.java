package com.ttc.ch2.ui.common.security.redirects;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.security.PathType;
import com.ttc.ch2.ui.moduls.tour.common.Format;

@Component
@Scope("prototype")
public class OutgoingRedirect extends BaseRedirectOperation
{
	
	private static Pattern LIST_PATTERN = Pattern.compile("(^/[V(1|3)]{2}/$)|(^/$)");
	
	@Value("${app.name}")
	private String appName;
	
	
	@Override
	public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		
		PathType type=PathType.getPathType(request);			
		String lastPath= getLastPathOfRequest(request);
		if(lastPath.indexOf(".zip")!=-1){
			String sellingCompanyCode= lastPath.substring(0,lastPath.indexOf(".zip"));
			FunctionType fType=getFunctionTypeFromRequest(request);
			if (SecurityHelper.isUserCcapi()) {			
				if (AuthorityHelper.hasAuthorityUserCcapi(fType, sellingCompanyCode) == false) {
					sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied for this function:"	+ fType.getSimpleName()+" and for resource:"+sellingCompanyCode);
					return;
				}
			}
			else {
				sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied for this function:"+ fType.getSimpleName() 
						+" and for resource:"+sellingCompanyCode);
				return;
			}
			
			fileRequestOperation(request, response);
		}
		else {
			if(validatePath(request, type)==false){
				  sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied invalid path or parameter 'format' not exist:"+request.getRequestURI());
				  return;
			}
						
			FunctionType fType=getFunctionTypeFromRequest(request);
			if (SecurityHelper.isUserCcapi()) {			
				if (AuthorityHelper.checkUserHasFunctionPermition(SecurityHelper.getUserCCAPIPrincipal().getUserDb(),fType) == false) {
					sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied for this function:"+
							fType.getSimpleName());
					return;
				}
			}
			else{
				sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied for this function:"+ fType.getSimpleName());						
				return;
			}
			
			// html or xml list
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
				dispatcher.forward(request, response);
				return;			
			}
			
			
//			// zul list
//			
//			if(fType==FunctionType.OA_VIEW_V1){
//				RequestDispatcher dispatcher=request.getRequestDispatcher(Ch2URIs.FILE_COLLECT_OLDEXT.getPath());
//				dispatcher.forward(request, response);	
//			}
//			else if(fType==FunctionType.OA_VIEW_V3){
//				RequestDispatcher dispatcher=request.getRequestDispatcher(Ch2URIs.FILE_COLLECT_NEWEXT.getPath());
//				dispatcher.forward(request, response);
//			}
//			else {
//				sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Access denied for this function:"+fType.getSimpleName());
//			}
		}
	}
		
	private void fileRequestOperation(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String uri="";
		if(StringUtils.isNotEmpty(appName))
		  uri=request.getRequestURI().substring(("/"+appName).length());
		else{
			uri=request.getRequestURI();
		}
//		String uri=request.getRequestURI().substring(("/"+appName).length());
		RequestDispatcher dispatcher=request.getRequestDispatcher(uri+"?"+request.getQueryString());
		dispatcher.forward(request, response);
	}
	
	private FunctionType getFunctionTypeFromRequest(HttpServletRequest request)
	{		
		FunctionType result=null;		
		String uri=request.getRequestURI();
		result= uri.toUpperCase().contains("/V3/") ? FunctionType.OA_VIEW_V3:FunctionType.OA_VIEW_V1;			
		return result;
	}
	
private boolean validatePath(HttpServletRequest request,PathType type){
		
		boolean result=true;
		String uri=request.getRequestURI();
		uri = StringUtils.substringBefore(uri, ";jsessionid");
		String pathToCheck="";
		if(type==PathType.O_ARCHIVES ){
			 pathToCheck=uri.substring(uri.toLowerCase().indexOf(PathType.O_ARCHIVES.getPartPath())+PathType.O_ARCHIVES.getPartPath().length());					 
		}
		
		result&=LIST_PATTERN.matcher(pathToCheck).find();
		
		 	result&=StringUtils.isNotEmpty(request.getParameter("format"));
		 
			try {
				Format.getFormatByParamName(request.getParameter("format"));
			} catch (Exception e) {
				result &= false;
			}
		
		 return result;	
	}
}