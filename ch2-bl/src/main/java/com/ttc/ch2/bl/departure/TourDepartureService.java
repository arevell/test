package com.ttc.ch2.bl.departure;

import java.util.List;

import facade.itropics.webservice.tropics.com.itropicsbuildws.WsDeparturesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursOfBrandsVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursWithSCListVO;

public interface TourDepartureService {

	WsToursWithSCListVO getToursWithSCList(String brandCode) throws TourDepartureServiceException;

	WsToursOfBrandsVO getToursOfBrands(List<String> brandsCodesList) throws TourDepartureServiceException;

	WsDeparturesVO getTourDatesAndRates(String tourCode, String brandSellingCompanyCode, String apiKey) throws TourDepartureServiceException;
}
