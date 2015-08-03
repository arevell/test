package com.ttc.ch2.api.ccapi;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.elasticsearch.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.AbstractEndpointExceptionResolver;
import org.springframework.ws.server.endpoint.MethodEndpoint;





import com.travcorp.contenthub.tour_data._2010._11._1.TourDetailsFullResponse;
import com.travelcorp.ccapi.SearchToursResponse;
import com.ttc.ch2.api.ccapi.v3.GetBrochureResponse;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedResponse;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesResponse;
import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullResponse;
import com.ttc.ch2.api.ccapi.v3.MethodExceptionResolverV3;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedResponse;
import com.ttc.ch2.api.ccapi.v3.GetTourDataUploadStatusResponse;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoResponse;

public class EndpointExceptionResolver extends AbstractEndpointExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(EndpointExceptionResolver.class);

	private static final String BEAN_NAME_CCAPI_V1 = "consolidatedContentAPIv1";
	private static final String BEAN_NAME_CCAPI_V3 = "consolidatedContentAPIv3";
	private static final String ERROR_NOT_REGISTERED_EXCEPTION = "Not registred exception resolver for Method endpoint -> %s.%s";
	private static final String ERROR_NOT_SUPPORTED_ENDPOINT = "Unsupported endpoint -> %s";

	private Map<String, Class<?>> exceptionResolver = Maps.newHashMap();

	@PostConstruct
	public void init() {
		exceptionResolver.put(BEAN_NAME_CCAPI_V1 + ".getTourDetailsFull", TourDetailsFullResponse.class);
		exceptionResolver.put(BEAN_NAME_CCAPI_V1 + ".searchTours", SearchToursResponse.class);
		exceptionResolver.put(BEAN_NAME_CCAPI_V3 + ".getBrochure", GetBrochureResponse.class);
		exceptionResolver.put(BEAN_NAME_CCAPI_V3 + ".getContinentsAndCountriesVisited", GetContinentsAndCountriesVisitedResponse.class);
		exceptionResolver.put(BEAN_NAME_CCAPI_V3 + ".getTourCategories", GetTourCategoriesResponse.class);
		exceptionResolver.put(BEAN_NAME_CCAPI_V3 + ".getTourDetailsFull", GetTourDetailsFullResponse.class);
		exceptionResolver.put(BEAN_NAME_CCAPI_V3 + ".searchTours", com.ttc.ch2.api.ccapi.v3.SearchToursResponse.class);
		exceptionResolver.put(BEAN_NAME_CCAPI_V3 + ".searchToursAggregated", SearchToursAggregatedResponse.class);
		exceptionResolver.put(BEAN_NAME_CCAPI_V3 + ".uploadTourInfo", UploadTourInfoResponse.class);
		exceptionResolver.put(BEAN_NAME_CCAPI_V3 + ".getSnapshotUploadTour", GetTourDataUploadStatusResponse.class);
	}

	protected boolean resolveExceptionInternal(MessageContext messageContext, Object endpoint, Exception exception) {

		if (endpoint instanceof MethodEndpoint) {

			MethodEndpoint methodEndpoint = (MethodEndpoint) endpoint;

			String methodEndpointBeanName = methodEndpoint.getBean().toString();
			String methodEndpointFullName = methodEndpointBeanName + "." + methodEndpoint.getMethod().getName();

			if (exceptionResolver.containsKey(methodEndpointFullName)) {

				// The old CCAPIv1 uses standard SimpleSoapExceptionResolver, thus no need to create our custom ExceptionResolver.
				if (BEAN_NAME_CCAPI_V1.equals(methodEndpointBeanName)) {
					return false;
				}

				Class<?> classResponse = exceptionResolver.get(methodEndpointFullName);
				return new MethodExceptionResolverV3().resolveExceptionInternal(messageContext, endpoint, classResponse, exception);

			} else {

				logger.error(String.format(ERROR_NOT_REGISTERED_EXCEPTION, methodEndpoint.getBean(), methodEndpoint.getMethod().getName()));
				return false;
			}

		} else {

			logger.error(String.format(ERROR_NOT_SUPPORTED_ENDPOINT, endpoint.getClass().getName()));
			return false;
		}
	}
}
