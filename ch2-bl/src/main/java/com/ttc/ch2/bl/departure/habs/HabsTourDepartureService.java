package com.ttc.ch2.bl.departure.habs;

import java.util.List;

import com.wsout.habs.itropicsbuildws.WsDeparturesVO;
import com.wsout.habs.itropicsbuildws.WsToursOfBrandsVO;
import com.wsout.habs.itropicsbuildws.WsToursWithSCListVO;



public interface HabsTourDepartureService {

	WsToursWithSCListVO getToursWithSCList(String brandCode) throws HabsTourDepartureServiceException;

	WsToursOfBrandsVO getToursOfBrands(List<String> brandsCodesList) throws HabsTourDepartureServiceException;

	WsDeparturesVO getTourDatesAndRates(String tourCode, String brandSellingCompanyCode, String apiKey) throws HabsTourDepartureServiceException;
}
