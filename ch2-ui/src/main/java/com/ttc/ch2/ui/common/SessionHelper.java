package com.ttc.ch2.ui.common;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Executions;

import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.exceptions.PermissionException;
import com.ttc.ch2.ui.common.exceptions.SessionIncorrectException;
import com.ttc.ch2.ui.common.security.UserContext;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;

public class SessionHelper {

	
	public static HttpSession getSession()
	{
		HttpServletRequest request=(HttpServletRequest) Executions.getCurrent().getNativeRequest();
		return getSession(request);
	}
	public static HttpSession getSession(HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		if(session==null)
			throw new SessionIncorrectException("Session don't exist");
		return session;
	}
	
	public static Object getAttributeFromUserContext(UserContextStaticName key)
	{
		return getAttributeFromUserContext(key.toString());
	}
	
	public static Object getAttributeFromUserContext(String key)
	{
		UserContext usrCtx=(UserContext)getSession().getAttribute(UserContext.class.getName());
		if(usrCtx==null)
		{
			throw new SessionIncorrectException("UserContext is not initialize - please login to system again"); 
		}
		return usrCtx.getObject(key);
	}
	
	public static Object getAttributeFromUserContext(String key,Class<? extends User> userClazz)
	{		
		Object u=getAttributeFromUserContext(key);
		if(u!=null)
		{
			if(u.getClass().equals(userClazz)==false)
			throw new PermissionException("Attempt use the not appropriate user");
		}		
		return u;
	}
	
	public static Object getAttributeFromUserContext(HttpServletRequest request,String key)
	{		
		UserContext usrCtx=(UserContext)getSession(request).getAttribute(UserContext.class.getName());
		if(usrCtx==null)
		{
			throw new SessionIncorrectException("UserContext is not initialize - please login to system again"); 
		}
		return usrCtx.getObject(key);
	}
	public static void putAttributeToUserContext(String key,Object value)
	{
		UserContext usrCtx=(UserContext)getSession().getAttribute(UserContext.class.getName());
		if(usrCtx==null)
		{
			throw new SessionIncorrectException("UserContext is not initialize - please login to system again"); 
		}
		usrCtx.putObj(key, value);
	}
	public static void putAttributeToUserContext(HttpServletRequest request,String key,Object value)
	{
		UserContext usrCtx=(UserContext)getSession(request).getAttribute(UserContext.class.getName());
		if(usrCtx==null)
		{
			throw new SessionIncorrectException("UserContext is not initialize - please login to system again"); 
		}
		usrCtx.putObj(key, value);
	}
	
	public static UserGui getLoggedUser()
	{
		return (UserGui) getAttributeFromUserContext(UserContext.UserContextStaticName.LOGED_USER);		
	}
	
	
	public static void removeSession(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();				
		if(cookies!=null){
			Cookie cookie=new Cookie("JSESSIONID", null);
			for (int i = 0; i < cookies.length; i++) {
				if("JSESSIONID".equals(cookies[i].getName()))
				{
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setPath(cookies[i].getPath());				
				}
			}				
			response.addCookie(cookie);
		}
		request.getSession().invalidate();
	}
}
