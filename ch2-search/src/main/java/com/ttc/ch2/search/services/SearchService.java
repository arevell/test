package com.ttc.ch2.search.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;

import org.codehaus.jackson.JsonProcessingException;

import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedRequest;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedResponse;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesResponse;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedRequest;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedResponse;

public interface SearchService {

    GetTourCategoriesResponse getTourCategories(GetTourCategoriesRequest request);
	GetContinentsAndCountriesVisitedResponse getContinentsAndCountriesVisited(GetContinentsAndCountriesVisitedRequest param);
	com.travelcorp.ccapi.SearchToursResponse searchTours(com.travelcorp.ccapi.SearchTours param) throws SearchServiceException, JsonProcessingException, IOException, JAXBException, TransformerException, DatatypeConfigurationException;
	com.ttc.ch2.api.ccapi.v3.SearchToursResponse searchTours(com.ttc.ch2.api.ccapi.v3.SearchToursRequest param3);
	SearchToursAggregatedResponse searchToursAggregated(SearchToursAggregatedRequest param);
	
}
