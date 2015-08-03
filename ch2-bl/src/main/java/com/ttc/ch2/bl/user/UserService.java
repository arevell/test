package com.ttc.ch2.bl.user;

import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

public interface UserService {
		
	public static String passwordPattern="^[A-Za-z0-9!@#$%^&*()_]{5,32}$";
	public static int maxInvalidCountLogin=5;
	
	public void mergeUserGui(UserGui u,boolean changePassword) throws UserServiceException;
	
	public void mergeUserCCAPI(UserCCAPI u) throws UserServiceException;
	
	public void deleteUserCCAPI(Long id) throws UserServiceException;
	
	public void deleteUserGui(Long id) throws UserServiceException;
		
	public UserGui findUserGui(Long id)throws UserServiceException;
	
	public UserCCAPI findUserCCAPI(Long id)throws UserServiceException;
	
	public void chgPassword(String oldPassword,String newPassword,UserGui u)throws UserServiceException;
	
	public void invalidLoginOperation(String login) throws UserServiceException;
	public void resetInvalidCountLogin(String login) throws UserServiceException;	
	public User findUserByName(String name)throws UserServiceException;
}
