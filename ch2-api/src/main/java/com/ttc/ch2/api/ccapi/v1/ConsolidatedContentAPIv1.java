package com.ttc.ch2.api.ccapi.v1;

import javax.inject.Inject;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.travcorp.contenthub.tour_data._2010._11._1.TourDetailsFull;
import com.travcorp.contenthub.tour_data._2010._11._1.TourDetailsFullResponse;
import com.travelcorp.ccapi.ArrayOfString;
import com.travelcorp.ccapi.Header;
import com.travelcorp.ccapi.SearchTourResults;
import com.travelcorp.ccapi.SearchTours;
import com.travelcorp.ccapi.SearchToursResponse;
import com.ttc.ch2.api.ccapi.v1.weave1.V1WeaveService;
import com.ttc.ch2.search.services.SearchService;

@Endpoint
@WebService
public class ConsolidatedContentAPIv1 {

	private static final Logger logger = LoggerFactory.getLogger(ConsolidatedContentAPIv1.class);

	public static final String TARGET_NAMESPACE = "http://contenthub.travcorp.com/tour_data/2010/11/1.0";

	private static final String TARGET_NAMESPACE_SEARCH = "http://CCAPI.TravelCorp.com/";
	private static final String ERROR_SEARCH_TOURS = "Unable to complete searchTours request: %s - %s";
	private static final String ERROR_SEARCH_TOURS_INTERNAL = 	"Unable to complete request searchTours(): [" +
																"SellingCompanyCodes:%s, " +
																"Continent:%s, " +
																"Country:%s, " +
																"Duration:%s, " +
																"Months:%s, " +
																"Keywords:%s, " +
																"FirstRecordNumber:%s, " +
																"NumberOfRecords:%s, " +
																"OrderBy:%s, " +
																"OrderDirection:%s]";

	@Inject
	private V1WeaveService service;

	@Inject
	private SearchService searchService;

	
	/**
	 * <p> This method gives search functionality on Tour Info and Tour Departures data collected in Content Repository.
	 * 
	 * <p> General search parameters rules:
	 * <li> The search checks selected parameters from TIv1 and TDv1 per Selling Company.
	 * <li> All search parameters produce search criteria used by SOLR search engine
	 * 
	 * @param request 
	 * <li> <required> securityKey, sellingCompanyCodes
	 * <li> continent, country
	 * <li> duration, months
	 * <li> <list> keywords
	 * <li> FirstRecordNumber
	 * <li> NumberOfRecords
	 * <li> OrderBy
	 * <li> OrderDirection
	 * 
	 * @return
	 * 	<li> all tours matching the search criteria with summary details and a lead in, guide price 
	 */
	@PayloadRoot(localPart = "SearchTours", namespace = TARGET_NAMESPACE_SEARCH)
	public @ResponsePayload SearchToursResponse searchTours(@RequestPayload SearchTours request) {

		SearchToursResponse response = new SearchToursResponse();

		try {

			response = searchService.searchTours(request);

		} catch (Exception e) {

			ArrayOfString errorMessages = new ArrayOfString();
			errorMessages.getString().add(String.format(ERROR_SEARCH_TOURS, e.getClass().getName(), e.getLocalizedMessage()));

			Header header = new Header();
			header.setErrorMessaages(errorMessages);
			header.setSuccessful(false);

			SearchTourResults searchTourResults = new SearchTourResults();
			searchTourResults.setHeader(header);

			response.setSearchToursResult(searchTourResults);

			logger.error(String.format(ERROR_SEARCH_TOURS_INTERNAL,
					request.getSellingCompanyCodes() != null && request.getSellingCompanyCodes().getString() != null ? request.getSellingCompanyCodes().getString().toString() : null,
					request.getContinent(),
					request.getCountry(),
					request.getDuration(),
					request.getMonths(),
					request.getKeywords() != null && request.getKeywords().getString() != null ? request.getKeywords().getString().toString() : null,
					request.getFirstRecordNumber(),
					request.getNumberOfRecords(),
					request.getOrderBy(),
					request.getOrderDirection()));
		}

		return response;
	}

	
	/**
	 * <p> This method retrieves Tour Info and Tour Departure data limited to single selling company in format stored in Content Repository under the @tourCode.
	 * 
	 * @param request 
	 *  <li> securityKey
	 *  <li> sellingCompanyCode
	 *  <li> marketVariationCode
	 * @return 
	 * <li> returns further descriptive content about an individual tour product along with departures, further pricing and availability data
	 */
	@PayloadRoot(localPart = "TourDetailsFull", namespace = TARGET_NAMESPACE)
	public @ResponsePayload TourDetailsFullResponse getTourDetailsFull(@RequestPayload TourDetailsFull request)  {
		return service.getTourDetailsFull(request);
	}
}
