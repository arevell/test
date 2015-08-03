package com.ttc.ch2.search.export;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.io.ByteStreams;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.SellingCompanyType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompany;
import com.ttc.ch2.bl.departure.ImportStatusServicePartial;
import com.ttc.ch2.bl.upload.UploadStatusServicePartial;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.tour.ContentRepository;
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


@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class IndexSynchronizerServiceImpl implements IndexSynchronizerService {

	private static final Logger logger = LoggerFactory.getLogger(IndexSynchronizerServiceImpl.class);

	private static final String PATH_TOUR_INFO_BRAND_CODE = "TourInfoSimp.BrandCode";
	private static final String PATH_TOUR_INFO_BRAND_CODE_AGG = "ToursList.TourInfoSimp.BrandCode";

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
	private Node node;

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
	public IndexSynchronizerVO synchronize(ProcessName processName, String brandCode) throws IndexSynchronizerServiceException {

		IndexSynchronizerVO indexSynchronizerVO = new IndexSynchronizerVO();

		try {

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

			List<ContentRepository> contentRepositoryList = contentRepositoryDAO.getContentRepositoriesForExport(brandCode);
			List<Long> ids = new ArrayList<Long>();
			for (ContentRepository contentRepository : contentRepositoryList) {
			    Long id = contentRepository.getId();
			    ids.add(id);
			    contentRepositoryDAO.evictEntity(contentRepository);
            }
			contentRepositoryList.clear();

			Marshaller jaxbATMarshaller = createMarshaller(jaxbATContext);
			Marshaller jaxbRTIMarshaller = createMarshaller(jaxbRTIContext);
			Marshaller jaxbTDSMarshaller = createMarshaller(jaxbTDSContext);
			Marshaller jaxbSCMarshaller = createMarshaller(jaxbSCContext);
			Marshaller jaxbTISMarshaller = jaxbTISContext.createMarshaller();
			
			//Unmarshaller jaxbTISUnmarshaller = jaxbTISContext.createUnmarshaller();
			Unmarshaller jaxbRTIUnmarshaller = jaxbRTIContext.createUnmarshaller();
			Unmarshaller jaxbTDUnmarshaller = jaxbTDContext.createUnmarshaller();
			Unmarshaller jaxbSCSUnmarshaller = jaxbSCSContext.createUnmarshaller();

			TourInfoMapper tourInfoMapper = new TourInfoMapper();
			AggregatedTours aggregatedTours = null;
			String prevCataloguedTourCode = "fake";

			Exception lastException=null;	
			int index = 0;
			int snapshotCount=0;
			makeSnapshot(MAX_COUNT_OF_SNAPSHOT, processName, brandCode, String.format("Add elements to ElasticSearch index"));
			for (Long id : ids) {
                ContentRepository contentRepository = contentRepositoryDAO.find(id);

                snapshotCount++;
                snapshotCount=makeSnapshot(snapshotCount, processName, brandCode, String.format(INFO_TOUR_INDEXING, contentRepository.getTourCode(), ++index, ids.size()));                

				com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo tourInfo = (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo) jaxbRTIUnmarshaller.unmarshal(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getTourInfoXML().getBytes(StandardCharsets.UTF_8)));
				TourDeparturesType tourDeparture = (TourDeparturesType) jaxbTDUnmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getTourDepartureXML().getBytes(StandardCharsets.UTF_8))), TourDeparturesType.class).getValue();

				for (SellingCompanyType sellingCompantTD : tourDeparture.getSellingCompanies().getSellingCompany()) {

					for (SellingCompany sellingCompanyTI : tourInfo.getSellingCompanies().getSellingCompany()) {

						if (sellingCompanyTI.getCode().equals(sellingCompantTD.getCode())) {

							String documentID = String.format(ID_TEMPLATE, contentRepository.getTourCode(), sellingCompantTD.getCode());

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

									if (aggregatedTours != null) {

										try {
											exportAggregatedTour(brandCode, aggregatedTours, jaxbATMarshaller, client);
										} catch (Exception e) {
											indexSynchronizerVO.getAggregatedIDsListNotAdded().add(aggregatedTours.getCataloguedTourCode());
										}

										aggregatedIDsListNew.add(String.format(ID_TEMPLATE, aggregatedTours.getCataloguedTourCode(), brandCode));
									}

									prevCataloguedTourCode = tourInfoNormalized.getCataloguedTour().getCode();

									aggregatedTours = new AggregatedTours();
									aggregatedTours.setCataloguedTourCode(tourInfoNormalized.getCataloguedTour().getCode());
									aggregatedTours.setCataloguedTourName(tourInfoNormalized.getCataloguedTour().getName());
								}

								Tour tour = new Tour();
								tour.setTourDeparturesSimp(tourDeparturesSimpType);
								tour.setTourInfoSimp(tourInfoSimp);
								

								aggregatedTours.getToursList().add(tour);

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

								documentIDsListNew.add(documentID);

							} catch (Exception e) {
								indexSynchronizerVO.getDocumentIDsListNotAdded().add(documentID);
								logger.error("",e);
								lastException=e;
							}
						}
					}
				}
				
//				
				contentRepositoryDAO.flush();
				contentRepositoryDAO.evictEntity(contentRepository);
//				contentRepositoryDAO.clearSession();
			}

			
					
			if (aggregatedTours != null) {

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
			
			if(lastException!=null)
				throw lastException;

		} catch (Exception e) {

			logger.error("Synchronization of ElasticSearch engine has failed: ", e);

			PrintWriter printWriter = new PrintWriter(new StringWriter());
			printWriter.println("Synchronization failed. Stacktrace:");

			e.printStackTrace(printWriter); // XXX

			IndexSynchronizerServiceException indexException = new IndexSynchronizerServiceException(e);

			throw indexException;
		}

		return indexSynchronizerVO;
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
}
