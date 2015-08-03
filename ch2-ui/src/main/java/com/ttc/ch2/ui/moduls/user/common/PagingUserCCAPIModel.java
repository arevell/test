package com.ttc.ch2.ui.moduls.user.common;

import java.util.List;

import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;


public class PagingUserCCAPIModel extends AbstractFilterPagingListModel<UserCCAPI> {
	
	private static final long serialVersionUID = 1266540936158676578L;

	private static final int PAGE_SIZE=20;
	
	private UserCCAPIDAO userDAO;
		
	public PagingUserCCAPIModel(UserCCAPIDAO userGuiDAO,QueryCondition condition,UserCCAPI filter) {
		super(0, PAGE_SIZE,filter);
		this.userDAO=userGuiDAO;		
		loadData(condition);
	}
	
	public PagingUserCCAPIModel(UserCCAPIDAO userGuiDAO,QueryCondition condition,UserCCAPI filter,int activePage) {
		super(activePage, PAGE_SIZE,filter);
		this.userDAO=userGuiDAO;	
		loadData(condition);
	}
	
	@Override
	public int getTotalSize() {
		return userDAO.getUsersCount(filter);
	}

	
	@Override
	protected List<UserCCAPI> getPageData(QueryCondition conditions, UserCCAPI filter) {
		return userDAO.getUsers(conditions, filter);
	}

	@Override
	protected void storeSortCondition(QueryCondition condition) {
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_USER, condition);		
	}
}
