package com.ttc.ch2.ui.moduls.jobs.common;

import java.util.List;

import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;


public class PagingQuartzHistoryListModel extends AbstractFilterPagingListModel<QuartzJobHistory> {
	
	private static final long serialVersionUID = -1070933550377639125L;

	public static final int PAGE_SIZE=20;
	
	private QuartzJobCh2Service quartzJobCh2Service;
		
	public PagingQuartzHistoryListModel(QuartzJobCh2Service quartzJobCh2Service,QueryCondition condition,QuartzJobHistory filter) {
		super(0, PAGE_SIZE,filter);
		this.quartzJobCh2Service=quartzJobCh2Service;		
		loadData(condition);
	}
	
	public PagingQuartzHistoryListModel(QuartzJobCh2Service quartzJobCh2Service,QueryCondition condition,QuartzJobHistory filter,int activePage) {
		super(activePage, PAGE_SIZE,filter);
		this.quartzJobCh2Service=quartzJobCh2Service;	
		loadData(condition);
	}
	

	@Override
	public int getTotalSize() {
		return quartzJobCh2Service.getJobsCount(filter);
	}


	
	@Override
	protected List<QuartzJobHistory> getPageData(QueryCondition conditions, QuartzJobHistory filter) {
		return quartzJobCh2Service.getJobsHistoryList(conditions, filter);
	}

	@Override
	protected void storeSortCondition(QueryCondition condition) {
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_JOB, condition);			
	}

	



}
