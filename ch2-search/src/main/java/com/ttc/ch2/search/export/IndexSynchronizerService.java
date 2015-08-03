package com.ttc.ch2.search.export;

import com.ttc.ch2.common.enums.ProcessName;

public interface IndexSynchronizerService {

	public static final String ES_INDEX_NAME = "contentrepositoryindex";
	public static final String ES_TOURS_ANS_SC_TYPE = "tiandtdsc";
	public static final String ES_AGGREGATED_TOURS_TYPE = "aggtours";
	public static final String TI_TAG = "TourInfoSimp";
	public static final String TD_TAG = "TourDeparturesSimp";
	public static final String AG_TL_TAG = "ToursList";
	public static final String AG_CTC_TAG = "CataloguedTourCode";
	public static final String AG_CTN_TAG = "CataloguedTourName";

	public IndexSynchronizerVO synchronize(ProcessName processName, String brandCode) throws IndexSynchronizerServiceException;
}
