package com.ttc.ch2.ui.moduls.user.common;

import java.util.List;

import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;


public class PagingUserGuiModel extends AbstractFilterPagingListModel<UserGui> {
	
	private static final long serialVersionUID = 1266540936158676578L;

	public static final int PAGE_SIZE=20;
	
	private UserGuiDAO userGuiDAO;
		
	public PagingUserGuiModel(UserGuiDAO userGuiDAO,QueryCondition condition,UserGui filter) {
		super(0, PAGE_SIZE,filter);
		this.userGuiDAO=userGuiDAO;		
		loadData(condition);
	}
	
	public PagingUserGuiModel(UserGuiDAO userGuiDAO,QueryCondition condition,UserGui filter,int activePage) {
		super(activePage, PAGE_SIZE,filter);
		this.userGuiDAO=userGuiDAO;	
		loadData(condition);
	}
	
	@Override
	public int getTotalSize() {
		return userGuiDAO.getUsersCount(filter);
	}

	
	@Override
	protected List<UserGui> getPageData(QueryCondition conditions, UserGui filter) {
		return userGuiDAO.getUsers(conditions, filter);
	}

	@Override
	protected void storeSortCondition(QueryCondition condition) {
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_USER, condition);		
	}
}
