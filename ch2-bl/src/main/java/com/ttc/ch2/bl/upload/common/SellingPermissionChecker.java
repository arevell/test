package com.ttc.ch2.bl.upload.common;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.user.UserCCAPI;

@Component
@Scope("prototype")
public class SellingPermissionChecker implements ExtraPermissionChecker {

	private UserCCAPI userCcapi;
	private Set<String> sellingCompanies;
	private Set<String> listCompaniesWithoutAuthority;
	
	private String brandCode;
		
	public SellingPermissionChecker() {
		super();	
	}

	@Override
	public boolean checkPermission() {
		
		Preconditions.checkState(sellingCompanies!=null,"SellingPermissionChecker.checkPermission -> sellingCompanies is empty");
		Preconditions.checkState(userCcapi!=null,"SellingPermissionChecker.userCcapi -> userCcapi is empty");

		Set<String> userCompanies=Sets.newHashSet();	
		for (CCAPIAuthority authority : userCcapi.getCcapiAuthorities()) {			
			if(FunctionType.UPLOAD_TOUR_INFO.getSimpleName().equals(authority.getFunction().getName())){
				userCompanies.add(authority.getSellingCompany().getCode());
			}
		}		
		this.listCompaniesWithoutAuthority = Sets.difference(sellingCompanies, userCompanies);
						
		return listCompaniesWithoutAuthority.size()==0;
	}
	

	@Override
	public boolean checkBrandPermission() {
		Preconditions.checkState(StringUtils.isNotEmpty(brandCode),"SellingPermissionChecker.checkBrandPermission -> brandCode is empty");
		Preconditions.checkState(userCcapi!=null,"SellingPermissionChecker.userCcapi -> userCcapi is empty");
				
		Set<String> brands=Sets.newHashSet();
		for (CCAPIAuthority auth : userCcapi.getCcapiAuthorities()) {		
			brands.add(auth.getSellingCompany().getBrand().getCode());
		}
		return brands.contains(brandCode);
	}
	

	public Set<String> getSellingCompanies() {
		return sellingCompanies;
	}

	public void setSellingCompanies(Set<String> sellingCompanies) {
		this.sellingCompanies = sellingCompanies;
	}

	@Override
	public CheckerType checkerFor() {
		return CheckerType.SELLING_COMPANIES;
	}

	public Set<String> getListCompaniesWithoutAuthority() {
		return listCompaniesWithoutAuthority;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public UserCCAPI getUserCcapi() {
		return userCcapi;
	}

	public void setUserCcapi(UserCCAPI userCcapi) {
		this.userCcapi = userCcapi;
	}


}
