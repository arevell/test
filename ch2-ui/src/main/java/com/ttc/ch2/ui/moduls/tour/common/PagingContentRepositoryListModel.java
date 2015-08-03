package com.ttc.ch2.ui.moduls.tour.common;

import java.util.List;

import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;


public class PagingContentRepositoryListModel extends AbstractFilterPagingListModel<ContentRepository> {
	
	private static final long serialVersionUID = -1070933550377639125L;

	public static final int PAGE_SIZE=18;
	
	private ContentRepositoryService contentRepositoryService;
	
	private RepositoryStatus  [] repositoryStatus; 
	
	private RepositoryStatus searchType;
		
	public PagingContentRepositoryListModel(ContentRepositoryService contentRepositoryService,QueryCondition condition,ContentRepository filter,RepositoryStatus  ... repositoryStatus) {		
		this(contentRepositoryService,condition, filter, 0, repositoryStatus);
	}
	
	public PagingContentRepositoryListModel(ContentRepositoryService contentRepositoryService,QueryCondition condition,ContentRepository filter,int activePage,RepositoryStatus  ... repositoryStatus) {
		super(activePage, PAGE_SIZE,filter);
		this.contentRepositoryService=contentRepositoryService;
		this.repositoryStatus=repositoryStatus;
		
		for (int i = 0; i < repositoryStatus.length; i++) {
			
			if(repositoryStatus[i]==RepositoryStatus.TourInfoOnly)
			{
				searchType=RepositoryStatus.TourInfoOnly;
				break;
			}
			else if(repositoryStatus[i]==RepositoryStatus.TourDepartureOnly)
			{
				searchType=RepositoryStatus.TourDepartureOnly;
				break;
			}
		}		
		loadData(condition);
	}
	

	@Override
	public int getTotalSize() {
		
		int result=0;
		if(searchType==RepositoryStatus.TourInfoOnly)
			result=contentRepositoryService.getCountentRepositoryTourInfoCount(filter,repositoryStatus);
		else if(searchType==RepositoryStatus.TourDepartureOnly)
			result=contentRepositoryService.getCountentRepositoryTourDepartureCount(filter,repositoryStatus);		
		return result;
	}
	
	@Override
	protected List<ContentRepository> getPageData(QueryCondition conditions, ContentRepository filter) {
		return contentRepositoryService.getContentRepositoriesList(conditions, filter,repositoryStatus);
	}

	@Override
	protected void storeSortCondition(QueryCondition condition) {
				
		if (SessionHelper.getSession().getAttribute(UserContext.class.getName()) != null) {
			SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_CR, condition);
		} else {
			SessionHelper.getSession().setAttribute(UserContextStaticName.SORT_CONDITION_CR, condition);
		}
		
	}	
}
