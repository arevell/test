package com.ttc.ch2.dao.security;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.user.UserGui;

public interface UserGuiDAO extends GenericDAO<UserGui, Long> {

	int getUsersCount(UserGui filter);

	List<UserGui> getUsers(QueryCondition conditions, UserGui filter);

	public UserGui findByExample(UserGui exemple);
}
