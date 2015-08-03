package com.ttc.ch2.bl.lock;

import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.BrandLock;

public interface LockBrandService {

	boolean lockBrand(String brandCode, ProcessName processName);

	boolean releaseLockBrand(String brandCode, ProcessName processName);
	
	boolean releaseLockBrand(String brandCode);
	
	boolean isLockBrand(String brandCode);
	
	public boolean isLockBrand(String brandCode,ProcessName processName);
	
	public BrandLock findBrandLockByCode(String brandCode);

}