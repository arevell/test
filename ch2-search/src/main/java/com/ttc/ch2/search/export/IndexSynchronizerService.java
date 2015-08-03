package com.ttc.ch2.search.export;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.Brand;

public interface IndexSynchronizerService {

	public static final String ES_INDEX_NAME = "contentrepositoryindex";
	public static final String ES_TOURS_ANS_SC_TYPE = "tiandtdsc";
	public static final String ES_AGGREGATED_TOURS_TYPE = "aggtours";
	public static final String TI_TAG = "TourInfoSimp";
	public static final String TD_TAG = "TourDeparturesSimp";
	public static final String AG_TL_TAG = "ToursList";
	public static final String AG_CTC_TAG = "CataloguedTourCode";
	public static final String AG_CTN_TAG = "CataloguedTourName";
	public static final String ES_EXCEPTION_MSG = "Some tours were indexed incorrectly, check Reconciliation report for details";
	
	//full indexation
	public void synchronize(ProcessName processName, String brandCode, IndexSynchronizerVO syncOut) throws IndexSynchronizerServiceException;
	
	//partial indexation
	public void synchronize(ProcessName processName, String brandCode, List<Long> tourIds,IndexSynchronizerVO indexSynchronizerVO) throws IndexSynchronizerServiceException;
	public void synchronize_delete(ProcessName processName, Brand brand, Set<Long> tourIds,IndexSynchronizerVO indexSynchronizerVO) throws IndexSynchronizerServiceException;
	
	public IndexSynchronizerVO getStateOfIndex(Map<Date, IndexSynchronizerVO> map,String brandCode);
	
	public IndexSynchronizerVO margeForShortIndexsynchronizeVO(Map<Date, IndexSynchronizerVO> map);
	
}
