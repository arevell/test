package com.ttc.ch2.dao.tour;

import java.util.List;
import java.util.Set;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.user.UserCCAPI;

public interface ContentRepositoryDAO extends GenericDAO<ContentRepository, Long> {

	public boolean checkPermissionToFile(UserCCAPI user, String tourCode,String brandCode,FunctionType function);

	public List<ContentRepository> getContentRepositoriesList(QueryCondition queryCondition, ContentRepository filter);

	public int getContentRepositoriesCount(QueryCondition queryCondition, ContentRepository filter);

	public List<ContentRepository> getContentRepositoriesList(QueryCondition queryCondition, ContentRepository filter, List<ContentRepository.RepositoryStatus> statuses);
	
	public List<Long> getContentRepositoriesIdsList(QueryCondition queryCondition, ContentRepository filter, List<RepositoryStatus> statuses) ;

	public int getContentRepositoriesCount(QueryCondition queryCondition, ContentRepository filter, List<ContentRepository.RepositoryStatus> statuses);

	public ContentRepository findByTourCode(String tourCode,String brandCode);

	public List<ContentRepository> findByTourCodes(List<String> tourCodes, String brandCode);

	// this method need be check
	public ContentRepository findByTourCodeWithLock(String tourCode,String brandCode);

	public List<ContentRepository> getContentRepositoriesElementsToClear(Set<Long> crToSave,Brand brand,RepositoryStatus ... status);
	
	public List<Long> getContentRepositoriesIdsToClear(Set<Long> crToSave,Brand brand,RepositoryStatus ... status);

	public int deleteEmptyContentRepository(String brandCode);

	@Deprecated
	public List<ContentRepository> getContentRepositoriesForExport();

	public List<ContentRepository> getContentRepositoriesForExport(String brandCode);
	
	void clearSession();
	
	void evictEntity(ContentRepository entity);
	
	List<Long> getExtendedCRIdsforSearchToursAggregated(List<Long> ids, String brandCode);
	
	Long[] getCRStatistics(String brandCode);
}
