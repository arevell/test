package com.ttc.ch2.bl.departure.habs;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.wsout.habs.itropicsbuildws.ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO;
import com.wsout.habs.itropicsbuildws.ITropicsBuild;
import com.wsout.habs.itropicsbuildws.ITropicsBuildWS;
import com.wsout.habs.itropicsbuildws.WsDeparturesVO;
import com.wsout.habs.itropicsbuildws.WsInGetTourDatesAndRatesVO;
import com.wsout.habs.itropicsbuildws.WsInGetToursOfBrandsVO;
import com.wsout.habs.itropicsbuildws.WsInGetToursWithSCListVO;
import com.wsout.habs.itropicsbuildws.WsToursOfBrandsVO;
import com.wsout.habs.itropicsbuildws.WsToursWithSCListVO;



@Service
public class HabsTourDepartureServiceImpl implements HabsTourDepartureService {

	private static final Logger logger = LoggerFactory.getLogger(HabsTourDepartureServiceImpl.class);

	private final static String ERROR_WEBSERVICE_UNAVAILABLE = "WebService .../tropics/TropicsBuildWS is not available";
	private final static String ERROR_WEBSERVICE_PROBLEM = "Problem with WebService .../tropics/TropicsBuildWS";
	private final static String ERROR_SOCKET_EXCEPTION = "SocketException problem request count %s";

	private final static int RE_QUERY_MAX_ATTEMPTS = 2;

	@Value("${endpoint.tropics_build_ws}")
	private String endpoint; 

	private ITropicsBuildWS port;


	public ITropicsBuildWS getPort() throws HabsTourDepartureServiceException {

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

			throw new HabsTourDepartureServiceException(e);

		} catch (Exception e) {

			logger.error(ERROR_WEBSERVICE_PROBLEM, e);
			throw new HabsTourDepartureServiceException(e);
		}

		logger.trace("TourDepartureServiceImpl:getPort-end");

