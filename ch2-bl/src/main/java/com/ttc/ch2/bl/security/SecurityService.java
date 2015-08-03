package com.ttc.ch2.bl.security;

import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.domain.user.UserCCAPI;

public interface SecurityService {
	
	boolean verifyPermissionsToFile(UserCCAPI user, String tourCode,String brandCode, FunctionType function);
	
	boolean verifiPermisionToBrand(UserCCAPI user,String brandCode, FunctionType functionType);
}
