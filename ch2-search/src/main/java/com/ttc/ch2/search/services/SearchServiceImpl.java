package com.ttc.ch2.search.services;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;








import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.count.CountRequestBuilder;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelcorp.ccapi.ArrayOfSearchTourResultsItem;
import com.travelcorp.ccapi.ArrayOfString;
import com.travelcorp.ccapi.Header;
import com.travelcorp.ccapi.SearchTourResults;
import com.travelcorp.ccapi.SearchTourResultsItem;
import com.ttc.ch2.api.ccapi.v3.ContinentAndCountries;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedRequest;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedResponse;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesResponse;
import com.ttc.ch2.api.ccapi.v3.SearchAggregatedResults;
import com.ttc.ch2.api.ccapi.v3.SearchAggregatedSubResults;
import com.ttc.ch2.api.ccapi.v3.SearchResults;
import com.ttc.ch2.api.ccapi.v3.SearchResultsExtended;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedRequest;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedResponse;
import com.ttc.ch2.api.ccapi.v3.SearchToursBaseRequest;
import com.ttc.ch2.api.ccapi.v3.Status;
import com.ttc.ch2.api.ccapi.v3.TourCategories;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.RoomTypeCodeType;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Highlights;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.schema.mapper.TourInfoMapper;
import com.ttc.ch2.search.export.IndexSynchronizerService;
import com.ttc.util.ext.EmptyPropertyChecker;
import com.ttc.util.ext.NotBlankPropertyChecker;
import com.ttc.util.ext.NullPropertyChecker;
import com.ttc.util.ext.V3MT;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.Severity;
import com.ttc.util.validation.AllPassValidator;
import com.ttc.util.validation.Checker;
import com.ttc.util.validation.Validator;
import com.ttc.util.ws.MessagesUtil;

