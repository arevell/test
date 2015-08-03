package com.ttc.ch2.bl.contentrepository;

import java.util.List;
import java.util.Set;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;

public interface ContentRepositoryService {

	/**
	 * Get filtred ContentRepository - for Central repository ui
	 * */
	int getCountentRepositoryTourInfoCount(ContentRepository filter, RepositoryStatus... repositoryStatusTab);

	/**
	 * Get filtred ContentRepository - for Central repository ui
	 * */
	int getCountentRepositoryTourDepartureCount(ContentRepository filter, RepositoryStatus... repositoryStatusTab);

	/**
	 * Get filtred ContentRepository - for Central repository ui
	 * */
	List<ContentRepository> getContentRepositoriesList(QueryCondition conditions, ContentRepository filter, RepositoryStatus... repositoryStatusTab);

	List<ContentRepository> getContentRepositoriesList(List<SortCondition> sortConditionList, ContentRepository filter);

	List<Long> getContentRepositoryIDsList(List<String> tourCodes, String brandCode);

	/**
	 * Find by tour code
	 * */
	public ContentRepository findByTourCode(String tourCode, String brandCode);

	public List<ContentRepository> findByTourCodes(List<String> tourCodes, String brandCode);

	public ContentRepository findByTourCodeWithXML(String tourCode, String brandCode);

	/*
	 * For CCAPI webservice method
	 */
	public ContentRepository getContentRepositoryForTourcodeAndSC(String tourCode, String sellingCompany) throws ContentRepositoryServiceException;

	/**
	 * Clear tourInfo method invoke on upload process
	 * */
	public void clearTourInfo(Set<Long> crToSave, Brand b);

	/**
	 * Clear tourInfo method invoke on upload process
	 * */
	public void clearTourDeparture(Set<Long> crToSave, Brand b);

	/**
	 * Delete all content repository rows without tourinfoXml and
	 * tourDeparturexml version 3
	 * */
	public int deleteEmptyContentRepository(String brandCode);
	
	
	/**
	 * Get all ContentRepository id for SearchToursAggregated indexing
	 * 
	 */
	public List<Long> getExtendedCRIdsforSearchToursAggregated(List<Long> ids, String brandCode);
}
