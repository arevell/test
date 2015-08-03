package com.ttc.ch2.bl.lock;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandLockDAO;
import com.ttc.ch2.domain.BrandLock;

@Service
public class LockBrandServiceImpl implements LockBrandService {

	private static final Logger logger = LoggerFactory.getLogger(LockBrandServiceImpl.class);
	@Inject
	private BrandLockDAO brandLockDAO;
		

	@Override
	public boolean lockBrand(String brandCode,ProcessName processName){
		logger.trace("LockBrandServiceImpl:lockBrand-start");
		
		Preconditions.checkArgument(StringUtils.isNotBlank(brandCode),"LockBrandServiceImpl->lockBrandIfNecessary arg brandCode is null");
		Preconditions.checkArgument(processName!=null,"LockBrandServiceImpl->lockBrandIfNecessary arg processName is null");
		
		boolean result=false;
		BrandLock brandLock = findBrandLockByCode(brandCode);
		if(brandLock==null){
			BrandLock bLock=new BrandLock();
			bLock.setBrandCode(brandCode);
			bLock.setProccessName(processName);
			try {
				brandLockDAO.save(bLock);
				result=true;
			}catch(Exception e) {
				logger.warn("Cannot set lock on database for brandCode:"+brandCode,e);
				result=false;
			}
			
		}
		else{			 
			result=false;
		}
		logger.trace("LockBrandServiceImpl:lockBrand-end");
		return result;
	}
	
	@Override
	public boolean releaseLockBrand(String brandCode,ProcessName processName){
		logger.trace("LockBrandServiceImpl:releaseLockBrand-start");
		Preconditions.checkArgument(StringUtils.isNotBlank(brandCode),"LockBrandServiceImpl->lockBrandIfNecessary arg brandCode is null");
		Preconditions.checkArgument(processName!=null,"LockBrandServiceImpl->lockBrandIfNecessary arg processName is null");
		boolean result=false;
		BrandLock brandLock = findBrandLockByCode(brandCode);
		if(brandLock!=null && processName==brandLock.getProccessName()){			
				brandLockDAO.remove(brandLock);
				result=true;						
		}
		else {
			result=false;
		}
		logger.trace("LockBrandServiceImpl:releaseLockBrand-end");
		return result;	
	}
	
	@Override
	public boolean releaseLockBrand(String brandCode) {
		logger.trace("LockBrandServiceImpl:releaseLockBrand-start");
		Preconditions.checkArgument(StringUtils.isNotBlank(brandCode),"LockBrandServiceImpl->lockBrandIfNecessary arg brandCode is null");
		
		boolean result=false;
		BrandLock brandLock = findBrandLockByCode(brandCode);
		if(brandLock!=null){
			result=releaseLockBrand(brandLock.getBrandCode(), brandLock.getProccessName());
		}
		logger.trace("LockBrandServiceImpl:releaseLockBrand-end");
		return result;
	}
	
	public BrandLock findBrandLockByCode(String brandCode){
		Search search = new Search();
		search.addFilterEqual("brandCode", brandCode);		
		BrandLock brandLock = brandLockDAO.searchUnique(search);
		return brandLock;
	}

	@Override
	public boolean isLockBrand(String brandCode) {
		logger.trace("LockBrandServiceImpl:isLockBrand-start");
		Preconditions.checkArgument(StringUtils.isNotBlank(brandCode),"LockBrandServiceImpl->isLockBrand arg brandCode is null");
		BrandLock brandLock = findBrandLockByCode(brandCode);
		logger.trace("LockBrandServiceImpl:isLockBrand-end");
		return brandLock!=null;
	}
	
	@Override
	public boolean isLockBrand(String brandCode,ProcessName processName) {
		logger.trace("LockBrandServiceImpl:isLockBrand-start");
		Preconditions.checkArgument(StringUtils.isNotBlank(brandCode),"LockBrandServiceImpl->isLockBrand arg brandCode is null");
		BrandLock brandLock = findBrandLockByCode(brandCode);
		logger.trace("LockBrandServiceImpl:isLockBrand-end");
		return brandLock!=null && brandLock.getProccessName()==processName;
	}

	
	
}
