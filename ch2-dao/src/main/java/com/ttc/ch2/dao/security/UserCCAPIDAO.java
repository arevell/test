package com.ttc.ch2.dao.security;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.user.UserCCAPI;

public interface UserCCAPIDAO extends GenericDAO<UserCCAPI, Long> {
	
	int getUsersCount(UserCCAPI filter);
	
	List<UserCCAPI> getUsers(QueryCondition conditions, UserCCAPI filter);
	
	UserCCAPI findByToken(String token);
	
}
