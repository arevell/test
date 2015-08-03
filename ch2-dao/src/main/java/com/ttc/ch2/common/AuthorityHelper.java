package com.ttc.ch2.common;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.ttc.ch2.common.predicates.FindeSellingCompanyByCodePredicate;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.Authority;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.auth.GroupAuthority;
import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

public class AuthorityHelper {

	public static Set<GrantedAuthority> getAllGrantedAuthoritiesForUser(User user) {
		Set<GrantedAuthority> grantedAuths = Sets.newHashSet();
		Iterables.addAll(grantedAuths, Iterables.transform(user.getAuthorities(), new AuthorityTransformer()));

		if (user.getGroups() != null)
			for (Group group : user.getGroups()) {
				Iterables.addAll(grantedAuths, Iterables.transform(group.getGroupAuthorities(), new GroupAuthorityTransformer()));
			}

		return grantedAuths;
	}
	
	
	public static boolean checkUserHasFunctionPermition(UserCCAPI user,FunctionType fType)
	{
		boolean result=false;				
		for (CCAPIAuthority auth : user.getCcapiAuthorities()) {
			if(auth.getFunction().getName().equals(fType.getSimpleName()))
			{
				result=true;
				break;
			}
		}		
		return result;
	}
	
	public static boolean hasAuthorityUserCcapi(FunctionType function,String sellingCompanyCode)
	{
		boolean result=false;
		if(SecurityHelper.isUserCcapi())
		{
			UserCCAPI user=SecurityHelper.getUserCCAPIPrincipal().getUserDb();
			if(checkUserHasFunctionPermition(user,function))
			{
				for (CCAPIAuthority auth : user.getCcapiAuthorities()) {
					if(auth.getFunction().getName().equals(function.getSimpleName())){						
						result=auth.getSellingCompany().getCode().equals(sellingCompanyCode);
						if (result == true) {
							break;
						}
					}	
				}
			}
		}		
		return result;
	}
	
	public static boolean hasAuthorityForUserGui(String sellingCompanyCode)
	{
		boolean result=false;
		if(SecurityHelper.isUserGui())
		{
			UserGui user=SecurityHelper.getUserGuiPrincipal().getUserDb();
			for (Brand brand : user.getBrands()) {
				Optional<SellingCompany> oSellingCompany=Iterables.tryFind(brand.getSellingCompanies(), new FindeSellingCompanyByCodePredicate(sellingCompanyCode));
				if(oSellingCompany.isPresent()){
					result=true;
					break;
				}					
			}
		}
		return result;
	}
	
	
	public static Multimap<FunctionType,SellingCompany> transformAuthorityforUserCcapi(UserCCAPI user)
	{		
		Multimap<FunctionType,SellingCompany> extraAuthority = ArrayListMultimap.create();
			for (CCAPIAuthority auth : user.getCcapiAuthorities()) {				
				FunctionType fType=FunctionType.getValueBySimpleName(auth.getFunction().getName());
				extraAuthority.put(fType, auth.getSellingCompany());				
			}				
		return extraAuthority;
	}
	
   public static Brand getBrandFromUserCcapiByBrandCode(UserCCAPI user,String brandCode,FunctionType fType)
   {
	   Collection<SellingCompany> companies=transformAuthorityforUserCcapi(user).get(fType);
	   if(companies!=null){
		   for (SellingCompany sellingCompany : companies) {
			   if(sellingCompany.getBrand().getCode().equals(brandCode))
				   return sellingCompany.getBrand();
		   }
	   }		   
	   return null;
   }
   
   public static Collection<Brand> getBrandsFromUserCcapi(UserCCAPI user,FunctionType fType)
   {
	   Map<String,Brand> brands=Maps.newHashMap();
	   Collection<SellingCompany> companies=transformAuthorityforUserCcapi(user).get(fType);
	   if(companies!=null){
		   for (SellingCompany sellingCompany : companies) {			   
			   if(brands.containsKey(sellingCompany.getBrand().getCode())==false)
				  brands.put(sellingCompany.getBrand().getCode(),sellingCompany.getBrand());
		   }
	   }		   
	   return brands.values();
   }

	static class AuthorityTransformer implements Function<Authority, GrantedAuthority> {
		@Override
		public GrantedAuthority apply(Authority input) {
			return new SimpleGrantedAuthority(input.getAuthority());
		}
	}

	static class GroupAuthorityTransformer implements Function<GroupAuthority, GrantedAuthority> {
		@Override
		public GrantedAuthority apply(GroupAuthority input) {
			return new SimpleGrantedAuthority(input.getAuthority());
		}
	}
}
