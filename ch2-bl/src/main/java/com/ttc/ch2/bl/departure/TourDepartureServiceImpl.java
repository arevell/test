package com.ttc.ch2.bl.departure;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import facade.itropics.webservice.tropics.com.itropicsbuildws.ITropicsBuild;
import facade.itropics.webservice.tropics.com.itropicsbuildws.ITropicsBuildWS;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsDeparturesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsInGetTourDatesAndRatesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsInGetToursOfBrandsVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsInGetToursWithSCListVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursOfBrandsVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursWithSCListVO;

@Service
public class TourDepartureServiceImpl implements TourDepartureService {

	private static final Logger logger = LoggerFactory.getLogger(TourDepartureServiceImpl.class);

	private final static String ERROR_WEBSERVICE_UNAVAILABLE = "WebService .../tropics/TropicsBuildWS is not available";
	private final static String ERROR_WEBSERVICE_PROBLEM = "Problem with WebService .../tropics/TropicsBuildWS";
	private final static String ERROR_SOCKET_EXCEPTION = "SocketException problem request count %s";

	private final static int RE_QUERY_MAX_ATTEMPTS = 2;

	@Value("${endpoint.tropics_build_ws}")
	private String endpoint; 

	private ITropicsBuildWS port;


	public ITropicsBuildWS getPort() throws TourDepartureServiceException {

		if (port != null) {
			return port;
		}

		logger.trace("TourDepartureServiceImpl:getPort-start");

		try {

			URL baseUrl;
			baseUrl = facade.itropics.webservice.tropics.com.itropicsbuildws.ITropicsBuild.class.getResource(".");
			URL url = new URL(baseUrl, endpoint + "?wsdl");

			ITropicsBuild service = new ITropicsBuild(url, new QName("http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd", "ITropicsBuild"));
			port = service.getITropicsBuildWSPort();

		} catch (WebServiceException e) {

			if (e.getCause() instanceof IOException) {
				logger.error(ERROR_WEBSERVICE_UNAVAILABLE, e);
			} else {
				logger.error(ERROR_WEBSERVICE_PROBLEM, e);
			}

			throw new TourDepartureServiceException(e);

		} catch (Exception e) {

			logger.error(ERROR_WEBSERVICE_PROBLEM, e);
			throw new TourDepartureServiceException(e);
		}

		logger.trace("TourDepartureServiceImpl:getPort-end");

		return port;
	}

	@Override
	public WsToursWithSCListVO getToursWithSCList(String brandCode) throws TourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getToursWithSCList-start");

		WsToursWithSCListVO result = getToursWithSCListLocal(brandCode, 0);

		logger.trace("TourDepartureServiceImpl:getToursWithSCList-end");

		return result;
	}

	@Override
	public WsToursOfBrandsVO getToursOfBrands(List<String> brandsCodesList) throws TourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getToursOfBrands-start");

		WsToursOfBrandsVO result = getToursOfBrandsLocal(brandsCodesList, 0);

		logger.trace("TourDepartureServiceImpl:getToursOfBrands-end");

		return result;
	}

	@Override
	public WsDeparturesVO getTourDatesAndRates(String tourCode, String brandSellingCompanyCode, String apiKey) throws TourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getTourDatesAndRates-start");

		WsDeparturesVO result = getTourDatesAndRatesLocal(tourCode, brandSellingCompanyCode, apiKey, 0);

		logger.trace("TourDepartureServiceImpl:getTourDatesAndRates-end");

		return result;
	}


	private WsToursWithSCListVO getToursWithSCListLocal(String brandCode, int count) throws TourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getToursWithSCListLocal-start");

		try {

			WsInGetToursWithSCListVO request = new WsInGetToursWithSCListVO();
			request.setBrandCode(brandCode);

			WsToursWithSCListVO tours = getPort().getToursWithSCList(request);

			logger.trace("TourDepartureServiceImpl:getToursWithSCListLocal-end");
			return tours;

		} catch (WebServiceException e) {

			if (e.getCause() instanceof SocketException) {

				if (count < RE_QUERY_MAX_ATTEMPTS) {

					logger.trace("TourDepartureServiceImpl:getToursWithSCListLocal-end");
					WsToursWithSCListVO tours = getToursWithSCListLocal(brandCode, count + 1);
					return tours;

				} else {

					logger.trace("TourDepartureServiceImpl:getToursWithSCListLocal-end");
					throw new TourDepartureServiceException(String.format(ERROR_SOCKET_EXCEPTION, count), e);
				}

			} else {

				logger.trace("TourDepartureServiceImpl:getToursWithSCListLocal-end");
				throw new TourDepartureServiceException(e);
			}

		} catch (Exception e) {

			logger.trace("TourDepartureServiceImpl:getToursWithSCListLocal-end");
			throw new TourDepartureServiceException(e);
		}
	}

	private WsToursOfBrandsVO getToursOfBrandsLocal(List<String> brandsCodesList, int count) throws TourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-start");

		try {

			WsInGetToursOfBrandsVO request = new WsInGetToursOfBrandsVO();
			request.getBrandCode().addAll(brandsCodesList);

			WsToursOfBrandsVO tours = getPort().getToursOfBrands(request);

			logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-end");
			return tours;

		} catch (WebServiceException e) {

			if (e.getCause() instanceof SocketException) {

				if (count < RE_QUERY_MAX_ATTEMPTS) {

					logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-end");
					WsToursOfBrandsVO tours = getToursOfBrandsLocal(brandsCodesList, count + 1);
					return tours;

				} else {

					logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-end");
					throw new TourDepartureServiceException(String.format(ERROR_SOCKET_EXCEPTION, count), e);
				}

			} else {

				logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-end");
				throw new TourDepartureServiceException(e);
			}

		} catch (Exception e) {

			logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-end");
			throw new TourDepartureServiceException(e);
		}
	}

	private WsDeparturesVO getTourDatesAndRatesLocal(String tourCode, String brandSellingCompanyCode, String apiKey, int count) throws TourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getTourDatesAndRatesLocal-start");

		try {

			WsInGetTourDatesAndRatesVO request = new WsInGetTourDatesAndRatesVO();
			request.setApiKey(apiKey);
			request.setTourCode(tourCode);
			request.setSellingCompanyCode(brandSellingCompanyCode);

			WsDeparturesVO result = getPort().getTourDatesAndRates(request);

			logger.trace("TourDepartureServiceImpl:getTourDatesAndRatesLocal-end");
			return result;

		} catch (WebServiceException e) {

			if (e.getCause() instanceof SocketException) {

				if (count < RE_QUERY_MAX_ATTEMPTS) {

					logger.trace("TourDepartureServiceImpl:getTourDatesAndRatesLocal-end");
					WsDeparturesVO resDeparturesVO = getTourDatesAndRatesLocal(tourCode, brandSellingCompanyCode, apiKey, count + 1);
					return resDeparturesVO;

				} else {

					logger.trace("TourDepartureServiceImpl:getTourDatesAndRatesLocal-end");
					throw new TourDepartureServiceException(String.format(ERROR_SOCKET_EXCEPTION, count), e);
				}

			} else {

				logger.trace("TourDepartureServiceImpl:getTourDatesAndRatesLocal-end");
				throw new TourDepartureServiceException(e);
			}

		} catch (Exception e) {

			logger.trace("TourDepartureServiceImpl:getTourDatesAndRatesLocal-end");
			throw new TourDepartureServiceException(e);
		}
	}
}
