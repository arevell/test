package com.ttc.ch2.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.ttc.ch2.common.predicates.FindAuthorityPredicate;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.auth.UserCCAPIDetails;
import com.ttc.ch2.domain.auth.UserGuiDetails;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

public class SecurityHelper {

	public static boolean isAuthenticated(){	
		SecurityContext sctx=SecurityContextHolder.getContext();
		Preconditions.checkState(sctx!=null, "SecurityHelper->isAuthenticated SecurityContext is null");
		Authentication authentication=sctx.getAuthentication();
		Preconditions.checkState(authentication!=null, "SecurityHelper->isAuthenticated Authentication is null");		
		return authentication.isAuthenticated();
	}
	
	public static boolean isAuthenticatedSilent(){		
		try{
			return isAuthenticated();
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static boolean hasAuthority(String authorityName){
		return hasAuthority(Role.getRoleByString(authorityName));
	}
	
	public static boolean hasAuthority(Role role){
		SecurityContext sctx=SecurityContextHolder.getContext();
		Preconditions.checkState(sctx!=null, "SecurityHelper->hasAuthority SecurityContext is null");
		Authentication authentication=sctx.getAuthentication();
		Preconditions.checkState(authentication!=null, "SecurityHelper->hasAuthority Authentication is null");

		return authentication!=null &&  Iterables.tryFind(authentication.getAuthorities(),new FindAuthorityPredicate(role)).isPresent();
	}
	
	public static String getLogin(){
		SecurityContext sctx=SecurityContextHolder.getContext();
		Preconditions.checkState(sctx!=null, "SecurityHelper->getLogin SecurityContext is null");
		Authentication authentication=sctx.getAuthentication();
		Preconditions.checkState(authentication!=null, "SecurityHelper->getLogin Authentication is null");		
		
		return authentication!=null ? authentication.getName() : "absence";
	}

    public static String getLoginSilent() {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            if (context == null) {
                return null;
            }
            
            Authentication authentication = context.getAuthentication();
            if (authentication == null) {
                return null;
            }
            
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof UserGuiDetails) {
                UserGuiDetails userDetails = (UserGuiDetails) principal;
                UserGui user = userDetails.getUserDb();
                return user.getUsername();
            }
            
            if (principal instanceof UserCCAPIDetails) {
                UserCCAPIDetails userDetails = (UserCCAPIDetails) principal;
                UserCCAPI user = userDetails.getUserDb();
                return user.getUsername();
            }
        } catch (Throwable ignored) {
        }
        
        return null;
    }
    
	public static UserCCAPIDetails getUserCCAPIPrincipal(){
		SecurityContext sctx=SecurityContextHolder.getContext();
		Preconditions.checkState(sctx!=null, "SecurityHelper->getUserCCAPIPrincipal SecurityContext is null");
		Authentication authentication=sctx.getAuthentication();
		Preconditions.checkState(authentication!=null, "SecurityHelper->getUserCCAPIPrincipal Authentication is null");		
		Preconditions.checkState(authentication.getPrincipal()!=null, "SecurityHelper->getUserCCAPIPrincipal Principal is null");
		
		if (authentication.getPrincipal() instanceof UserCCAPIDetails) {
			UserCCAPIDetails user = (UserCCAPIDetails) authentication.getPrincipal();
			return user;			
		}
				
		throw new UnsupportedOperationException("Principal is different type expect UserCCAPIDetails get:"+SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getName());
	}
	
	public static UserGuiDetails getUserGuiPrincipal() {
		SecurityContext sctx=SecurityContextHolder.getContext();
		Preconditions.checkState(sctx!=null, "SecurityHelper->getUserGuiPrincipal SecurityContext is null");
		Authentication authentication=sctx.getAuthentication();
		Preconditions.checkState(authentication!=null, "SecurityHelper->getUserGuiPrincipal Authentication is null");		
		Preconditions.checkState(authentication.getPrincipal()!=null, "SecurityHelper->getUserGuiPrincipal Principal is null");
		
		if (authentication.getPrincipal() instanceof UserGuiDetails) {
			UserGuiDetails user =  (UserGuiDetails) authentication.getPrincipal();
			return user;			
		}		
		throw new UnsupportedOperationException("Principal is different type expect UserGui get:"+SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getName());
	}
	
	public static boolean isUserGui() {
		SecurityContext sctx=SecurityContextHolder.getContext();
		Preconditions.checkState(sctx!=null, "SecurityHelper->isUserGui SecurityContext is null");
		Authentication authentication=sctx.getAuthentication();
		Preconditions.checkState(authentication!=null, "SecurityHelper->isUserGui Authentication is null");		
		Preconditions.checkState(authentication.getPrincipal()!=null, "SecurityHelper->isUserGui Principal is null");
		
		return authentication.getPrincipal() instanceof UserGuiDetails;
	}
	
	public static boolean isUserCcapi() {
		SecurityContext sctx=SecurityContextHolder.getContext();
		Preconditions.checkState(sctx!=null, "SecurityHelper->isUserCcapi SecurityContext is null");
		Authentication authentication=sctx.getAuthentication();
		Preconditions.checkState(authentication!=null, "SecurityHelper->isUserCcapi Authentication is null");		
		Preconditions.checkState(authentication.getPrincipal()!=null, "SecurityHelper->isUserCcapi Principal is null");		
		return authentication.getPrincipal() instanceof UserCCAPIDetails;
	}
	
	public static String getTokenSilent() {
	    try {
    	    SecurityContext context = SecurityContextHolder.getContext();
    	    if (context == null) {
    	        return null;
    	    }
    	    
    	    Authentication authentication = context.getAuthentication();
    	    if (authentication == null) {
    	        return null;
    	    }
    
    	    Object principal = authentication.getPrincipal();  
    	    if (!(principal instanceof UserCCAPIDetails)) {
    	        return null;
            }
    	    
    	    UserCCAPIDetails userDetails = (UserCCAPIDetails) principal;
    	    UserCCAPI user = userDetails.getUserDb();
    	    return user.getToken();
	    } catch (Throwable ignored) {   
	    }
	    
	    return null;
	}
	
	public static String getToken() {
			UserCCAPIDetails userDetails = getUserCCAPIPrincipal();
			UserCCAPI user = userDetails.getUserDb();
			return user.getToken();		
	}
}