@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    private static final Logger activityLogger = LoggerFactory.getLogger("ch2.activity.SearchService");

	private static final String HIGHLIGHTS_TO_XML_TRANSFORM = "/com/ttc/ch2/api/ccapi/v3/HighlightsToXml.xsl";
	private static final String NO_RECORDS_FOUND = "No records found";
	private static final String PATH_TOUR_INFO_CATEGORYNESTED = "TourInfoSimp.SellingCompanies.SellingCompany.TourCategories.TourCategory";
	private static final String PATH_TOUR_INFO_CATEGORY = "TourInfoSimp.SellingCompanies.SellingCompany.TourCategories.TourCategory.CategoryValue";
	private static final String PATH_TOUR_INFO_CATEGORYNAME = "TourInfoSimp.SellingCompanies.SellingCompany.TourCategories.TourCategory.Name";
	private static final String PATH_TOUR_INFO_COUNTRY_CONTINENT = "TourInfoSimp.CountriesVisited.Country.ContinentCode";
	private static final String PATH_TOUR_INFO_COUNTRY_NESTED = "TourInfoSimp.CountriesVisited.Country";
	private static final String PATH_TOUR_INFO_COUNTRY = "TourInfoSimp.CountriesVisited.Country.Code";
	private static final String PATH_TOUR_INFO_COUNTRY_NAME = "TourInfoSimp.CountriesVisited.Country.Name";
	private static final String PATH_TOUR_INFO_SELLING_COMPANY = "TourInfoSimp.SellingCompanies.SellingCompany.Code";
	private static final String ST_PATH_PRICE = "TourDeparturesSimp.SellingCompany.Departures.Departure.TourRules.Rooms.Room.Price.Adult.Combined";
	private static final String ST_PATH_NESTED_ROOM = "TourDeparturesSimp.SellingCompany.Departures.Departure.TourRules.Rooms.Room";
	private static final String ST_PATH_NESTED_DEPARTURE = "TourDeparturesSimp.SellingCompany.Departures.Departure";
	private static final String ST_PATH_CONTINENT_CODE = "TourInfoSimp.ContinentsVisited.Continent.Code";
	private static final String ST_PATH_COUNTRY_CODE = "TourInfoSimp.CountriesVisited.Country.Code";
	private static final String ST_PATH_DURATION = "TourInfoSimp.Duration";
	private static final String ST_PATH_TOUR_NAME = "TourInfoSimp.TourName";
	private static final String ST_PATH_MONTH = "TourDeparturesSimp.SellingCompany.Departures.Departure.Month";
	private static final String ST_PATH_ROOM_TYPE = "TourDeparturesSimp.SellingCompany.Departures.Departure.TourRules.Rooms.Room.Type";
	private static final String ST_PATH_SELLING_COMPANY_CODE = "TourDeparturesSimp.SellingCompany.Code";
	private static final String ST_PATH_AVAILABILITY_STATUS = "TourDeparturesSimp.SellingCompany.Departures.Departure.AvailabilityStatus";
	private static final String ST_PATH_STARTDATETIME = "TourDeparturesSimp.SellingCompany.Departures.Departure.StartDateTime";
	private static final String ST_PATH_BRANDCODE = "TourInfoSimp.BrandCode";
	private static final String DEP_NOT_CANCELED = "NOT Canceled";
	private static final String DEP_CANCELED = "Canceled";  
	private static final String SUBSET_EMPTY_V1 = "1-0";
	private static final String SUBSET_EMPTY_V3 = "empty";
	private static final String ST_CAT_TOURCODE = "TourInfoSimp.CataloguedTour.Code";
	private static final String STA_CAT_TOURCODE = "CataloguedTourCode";
	private static final String EARLY_PAYMENT_DISCOUNT = "Early Payment Discount";
	private static final String STA_PREFIX = "ToursList.";
	private static final String STA_DEF_ORDER_DIR = "ASC";
	private static final String SEARCH_TOURS_REQUEST_DATA_DELIMITER = ",";
	private static final String SEARCH_TOURS_REQUEST_DATA_OR_DELIMITER_V3 = " ";
	private static final String KEYWORD_AND_PHRASES_SPLITTER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
	private static final String SEARCH_TOURS_REQUEST_DURATION_DELIMITER = "-";
	private static final String ERROR_SEARCH_TOURS_INVALID_PARAM_CONTINENT = "input validation: continent: '%s' - pattern '[a-z,A-Z]*' expected";
	private static final String ERROR_SEARCH_TOURS_INVALID_PARAM_COUNTRY = "input validation: country: '%s' - pattern '[a-z,A-Z]*' expected";
	private static final String ERROR_SEARCH_TOURS_INVALID_PARAM_DURATION = "input validation: duration: '%s' - pattern '(\\d*)-(\\d*)' expected";
	private static final String ERROR_SEARCH_TOURS_INVALID_PARAM_MONTHS = "input validation: months: '%s' - numbers expected";
	private static final String ERROR_SEARCH_TOURS_INVALID_PARAM_FIRST_RECORD = "input validation: firstRecordNumber: %s - expected '>0'";
	private static final String ERROR_SEARCH_TOURS_INVALID_PARAM_ORDER_BY = "input validation: orderBy: '%s' - expected: [null|1|2|3]";
	private static final String ERROR_SEARCH_TOURS_INVALID_PARAM_ORDER_DIRECTION = "input validation: orderDirection: '%s' - expected: [null|ASC|DESC]";
	private static final String ERROR_SEARCH_TOURS_NO_PARAM_SELLING_COMPANY = "Please give at least one sellingCompany parameter";
	private static final String[] ROOM_ORDER= { "Twin", "TwinShare", "Triple", "TripleShare", "Quad", "QuadShare", "Single" };
	private static final String AGG_NAME_TOURCAT = "AggTourCategories";
	private static final String SUBAGG_NAME_TOURCAT = "AggTourCategoryValue";
	private static final String NESTAGG_NAME_TOURCAT = "AggTourCatNested";
	private static final String NESTAGG_NAME_CONTINENT = "AggContinentCountryNest";
	private static final String FILTERAGG_NAME_CONTINENT = "AggContinentCountryFilter";
	private static final String AGG_NAME_CONTINENT = "AggContinent";
	private static final String SUBAGG_NAME_COUNTRY = "SubAggCountry";
	private static final String ST_PATH_KEYWORD_AND_PHRASES_LIST[] = {"TourInfoSimp.TourCode", "TourInfoSimp.TourName" ,
		"TourInfoSimp.CataloguedTour.Code", "TourDeparturesSimp.OperatingProductCode",
		"TourInfoSimp.SellingCompanies.SellingCompany.Brochure.Code", "TourInfoSimp.SellingCompanies.SellingCompany.Brochure.Name",
		"TourInfoSimp.Description", "TourInfoSimp.LocationsVisited.Location.Name","TourInfoSimp.LocationsVisited.Location.CountryCode",
		"TourInfoSimp.ContinentsVisited.Continent.Name", "TourInfoSimp.ContinentsVisited.Continent.Code",
		"TourInfoSimp.Itinerary.ItinerarySegment.Text", "TourInfoSimp.Itinerary.ItinerarySegment.Title",
		"TourInfoSimp.WhatsIncluded.Section.Title", "TourInfoSimp.WhatsIncluded.Section.Text",
		"TourInfoSimp.Highlights.Section.Text", "TourInfoSimp.SellingCompanies.SellingCompany.MarketingFlags.KeywordsPhrases.Text",
		};
		//"TourInfoSimp.SellingCompanies.SellingCompany.TourCategories.TourCategory.CategoryValue"};
	
	
	private static final Pattern PATTERN_CONTINENT_AND_COUNTRY = Pattern.compile("[a-z,A-Z]*");
	private static final Pattern PATTERN_DURATION = Pattern.compile("(\\d*)-(\\d*)");

	private static final int MAX_RESULT_SIZE = 0x100000; // The potential maximum allowed number is 2147483391 but it causes out of memory in our case;

	private static final Map<String, String> pricePerRoomMap = new HashMap<String,String>() {
		private static final long serialVersionUID = 2389502595246860569L;
		{
			put(ROOM_ORDER[0].toUpperCase(),"TourDeparturesSimp.SellingCompany.Departures.PricesRange.TwinRoom.PriceFrom");
			put(ROOM_ORDER[1].toUpperCase(),"TourDeparturesSimp.SellingCompany.Departures.PricesRange.TwinShareRoom.PriceFrom");
			put(ROOM_ORDER[2].toUpperCase(),"TourDeparturesSimp.SellingCompany.Departures.PricesRange.TripleRoom.PriceFrom");
			put(ROOM_ORDER[3].toUpperCase(),"TourDeparturesSimp.SellingCompany.Departures.PricesRange.TripleShareRoom.PriceFrom");
			put(ROOM_ORDER[4].toUpperCase(),"TourDeparturesSimp.SellingCompany.Departures.PricesRange.QuadRoom.PriceFrom");
			put(ROOM_ORDER[5].toUpperCase(),"TourDeparturesSimp.SellingCompany.Departures.PricesRange.QuadShareRoom.PriceFrom");
			put(ROOM_ORDER[6].toUpperCase(),"TourDeparturesSimp.SellingCompany.Departures.PricesRange.SingleRoom.PriceFrom");
		}
	};

	private static final Map<String, String> orderByMap = new HashMap<String, String>() {

		private static final long serialVersionUID = 1389502595246860569L;

		{
			put("1", ST_PATH_PRICE);
			put("2", ST_PATH_TOUR_NAME);
			put("3", ST_PATH_DURATION);
		}
	};

	private Transformer transformerHighlightsV1;
	private JAXBContext jcHLv1;
	
	@Inject
	private Node node;

    private Validator<Object> validator;
    {
        List<Checker<Object>> list = new ArrayList<>();
        
        NullPropertyChecker checker = new NullPropertyChecker();
        checker.setPropertyName("");
        checker.setSubjectName("request");
        list.add(checker);
        
        NullPropertyChecker checker1 = new NotBlankPropertyChecker();
        checker1.setPrerequisite(checker);
        checker1.setPropertyName("sellingCompany");
        checker1.setSubjectName("sellingCompanyName");
        list.add(checker1);
        
        validator = new AllPassValidator<Object>(list);    
    }

    
    private Validator<Object> continentsCountriesValidator;
    {
        List<Checker<Object>> list = new ArrayList<>();
        
        NullPropertyChecker checker = new NullPropertyChecker();
        checker.setPropertyName("");
        checker.setSubjectName("request");
        list.add(checker);
        
        NullPropertyChecker checker1 = new EmptyPropertyChecker();
        checker1.setPrerequisite(checker);
        checker1.setPropertyName("sellingCompanies");
        checker1.setSubjectName("sellingCompanies");
        list.add(checker1);
        
        continentsCountriesValidator = new AllPassValidator<>(list);
    }
    
    private Validator<Object> searchToursAggregatedRequestValidator;
    {
        List<Checker<Object>> list = new ArrayList<>();
        
        NullPropertyChecker checker = new NullPropertyChecker();
        checker.setPropertyName("");
        checker.setSubjectName("request");
        list.add(checker);
        
        NullPropertyChecker checker1 = new EmptyPropertyChecker();
        checker1.setPrerequisite(checker);
        checker1.setPropertyName("sellingCompanies");
        checker1.setSubjectName("sellingCompanies");
        list.add(checker1);
        
        
        NullPropertyChecker recordsNUmberChecker = new NullPropertyChecker(){
            Long number(Object property) {
                if (property instanceof Number) {
                    Number number = (Number) property;
                    return number.longValue();
                }
                String string = ObjectUtils.toString(property);
                if (NumberUtils.isNumber(string)) {
                    return NumberUtils.toLong(string);
                }
                return null;
            }
            @Override
            protected void check(Collection<Message> messages, Object property) {
                Long number = number(property);
                if (number == null || !(1L <= number && number <= 100L)) {
                    Message message = MessageBuilder.newMessage(V3MT.INVALID_NUMBER_RANGE).withNameOnlySubject(getSubjectName()).build();
                    messages.add(message);
                }
                
                super.check(messages, property);
            }
        };
        recordsNUmberChecker.setPrerequisite(checker);
        recordsNUmberChecker.setPropertyName("numberOfRecords");
        recordsNUmberChecker.setSubjectName("numberOfRecords");
        list.add(recordsNUmberChecker);

        NullPropertyChecker firstRecordChecker = new NullPropertyChecker(){
            Long number(Object property) {
                if (property instanceof Number) {
                    Number number = (Number) property;
                    return number.longValue();
                }
                String string = ObjectUtils.toString(property);
                if (NumberUtils.isNumber(string)) {
                    return NumberUtils.toLong(string);
                }
                return null;
            }
            @Override
            protected void check(Collection<Message> messages, Object property) {
                Long number = number(property);
                if (number == null || number < 1L) {
                    Message message =  MessageBuilder.newMessage(V3MT.INVALID_NUMBER).withNameOnlySubject(getSubjectName()).withNameOnlySubject(" >= 1").build();
                    messages.add(message);
                }
                
                super.check(messages, property);
            }
        };
        firstRecordChecker.setPrerequisite(checker);
        firstRecordChecker.setPropertyName("firstRecordNumber");
        firstRecordChecker.setSubjectName("first record number");
        
        list.add(firstRecordChecker);
        
        searchToursAggregatedRequestValidator = new AllPassValidator<Object>(list);    
    }
    
	@PostConstruct
	public void init() throws JAXBException, TransformerConfigurationException, TransformerFactoryConfigurationError {

		transformerHighlightsV1 = TransformerFactory.newInstance().newTransformer(new StreamSource(getClass().getResourceAsStream(HIGHLIGHTS_TO_XML_TRANSFORM)));
		transformerHighlightsV1.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
		transformerHighlightsV1.setOutputProperty(OutputKeys.METHOD,"xml");
		jcHLv1 = JAXBContext.newInstance(new Class[] { Highlights.class });

		

        
	}

	@Override
	public GetTourCategoriesResponse getTourCategories(GetTourCategoriesRequest request) {
	    
	    GetTourCategoriesResponse response = new GetTourCategoriesResponse();
	    Collection<Message> messages = validate(request);
	    
	    MessagesUtil.assignContext(response, messages);
	    if (MessagesUtil.severity(messages) == Severity.ERROR) {    
	        response.setSuccessful(false);
	        return response;
	    }
	    
	    try {
	        SearchRequestBuilder srb  = node.client().prepareSearch(IndexSynchronizerService.ES_INDEX_NAME).setTypes(IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE)
	                .setQuery(QueryBuilders.queryString(request.getSellingCompany()).field(ST_PATH_SELLING_COMPANY_CODE))
                .addAggregation(AggregationBuilders.nested(NESTAGG_NAME_TOURCAT).path(PATH_TOUR_INFO_CATEGORYNESTED).subAggregation(AggregationBuilders.terms(AGG_NAME_TOURCAT).field(PATH_TOUR_INFO_CATEGORYNAME).size(14000).order(Order.term(true)).
                		subAggregation(AggregationBuilders.terms(SUBAGG_NAME_TOURCAT).field(PATH_TOUR_INFO_CATEGORY).size(14000).order(Order.term(true)))));
	        
	        
	        logger.trace(srb.toString());
	        SearchResponse esresponse = srb.setSize(0).execute().actionGet();
	        logger.trace(esresponse.toString());
	        
	        InternalNested in = esresponse.getAggregations().get(NESTAGG_NAME_TOURCAT);
	        Terms terms = in.getAggregations().get(AGG_NAME_TOURCAT);
	        
	        for(Bucket bucket: terms.getBuckets()) {
	            TourCategories tourCategories = new TourCategories();
	            tourCategories.setTourCategory(bucket.getKey());
	            List<String> categoryValues = new ArrayList<String>();
	            Terms  subTerms = bucket.getAggregations().get(SUBAGG_NAME_TOURCAT);
	            for(Bucket subBucket: subTerms.getBuckets() ) {
	                categoryValues.add(subBucket.getKey());
	            }
	            tourCategories.getCategoryValue().addAll(categoryValues);
	            response.getTourCategories().add(tourCategories);
	        }
	        MessagesUtil.assignContext(response, messages);

            activityLogger.info("USER: {}  called getTourCategories() for selling company: {} ", SecurityHelper.getLoginSilent(), request.getSellingCompany());
	    } catch (Exception e) {
	        logger.error("", e);
	        Message message = MessageBuilder.newMessage(V3MT. SYSTEM_ERROR)
	                .withSubject("Exception", e.getMessage())
	                .build();
	        MessagesUtil.assignContext(response, message);
	    }
	    
	    
	    return response;
	}

	protected Collection<Message> validate(GetTourCategoriesRequest request) {
	    Collection<Message> messages = validator.validate(request);
	    return messages;
	}
	
	protected Collection<Message> validate(SearchToursBaseRequest request) {
	    Collection<Message> messages = searchToursAggregatedRequestValidator.validate(request);
	    return messages;
	}

	protected Collection<Message> validate(GetContinentsAndCountriesVisitedRequest request) {
	    Collection<Message> messages = continentsCountriesValidator.validate(request);
	    return messages;
	}
	
	@Override
	public GetContinentsAndCountriesVisitedResponse getContinentsAndCountriesVisited(GetContinentsAndCountriesVisitedRequest param) {
	    GetContinentsAndCountriesVisitedResponse response = new GetContinentsAndCountriesVisitedResponse();
	    Collection<Message> messages = validate(param);

	    MessagesUtil.assignSoapMessageContext(response, messages);
	    
        if (MessagesUtil.severity(messages) == Severity.ERROR) {
//            MessageContext msgCtx = new MessageContext();
//            MessagesUtil.append(msgCtx, messages);
            response.setSuccessful(false);
            return response;
        }
		try {
            List<FilterBuilder> filterList = new ArrayList<FilterBuilder>();

            if (param.getSellingCompanies() != null && param.getSellingCompanies().size() > 0) {
            	for (String sellingCompanyCode : param.getSellingCompanies()) {
            		FilterBuilder filter = 	FilterBuilders.queryFilter(QueryBuilders.queryString(sellingCompanyCode).field(PATH_TOUR_INFO_SELLING_COMPANY));
            		filterList.add(filter);
            	}
            }

            FilterBuilder filterMother = FilterBuilders.orFilter(filterList.toArray(new FilterBuilder[filterList.size()]));

            SearchRequestBuilder srb = null;
            if (StringUtils.isNotBlank((String) param.getContinent())) {
            	FilterBuilder continentFilter = FilterBuilders.queryFilter(QueryBuilders.queryString((String) param.getContinent()).field(PATH_TOUR_INFO_COUNTRY_CONTINENT)); 
            	FilterBuilder filter = FilterBuilders.nestedFilter(PATH_TOUR_INFO_COUNTRY_NESTED, continentFilter);
            	filterMother = FilterBuilders.andFilter(filterMother, filter);
            	srb  = node.client().prepareSearch(IndexSynchronizerService.ES_INDEX_NAME).setTypes(IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE)
                        .setQuery(QueryBuilders.filteredQuery(QueryBuilders.matchAllQuery(), filterMother))    
            				.addAggregation(AggregationBuilders.nested(NESTAGG_NAME_CONTINENT).path(PATH_TOUR_INFO_COUNTRY_NESTED).
            					subAggregation(AggregationBuilders.filter(FILTERAGG_NAME_CONTINENT).filter(continentFilter).
								subAggregation(AggregationBuilders.terms(AGG_NAME_CONTINENT).field(PATH_TOUR_INFO_COUNTRY_CONTINENT).size(14000).order(Order.term(true)).
									subAggregation(AggregationBuilders.terms(SUBAGG_NAME_COUNTRY).field(PATH_TOUR_INFO_COUNTRY).size(14000).order(Order.term(true))))));
            }
            else {
            	srb  = node.client().prepareSearch(IndexSynchronizerService.ES_INDEX_NAME).setTypes(IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE)
                        .setQuery(QueryBuilders.filteredQuery(QueryBuilders.matchAllQuery(), filterMother))    
            				.addAggregation(AggregationBuilders.nested(NESTAGG_NAME_CONTINENT).path(PATH_TOUR_INFO_COUNTRY_NESTED).
		                	subAggregation(AggregationBuilders.terms(AGG_NAME_CONTINENT).field(PATH_TOUR_INFO_COUNTRY_CONTINENT).size(14000).order(Order.term(true)).
		                		subAggregation(AggregationBuilders.terms(SUBAGG_NAME_COUNTRY).field(PATH_TOUR_INFO_COUNTRY).size(14000).order(Order.term(true)))));
            	
            	
            }
            
            logger.trace(srb.toString());
            SearchResponse esresponse = srb.setSize(0).execute().actionGet();
            logger.trace(esresponse.toString());
            
            Terms terms = null;
            if (StringUtils.isNotBlank((String) param.getContinent())) {
            	InternalNested in = esresponse.getAggregations().get(NESTAGG_NAME_CONTINENT);
            	InternalFilter ifi = in.getAggregations().get(FILTERAGG_NAME_CONTINENT);
            	terms = ifi.getAggregations().get(AGG_NAME_CONTINENT);
            }
            else {
            	InternalNested in = esresponse.getAggregations().get(NESTAGG_NAME_CONTINENT);
            	terms = in.getAggregations().get(AGG_NAME_CONTINENT);
            }
            
            

            for(Bucket bucket: terms.getBuckets()) {
            	ContinentAndCountries continentAndCountries = new ContinentAndCountries();
            	continentAndCountries.setContinent(bucket.getKey());
            	List<String> countryCodes = new ArrayList<String>();
            	Terms  subTerms = bucket.getAggregations().get(SUBAGG_NAME_COUNTRY);
            	for(Bucket subBucket: subTerms.getBuckets() ) {
            		countryCodes.add(subBucket.getKey());
            	}
            	continentAndCountries.getCountries().addAll(countryCodes);
            	response.getContinentsAndCountries().add(continentAndCountries);
            }
            
            MessagesUtil.assignSoapMessageContext(response, messages);
            activityLogger.info("USER: {}  called getContinentsAndCountriesVisited ()", SecurityHelper.getLoginSilent());
        } catch (Exception e) {
            logger.error("",  e);
            Message message = MessageBuilder.newMessage(V3MT. SYSTEM_ERROR)
                    .withSubject("Exception", e.getMessage())
                    .build();
            
            MessagesUtil.assignSoapMessageContext(response, message);
        }

        response.setSuccessful(MessagesUtil.severity(messages) != Severity.ERROR);
        
		return response;
		
	}

	@Override
	public com.travelcorp.ccapi.SearchToursResponse searchTours(com.travelcorp.ccapi.SearchTours param) throws SearchServiceException, JsonProcessingException, IOException, JAXBException, TransformerException, DatatypeConfigurationException {

		validateSearchToursV1Parameters(param);

		com.ttc.ch2.api.ccapi.v3.SearchToursRequest paramV3 = new com.ttc.ch2.api.ccapi.v3.SearchToursRequest();
		paramV3.setContinentCodes(StringUtils.replace(param.getContinent(), SEARCH_TOURS_REQUEST_DATA_DELIMITER, SEARCH_TOURS_REQUEST_DATA_OR_DELIMITER_V3));
		paramV3.setCountryCodes(StringUtils.replace(param.getCountry(), SEARCH_TOURS_REQUEST_DATA_DELIMITER, SEARCH_TOURS_REQUEST_DATA_OR_DELIMITER_V3));
		paramV3.setFirstRecordNumber(BigInteger.valueOf(param.getFirstRecordNumber()));
		paramV3.setMonths(StringUtils.replace(param.getMonths(), SEARCH_TOURS_REQUEST_DATA_DELIMITER, SEARCH_TOURS_REQUEST_DATA_OR_DELIMITER_V3));
		paramV3.setNumberOfRecords(param.getNumberOfRecords() == null ? BigInteger.ZERO : BigInteger.valueOf(param.getNumberOfRecords()));
		paramV3.setOrderBy(StringUtils.isNotBlank(param.getOrderBy()) ? param.getOrderBy() : "1");
		paramV3.setOrderDirection(StringUtils.isNotBlank(param.getOrderDirection()) ? param.getOrderDirection() : STA_DEF_ORDER_DIR);
		paramV3.getSellingCompanies().addAll(param.getSellingCompanyCodes().getString());

		if (param.getKeywords() != null && param.getKeywords().getString() != null && param.getKeywords().getString().size() > 0) {
			paramV3.getKeywordsAndPhrases().addAll(param.getKeywords().getString());
		}

		if (param.getDuration() != null) {
			String durationFrom = StringUtils.substringBefore(param.getDuration(), SEARCH_TOURS_REQUEST_DURATION_DELIMITER);
			String durationTo = StringUtils.substringAfter(param.getDuration(), SEARCH_TOURS_REQUEST_DURATION_DELIMITER);
			if (StringUtils.isNotBlank(durationFrom)) { paramV3.setDurationFrom(BigInteger.valueOf(Long.parseLong(durationFrom))); };
			if (StringUtils.isNotBlank(durationTo)) { paramV3.setDurationTo(BigInteger.valueOf(Long.parseLong(durationTo))); };
		}

		com.ttc.ch2.api.ccapi.v3.SearchToursResponse resultV3 = searchTours(paramV3, true);

		Header header = new Header();
		header.setNumberOfRecords(resultV3.getNumberOfRecords() == null ? 0 : resultV3.getNumberOfRecords().intValue());
		header.setSearchResultsTotalRecords(resultV3.getTotalRecords() == null ? 0 : resultV3.getTotalRecords().intValue());
		header.setSuccessful(resultV3.isSuccessful());

		if (resultV3.isSuccessful()) {

			header.setOrderBy(paramV3.getOrderBy());
			header.setOrderDirection(paramV3.getOrderDirection());
			header.setSubsetReturned(resultV3.getSubsetReturned() == null || SUBSET_EMPTY_V3.equals(resultV3.getSubsetReturned()) ? SUBSET_EMPTY_V1 : resultV3.getSubsetReturned());	
		}

		if (resultV3.getMessageContext() != null && Status.FAILURE.equals(resultV3.getMessageContext().getStatus())) {

			ArrayOfString errorMessages = new ArrayOfString();

			for (com.ttc.ch2.api.ccapi.v3.Message message : resultV3.getMessageContext().getMessage()) {
				errorMessages.getString().add(message.getInterpolatedMessage());
			}

			header.setErrorMessaages(errorMessages);
		}

		ArrayOfSearchTourResultsItem tourItemArray = new ArrayOfSearchTourResultsItem();

		for (SearchResults tourItemSrc : resultV3.getSearchResults()) {

			SearchTourResultsItem tourItemDst = new SearchTourResultsItem();

			tourItemDst.setBrochureCode(tourItemSrc.getBrochureCode());
			tourItemDst.setDuration(tourItemSrc.getDuration().intValue());
			tourItemDst.setMVCode(tourItemSrc.getTourCode());
			tourItemDst.setPrice(BigDecimal.valueOf(tourItemSrc.getPriceFrom()).setScale(2));
			tourItemDst.setSellingCompanyCode(tourItemSrc.getSellingCompanyCode());
			tourItemDst.setTourName(tourItemSrc.getTourName());
			tourItemDst.setSellable(((SearchResultsExtended) tourItemSrc).isOnlineBookable());

			if (tourItemSrc.getHighlights() != null) {

				JAXBSource source = new JAXBSource(jcHLv1, tourItemSrc.getHighlights());
				StreamResult result = new StreamResult(new StringWriter());

				transformerHighlightsV1.transform(source, result);
				
				tourItemDst.setHighlights(result.getWriter().toString());
			}

			tourItemArray.getSearchTourResultsItem().add(tourItemDst);
		}

		SearchTourResults searchTourResults = new SearchTourResults();
		searchTourResults.setHeader(header);
		searchTourResults.setResults(tourItemArray);

		com.travelcorp.ccapi.SearchToursResponse result = new com.travelcorp.ccapi.SearchToursResponse();
		result.setSearchToursResult(searchTourResults);

		activityLogger.info("USER: {}  called searchTour (V1)", SecurityHelper.getLoginSilent());

		return result;
	}

	@Override
	public com.ttc.ch2.api.ccapi.v3.SearchToursResponse searchTours(com.ttc.ch2.api.ccapi.v3.SearchToursRequest param) {
		return searchTours(param, false);
	}

	private com.ttc.ch2.api.ccapi.v3.SearchToursResponse searchTours(com.ttc.ch2.api.ccapi.v3.SearchToursRequest param, boolean lockValidation) {

	    com.ttc.ch2.api.ccapi.v3.SearchToursResponse result =  new com.ttc.ch2.api.ccapi.v3.SearchToursResponse();

		Collection<Message> messages = lockValidation ? new ArrayList<Message>() : validate(param);
	    MessagesUtil.assignContext(result, messages);
	    if (MessagesUtil.severity(messages) == Severity.ERROR) {   
	        result.setSuccessful(false);
	        return result;
	    }
	    
        try {

            SearchResponse response = null;
            if(!StringUtils.isNotBlank(param.getPreferedRoomType())) {
            	for(String rt: ROOM_ORDER) {
            		param.setPreferedRoomType(rt);
            		response = searchToursPrepareAndExec(param);
            		if(response.getHits().getTotalHits() > 0l)
            			break;
            	}
            }
            else {
            	response = searchToursPrepareAndExec(param);
            }
            
            result = searchToursMapResponseToResult(response, param);
            MessagesUtil.assignContext(result, messages);

            activityLogger.info("USER: {}  called searchTour (V3)", SecurityHelper.getLoginSilent());
        } catch (Exception e) {
            logger.error("", e);
            Message message = MessageBuilder.newMessage(V3MT. SYSTEM_ERROR)
                    .withSubject("Exception", e.getMessage())
                    .build();
            
            MessagesUtil.assignContext(result, message);
        }
        
        return result;
	}

	@Override
	public SearchToursAggregatedResponse searchToursAggregated(SearchToursAggregatedRequest param) {
	    SearchToursAggregatedResponse result =  new SearchToursAggregatedResponse();

	    Collection<Message> messages = validate(param);
	    MessagesUtil.assignContext(result, messages);
		if (MessagesUtil.severity(messages) == Severity.ERROR) {
		    result.setSuccessful(false);
		    return result;
		}
		
        try {
            
            SearchResponse response = null;
            if(!StringUtils.isNotBlank(param.getPreferedRoomType())) {
            	for(String rt: ROOM_ORDER) {
            		param.setPreferedRoomType(rt);
            		response = searchToursAggregatedPrepareAndExec(param);
            		if(response.getHits().getTotalHits() > 0l)
            			break;
            	}
            }
            else {
            	response = searchToursAggregatedPrepareAndExec(param);
            }
            result = searchToursAggregatedMapResponseToResult(response,param);
            MessagesUtil.assignContext(result, messages);

            activityLogger.info("USER: {}  called searchToursAggregated(V3)", SecurityHelper.getLoginSilent());
        } catch (Exception e) {
            logger.error("", e);
            Message message = MessageBuilder.newMessage(V3MT. SYSTEM_ERROR)
                    .withSubject("Exception", e.getMessage())
                    .build();
            
            MessagesUtil.assignContext(result, message);
        }
        
        return result;
	}


	private FilterBuilder prepareFilters(SearchToursBaseRequest param, String prefix, List<String> cataloguedTourCodeList) {	
		List<FilterBuilder> filterList = new ArrayList<FilterBuilder>();
		List<FilterBuilder> filterDeparturesList = new ArrayList<FilterBuilder>();
		
		if(StringUtils.isNotBlank(param.getPreferedRoomType())) {
			if(param.getPriceFrom() != null && param.getPriceTo() != null) {
				FilterBuilder f = FilterBuilders.rangeFilter(prefix + ST_PATH_PRICE)
						.from(param.getPriceFrom())
						.to(param.getPriceTo())
						.includeLower(true)
					    .includeUpper(true);
				FilterBuilder fp = FilterBuilders.queryFilter(QueryBuilders.queryString(param.getPreferedRoomType()).field(prefix + ST_PATH_ROOM_TYPE));
				filterDeparturesList.add(FilterBuilders.nestedFilter(prefix +ST_PATH_NESTED_ROOM, FilterBuilders.andFilter(f,fp)));

			}
			else if(param.getPriceFrom() != null) {
				FilterBuilder f = FilterBuilders.rangeFilter(prefix + ST_PATH_PRICE)
						.gt(param.getPriceFrom())
						.includeLower(true);
				FilterBuilder fp = FilterBuilders.queryFilter(QueryBuilders.queryString(param.getPreferedRoomType()).field(prefix + ST_PATH_ROOM_TYPE));
				filterDeparturesList.add(FilterBuilders.nestedFilter(prefix +ST_PATH_NESTED_ROOM,FilterBuilders.andFilter(f,fp)));
			}
			else if(param.getPriceTo() != null) {
				FilterBuilder f = FilterBuilders.rangeFilter(prefix + ST_PATH_PRICE)
						.lt(param.getPriceTo())
						.includeUpper(true);
				FilterBuilder fp = FilterBuilders.queryFilter(QueryBuilders.queryString(param.getPreferedRoomType()).field(prefix + ST_PATH_ROOM_TYPE));
				filterDeparturesList.add(FilterBuilders.nestedFilter(prefix +ST_PATH_NESTED_ROOM,FilterBuilders.andFilter(f,fp)));
			}else {
				FilterBuilder fp = FilterBuilders.queryFilter(QueryBuilders.queryString(param.getPreferedRoomType()).field(prefix + ST_PATH_ROOM_TYPE));
				filterDeparturesList.add(FilterBuilders.nestedFilter(prefix +ST_PATH_NESTED_ROOM,fp));
			}
		}
		else {
			if(param.getPriceFrom() != null && param.getPriceTo() != null) {
				FilterBuilder f = FilterBuilders.rangeFilter(prefix + ST_PATH_PRICE)
						.from(param.getPriceFrom())
						.to(param.getPriceTo())
						.includeLower(true)
					    .includeUpper(true);
				filterDeparturesList.add(FilterBuilders.nestedFilter(prefix +ST_PATH_NESTED_ROOM,f));

			}
			else if(param.getPriceFrom() != null) {
				FilterBuilder f = FilterBuilders.rangeFilter(prefix + ST_PATH_PRICE)
						.gt(param.getPriceFrom())
						.includeLower(true);
				filterDeparturesList.add(FilterBuilders.nestedFilter(prefix +ST_PATH_NESTED_ROOM,f));
			}
			else if(param.getPriceTo() != null) {
				FilterBuilder f = FilterBuilders.rangeFilter(prefix + ST_PATH_PRICE)
						.lt(param.getPriceTo())
						.includeUpper(true);
				filterDeparturesList.add(FilterBuilders.nestedFilter(prefix +ST_PATH_NESTED_ROOM,f));
			}
		}
		if (StringUtils.isNotBlank(param.getContinentCodes()) ) {
			String[] ccList = StringUtils.split(param.getContinentCodes(), SEARCH_TOURS_REQUEST_DATA_DELIMITER);
			for(String elem: ccList) {
				if(StringUtils.isNotBlank(elem)) {
					FilterBuilder f = FilterBuilders.queryFilter(QueryBuilders.queryString(elem).field(prefix + ST_PATH_CONTINENT_CODE));
					filterList.add(f);
				}
			}
		}

		if (StringUtils.isNotBlank(param.getCountryCodes())) {
			String[] ccList = StringUtils.split(param.getCountryCodes(), SEARCH_TOURS_REQUEST_DATA_DELIMITER);
			for(String elem: ccList) {
				if(StringUtils.isNotBlank(elem)){
					if(StringUtils.isNotBlank(prefix)) {
						FilterBuilder f = FilterBuilders.queryFilter(QueryBuilders.queryString(elem).field(prefix + ST_PATH_COUNTRY_CODE));
						filterList.add(f);
					}
					else {
						FilterBuilder f = FilterBuilders.nestedFilter(PATH_TOUR_INFO_COUNTRY_NESTED,
								FilterBuilders.queryFilter(QueryBuilders.queryString(elem).field(ST_PATH_COUNTRY_CODE)));
						filterList.add(f);
					}
				}
			}
		}
				
		if(param.getDurationFrom() != null && param.getDurationTo() != null) {
			FilterBuilder f = FilterBuilders.rangeFilter(prefix + ST_PATH_DURATION)
					.from(param.getDurationFrom())
					.to(param.getDurationTo())
					.includeLower(true)
					.includeUpper(true);
			filterList.add(f);

		}
		else if(param.getDurationFrom() != null) {
			FilterBuilder f = FilterBuilders.rangeFilter(prefix + ST_PATH_DURATION)
					.gt(param.getDurationFrom())
					.includeLower(true);
			filterList.add(f);
		}
		else if(param.getDurationTo() != null) {
			FilterBuilder f = FilterBuilders.rangeFilter(prefix + ST_PATH_DURATION)
					.lt(param.getDurationTo())
					.includeUpper(true);
			filterList.add(f);
		}
		
		if (StringUtils.isNotBlank(param.getMonths())) {
			String months[] = StringUtils.split(param.getMonths(), SEARCH_TOURS_REQUEST_DATA_DELIMITER);
			for (String month : months) {
				FilterBuilder f = FilterBuilders.queryFilter(QueryBuilders.queryString(month).field(prefix + ST_PATH_MONTH));
				filterDeparturesList.add(f);
			}
		}
		
		if(param.getKeywordsAndPhrases() != null && param.getKeywordsAndPhrases().size() > 0) {
			List<String> phrasesList = param.getKeywordsAndPhrases();
			for(String phrase: phrasesList) {
				String[] splitedElem = phrase.split(KEYWORD_AND_PHRASES_SPLITTER);
				for(String elem: splitedElem) {
					if(StringUtils.isNotBlank(elem)){
						
						QueryStringQueryBuilder keywordBuilder  =  QueryBuilders.queryString(elem);
						for(String str: ST_PATH_KEYWORD_AND_PHRASES_LIST) {
							keywordBuilder = keywordBuilder.field(prefix + str);
						}
						
						if(!STA_PREFIX.equals(prefix)) {
							FilterBuilder f1 = FilterBuilders.queryFilter(keywordBuilder);
							FilterBuilder f2 = FilterBuilders.nestedFilter(PATH_TOUR_INFO_CATEGORYNESTED,
									QueryBuilders.queryString(elem).field(PATH_TOUR_INFO_CATEGORY).field(PATH_TOUR_INFO_CATEGORYNAME));
							FilterBuilder f3 = FilterBuilders.nestedFilter(PATH_TOUR_INFO_COUNTRY_NESTED,QueryBuilders.queryString(elem).field(PATH_TOUR_INFO_COUNTRY_NAME));
							filterList.add(FilterBuilders.orFilter(f1,f2,f3));
						}
						else {
							filterList.add(FilterBuilders.queryFilter(keywordBuilder.field(prefix + PATH_TOUR_INFO_CATEGORY).field(prefix+PATH_TOUR_INFO_CATEGORYNAME).field(prefix + PATH_TOUR_INFO_COUNTRY_NAME)));
						}
						
					}
				}
			}
		}
			
		List<FilterBuilder> filterSClist = new ArrayList<FilterBuilder>();
		if(param.getSellingCompanies() != null && param.getSellingCompanies().size() > 0) {
			for(String term: param.getSellingCompanies()) {
				if(StringUtils.isNotBlank(term)) {
					FilterBuilder f = FilterBuilders.queryFilter(QueryBuilders.queryString(term).field(prefix + ST_PATH_SELLING_COMPANY_CODE));
					filterSClist.add(f);
				}
			}
		}
		
		List<FilterBuilder> filterCTClist = new ArrayList<FilterBuilder>();
		if(cataloguedTourCodeList != null && cataloguedTourCodeList.size() > 0) {
			for(String term: cataloguedTourCodeList) {
				if(StringUtils.isNotBlank(term)) {
					FilterBuilder f = FilterBuilders.queryFilter(QueryBuilders.queryString(term).field(ST_CAT_TOURCODE));
					filterCTClist.add(f);
				}
			}
		}
		
		filterDeparturesList.add(FilterBuilders.queryFilter(QueryBuilders.queryString(DEP_NOT_CANCELED).field(prefix + ST_PATH_AVAILABILITY_STATUS)));
		filterDeparturesList.add(FilterBuilders.rangeFilter(prefix + ST_PATH_STARTDATETIME).from(new Date()).includeLower(true));
		filterList.add(FilterBuilders.nestedFilter(prefix +ST_PATH_NESTED_DEPARTURE, FilterBuilders.andFilter(filterDeparturesList.toArray(new FilterBuilder[filterDeparturesList.size()]))));
		
		FilterBuilder sellingCompaniesFilter = FilterBuilders.orFilter(filterSClist.toArray(new FilterBuilder[filterSClist.size()]));
		FilterBuilder filterMother = FilterBuilders.andFilter(filterList.toArray(new FilterBuilder[filterList.size()]));
		if(filterCTClist.size() > 0) {
			FilterBuilder cataloguedTourCodeFilter = FilterBuilders.orFilter(filterCTClist.toArray(new FilterBuilder[filterCTClist.size()])); 
			return FilterBuilders.andFilter(filterMother, sellingCompaniesFilter, cataloguedTourCodeFilter);
		}
		if(STA_PREFIX.equals(prefix)) {
			return FilterBuilders.nestedFilter(prefix.replace(".", ""), FilterBuilders.andFilter(filterMother, sellingCompaniesFilter));
		}
		else {
			return FilterBuilders.andFilter(filterMother, sellingCompaniesFilter);
		}
	}

	private SearchResponse executeESQuery(SearchRequestBuilder srb, SearchToursBaseRequest param) throws SearchServiceException{
		if(StringUtils.isNotBlank(param.getOrderBy()) && StringUtils.isNotBlank(param.getOrderDirection())) {
			String orderByList[] = param.getOrderBy().split(",");
			String orderDirectionList[] = param.getOrderDirection().split(",");
			if(orderByList.length != orderDirectionList.length) {
				throw new SearchServiceException("orderBy and orderDirection do not have the same ammount of arguments");
			}
			boolean isPrefix=false;
			if(param.getOrderBy().startsWith(STA_CAT_TOURCODE)) {
				isPrefix=true;
			}
			for(int i=0; i < orderByList.length; i++) {
				String prefix="";
				if(isPrefix && !orderByList[i].startsWith(STA_CAT_TOURCODE)) {
					prefix=STA_PREFIX;
				}
				FieldSortBuilder fsb=null;
				if(orderByList[i].trim().equals("1")){
					fsb = SortBuilders.fieldSort(prefix + pricePerRoomMap.get(param.getPreferedRoomType().toUpperCase()))
							.order(SortOrder.valueOf(orderDirectionList[i].trim().toUpperCase()));
				}
				else {	
					fsb = SortBuilders.fieldSort(prefix + (orderByMap.containsKey(orderByList[i].trim()) ? orderByMap.get(orderByList[i].trim()) : orderByList[i].trim()))
						.order(SortOrder.valueOf(orderDirectionList[i].trim().toUpperCase()));
				}
				srb=srb.addSort(fsb);
			} 
		}
		logger.trace(srb.toString());
		SearchResponse response = srb.execute().actionGet();
		logger.trace(response.toString());
		
		return response;
	}

	private void validateSearchToursV1Parameters(com.travelcorp.ccapi.SearchTours param) throws SearchServiceException {

		if (StringUtils.isNotEmpty(param.getContinent())) {

			for (String continent : StringUtils.splitPreserveAllTokens(param.getContinent(), SEARCH_TOURS_REQUEST_DATA_DELIMITER)) {

				if (!PATTERN_CONTINENT_AND_COUNTRY.matcher(continent.trim()).matches()) {
					throw new SearchServiceException(String.format(ERROR_SEARCH_TOURS_INVALID_PARAM_CONTINENT, param.getContinent()));
				}
			}
		}

		if (StringUtils.isNotEmpty(param.getCountry())) {

			for (String country : StringUtils.splitPreserveAllTokens(param.getCountry(), SEARCH_TOURS_REQUEST_DATA_DELIMITER)) {

				if (!PATTERN_CONTINENT_AND_COUNTRY.matcher(country.trim()).matches()) {
					throw new SearchServiceException(String.format(ERROR_SEARCH_TOURS_INVALID_PARAM_COUNTRY, param.getCountry()));
				}
			}
		}

		if (StringUtils.isNotEmpty(param.getDuration()) && !PATTERN_DURATION.matcher(param.getDuration()).matches()) {
			throw new SearchServiceException(String.format(ERROR_SEARCH_TOURS_INVALID_PARAM_DURATION, param.getDuration()));
		}

		if (StringUtils.isNotEmpty(param.getMonths())) {

			for (String month : StringUtils.splitPreserveAllTokens(param.getMonths(), SEARCH_TOURS_REQUEST_DATA_DELIMITER)) {

				if (!NumberUtils.isNumber(month.trim())) {
					throw new SearchServiceException(String.format(ERROR_SEARCH_TOURS_INVALID_PARAM_MONTHS, month));
				}
			}
		}

		if (param.getFirstRecordNumber() == null || param.getFirstRecordNumber().longValue() < 1) {
			throw new SearchServiceException(String.format(ERROR_SEARCH_TOURS_INVALID_PARAM_FIRST_RECORD, param.getFirstRecordNumber() == null ? 0 : param.getFirstRecordNumber().longValue()));
		}

		if (StringUtils.isNotEmpty(param.getOrderBy()) && !Arrays.asList("1", "2", "3").contains(param.getOrderBy().trim())) {
			throw new SearchServiceException(String.format(ERROR_SEARCH_TOURS_INVALID_PARAM_ORDER_BY, param.getOrderBy()));
		}

		if (StringUtils.isNotEmpty(param.getOrderDirection()) && !Arrays.asList("ASC", "DESC").contains(param.getOrderDirection().trim())) {
			throw new SearchServiceException(String.format(ERROR_SEARCH_TOURS_INVALID_PARAM_ORDER_DIRECTION, param.getOrderDirection()));
		}

		if (param.getSellingCompanyCodes() == null || param.getSellingCompanyCodes().getString() == null || param.getSellingCompanyCodes().getString().size() == 0) {
			throw new SearchServiceException(ERROR_SEARCH_TOURS_NO_PARAM_SELLING_COMPANY);
		}
	}

	private SearchResponse searchToursPrepareAndExec(com.ttc.ch2.api.ccapi.v3.SearchToursRequest param) throws SearchServiceException {
		FilterBuilder rootFilter = prepareFilters(param, "", null);
		Client client = node.client();
		SearchRequestBuilder srb = client.prepareSearch(IndexSynchronizerService.ES_INDEX_NAME)
		        .setTypes(IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE)
		        .setQuery(QueryBuilders.matchAllQuery())
		        .setPostFilter(rootFilter)
		        .setFrom(param.getFirstRecordNumber().intValue()-1).setSize(param.getNumberOfRecords().intValue());
		if( StringUtils.isBlank(param.getOrderBy()) ) {
			param.setOrderBy("1");
			if(StringUtils.isBlank(param.getOrderDirection()) ) {
				param.setOrderDirection(STA_DEF_ORDER_DIR);
			}
		}
		else {
			int orderCount = param.getOrderBy().split(",").length;
			int directionCount = 0;
			if(StringUtils.isNotBlank(param.getOrderDirection()) ) {
				directionCount = param.getOrderDirection().split(",").length;
			}
			if(directionCount < orderCount) {
				for(int i=0; i<orderCount-directionCount; i++) {
					if(StringUtils.isNotBlank(param.getOrderDirection())) {
						param.setOrderDirection(param.getOrderDirection()+","+STA_DEF_ORDER_DIR);
					}
					else {
						param.setOrderDirection(STA_DEF_ORDER_DIR);
					}
				}
			}
			
		}
		SearchResponse response = executeESQuery(srb, param);
		return response;
	}
	
	private SearchResponse searchToursAggregatedPrepareAndExec(SearchToursAggregatedRequest param) throws SearchServiceException {
		//Query on aggregated results
		FilterBuilder rootFilter = prepareFilters(param, STA_PREFIX, null);
		Client client = node.client();
		SearchRequestBuilder srb = client.prepareSearch(IndexSynchronizerService.ES_INDEX_NAME)
		        .setTypes(IndexSynchronizerService.ES_AGGREGATED_TOURS_TYPE)
		        .setQuery(QueryBuilders.matchAllQuery())
		        .setPostFilter(rootFilter)
		        .setSize(MAX_RESULT_SIZE)
		        .addFields(IndexSynchronizerService.AG_CTC_TAG, IndexSynchronizerService.AG_CTN_TAG);
		if( StringUtils.isBlank(param.getOrderBy()) ) {
			param.setOrderBy(STA_CAT_TOURCODE+",1");
			if(StringUtils.isBlank(param.getOrderDirection()) ) {
				param.setOrderDirection(STA_DEF_ORDER_DIR+","+STA_DEF_ORDER_DIR);
			}
			else {
				param.setOrderDirection(STA_DEF_ORDER_DIR+","+param.getOrderDirection());
			}
		}
		else {
			int orderCount = param.getOrderBy().split(",").length;
			int directionCount = 0;
			if(StringUtils.isNotBlank(param.getOrderDirection()) ) {
				directionCount = param.getOrderDirection().split(",").length;
			}
			if(directionCount < orderCount) {
				for(int i=0; i<orderCount-directionCount; i++) {
					if(StringUtils.isNotBlank(param.getOrderDirection())) {
						param.setOrderDirection(param.getOrderDirection()+","+STA_DEF_ORDER_DIR);
					}
					else {
						param.setOrderDirection(STA_DEF_ORDER_DIR);
					}
				}
			}
			if(!param.getOrderBy().startsWith(STA_CAT_TOURCODE)) {
				param.setOrderBy(STA_CAT_TOURCODE+","+param.getOrderBy());
				param.setOrderDirection(STA_DEF_ORDER_DIR+","+param.getOrderDirection());
			}
		}
		SearchResponse response = executeESQuery(srb, param);
		return response;
	}
	
	private com.ttc.ch2.api.ccapi.v3.SearchToursResponse searchToursMapResponseToResult(SearchResponse response, com.ttc.ch2.api.ccapi.v3.SearchToursRequest param) throws JsonProcessingException, IOException, JAXBException, DatatypeConfigurationException{
		com.ttc.ch2.api.ccapi.v3.SearchToursResponse result = new com.ttc.ch2.api.ccapi.v3.SearchToursResponse();
		Integer firstRecordNumber = param.getFirstRecordNumber().intValue();
		result.setNumberOfRecords(new BigInteger(new Long(response.getHits().getHits().length).toString()));
		result.setTotalRecords( new BigInteger(new Long(response.getHits().getTotalHits()).toString()) );
		
		if(response.getHits().getHits().length > 0) {
			result.setSubsetReturned(new Integer(firstRecordNumber).toString()+"-"+(new Long(firstRecordNumber+response.getHits().getHits().length-1).toString()));
		}
		else {
			result.setSubsetReturned(SUBSET_EMPTY_V3);
		}
		
		TourInfoMapper tourInfoMapper = new TourInfoMapper();
		List<SearchResults> resultList = result.getSearchResults();
		ObjectMapper jom = new ObjectMapper();
		for(SearchHit hit: response.getHits()) {
			SearchResultsExtended elem = new SearchResultsExtended();
			resultList.add(elem);
			String hitParse = hit.getSourceAsString();
			JsonNode rootNode = jom.readTree(hitParse);
			JsonNode tiJson = rootNode.path(IndexSynchronizerService.TI_TAG);
			JsonNode tdJson = rootNode.path(IndexSynchronizerService.TD_TAG);
			elem.setTourCode(tiJson.path("TourCode").getTextValue());
			logger.trace("Processing tour: " + elem.getTourCode());
			
			JsonNode tourVariationDefinersJson = tiJson.path("TourVariationDefiners");
			if(tourVariationDefinersJson.path("EndCity").has("Airports")) {
				elem.setAirportsEndCity(tourVariationDefinersJson.path("EndCity").path("Airports").path("Airport").getElements().next().path("City").getTextValue());
			}
			if(tourVariationDefinersJson.path("StartCity").has("Airports")) {
				elem.setAirportsStartCity(tourVariationDefinersJson.path("StartCity").path("Airports").path("Airport").getElements().next().path("City").getTextValue());
			}
			elem.setContractingSeason(tourVariationDefinersJson.path("OperatingProduct").path("ContractingSeason").getTextValue());
			JsonNode departuresJson = tdJson.path("SellingCompany").path("Departures").path("Departure");
			Iterator<JsonNode> departuresIt = departuresJson.getElements();
			while(departuresIt.hasNext()) {
				JsonNode dep = departuresIt.next(); 
				if(dep.path("AvailabilityStatus").getTextValue().equals(DEP_CANCELED)){
					departuresIt.remove();
				}
				else if(DatatypeFactory.newInstance().newXMLGregorianCalendar(dep.path("StartDateTime").getTextValue()).toGregorianCalendar().getTime().compareTo(new Date()) < 0 ) {
					departuresIt.remove();
				}	
			}
			Boolean definiteDeparturesAvailable = false;
			departuresIt = departuresJson.getElements();
			while(departuresIt.hasNext()) {
				JsonNode dep = departuresIt.next();
				if(dep.path("DefiniteDeparture").getBooleanValue()) {
					definiteDeparturesAvailable=true;
					break;
				}
			}
			elem.setDefiniteDeparturesAvailable(definiteDeparturesAvailable);
			
			JsonNode brochureJson = tiJson.path("SellingCompanies").path("SellingCompany").getElements().next().path("Brochure").getElements().next();
			elem.setBrochureCode(brochureJson.path("Code").getTextValue());
			elem.setBrochureName(brochureJson.path("Name").getTextValue());
			if(tiJson.has("Duration"))
				elem.setDuration(new BigInteger(new Long(tiJson.path("Duration").getLongValue()).toString()));
			
			
			List<JsonNode> departureList = new ArrayList<JsonNode>();
			departuresIt = departuresJson.getElements();
			while(departuresIt.hasNext()) {
				departureList.add(departuresIt.next());
			}
			Collections.sort(departureList,new Comparator<JsonNode>() {
				@Override
				public int compare(JsonNode o1, JsonNode o2) {
					return o1.path("StartDateTime").getTextValue().compareTo(o2.path("StartDateTime").getTextValue()); 
				}});
			
			elem.setEarliestDepartureStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(departureList.get(0).path("StartDateTime").getTextValue()));
			elem.setLatestDepartureStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(departureList.get(departureList.size()-1).path("StartDateTime").getTextValue()));
			
			Boolean earlyPaymentDiscountAvailable = false;
			
			departuresIt = departuresJson.getElements();
			while(departuresIt.hasNext()) {
				JsonNode disJson = departuresIt.next().path("TourRules").path("Discounts"); 
				if(!disJson.isMissingNode()) {
					Iterator<JsonNode> disIt = disJson.path("Discount").getElements();
					while(disIt.hasNext()) {
						if(disIt.next().path("Type").getTextValue().equals(EARLY_PAYMENT_DISCOUNT)) {
							earlyPaymentDiscountAvailable = true;
							break;
						}
					}					
				}
			}
			elem.setEarlyPaymentDiscountAvailable(earlyPaymentDiscountAvailable);
			elem.setEndCity(tourVariationDefinersJson.path("EndCity").path("Name").getTextValue());
			elem.setIncludedCruiseCabinType(tourVariationDefinersJson.path("IncludedCruiseCabinType").getTextValue());
			elem.setOperatingProductCode(tdJson.path("OperatingProductCode").getTextValue());
			elem.setOnlineBookable(tdJson.path("OnlineBookable").getBooleanValue());
			
			JsonNode pricesRangeJson = tdJson.path("SellingCompany").path("Departures").path("PricesRange");
			Double priceFrom=0.0;
			Double priceTo=0.0;
			String prefferedRoomPrefix=param.getPreferedRoomType().toUpperCase();
			if(RoomTypeCodeType.SINGLE.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("SingleRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.TWIN.value().toUpperCase().equals(prefferedRoomPrefix)){
				JsonNode roomJson = pricesRangeJson.path("TwinRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.TWIN_SHARE.value().toUpperCase().equals(prefferedRoomPrefix)){
				JsonNode roomJson = pricesRangeJson.path("TwinShareRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.TRIPLE.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("TripleRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.TRIPLE_SHARE.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("TripleShareRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.QUAD.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("QuadRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.QUAD_SHARE.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("QuadShareRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}
			
			elem.setPriceFrom(priceFrom);
			elem.setPriceTo(priceTo);
			elem.setSellingCompanyCode(tdJson.path("SellingCompany").path("Code").getTextValue());
			elem.setStartCity(tourVariationDefinersJson.path("StartCity").path("Name").getTextValue());
			elem.setTourName(tiJson.path("TourName").getTextValue());
						
			if(tourVariationDefinersJson.has("AdditionalDefiners")) {
				elem.setAdditionalDefiners(tourInfoMapper.mapAdditionalDefiners(tourVariationDefinersJson.path("AdditionalDefiners").toString()));
			}
			elem.setContinentsVisited(tourInfoMapper.mapContinentsVisited(tiJson.path("ContinentsVisited").toString()));
			
			elem.setCountriesVisited(tourInfoMapper.mapCountriesVisited(tiJson.path("CountriesVisited").toString()));
			
			if(tiJson.has("Highlights")) {
				elem.setHighlights(tourInfoMapper.mapHighlights(tiJson.path("Highlights").toString()));
			}
			if(tourVariationDefinersJson.has("IncludedSubProducts")) {
				elem.setIncludedSubProducts(tourInfoMapper.mapIncludedSubProducts(tourVariationDefinersJson.path("IncludedSubProducts").toString()));
			}
			
			JsonNode marketingFlagsJson = tiJson.path("SellingCompanies").path("SellingCompany").getElements().next().path("MarketingFlags");
			if(!marketingFlagsJson.isMissingNode()) {
				elem.setMarketingFlags(tourInfoMapper.mapMarketingFlags(marketingFlagsJson.toString()));
			}
			
			if(tourVariationDefinersJson.has("RoomTypes") && tourVariationDefinersJson.path("RoomTypes").path("RoomType").getElements().hasNext()) {
				elem.setSellableRoomTypes(tourInfoMapper.mapRoomTypes(tourVariationDefinersJson.path("RoomTypes").toString()));
			}
			if(tiJson.has("Assets")) {
				elem.setAssets(tourInfoMapper.mapAssets(tiJson.path("Assets").toString()));	
			}
			if(tiJson.has("Itinerary") && tiJson.path("Itinerary").path("ItinerarySegment").getElements().hasNext()) {
				Iterator<JsonNode> itinerarySegmentIt = tiJson.path("Itinerary").path("ItinerarySegment").getElements();
				while(itinerarySegmentIt.hasNext()) {
					JsonNode itsJson = itinerarySegmentIt.next();
					if(itsJson.has("Accommodation") && !itsJson.path("Accommodation").getTextValue().equals("None")) {
						elem.getAccommodations().add(itsJson.path("Accommodation").getTextValue());
					}
				}
			}
		}
		result.setSuccessful(true);
		return result;
	}
	
	private SearchToursAggregatedResponse searchToursAggregatedMapResponseToResult(SearchResponse response, SearchToursAggregatedRequest param) throws JsonProcessingException, IOException, SearchServiceException, JAXBException, DatatypeConfigurationException{

		SearchToursAggregatedResponse result = new SearchToursAggregatedResponse();

		if (response.getHits().getHits().length == 0) {

			result.setNumberOfRecords(new BigInteger(new Long(response.getHits().getHits().length).toString()));
			result.setTotalRecords(new BigInteger(new Long(response.getHits().getTotalHits()).toString()));
			result.setSubsetReturned(SUBSET_EMPTY_V3);
			result.setSuccessful(true);

			return result;
		}

		List<String> cataloguedTourCodeListAll = new ArrayList<String>();
		List<String> cataloguedTourCodeListMatched = new ArrayList<String>();
		List<SearchAggregatedResults> resultList = result.getSearchAggregatedResults();

		for (SearchHit hit : response.getHits()) {

			String cataloguedTourCode = hit.getFields().get(IndexSynchronizerService.AG_CTC_TAG).getValue().toString();
			String cataloguedTourName = hit.getFields().get(IndexSynchronizerService.AG_CTN_TAG).getValue().toString();

			if (cataloguedTourCodeListAll.contains(cataloguedTourCode)) {
				continue;
			}

			cataloguedTourCodeListAll.add(cataloguedTourCode);

			if (cataloguedTourCodeListAll.size() < param.getFirstRecordNumber().intValue()) {
				continue;
			}

			if (cataloguedTourCodeListAll.size() >= param.getFirstRecordNumber().intValue() + param.getNumberOfRecords().intValue()) {
				continue;
			}

			cataloguedTourCodeListMatched.add(cataloguedTourCode);

			SearchAggregatedResults elemAgg = new SearchAggregatedResults();

			elemAgg.setCataloguedTourCode(cataloguedTourCode);
			elemAgg.setCataloguedTourName(cataloguedTourName);

			resultList.add(elemAgg);
		}

		result.setTotalRecords(new BigInteger(String.valueOf(cataloguedTourCodeListAll.size())));
		result.setNumberOfRecords(new BigInteger(String.valueOf(cataloguedTourCodeListMatched.size())));

		if (cataloguedTourCodeListMatched.size() == 0) {

			result.setSubsetReturned(SUBSET_EMPTY_V3);
			result.setSuccessful(true);

			return result;
		}

		result.setSubsetReturned(String.format("%s-%s", param.getFirstRecordNumber().intValue(), param.getFirstRecordNumber().intValue() + cataloguedTourCodeListMatched.size() - 1));

		FilterBuilder rootFilter2 = prepareFilters(param, "", cataloguedTourCodeListMatched);
		Client client2 = node.client();
		SearchRequestBuilder srb2 = client2.prepareSearch(IndexSynchronizerService.ES_INDEX_NAME)
		        .setTypes(IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE)
		        .setQuery(QueryBuilders.matchAllQuery())
		        .setPostFilter(rootFilter2)
		        .setSize(MAX_RESULT_SIZE);
		param.setOrderBy(param.getOrderBy().replaceAll("^"+STA_CAT_TOURCODE, ST_CAT_TOURCODE));
		SearchResponse response2 = executeESQuery(srb2, param);
		
		TourInfoMapper tourInfoMapper = new TourInfoMapper();
		
		int aggResultsIdx = 0;
		ObjectMapper jom2 = new ObjectMapper();
		for(SearchHit hit: response2.getHits()) {
			String hitParse = hit.getSourceAsString();
			JsonNode rootNode = jom2.readTree(hitParse);
			
			JsonNode tiJson = rootNode.path(IndexSynchronizerService.TI_TAG);
			JsonNode tdJson = rootNode.path(IndexSynchronizerService.TD_TAG);
			JsonNode tourVariationDefinersJson = tiJson.path("TourVariationDefiners");
			
			if(aggResultsIdx < (resultList.size() - 1) && !resultList.get(aggResultsIdx).getCataloguedTourCode().equals(tiJson.path("CataloguedTour").path("Code").getTextValue())) {
				aggResultsIdx++;
			}
			SearchAggregatedResults elemAgg = resultList.get(aggResultsIdx);
			SearchAggregatedSubResults elem = new SearchAggregatedSubResults();
			elemAgg.getSearchAggregatedSubResults().add(elem);
			elem.setTourCode(tiJson.path("TourCode").getTextValue());
			logger.trace("Processing tour: " + elem.getTourCode());
			
			if(tourVariationDefinersJson.path("EndCity").has("Airports")) {
				elem.setAirportsEndCity(tourVariationDefinersJson.path("EndCity").path("Airports").path("Airport").getElements().next().path("City").getTextValue());
			}
			if(tourVariationDefinersJson.path("StartCity").has("Airports")) {
				elem.setAirportsStartCity(tourVariationDefinersJson.path("StartCity").path("Airports").path("Airport").getElements().next().path("City").getTextValue());
			}
			elem.setContractingSeason(tourVariationDefinersJson.path("OperatingProduct").path("ContractingSeason").getTextValue());
			
			JsonNode departuresJson = tdJson.path("SellingCompany").path("Departures").path("Departure");
			Iterator<JsonNode> departuresIt = departuresJson.getElements();
			while(departuresIt.hasNext()) {
				JsonNode dep = departuresIt.next(); 
				if(dep.path("AvailabilityStatus").getTextValue().equals(DEP_CANCELED)){
					departuresIt.remove();
				}
				else if(DatatypeFactory.newInstance().newXMLGregorianCalendar(dep.path("StartDateTime").getTextValue()).toGregorianCalendar().getTime().compareTo(new Date()) < 0 ) {
					departuresIt.remove();
				}
				}
				
			
			Boolean definiteDeparturesAvailable = false;
			departuresIt = departuresJson.getElements();
			while(departuresIt.hasNext()) {
				JsonNode dep = departuresIt.next();
				if(dep.path("DefiniteDeparture").getBooleanValue()) {
					definiteDeparturesAvailable=true;
					break;
				}
			}
			elem.setDefiniteDeparturesAvailable(definiteDeparturesAvailable);
			
			JsonNode brochureJson = tiJson.path("SellingCompanies").path("SellingCompany").getElements().next().path("Brochure").getElements().next();
			elem.setBrochureCode(brochureJson.path("Code").getTextValue());
			elem.setBrochureName(brochureJson.path("Name").getTextValue());
			if(tiJson.has("Duration"))
				elem.setDuration(new BigInteger(new Long(tiJson.path("Duration").getLongValue()).toString()));
						
			List<JsonNode> departureList = new ArrayList<JsonNode>();
			departuresIt = departuresJson.getElements();
			while(departuresIt.hasNext()) {
				departureList.add(departuresIt.next());
			}
			Collections.sort(departureList,new Comparator<JsonNode>() {
				@Override
				public int compare(JsonNode o1, JsonNode o2) {
					return o1.path("StartDateTime").getTextValue().compareTo(o2.path("StartDateTime").getTextValue()); 
				}});
			
			elem.setEarliestDepartureStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(departureList.get(0).path("StartDateTime").getTextValue()));
			elem.setLatestDepartureStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(departureList.get(departureList.size()-1).path("StartDateTime").getTextValue()));
			
			Boolean earlyPaymentDiscountAvailable = false;
			
			departuresIt = departuresJson.getElements();
			while(departuresIt.hasNext()) {
				JsonNode disJson = departuresIt.next().path("TourRules").path("Discounts"); 
				if(!disJson.isMissingNode()) {
					Iterator<JsonNode> disIt = disJson.path("Discount").getElements();
					while(disIt.hasNext()) {
						if(disIt.next().path("Type").getTextValue().equals(EARLY_PAYMENT_DISCOUNT)) {
							earlyPaymentDiscountAvailable = true;
							break;
						}
					}					
				}
			}
			elem.setEarlyPaymentDiscountAvailable(earlyPaymentDiscountAvailable);
			elem.setEndCity(tourVariationDefinersJson.path("EndCity").path("Name").getTextValue());
			elem.setIncludedCruiseCabinType(tourVariationDefinersJson.path("IncludedCruiseCabinType").getTextValue());
			elem.setOperatingProductCode(tdJson.path("OperatingProductCode").getTextValue());
			
			JsonNode pricesRangeJson = tdJson.path("SellingCompany").path("Departures").path("PricesRange");
			Double priceFrom=0.0;
			Double priceTo=0.0;
			String prefferedRoomPrefix=param.getPreferedRoomType().toUpperCase();
			if(RoomTypeCodeType.SINGLE.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("SingleRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.TWIN.value().toUpperCase().equals(prefferedRoomPrefix)){
				JsonNode roomJson = pricesRangeJson.path("TwinRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.TWIN_SHARE.value().toUpperCase().equals(prefferedRoomPrefix)){
				JsonNode roomJson = pricesRangeJson.path("TwinShareRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.TRIPLE.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("TripleRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.TRIPLE_SHARE.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("TripleShareRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.QUAD.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("QuadRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}else if(RoomTypeCodeType.QUAD_SHARE.value().toUpperCase().equals(prefferedRoomPrefix)) {
				JsonNode roomJson = pricesRangeJson.path("QuadShareRoom");
				priceFrom = roomJson.path("PriceFrom").getDoubleValue();
				priceTo = roomJson.path("PriceTo").getDoubleValue();
			}
			elem.setPriceFrom(priceFrom);
			elem.setPriceTo(priceTo);
			elem.setSellingCompanyCode(tdJson.path("SellingCompany").path("Code").getTextValue());
			elem.setStartCity(tourVariationDefinersJson.path("StartCity").path("Name").getTextValue());
			elem.setTourName(tiJson.path("TourName").getTextValue());
			
			if(tourVariationDefinersJson.has("AdditionalDefiners")) {
				elem.setAdditionalDefiners(tourInfoMapper.mapAdditionalDefiners(tourVariationDefinersJson.path("AdditionalDefiners").toString()));
			}
			elem.setContinentsVisited(tourInfoMapper.mapContinentsVisited(tiJson.path("ContinentsVisited").toString()));
			
			elem.setCountriesVisited(tourInfoMapper.mapCountriesVisited(tiJson.path("CountriesVisited").toString()));
			
			if(tiJson.has("Highlights")) {
				elem.setHighlights(tourInfoMapper.mapHighlights(tiJson.path("Highlights").toString()));
			}
			if(tourVariationDefinersJson.has("IncludedSubProducts")) {
				elem.setIncludedSubProducts(tourInfoMapper.mapIncludedSubProducts(tourVariationDefinersJson.path("IncludedSubProducts").toString()));
			}
			
			JsonNode marketingFlagsJson = tiJson.path("SellingCompanies").path("SellingCompany").getElements().next().path("MarketingFlags");
			if(!marketingFlagsJson.isMissingNode()) {
				elem.setMarketingFlags(tourInfoMapper.mapMarketingFlags(marketingFlagsJson.toString()));
			}
			
			if(tourVariationDefinersJson.has("RoomTypes") && tourVariationDefinersJson.path("RoomTypes").path("RoomType").getElements().hasNext()) {
				elem.setSellableRoomTypes(tourInfoMapper.mapRoomTypes(tourVariationDefinersJson.path("RoomTypes").toString()));
			}
			if(tiJson.has("Assets")) {
				elem.setAssets(tourInfoMapper.mapAssets(tiJson.path("Assets").toString()));	
			}
			if(tiJson.has("Itinerary") && tiJson.path("Itinerary").path("ItinerarySegment").getElements().hasNext()) {
				Iterator<JsonNode> itinerarySegmentIt = tiJson.path("Itinerary").path("ItinerarySegment").getElements();
				while(itinerarySegmentIt.hasNext()) {
					JsonNode itsJson = itinerarySegmentIt.next();
					if(itsJson.has("Accommodation") && !itsJson.path("Accommodation").getTextValue().equals("None")) {
						elem.getAccommodations().add(itsJson.path("Accommodation").getTextValue());
					}
				}
			}
		}
		result.setSuccessful(true);
		return result;
	}

	@Override
	public int countIndexedSearchToursDocuments(String brandCode) {
		try {
			Client client = node.client();
			
			CountRequestBuilder crb = client.prepareCount(IndexSynchronizerService.ES_INDEX_NAME).setTypes(IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE).
				setQuery(QueryBuilders.queryString(brandCode).field(ST_PATH_BRANDCODE));
		
			CountResponse cntresp =  crb.execute().actionGet();
			
			return (int)cntresp.getCount();
			
		}catch(Exception e) {
			 logger.error("ElasticSearch error", e);
			 throw e;
		}
		
	}

	@Override
	public int countIndexedSearchToursAggregatedDocuments(String brandCode) {
		try {
			Client client = node.client();
			
			CountRequestBuilder crb = client.prepareCount(IndexSynchronizerService.ES_INDEX_NAME).setTypes(IndexSynchronizerService.ES_AGGREGATED_TOURS_TYPE).
				setQuery( QueryBuilders.nestedQuery(STA_PREFIX.replace(".", ""), QueryBuilders.queryString(brandCode).field(STA_PREFIX+ST_PATH_BRANDCODE)));
		
			CountResponse cntresp =  crb.execute().actionGet();
			
			return (int)cntresp.getCount();
			
		}catch(Exception e) {
			 logger.error("ElasticSearch error", e);
			 throw e;
		}
	}
}
