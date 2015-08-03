package com.ttc.ch2.ui.moduls.messages.common;

import java.util.List;

import com.ttc.ch2.bl.message.MessagesService;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.messages.EmailHistory;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;


public class PagingEmailHistoryModel extends AbstractFilterPagingListModel<EmailHistory> {
	
	private static final long serialVersionUID = 1266540936158676578L;

	public static final int PAGE_SIZE=20;
	
	private MessagesService service;
		
	public PagingEmailHistoryModel(MessagesService service,QueryCondition condition,EmailHistory filter) {
		super(0, PAGE_SIZE,filter);
		this.service=service;		
		loadData(condition);
	}
	
	public PagingEmailHistoryModel(MessagesService userGuiDAO,QueryCondition condition,EmailHistory filter,int activePage) {
		super(activePage, PAGE_SIZE,filter);
		this.service=userGuiDAO;	
		loadData(condition);
	}
	
	@Override
	public int getTotalSize() {
		return service.getEmailHistoryCount(filter);
	}

	
	@Override
	protected List<EmailHistory> getPageData(QueryCondition conditions, EmailHistory filter) {
		return service.getEmailHistoryList(conditions, filter);
	}

	@Override
	protected void storeSortCondition(QueryCondition condition) {
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_MESSAGES, condition);		
	}
}
