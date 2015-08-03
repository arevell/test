package com.ttc.ch2.search.export;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Sets;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.SellingCompanyType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompany;
import com.ttc.ch2.bl.departure.ImportStatusServicePartial;
import com.ttc.ch2.bl.upload.UploadStatusServicePartial;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.schema.aggregatedtours._2014._05._3.AggregatedTours;
import com.ttc.ch2.schema.aggregatedtours._2014._05._3.Tour;
import com.ttc.ch2.schema.mapper.TourInfoMapper;
import com.ttc.ch2.schema.tourdeparturessimp._2014._04._3.AvailabilityStatusType;
import com.ttc.ch2.schema.tourdeparturessimp._2014._04._3.DepartureType;
import com.ttc.ch2.schema.tourdeparturessimp._2014._04._3.PriceRangeType;
import com.ttc.ch2.schema.tourdeparturessimp._2014._04._3.PricesRangeType;
import com.ttc.ch2.schema.tourdeparturessimp._2014._04._3.RoomType;
import com.ttc.ch2.schema.tourdeparturessimp._2014._04._3.RoomTypeCodeType;
import com.ttc.ch2.schema.tourdeparturessimp._2014._04._3.TourDeparturesSimpType;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.TourInfoSimp;
import com.ttc.ch2.search.services.SearchService;
import com.ttc.ch2.statistics.BrandInfo;
import com.ttc.ch2.statistics.StatisticsBean;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class IndexSynchronizerServiceImpl implements IndexSynchronizerService {

	private static final Logger logger = LoggerFactory.getLogger(IndexSynchronizerServiceImpl.class);

	private Exception lastException=null;
	
	private static final String PATH_TOUR_INFO_BRAND_CODE = "TourInfoSimp.BrandCode";
	private static final String ST_PATH_SELLING_COMPANY_CODE = "TourDeparturesSimp.SellingCompany.Code";
	private static final String ST_PATH_TOUR_CODE = "TourInfoSimp.TourCode";
	private static final String ST_PATH_BRAND_CODE = "TourInfoSimp.BrandCode";
	private static final String PATH_TOUR_INFO_BRAND_CODE_AGG = "ToursList.TourInfoSimp.BrandCode";
	private static final int MAX_ELEMENTS_INSIDE_SQL_IN_CLAUSE = 999;

	private static final String PRICE_FROM = "priceFrom";
	private static final String PRICE_TO = "priceTo";

	private static final String ID_TEMPLATE = "%s_%s";

	private static final String INFO_TOUR_INDEXING = "Elasticsearch - indexing tour: [%s] being: [%s/%s]";

	private static final int MAX_RESULT_SIZE = 0x100000; // The potential maximum allowed number is 2147483391 but it causes out of memory in our case;

	private static final int MAX_COUNT_OF_SNAPSHOT = 100;

	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;

	@Inject
	private ImportStatusServicePartial importStatusService;

	@Inject
	private UploadStatusServicePartial uploadStatusService;
	
	@Inject
	private SearchService searchService;

	@Inject
	private Node node;

	@Inject
	private StatisticsBean statisticsBean;
	
	private JAXBContext jaxbATContext;
	private JAXBContext jaxbTISContext;
	private JAXBContext jaxbRTIContext;
	private JAXBContext jaxbTDContext;
	private JAXBContext jaxbTDSContext;
	private JAXBContext jaxbSCContext;
	private JAXBContext jaxbSCSContext;

	@PostConstruct
	private void init() throws JAXBException {

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
		properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);

		jaxbATContext = JAXBContext.newInstance(AggregatedTours.class);
		jaxbTISContext = JAXBContext.newInstance(new Class[] { TourInfoSimp.class }, properties);
		jaxbRTIContext = JAXBContext.newInstance(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo.class);
		jaxbTDContext = JAXBContext.newInstance(TourDeparturesType.class);
		jaxbTDSContext = JAXBContext.newInstance(TourDeparturesSimpType.class);
		jaxbSCContext = JAXBContext.newInstance(SellingCompanyType.class);
		jaxbSCSContext = JAXBContext.newInstance(new Class[] { com.ttc.ch2.schema.tourdeparturessimp._2014._04._3.SellingCompanyType.class }, properties);
	}

	@Override
	public void synchronize(ProcessName processName, String brandCode, List<Long> ids,IndexSynchronizerVO indexSynchronizerVO) throws IndexSynchronizerServiceException {
		long statsSerial = BrandInfo.SERIAL_WAITING;
		try {
			if(ids == null || ids.isEmpty()){
				indexSynchronizerVO.setAggregatedCount(searchService.countIndexedSearchToursAggregatedDocuments(brandCode));
				indexSynchronizerVO.setDocumentCount(searchService.countIndexedSearchToursDocuments(brandCode));
				return;
			}
			statsSerial = statisticsBean.registerStartShortIndexationAdd(brandCode);
			Client client = node.client();

			checkIndex(client);
						
			ids = contentRepositoryDAO.getExtendedCRIdsforSearchToursAggregated(ids, brandCode);
			
			Marshaller jaxbATMarshaller = createMarshaller(jaxbATContext);
			Marshaller jaxbTDSMarshaller = createMarshaller(jaxbTDSContext);
			Marshaller jaxbSCMarshaller = createMarshaller(jaxbSCContext);
			Marshaller jaxbTISMarshaller = jaxbTISContext.createMarshaller();
			
			//Unmarshaller jaxbTISUnmarshaller = jaxbTISContext.createUnmarshaller();
			Unmarshaller jaxbRTIUnmarshaller = jaxbRTIContext.createUnmarshaller();
			Unmarshaller jaxbTDUnmarshaller = jaxbTDContext.createUnmarshaller();
			Unmarshaller jaxbSCSUnmarshaller = jaxbSCSContext.createUnmarshaller();

			
			AggregatedTours aggregatedTours = new AggregatedTours();
			String prevCataloguedTourCode = "fake";
			TourInfoMapper tourInfoMapper = new TourInfoMapper();
				
			int index = 0;
			int snapshotCount=0;
			makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brandCode, String.format("Add elements to ElasticSearch index"));
			for (Long id : ids) {
                ContentRepository contentRepository = contentRepositoryDAO.find(id);

                snapshotCount++;
                snapshotCount=makeSnapshot(snapshotCount, processName, brandCode, String.format(INFO_TOUR_INDEXING, contentRepository.getTourCode(), ++index, ids.size()));
                prevCataloguedTourCode = executeIndexingForTour(brandCode, contentRepository, jaxbRTIUnmarshaller, jaxbTDUnmarshaller, jaxbSCSUnmarshaller, 
            			jaxbTISMarshaller, jaxbTDSMarshaller, jaxbSCMarshaller, jaxbATMarshaller, aggregatedTours, prevCataloguedTourCode, 
            			client, null, null, indexSynchronizerVO, tourInfoMapper, false);
                
                contentRepositoryDAO.flush();
        		contentRepositoryDAO.evictEntity(contentRepository);
			}
					
			if (aggregatedTours.getToursList().size() > 0) {

				logger.info("Indexing the last element of aggregated tours in ElasticSearch");				
				try {					
					makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brandCode,String.format("Indexing the last element of aggregated tours in ElasticSearch"));						
					exportAggregatedTour(brandCode, aggregatedTours, jaxbATMarshaller, client);
				} catch (Exception e) {
					indexSynchronizerVO.getAggregatedIDsListNotAdded().add(aggregatedTours.getCataloguedTourCode());
					logger.error("",e);
					lastException=e;
				}				
			}
			
			indexSynchronizerVO.setAggregatedCount(searchService.countIndexedSearchToursAggregatedDocuments(brandCode));
			indexSynchronizerVO.setDocumentCount(searchService.countIndexedSearchToursDocuments(brandCode));
			
		} catch (Exception e) {
			indexSynchronizerVO.setUnexpectedErrorOccurred(true);
			logger.error("Synchronization of ElasticSearch engine has failed: ", e);
			IndexSynchronizerServiceException indexException = new IndexSynchronizerServiceException(e);
			throw indexException;
		}finally {
			statisticsBean.registerStopShortIndexationAdd(brandCode, statsSerial);
		}
	}

	private void checkIndex(Client client) throws IOException {

		boolean hasIndex = client.admin().indices().prepareExists(ES_INDEX_NAME).execute().actionGet().isExists();

		if (hasIndex) {
			return;
		}

		byte[] tiandtdscMappingArray = ByteStreams.toByteArray(IndexSynchronizerServiceImpl.class.getResourceAsStream("tiandtdsc.mapping"));
		byte[] aggtoursMappingArray = ByteStreams.toByteArray(IndexSynchronizerServiceImpl.class.getResourceAsStream("aggtours.mapping"));

		CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate(ES_INDEX_NAME);
		createIndexRequestBuilder.addMapping(ES_TOURS_ANS_SC_TYPE, new String(tiandtdscMappingArray));
		createIndexRequestBuilder.addMapping(ES_AGGREGATED_TOURS_TYPE, new String(aggtoursMappingArray));
		createIndexRequestBuilder.execute().actionGet();
	}

	private void exportAggregatedTour(String brandCode, AggregatedTours aggregatedTours, Marshaller jaxbATMarshaller, Client client) throws JAXBException {

		StringWriter stringWriter = new StringWriter();
		jaxbATMarshaller.marshal(aggregatedTours, stringWriter);

		logger.trace("Indexing aggregated: " + stringWriter.toString());

		String aggregatedID = String.format(ID_TEMPLATE, aggregatedTours.getCataloguedTourCode(), brandCode);

		IndexRequestBuilder indexRequest = client
				.prepareIndex(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_AGGREGATED_TOURS_TYPE, aggregatedID)
				.setSource(stringWriter.toString());

		IndexResponse response = indexRequest.execute().actionGet();

		logger.info("CataloguedTourcode: [" + aggregatedID + "]; is created: [" + response.isCreated() + "]; version: [" + response.getVersion() + "]");
	}

	private void calculatePriceRanges(TourDeparturesSimpType tourDeparturesSimpType) {

		PricesRangeType pricesRange = new PricesRangeType();
		tourDeparturesSimpType.getSellingCompany().getDepartures().setPricesRange(pricesRange);

		for (RoomTypeCodeType roomType : RoomTypeCodeType.values()) {

			Map<String, BigDecimal> prices = calculatePricesFor(tourDeparturesSimpType, roomType);

			PriceRangeType priceRange = new PriceRangeType();
			priceRange.setPriceFrom(prices.get(PRICE_FROM));
			priceRange.setPriceTo(prices.get(PRICE_TO));

			switch (roomType) {

				case SINGLE:
					pricesRange.setSingleRoom(priceRange);
					break;
				case TWIN:
					pricesRange.setTwinRoom(priceRange);
					break;
				case TWIN_SHARE:
					pricesRange.setTwinShareRoom(priceRange);
					break;
				case TRIPLE:
					pricesRange.setTripleRoom(priceRange);
					break;
				case TRIPLE_SHARE:
					pricesRange.setTripleShareRoom(priceRange);
					break;
				case QUAD:
					pricesRange.setQuadRoom(priceRange);
					break;
				case QUAD_SHARE:
					pricesRange.setQuadShareRoom(priceRange);
					break;
			}
		}
	}

	private Map<String, BigDecimal> calculatePricesFor(TourDeparturesSimpType tourDeparturesSimpType, RoomTypeCodeType roomType) {

		Map<String, BigDecimal> prices = new HashMap<String, BigDecimal>();

		BigDecimal priceFrom = new BigDecimal(Double.MAX_VALUE);
		BigDecimal priceTo = new BigDecimal(0);

		for (DepartureType dep : tourDeparturesSimpType.getSellingCompany().getDepartures().getDeparture()) {
			for (RoomType room : dep.getTourRules().getRooms().getRoom()) {
				if (room.getType() == roomType && !dep.getAvailabilityStatus().equals(AvailabilityStatusType.CANCELLED)) {
					BigDecimal price = room.getPrice().getAdult().getCombined();
					if (price.compareTo(priceTo) > 0)
						priceTo = price;
					if (price.compareTo(priceFrom) < 0)
						priceFrom = price;
				}
			}
		}

		if (priceTo.compareTo(new BigDecimal(0)) == 0)
			prices.put(PRICE_FROM, new BigDecimal(0));
		else
			prices.put(PRICE_FROM, priceFrom);

		prices.put(PRICE_TO, priceTo);

		return prices;
	}	

	private Marshaller createMarshaller(JAXBContext jaxbContext) throws JAXBException {

		Marshaller marshaller = jaxbContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

		return marshaller;
	}
	
	private int makeSnapshot(int count,ProcessName processName,String brandCode,String msg){		
		if(count==MAX_COUNT_OF_SNAPSHOT){
			if (ProcessName.IMPORT.equals(processName)) {
				importStatusService.setupMessage(msg, brandCode, TypeMsg.INF.name(), ProcessLevel.INDEXING.name());
			} else if (ProcessName.UPLOAD.equals(processName)) {
				uploadStatusService.proccesingDescription(brandCode,msg, false);
			}
			return 0;
		}
		
		return count;
	}
	
	private String executeIndexingForTour(String brandCode, ContentRepository contentRepository, Unmarshaller  jaxbRTIUnmarshaller, Unmarshaller jaxbTDUnmarshaller, Unmarshaller jaxbSCSUnmarshaller, 
			Marshaller jaxbTISMarshaller, Marshaller jaxbTDSMarshaller, Marshaller jaxbSCMarshaller, Marshaller jaxbATMarshaller, AggregatedTours aggregatedTours, String prevCataloguedTourCode, 
			Client client, List<String> documentIDsListNew, List<String> aggregatedIDsListNew, IndexSynchronizerVO indexSynchronizerVO, TourInfoMapper tourInfoMapper, boolean aggregatedOnly) throws Exception {
		
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo tourInfo = (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo) jaxbRTIUnmarshaller.unmarshal(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getTourInfoXML().getBytes(StandardCharsets.UTF_8)));
		TourDeparturesType tourDeparture = (TourDeparturesType) jaxbTDUnmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getTourDepartureXML().getBytes(StandardCharsets.UTF_8))), TourDeparturesType.class).getValue();

		List<String> sellingCompanyCodes = new ArrayList<String>();
		
		for (SellingCompanyType sellingCompantTD : tourDeparture.getSellingCompanies().getSellingCompany()) {

			for (SellingCompany sellingCompanyTI : tourInfo.getSellingCompanies().getSellingCompany()) {

				if (sellingCompanyTI.getCode().equals(sellingCompantTD.getCode())) {

					String documentID = String.format(ID_TEMPLATE, contentRepository.getTourCode(), sellingCompantTD.getCode());
					sellingCompanyCodes.add(sellingCompanyTI.getCode());
					
					try {

						com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo tourInfoNormalized = (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo) jaxbRTIUnmarshaller.unmarshal(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getTourInfoXML().getBytes(StandardCharsets.UTF_8)));
						tourInfoNormalized.getSellingCompanies().getSellingCompany().clear();
						tourInfoNormalized.getSellingCompanies().getSellingCompany().add(sellingCompanyTI);
						TourInfoSimp tourInfoSimp = tourInfoMapper.mapToTourInfoSimp(tourInfoNormalized);
						
						StringWriter stringWriterTIS = new StringWriter();
						jaxbTISMarshaller.marshal(tourInfoSimp, stringWriterTIS);

						TourDeparturesSimpType tourDeparturesSimpType = new TourDeparturesSimpType();
						tourDeparturesSimpType.setOnlineBookable(tourDeparture.isOnlineBookable());
						tourDeparturesSimpType.setOperatingProductCode(tourDeparture.getOperatingProductCode());
						tourDeparturesSimpType.setTourCode(tourDeparture.getTourCode());

						StringWriter stringWriterSC = new StringWriter();
						jaxbSCMarshaller.marshal(sellingCompantTD, stringWriterSC);

						tourDeparturesSimpType.setSellingCompany(jaxbSCSUnmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(stringWriterSC.toString().getBytes())), com.ttc.ch2.schema.tourdeparturessimp._2014._04._3.SellingCompanyType.class).getValue());

						for (DepartureType departureType : tourDeparturesSimpType.getSellingCompany().getDepartures().getDeparture()) {
							departureType.setMonth(new BigInteger(new Long(departureType.getStartDateTime().toGregorianCalendar().get(Calendar.MONTH) + 1).toString()));
						}

						calculatePriceRanges(tourDeparturesSimpType);

						StringWriter stringWriterTDS = new StringWriter();
						jaxbTDSMarshaller.marshal(tourDeparturesSimpType, stringWriterTDS);

						if (!prevCataloguedTourCode.equals(tourInfoNormalized.getCataloguedTour().getCode())) {

							if (aggregatedTours.getToursList().size() > 0) {

								try {
									exportAggregatedTour(brandCode, aggregatedTours, jaxbATMarshaller, client);
								} catch (Exception e) {
									indexSynchronizerVO.getAggregatedIDsListNotAdded().add(aggregatedTours.getCataloguedTourCode());
								}
								if(aggregatedIDsListNew != null)
									aggregatedIDsListNew.add(String.format(ID_TEMPLATE, aggregatedTours.getCataloguedTourCode(), brandCode));
							}

							prevCataloguedTourCode = tourInfoNormalized.getCataloguedTour().getCode();
							
							aggregatedTours.getToursList().clear();
							aggregatedTours.setCataloguedTourCode(tourInfoNormalized.getCataloguedTour().getCode());
							aggregatedTours.setCataloguedTourName(tourInfoNormalized.getCataloguedTour().getName());
						}

						Tour tour = new Tour();
						tour.setTourDeparturesSimp(tourDeparturesSimpType);
						tour.setTourInfoSimp(tourInfoSimp);
						
						aggregatedTours.getToursList().add(tour);

						if(!aggregatedOnly) {
							StringBuilder stringBuilder = new StringBuilder();
							stringBuilder.append("{ \"" + IndexSynchronizerService.TI_TAG + "\" : ");
							stringBuilder.append(stringWriterTIS.toString());
							stringBuilder.append(", \"" + IndexSynchronizerService.TD_TAG + "\" :");
							stringBuilder.append(stringWriterTDS.toString());
							stringBuilder.append("}");
	
							logger.trace("Indexing: " + stringBuilder.toString());
	
							IndexRequestBuilder indexRequest = client
									.prepareIndex(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE, documentID)
									.setSource(stringBuilder.toString());
	
							IndexResponse importResponse = indexRequest.execute().actionGet();
	
							logger.info("Tourcode: [" + documentID + "]; is created: [" + importResponse.isCreated() + "]; version: [" + importResponse.getVersion() + "]");
	
							if(documentIDsListNew !=null )
								documentIDsListNew.add(documentID);
							
							
						}
					} catch (Exception e) {
						indexSynchronizerVO.getDocumentIDsListNotAdded().add(documentID);
						logger.error("",e);
						lastException=e;
					}
				}
			}
		}
		
		// Delete all SellingCompany/Tour pairs which are not in modified TD/TI
		if(documentIDsListNew == null && aggregatedIDsListNew == null && !aggregatedOnly ) {
			deleteSellingCompaniesFromIndex(client,brandCode ,contentRepository.getTourCode(), sellingCompanyCodes);
		}
		return prevCataloguedTourCode;
	}

	@Override
	public void synchronize(ProcessName processName,String brandCode,IndexSynchronizerVO indexSynchronizerVO) throws IndexSynchronizerServiceException {
		long statsSerial = BrandInfo.SERIAL_WAITING;
		try {
			statsSerial = statisticsBean.registerStartLongIndexation(brandCode);
			Client client = node.client();

			checkIndex(client);

			SearchRequestBuilder searchRequest = client
					.prepareSearch(IndexSynchronizerService.ES_INDEX_NAME)
					.setTypes(IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE)
					.setQuery(QueryBuilders.queryString(brandCode).field(PATH_TOUR_INFO_BRAND_CODE))
					.setSize(MAX_RESULT_SIZE)
					.addField(StringUtils.EMPTY);

			SearchResponse searchResponse = searchRequest.execute().actionGet();

			List<String> documentIDsListCur = new ArrayList<String>();
			List<String> documentIDsListNew = new ArrayList<String>();

			for (SearchHit serchHit : searchResponse.getHits()) {
				documentIDsListCur.add(serchHit.getId());
			}

			searchRequest = client
					.prepareSearch(IndexSynchronizerService.ES_INDEX_NAME)
					.setTypes(IndexSynchronizerService.ES_AGGREGATED_TOURS_TYPE)
					.setQuery(QueryBuilders.queryString(brandCode).field(PATH_TOUR_INFO_BRAND_CODE_AGG))
					.setSize(MAX_RESULT_SIZE)
					.addField(StringUtils.EMPTY);

			searchResponse = searchRequest.execute().actionGet();

			List<String> aggregatedIDsListCur = new ArrayList<String>();
			List<String> aggregatedIDsListNew = new ArrayList<String>();

			for (SearchHit serchHit : searchResponse.getHits()) {
				aggregatedIDsListCur.add(serchHit.getId());
			}
			
			
			List<Long> ids = new ArrayList<Long>();
			List<ContentRepository> contentRepositoryList = contentRepositoryDAO.getContentRepositoriesForExport(brandCode);
			for (ContentRepository contentRepository : contentRepositoryList) {
			    Long id = contentRepository.getId();
			    ids.add(id);
			    contentRepositoryDAO.evictEntity(contentRepository);
	        }
			contentRepositoryList.clear();
			statisticsBean.registerTourPairsCount(brandCode, ids.size());
			/*
			if(ids.size() == 0){
				indexSynchronizerVO.setAggregatedCount(searchService.countIndexedSearchToursAggregatedDocuments(brandCode));
				indexSynchronizerVO.setDocumentCount(searchService.countIndexedSearchToursDocuments(brandCode));
				return;
			}
			*/	
			
			Marshaller jaxbATMarshaller = createMarshaller(jaxbATContext);
			Marshaller jaxbTDSMarshaller = createMarshaller(jaxbTDSContext);
			Marshaller jaxbSCMarshaller = createMarshaller(jaxbSCContext);
			Marshaller jaxbTISMarshaller = jaxbTISContext.createMarshaller();
			
			//Unmarshaller jaxbTISUnmarshaller = jaxbTISContext.createUnmarshaller();
			Unmarshaller jaxbRTIUnmarshaller = jaxbRTIContext.createUnmarshaller();
			Unmarshaller jaxbTDUnmarshaller = jaxbTDContext.createUnmarshaller();
			Unmarshaller jaxbSCSUnmarshaller = jaxbSCSContext.createUnmarshaller();

			
			AggregatedTours aggregatedTours = new AggregatedTours();
			String prevCataloguedTourCode = "fake";
			TourInfoMapper tourInfoMapper = new TourInfoMapper();
				
			int index = 0;
			int snapshotCount=0;
			makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brandCode, String.format("Add elements to ElasticSearch index"));
			for (Long id : ids) {
                ContentRepository contentRepository = contentRepositoryDAO.find(id);

                snapshotCount++;
                snapshotCount=makeSnapshot(snapshotCount, processName, brandCode, String.format(INFO_TOUR_INDEXING, contentRepository.getTourCode(), ++index, ids.size()));
                prevCataloguedTourCode = executeIndexingForTour(brandCode, contentRepository, jaxbRTIUnmarshaller, jaxbTDUnmarshaller, jaxbSCSUnmarshaller, 
            			jaxbTISMarshaller, jaxbTDSMarshaller, jaxbSCMarshaller, jaxbATMarshaller, aggregatedTours, prevCataloguedTourCode, 
            			client, documentIDsListNew, aggregatedIDsListNew, indexSynchronizerVO, tourInfoMapper, false);
                
                contentRepositoryDAO.flush();
        		contentRepositoryDAO.evictEntity(contentRepository);
			}
					
			if (aggregatedTours.getToursList().size() > 0) {

				logger.info("Indexing the last element of aggregated tours in ElasticSearch");				
				try {					
					makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brandCode,String.format("Indexing the last element of aggregated tours in ElasticSearch"));						
					exportAggregatedTour(brandCode, aggregatedTours, jaxbATMarshaller, client);
				} catch (Exception e) {
					indexSynchronizerVO.getAggregatedIDsListNotAdded().add(aggregatedTours.getCataloguedTourCode());
					logger.error("",e);
					lastException=e;
				}
				aggregatedIDsListNew.add(String.format(ID_TEMPLATE, aggregatedTours.getCataloguedTourCode(), brandCode));
			}

			@SuppressWarnings("unchecked")
			List<String> documentIDsListDel = (List<String>) CollectionUtils.subtract(documentIDsListCur, documentIDsListNew);
			snapshotCount=0;
			makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brandCode, String.format("Deleting element from ElasticSearch index"));
			for (String documentID : documentIDsListDel) {
				try {
					logger.info("Deleting element from ElasticSearch index");
					snapshotCount++;
					snapshotCount=makeSnapshot(snapshotCount, processName, brandCode,String.format("Deleting element from ElasticSearch index documentId:%s",documentID));	
					DeleteRequestBuilder deleteRequest = client.prepareDelete(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE, documentID);
					DeleteResponse deleteResponse = deleteRequest.execute().actionGet();
					logger.info("Tourcode: [" + documentID + "]; is deleted: [" + deleteResponse.isFound() + "]; version: [" + deleteResponse.getVersion() + "]");
				} catch (Exception e) {
					indexSynchronizerVO.getDocumentIDsListNotDeleted().add(documentID);
					logger.error("",e);
					lastException=e;
				}
			}

			@SuppressWarnings("unchecked")
			List<String> aggregatedIDsListDel = (List<String>) CollectionUtils.subtract(aggregatedIDsListCur, aggregatedIDsListNew);
			snapshotCount=0;
			makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brandCode, String.format("Deleting aggregated element from ElasticSearch index"));
			for (String aggregatedID : aggregatedIDsListDel) {
				try {
					logger.info("Deleting aggregated element from ElasticSearch index");
					snapshotCount++;
					snapshotCount=makeSnapshot(snapshotCount, processName, brandCode,String.format("Deleting aggregated element from ElasticSearch index aggregatedID:%s",aggregatedID));					
					DeleteRequestBuilder deleteRequest = client.prepareDelete(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_AGGREGATED_TOURS_TYPE, aggregatedID);
					DeleteResponse deleteResponse = deleteRequest.execute().actionGet();
					logger.info("CataloguedTourcode: [" + aggregatedID + "]; is deleted: [" + deleteResponse.isFound() + "]; version: [" + deleteResponse.getVersion() + "]");

				} catch (Exception e) {
					indexSynchronizerVO.getAggregatedIDsListNotDeleted().add(StringUtils.substringBeforeLast(aggregatedID, "_"));
					logger.error("",e);
					lastException=e;
				}
			}	
			
			indexSynchronizerVO.setAggregatedCount(searchService.countIndexedSearchToursAggregatedDocuments(brandCode));
			indexSynchronizerVO.setDocumentCount(searchService.countIndexedSearchToursDocuments(brandCode));
			
		} catch (Exception e) {
			indexSynchronizerVO.setUnexpectedErrorOccurred(true);
			logger.error("Synchronization of ElasticSearch engine has failed: ", e); 
			throw new IndexSynchronizerServiceException(e);
		} finally {
			statisticsBean.registerStopLongIndexation(brandCode, statsSerial);
		}
	}

	@Override
	public void synchronize_delete(ProcessName processName, Brand brand, Set<Long> tourIds,IndexSynchronizerVO indexSynchronizerVO) throws IndexSynchronizerServiceException {
		long statsSerial = BrandInfo.SERIAL_WAITING;
		try {
			statsSerial = statisticsBean.registerStartShortIndexationDelete(brand.getCode());
			List<Long> listCrToClear=contentRepositoryDAO.getContentRepositoriesIdsToClear(tourIds,brand,RepositoryStatus.TIandTD);
			if(listCrToClear.size()== 0){
				indexSynchronizerVO.setAggregatedCount(searchService.countIndexedSearchToursAggregatedDocuments(brand.getCode()));
				indexSynchronizerVO.setDocumentCount(searchService.countIndexedSearchToursDocuments(brand.getCode()));
				return;
			}
				
			
			Client client = node.client();

			checkIndex(client);
									
			List<Long> idsExtended = contentRepositoryDAO.getExtendedCRIdsforSearchToursAggregated(listCrToClear, brand.getCode());
			List<Long> toursToReindex = (List<Long>)CollectionUtils.subtract(idsExtended, listCrToClear);
			
				
			Marshaller jaxbATMarshaller = createMarshaller(jaxbATContext);
			Marshaller jaxbTDSMarshaller = createMarshaller(jaxbTDSContext);
			Marshaller jaxbSCMarshaller = createMarshaller(jaxbSCContext);
			Marshaller jaxbTISMarshaller = jaxbTISContext.createMarshaller();
			
			//Unmarshaller jaxbTISUnmarshaller = jaxbTISContext.createUnmarshaller();
			Unmarshaller jaxbRTIUnmarshaller = jaxbRTIContext.createUnmarshaller();
			Unmarshaller jaxbTDUnmarshaller = jaxbTDContext.createUnmarshaller();
			Unmarshaller jaxbSCSUnmarshaller = jaxbSCSContext.createUnmarshaller();
			
			Set<String> aggregatedSet = new HashSet<String>();
			
			int snapshotCount=0;
			makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brand.getCode(), String.format("Delete elements from ElasticSearch index"));
			for (Long id : listCrToClear) {
	            ContentRepository contentRepository = contentRepositoryDAO.find(id);
	            
	            com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo tourInfo = (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo) jaxbRTIUnmarshaller.unmarshal(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getTourInfoXML().getBytes(StandardCharsets.UTF_8)));
	    		TourDeparturesType tourDeparture = (TourDeparturesType) jaxbTDUnmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getTourDepartureXML().getBytes(StandardCharsets.UTF_8))), TourDeparturesType.class).getValue();

	    		for (SellingCompanyType sellingCompantTD : tourDeparture.getSellingCompanies().getSellingCompany()) {

	    			for (SellingCompany sellingCompanyTI : tourInfo.getSellingCompanies().getSellingCompany()) {

	    				if (sellingCompanyTI.getCode().equals(sellingCompantTD.getCode())) {

	    					String documentID = String.format(ID_TEMPLATE, contentRepository.getTourCode(), sellingCompantTD.getCode());
	    				
	    					try {
	    						logger.info("Deleting element from ElasticSearch index");
	    						snapshotCount++;
	    						snapshotCount=makeSnapshot(snapshotCount, processName, brand.getCode(),String.format("Deleting element from ElasticSearch index documentId:%s",documentID));	
	    						DeleteRequestBuilder deleteRequest = client.prepareDelete(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE, documentID);
	    						DeleteResponse deleteResponse = deleteRequest.execute().actionGet();

	    						logger.info("Tourcode: [" + documentID + "]; is deleted: [" + deleteResponse.isFound() + "]; version: [" + deleteResponse.getVersion() + "]");

	    					} catch (Exception e) {
	    						indexSynchronizerVO.getDocumentIDsListNotDeleted().add(documentID);
	    						logger.error("",e);
	    						lastException=e;
	    					}
	    				}		
	    			}
	    		}
	    		String aggregatedID = String.format(ID_TEMPLATE, contentRepository.getCataloguedTourCode(), brand.getCode());
	    		aggregatedSet.add(aggregatedID);
				
			}
			
			snapshotCount=0;
			makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brand.getCode(), String.format("Delete elements from ElasticSearch aggregated index"));
			for(String aggId: aggregatedSet) {
				try {
					
					logger.info("Deleting aggregated element from ElasticSearch index");
					snapshotCount++;
					snapshotCount=makeSnapshot(snapshotCount, processName, brand.getCode(),String.format("Deleting aggregated element from ElasticSearch index aggregatedID:%s",aggId));					
					DeleteRequestBuilder deleteRequest = client.prepareDelete(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_AGGREGATED_TOURS_TYPE, aggId);
					DeleteResponse deleteResponse = deleteRequest.execute().actionGet();

					logger.info("CataloguedTourcode: [" + aggId + "]; is deleted: [" + deleteResponse.isFound() + "]; version: [" + deleteResponse.getVersion() + "]");

				} catch (Exception e) {
					indexSynchronizerVO.getAggregatedIDsListNotDeleted().add(StringUtils.substringBeforeLast(aggId, "_"));
					logger.error("",e);
					lastException=e;
				}
			}
			
			AggregatedTours aggregatedTours = new AggregatedTours();
			String prevCataloguedTourCode = "fake";
			TourInfoMapper tourInfoMapper = new TourInfoMapper();
			
			int index = 0;
			snapshotCount=0;
			makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brand.getCode(), String.format("Add elements to ElasticSearch index"));
			for (Long id : toursToReindex) {
                ContentRepository contentRepository = contentRepositoryDAO.find(id);

                snapshotCount++;
                snapshotCount=makeSnapshot(snapshotCount, processName, brand.getCode(), String.format(INFO_TOUR_INDEXING, contentRepository.getTourCode(), ++index, toursToReindex.size()));
                
                prevCataloguedTourCode = executeIndexingForTour(brand.getCode(), contentRepository, jaxbRTIUnmarshaller, jaxbTDUnmarshaller, jaxbSCSUnmarshaller, 
            			jaxbTISMarshaller, jaxbTDSMarshaller, jaxbSCMarshaller, jaxbATMarshaller, aggregatedTours, prevCataloguedTourCode, 
            			client, null, null, indexSynchronizerVO, tourInfoMapper, true);
                
                //contentRepositoryDAO.flush();
        		contentRepositoryDAO.evictEntity(contentRepository);

			}
					
			if (aggregatedTours.getToursList().size() > 0) {

				logger.info("Indexing the last element of aggregated tours in ElasticSearch");				
				try {					
					makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brand.getCode(),String.format("Indexing the last element of aggregated tours in ElasticSearch"));						
					exportAggregatedTour(brand.getCode(), aggregatedTours, jaxbATMarshaller, client);
				} catch (Exception e) {
					indexSynchronizerVO.getAggregatedIDsListNotAdded().add(aggregatedTours.getCataloguedTourCode());
					logger.error("",e);
					lastException=e;
				}
			} 
			
			indexSynchronizerVO.setAggregatedCount(searchService.countIndexedSearchToursAggregatedDocuments(brand.getCode()));
			indexSynchronizerVO.setDocumentCount(searchService.countIndexedSearchToursDocuments(brand.getCode()));	
			
		}catch(Exception e) {
			logger.error("Synchronization of ElasticSearch engine has failed: ", e);
			indexSynchronizerVO.setUnexpectedErrorOccurred(true);
			IndexSynchronizerServiceException indexException = new IndexSynchronizerServiceException(e);
			throw indexException;			
		} finally {
			statisticsBean.registerStopShortIndexationDelete(brand.getCode(), statsSerial);
		}
	}
	
	@Override
	public IndexSynchronizerVO getStateOfIndex(Map<Date, IndexSynchronizerVO> map,String brandCode){
		
		Preconditions.checkArgument(map!=null);		
		IndexSynchronizerVO result=new IndexSynchronizerVO();				
		if(map.isEmpty()){
			try{				
				result.setIndexingAll(false);				
				result.setAggregatedCount(searchService.countIndexedSearchToursAggregatedDocuments(brandCode));
				result.setDocumentCount(searchService.countIndexedSearchToursDocuments(brandCode));				
			}
			catch(Exception e){
				logger.error("",e);
				result.setUnexpectedErrorOccurred(true);
			}
		}		
		else{				
			Set<Date> keys=Sets.newTreeSet(map.keySet());		
			for (Date key : keys) {
				IndexSynchronizerVO localVO=map.get(key);
					if(localVO.getIndexingAll()){
						result.getAggregatedIDsListNotAdded().clear();
						result.getAggregatedIDsListNotDeleted().clear();
						result.getDocumentIDsListNotAdded().clear();
						result.getDocumentIDsListNotDeleted().clear();
					}
							
					for (String tour : Lists.newArrayList(localVO.getAggregatedIDsListNotAdded())) {
						result.getAggregatedIDsListNotAdded().add(tour);
						result.getAggregatedIDsListNotDeleted().remove(tour);
					}
					
					for (String tour : Lists.newArrayList(localVO.getAggregatedIDsListNotDeleted())) {
						result.getAggregatedIDsListNotAdded().remove(tour);
						result.getAggregatedIDsListNotDeleted().add(tour);
					}
					
					for (String tour : Lists.newArrayList(localVO.getDocumentIDsListNotAdded())) {
						result.getDocumentIDsListNotAdded().add(tour);
						result.getDocumentIDsListNotDeleted().remove(tour);
					}
					
					for (String tour : Lists.newArrayList(localVO.getDocumentIDsListNotDeleted())) {
						result.getDocumentIDsListNotAdded().remove(tour);
						result.getDocumentIDsListNotDeleted().add(tour);
					}
					
					result.setIndexingAll(localVO.getIndexingAll());
					result.setDocumentCount(localVO.getDocumentCount());
					result.setAggregatedCount(localVO.getAggregatedCount());
										
					if(localVO.getUnexpectedErrorOccurred())
						result.setUnexpectedErrorOccurred(localVO.getUnexpectedErrorOccurred());	
			}		
		}
		return result;
	}
	
	@Deprecated
	@Override
	public IndexSynchronizerVO margeForShortIndexsynchronizeVO(Map<Date, IndexSynchronizerVO> map){
		
		if(map==null )
			return null;
		if(map.size()==0)
			return null;
				
		Set<Date> keys=Sets.newTreeSet(map.keySet());
		IndexSynchronizerVO result=new IndexSynchronizerVO();
		result.setIndexingAll(false);
		
		for (Date key : keys) {
			IndexSynchronizerVO localVO=map.get(key);
			result.getAggregatedIDsListNotAdded().addAll(localVO.getAggregatedIDsListNotAdded());
			result.getAggregatedIDsListNotDeleted().addAll(localVO.getAggregatedIDsListNotDeleted());
			result.getDocumentIDsListNotAdded().addAll(localVO.getDocumentIDsListNotAdded());
			result.getDocumentIDsListNotDeleted().addAll(localVO.getDocumentIDsListNotDeleted());
			
			result.setDocumentCount(localVO.getDocumentCount());
			result.setAggregatedCount(localVO.getAggregatedCount());
			
			if(localVO.getUnexpectedErrorOccurred())
				result.setUnexpectedErrorOccurred(localVO.getUnexpectedErrorOccurred());	
		}				
		return result;
	}
	
	private void deleteSellingCompaniesFromIndex(Client client,String brandCode, String tourCode, List<String> sellingCompanyCodes) {
		if(sellingCompanyCodes.size() > 0) {
			List<FilterBuilder> filterSClist = new ArrayList<FilterBuilder>();
			for(String term: sellingCompanyCodes) {
				if(StringUtils.isNotBlank(term)) {
					FilterBuilder f = FilterBuilders.queryFilter(QueryBuilders.queryString(term).field(ST_PATH_SELLING_COMPANY_CODE));
					filterSClist.add(f);
				}
			}
			FilterBuilder scToDeleteFilter = FilterBuilders.andFilter(FilterBuilders.queryFilter(QueryBuilders.queryString(tourCode).field(ST_PATH_TOUR_CODE)),
					FilterBuilders.queryFilter(QueryBuilders.queryString(brandCode).field(ST_PATH_BRAND_CODE)),
					FilterBuilders.notFilter(FilterBuilders.orFilter(filterSClist.toArray(new FilterBuilder[filterSClist.size()]))));
			DeleteByQueryRequestBuilder deleteRequest = client.prepareDeleteByQuery(IndexSynchronizerService.ES_INDEX_NAME)
					.setQuery(QueryBuilders.filteredQuery(QueryBuilders.matchAllQuery(), scToDeleteFilter)).setTypes(IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE);
			DeleteByQueryResponse deleteResponse = deleteRequest.execute().actionGet();
		
		}
		
		
	}
}
