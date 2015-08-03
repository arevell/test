package com.ttc.ch2.bl.contentrepository;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;

public interface ContentRepositoryServiceSec {
			
	@PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_CCAPI')")
	public ContentRepository findByTourCodeWithXML(String tourCode,String brandCode); 
		
	/**
	 * Get filtered ContentRepository - for Central repository ui 
	 * */
	@PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_CCAPI')")
	int getCountentRepositoryTourInfoCount(ContentRepository filter,RepositoryStatus ... repositoryStatusTab);
	
	/**
	 * Get filtered ContentRepository - for Central repository ui 
	 * */
	@PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_CCAPI')")
	int getCountentRepositoryTourDepartureCount(ContentRepository filter,RepositoryStatus ... repositoryStatusTab);
			
	/**
	 * Get filtered ContentRepository - for Central repository ui
	 * */
	@PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_CCAPI')")
	List<ContentRepository> getContentRepositoriesList(QueryCondition conditions, ContentRepository filter,RepositoryStatus ... repositoryStatusTab);
	
	@PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_CCAPI')")
	List<ContentRepository> getContentRepositoriesList(List<SortCondition> sortConditionList, ContentRepository filter);
}
