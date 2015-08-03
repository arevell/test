package com.ttc.ch2.common;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

public enum FunctionType {

	UPLOAD_TOUR_INFO("UploadTourInfoRequest", "SOAP:V3:upload-tour-info", "consolidatedContentAPIv3","Please be aware that Upload function access can be set only for all Selling Companies of a given Brand. This is due to assumption that all Tour Info xmls are set for all SC uploaded by Brand."),
	SEARCH_TOURS_AGGREGATED("SearchToursAggregatedRequest", "SOAP:V3:search-tours-aggregated", "consolidatedContentAPIv3","Please ensure selected Selling Companies have the same currency."),
	SEARCH_TOURS_V1("SearchTours", "SOAP:V1:search-tours", "consolidatedContentAPIv1","Please ensure selected Selling Companies have the same currency."),
	SEARCH_TOURS_V3("SearchToursRequest", "SOAP:V3:search-tours", "consolidatedContentAPIv3","Please ensure selected Selling Companies have the same currency."),
	GET_TOUR_DETAILS_FULL("GetTourDetailsFullRequest", "SOAP:V3:get-tour-details-full", "consolidatedContentAPIv3",""),
	GET_TOUR_CATEGORIES("GetTourCategoriesRequest", "SOAP:V3:get-tour-categories", "consolidatedContentAPIv3",""),
	GET_CONTINENTS_AND_COUNTRIES_VISITED("GetContinentsAndCountriesVisitedRequest", "SOAP:V3:get-continents-and-countries-visited", "consolidatedContentAPIv3",""),
	GET_BROCHURE("GetBrochureRequest", "SOAP:V3:get-ebrochure", "consolidatedContentAPIv3",""),
	TOUR_DETAILS_FULL("TourDetailsFull", "SOAP:V1:tour-details-full", "consolidatedContentAPIv1",""),
	
	CR_VIEW_V1("ContentRepository-View-Ver1", "REST:V1:cr-view", "","Please ensure that you selected all Selling Companies within a given Brand. This is due to assumption that each of the returned xml file may content different Selling Companies."),
	CR_VIEW_V3("ContentRepository-View-Ver3", "REST:V3:cr-view", "","Please ensure that you selected all Selling Companies within a given Brand. This is due to assumption that each of the returned xml file may content different Selling Companies."),
	OA_VIEW_V1("OutgoingArchives-View-Ver1", "REST:V1:outgoing-archives", "",""),
	OA_VIEW_V3("OutgoingArchives-View-Ver3", "REST:V3:outgoing-archives", "",""),
	
	EBROCHURE_V1("EBrochure-Ver1", "REST:V1:ebrochure", "",""),	
	;

	private String simpleName;
	private String soapName;
	private String beanName;
	private String info;

	private FunctionType(String soapName, String simpleName, String beanName,String info) {
		this.soapName = soapName;
		this.simpleName = simpleName;
		this.beanName = beanName;
		this.info=info;
	}

	public static FunctionType getValueBySoapName(String soapName, String beanName) {

		Preconditions.checkArgument(StringUtils.isNotEmpty(soapName), "FunctionType.getValueBySoapName-> arg soapName is null");

		if(soapName.equals("GetTourDataUploadStatusRequest") && StringUtils.isNotEmpty(beanName) && beanName.equals("consolidatedContentAPIv3"))
			return UPLOAD_TOUR_INFO;
		
		for (int i = 0; i < values().length; i++) {
			if (values()[i].soapName.equals(soapName) && (beanName == null || values()[i].beanName.equals(beanName))) {
				return values()[i];
			}
		}

		throw new IllegalArgumentException("FunctionType enum not found for:" + soapName);		
	}
	
	public static FunctionType getValueBySimpleName(String simpleName) {
		
		for (int i = 0; i < values().length; i++) {
			if (values()[i].simpleName.equals(simpleName)) {
				return values()[i];
			}
		}
		throw new IllegalArgumentException("FunctionType enum not found for:" + simpleName);		
	}

	public String getSimpleName() {
		return simpleName;
	}

	public String getSoapName() {
		return soapName;
	}

	public String getBeanName() {
		return beanName;
	}

	public String getInfo() {
		return info;
	}
}
