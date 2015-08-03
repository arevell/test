package com.ttc.ch2.api.ccapi.v1.weave1;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBResult;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travcorp.contenthub.tour_data._2010._11._1.TourDetailsFull;
import com.travcorp.contenthub.tour_data._2010._11._1.TourDetailsFullResponse;
import com.ttc.ch2.api.ccapi.DataNotFoundException;
import com.ttc.ch2.api.ccapi.v1.TransformationSource;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttsl.marketlocalisedtourdata._2010._11._1.MarketLocalisedTourData;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.Departure;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.DeparturePricing;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationDepartureInfo;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationPricing;
import com.ttsl.tourinfo._2010._08._2.SellingCompany;
import com.ttsl.tourinfo._2010._08._2.TourInfo;

@Service
public class V1WeaveService {

	private static final Logger logger = LoggerFactory.getLogger(V1WeaveService.class);

	private static final Logger activityLogger = LoggerFactory.getLogger("ch2.activity.ConsolidatedContentAPIv1");

	public static final String TARGET_NAMESPACE = "http://contenthub.travcorp.com/tour_data/2010/11/1.0";

	private static final String PATH_MARKET_LOCALISED_TRANSFORM = "/com/ttc/ch2/api/ccapi/v1/MarketLocalisedTourData.1.0.xsl";
	private static final String NAMESPACE_TRANSFORMATION_SOURCE = "com.ttc.ch2.api.ccapi.v1";
	private static final String NAMESPACE_MARKET_LOCALISED_TOUR_DEPARTURE = "com.ttsl.marketlocalisedtourdata._2010._11._1";
	private static final String ERROR_GET_TOUR_DETAILS_INVALID_DATA = "MarketVariationCode:%s not valid for SellingCompany:%s";
	private static final String ERROR_GET_TOUR_DETAILS_INTERNAL = "Unable to complete request tourDetailsFull(): [MarketVariationCode:%s, SellingCompany:%s]";

	private JAXBContext jaxbTIContext;
	private JAXBContext jaxbDIContext;
	private JAXBContext jaxbTSContext;
	private JAXBContext jaxbTDContext;

	@Inject
	private ContentRepositoryService contentRepositoryService;

	@Inject
	private SellingCompanyDAO sellingCompanyDAO;

	@PostConstruct
	public void init() throws JAXBException {

		jaxbTIContext = JAXBContext.newInstance(new Class[] { TourInfo.class });
		jaxbDIContext = JAXBContext.newInstance(new Class[] { MarketVariationDepartureInfo.class });
		jaxbTSContext = JAXBContext.newInstance(NAMESPACE_TRANSFORMATION_SOURCE);
		jaxbTDContext = JAXBContext.newInstance(NAMESPACE_MARKET_LOCALISED_TOUR_DEPARTURE);
	}

	public TourDetailsFullResponse getTourDetailsFull(TourDetailsFull request) {

		TourDetailsFullResponse response = new TourDetailsFullResponse();

		String tourCode = request.getMarketVariationCode();
		String sellingCompanyCode = request.getSellingCompanyCode();

		try {

			com.ttc.ch2.domain.SellingCompany sellingCompany = sellingCompanyDAO.findBySellingCompanyCode(sellingCompanyCode);

			if (sellingCompany == null || sellingCompany.getBrand() == null) {
				throw new DataNotFoundException(String.format(ERROR_GET_TOUR_DETAILS_INVALID_DATA, tourCode, sellingCompanyCode));
			}

			ContentRepository contentRepository = contentRepositoryService.findByTourCodeWithXML(tourCode,sellingCompany.getBrand().getCode());

			if (contentRepository == null ||
				StringUtils.isBlank(contentRepository.getXmlContentRepository().get(0).getOldTourInfoXML()) ||
				StringUtils.isBlank(contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML())) {

				throw new DataNotFoundException(String.format(ERROR_GET_TOUR_DETAILS_INVALID_DATA, tourCode, sellingCompanyCode));
			}

			ByteArrayInputStream tourInfoStream = new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getOldTourInfoXML().getBytes(StandardCharsets.UTF_8));
			ByteArrayInputStream departureInfoStream = new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML().getBytes(StandardCharsets.UTF_8));

			Unmarshaller jaxbTIUnmarshaller = jaxbTIContext.createUnmarshaller();
			Unmarshaller jaxbDIUnmarshaller = jaxbDIContext.createUnmarshaller();

			TourInfo tourInfo = (TourInfo) jaxbTIUnmarshaller.unmarshal(tourInfoStream);

			@SuppressWarnings("unchecked")
			MarketVariationDepartureInfo departureInfo = ((JAXBElement<MarketVariationDepartureInfo>) jaxbDIUnmarshaller.unmarshal(departureInfoStream)).getValue();

			if (!isValidSellingCompany(tourInfo, departureInfo, sellingCompanyCode)) {
				throw new DataNotFoundException(String.format(ERROR_GET_TOUR_DETAILS_INVALID_DATA, tourCode, sellingCompanyCode));
			}

			TransformationSource transformationSource = new TransformationSource();
			transformationSource.setBrandCode(sellingCompanyCode.substring(0, 2));
			transformationSource.setSellingCompanyCode(sellingCompanyCode);
			transformationSource.setTourInfo(tourInfo);
			transformationSource.setMarketVariationDepartureInfo(departureInfo);

			JAXBSource source = new JAXBSource(jaxbTSContext, transformationSource);
			JAXBResult result = new JAXBResult(jaxbTDContext);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer(new StreamSource(getClass().getResourceAsStream(PATH_MARKET_LOCALISED_TRANSFORM)));
			transformer.transform(source, result);

			response.setMarketLocalisedTourData((MarketLocalisedTourData) result.getResult());

			activityLogger.info("USER: {}  called getTourDetailsFull() for selling company: {} ", SecurityHelper.getLoginSilent(), sellingCompanyCode);

		} catch (Exception e) {

			logger.error(String.format(ERROR_GET_TOUR_DETAILS_INTERNAL, tourCode, sellingCompanyCode), e);
			throw new RuntimeException(e);
		}

		return response;
	}

	private static boolean isValidSellingCompany(TourInfo tourInfo, MarketVariationDepartureInfo departureInfo, String sellingCompanyCode) {

		boolean foundInTourInfo = false;

		for (SellingCompany sellingCompany : tourInfo.getMetadata().getSellingCompanies().getSellingCompany()) {
			if (sellingCompanyCode.equals(sellingCompany.getCode())) {
				foundInTourInfo = true;
				break;
			}
		}

		boolean foundInMarketVariationPricing = false;

		for (MarketVariationPricing marketVariationPricing : departureInfo.getMarketVariationPricings().getMarketVariationPricing()) {
			if (sellingCompanyCode.equals(marketVariationPricing.getSellingCompanyCode())) {

				foundInMarketVariationPricing = true;
				break;
			}
		}

		boolean foundInDeparturePricing = false;

		for (Departure departure : departureInfo.getDepartures().getValue().getDeparture()) {
			for (DeparturePricing departurePricing : departure.getDeparturePricings().getDeparturePricing()) {
				if (sellingCompanyCode.equals(departurePricing.getSellingCompanyCode())) {
					foundInDeparturePricing = true;
					break;
				}
			}

			if (foundInDeparturePricing) { break; }
		}

		return (foundInTourInfo && foundInMarketVariationPricing && foundInDeparturePricing);
	}
}
