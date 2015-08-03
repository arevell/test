package com.ttc.ch2.bl.security;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.user.UserCCAPI;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Override
	public boolean verifyPermissionsToFile(UserCCAPI user, String tourCode,String brandCode, FunctionType function) {
		return contentRepositoryDAO.checkPermissionToFile(user, tourCode,brandCode,function);
	}

	@Override
	public boolean verifiPermisionToBrand(UserCCAPI user, String brandCode,FunctionType function) {

		if(StringUtils.isBlank(brandCode))
			return false;
		
		Collection<SellingCompany> companiesForFunction= AuthorityHelper.transformAuthorityforUserCcapi(user).get(function);
		if(companiesForFunction==null)
			return false;
		
		for (SellingCompany sellingCompany : companiesForFunction) {
			if(sellingCompany.getBrand().getCode().equals(brandCode))
				return true;
		}		
		return false;
	}

}
