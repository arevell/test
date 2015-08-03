package com.ttc.ch2.bl.contentrepository;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;

@Service
public class ContentRepositoryServiceSecImpl implements ContentRepositoryServiceSec{

	@Inject
	private ContentRepositoryService contentRepositoryService;
	
	@Override
//	@PreAuthorize("hasRole('ROLE_BRAND','ROLE_ADMIN','ROLE_CCAPI')")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ContentRepository findByTourCodeWithXML(String tourCode,String brandCode) {
		return contentRepositoryService.findByTourCodeWithXML(tourCode,brandCode);
	}

	@Override
	public int getCountentRepositoryTourInfoCount(ContentRepository filter,
			RepositoryStatus... repositoryStatusTab) {
		return contentRepositoryService.getCountentRepositoryTourInfoCount(filter, repositoryStatusTab);
	}

	@Override
	public int getCountentRepositoryTourDepartureCount(ContentRepository filter, RepositoryStatus... repositoryStatusTab) {
		return contentRepositoryService.getCountentRepositoryTourDepartureCount(filter, repositoryStatusTab);
	}

	@Override
	public List<ContentRepository> getContentRepositoriesList(QueryCondition conditions, ContentRepository filter,RepositoryStatus... repositoryStatusTab) {
		return contentRepositoryService.getContentRepositoriesList(conditions, filter, repositoryStatusTab);
	}

	@Override
	public List<ContentRepository> getContentRepositoriesList(List<SortCondition> sortConditionList, ContentRepository filter) {
		return contentRepositoryService.getContentRepositoriesList(sortConditionList, filter);
	}
}
