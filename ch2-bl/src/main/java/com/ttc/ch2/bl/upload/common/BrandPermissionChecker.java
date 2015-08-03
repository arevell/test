package com.ttc.ch2.bl.upload.common;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.user.UserGui;

@Component
@Scope("prototype")
public class BrandPermissionChecker implements ExtraPermissionChecker {

	private UserGui userGui;
	private String brandCode;
	
	@Inject
	private BrandDAO brandDao;
		
	public BrandPermissionChecker() {
		super();	
	}

	@Override
	public boolean checkPermission() {
		
		return checkPermisionForBrand();
	}
		
	private boolean checkPermisionForBrand()
	{
		Preconditions.checkState(StringUtils.isNotEmpty(brandCode),"BrandPermissionChecker.checkPermission -> field brandCode is empty");
		Preconditions.checkState(userGui!=null,"BrandPermissionChecker.userGui -> userGui is empty");
		
		Brand b=brandDao.findByBrandCode(brandCode);
		
		boolean hasAuthority=false;
		for (Brand userBrand : userGui.getBrands()) {
			if(userBrand.getId().equals(b.getId()))
			{
				hasAuthority=true;
				break;
			}
		}		
		return hasAuthority;
	}
	
	@Override
	public boolean checkBrandPermission() {
		return checkPermisionForBrand();
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	@Override
	public CheckerType checkerFor() {
		return CheckerType.BRAND;
	}

	public UserGui getUserGui() {
		return userGui;
	}

	public void setUserGui(UserGui userGui) {
		this.userGui = userGui;
	}

	

}