		return port;
	}

	@Override
	public WsToursWithSCListVO getToursWithSCList(String brandCode) throws HabsTourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getToursWithSCList-start");

		WsToursWithSCListVO result = getToursWithSCListLocal(brandCode, 0);

		logger.trace("TourDepartureServiceImpl:getToursWithSCList-end");

		return result;
	}

	@Override
	public WsToursOfBrandsVO getToursOfBrands(List<String> brandsCodesList) throws HabsTourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getToursOfBrands-start");

		WsToursOfBrandsVO result = getToursOfBrandsLocal(brandsCodesList, 0);

		logger.trace("TourDepartureServiceImpl:getToursOfBrands-end");

		return result;
	}

	@Override
	public WsDeparturesVO getTourDatesAndRates(String tourCode, String brandSellingCompanyCode, String apiKey) throws HabsTourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getTourDatesAndRates-start");

		WsDeparturesVO result = getTourDatesAndRatesLocal(tourCode, brandSellingCompanyCode, apiKey, 0);

		logger.trace("TourDepartureServiceImpl:getTourDatesAndRates-end");

		return result;
	}


	private WsToursWithSCListVO getToursWithSCListLocal(String brandCode, int count) throws HabsTourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getToursWithSCListLocal-start");

		try {

			WsInGetToursWithSCListVO request = new WsInGetToursWithSCListVO();
			request.setBrandCode(brandCode);

			WsToursWithSCListVO tours = getPort().getToursWithSCList(request);
			
			Preconditions.checkState(tours!=null,String.format("System expected response from service '%s'","getToursWithSCList"));
			checkErrorsInWs("getToursWithSCList", tours.isSuccessful(), tours.getErrorMessage(), tours.getErrorMessagesArray());

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
					throw new HabsTourDepartureServiceException(String.format(ERROR_SOCKET_EXCEPTION, count), e);
				}

			} else {

				logger.trace("TourDepartureServiceImpl:getToursWithSCListLocal-end");
				throw new HabsTourDepartureServiceException(e);
			}

		} catch (Exception e) {

			logger.trace("TourDepartureServiceImpl:getToursWithSCListLocal-end");
			throw new HabsTourDepartureServiceException(e);
		}
	}

	private WsToursOfBrandsVO getToursOfBrandsLocal(List<String> brandsCodesList, int count) throws HabsTourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-start");

		try {

			WsInGetToursOfBrandsVO request = new WsInGetToursOfBrandsVO();
			request.getBrandCode().addAll(brandsCodesList);

			WsToursOfBrandsVO tours = getPort().getToursOfBrands(request);
			
			Preconditions.checkState(tours!=null,String.format("System expected response from service '%s'","getToursOfBrands"));
			checkErrorsInWs("getToursOfBrands", tours.isSuccessful(), tours.getErrorMessage(), tours.getErrorMessagesArray());
			
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
					throw new HabsTourDepartureServiceException(String.format(ERROR_SOCKET_EXCEPTION, count), e);
				}

			} else {

				logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-end");
				throw new HabsTourDepartureServiceException(e);
			}

		}catch (HabsTourDepartureServiceException e) {
			logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-end");
			throw e;
		}		
		catch (Exception e) {

			logger.trace("TourDepartureServiceImpl:getToursOfBrandsLocal-end");
			throw new HabsTourDepartureServiceException(e);
		}
	}

	private WsDeparturesVO getTourDatesAndRatesLocal(String tourCode, String brandSellingCompanyCode, String apiKey, int count) throws HabsTourDepartureServiceException {

		logger.trace("TourDepartureServiceImpl:getTourDatesAndRatesLocal-start");

		try {

			WsInGetTourDatesAndRatesVO request = new WsInGetTourDatesAndRatesVO();
			request.setApiKey(apiKey);
			request.setTourCode(tourCode);
			request.setSellingCompanyCode(brandSellingCompanyCode);

			WsDeparturesVO result = getPort().getTourDatesAndRates(request);
			
			Preconditions.checkState(result!=null,String.format("System expected response from service '%s'","getTourDatesAndRates"));
			checkErrorsInWs("getTourDatesAndRates", result.isSuccessful(), result.getErrorMessage(), result.getErrorMessagesArray());

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
					throw new HabsTourDepartureServiceException(String.format(ERROR_SOCKET_EXCEPTION, count), e);
				}

			} else {

				logger.trace("TourDepartureServiceImpl:getTourDatesAndRatesLocal-end");
				throw new HabsTourDepartureServiceException(e);
			}

		} catch (Exception e) {

			logger.trace("TourDepartureServiceImpl:getTourDatesAndRatesLocal-end");
			throw new HabsTourDepartureServiceException(e);
		}
	}
	
	private void checkErrorsInWs(String wsServiceName,boolean successFlag,String errorMessage,List<ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO> errList) throws HabsTourDepartureServiceException{
		
		if(successFlag==false || StringUtils.isNotEmpty(errorMessage) || errList.isEmpty()==false){			
			String msg=String.format("Tropics return error from service: '%s' \n", wsServiceName);  
			if(StringUtils.isNotEmpty(errorMessage)){
				msg+=String.format("Main error: %s \n",errorMessage);
			}			
			if(errList.isEmpty()==false){					
				msg+="List errors:\n";
				for (ComTropicsWebserviceITropicsVoCurrentCommonWSErrorMessagesVO err :errList) {
					String localResult=StringUtils.substring(err.getErrorMessage(), 0,200);		
					if(StringUtils.isNotEmpty(err.getErrorMessage()) &&  StringUtils.isNotEmpty(localResult) &&  localResult.length()<err.getErrorMessage().length()){
						localResult = localResult+" ......";
					}
					
					msg+=String.format("\tCode: %s - error message: %s \n",err.getErrorCode(),localResult);							
					for (String responseMsg : err.getResponseMessages()) {
						msg+="\t\t"+responseMsg+"\n";
					}		
				}						
			}		
			
			String result=StringUtils.substring(msg, 0,3800);
			throw new HabsTourDepartureServiceException(result.length()<msg.length() ? result+" ......":result);		
		}
	}
}
