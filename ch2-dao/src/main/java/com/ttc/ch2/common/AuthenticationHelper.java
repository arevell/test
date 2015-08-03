package com.ttc.ch2.common;

import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.ttc.ch2.common.exceptions.CompaniesAuthenticationException;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.auth.UserCCAPIDetails;
import com.ttc.ch2.domain.auth.UserGuiDetails;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

public class AuthenticationHelper {

	public static boolean companiesAuthentication(FunctionType fType,List<String> companies,UserCCAPI user)
	{		
		if (companies.size() == 0) {
			throw new CompaniesAuthenticationException("Invalid companies permission denied");
		} else {
			List<String> companiesWithOutAuthority = Lists.newArrayList();
			for (String compCode : companies) {

				boolean existAuthority = false;
				for (CCAPIAuthority auth : user.getCcapiAuthorities()) {					
					if(auth.getFunction().getName().equals(fType.getSimpleName())){
						if (auth.getSellingCompany().getCode().equals(compCode)) {
							existAuthority = true;
							break;
						}
					}
				}
				if (existAuthority == false)
					companiesWithOutAuthority.add(compCode);

				existAuthority = false;
			}

			if (companiesWithOutAuthority.size() > 0) {
				throw new CompaniesAuthenticationException(
						"Invalid companies ("+ Joiner.on(",").join(companiesWithOutAuthority)+ ") permission denied", companiesWithOutAuthority.get(0));
			}
		}

		return true;
	}
	
	
	public static Authentication buildAuthenticationForUserGui(UserGui uGui) 
	{
		 UserGuiDetails usrDetails = new UserGuiDetails(); 
		 Set<GrantedAuthority> grantedAuths = AuthorityHelper.getAllGrantedAuthoritiesForUser(uGui);
         Authentication auth = new UsernamePasswordAuthenticationToken(usrDetails, usrDetails.getPassword(), grantedAuths);
         
         usrDetails.setUserDb(uGui);
         usrDetails.setAuthorities(grantedAuths);
         usrDetails.setAccountNonExpired(true);
         usrDetails.setAccountNonLocked(uGui.getEnabled());
         usrDetails.setCredentialsNonExpired(true);	
         return auth;
	}
	
	public static Authentication buildAuthenticationForUserCcapi(UserCCAPI uCcapi,UserCCAPIDetails usrDetails) 
	{
		Set<GrantedAuthority> grantedAuths = AuthorityHelper.getAllGrantedAuthoritiesForUser(uCcapi);
        Authentication auth = new UsernamePasswordAuthenticationToken(usrDetails, usrDetails.getToken(), grantedAuths);		            
        usrDetails.setUserDb(uCcapi);
        usrDetails.setAuthorities(grantedAuths);
        usrDetails.setAccountNonExpired(true);
        usrDetails.setAccountNonLocked(uCcapi.getEnabled());
        usrDetails.setCredentialsNonExpired(true);	 
        return auth;
	}
	
}
