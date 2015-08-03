package com.ttc.ch2.bl.upload.common;

public interface ExtraPermissionChecker {
		
	enum  CheckerType{BRAND,SELLING_COMPANIES};
	
	public boolean checkPermission();
	
	public boolean checkBrandPermission();
	
	public CheckerType checkerFor();

	public void setBrandCode(String brandCode);

}
