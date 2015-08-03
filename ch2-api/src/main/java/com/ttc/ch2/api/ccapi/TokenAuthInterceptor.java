package com.ttc.ch2.api.ccapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.MethodEndpoint;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ttc.ch2.common.AuthenticationHelper;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.exceptions.CompaniesAuthenticationException;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.auth.UserCCAPIDetails;
import com.ttc.ch2.domain.user.UserCCAPI;

public class TokenAuthInterceptor implements EndpointInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TokenAuthInterceptor.class);

	private static final String BEAN_NAME_CCAPI_V1 = "consolidatedContentAPIv1";
	private static final String SOAP_NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope/";
	private static final String ERROR_INCORRECT_BODY_TYPE = "Source of Body has incorrect type: %s";
	private static final String ERROR_UNSUPPORTED_MESSAGE_CONTEXT = "Unsupported MessageContext type: %s";
	private static final String ERROR_NO_SECURITY_KEY = "System not found securityKey element";
	private static final String ERROR_INVALID_SECURITY_KEY = "Invalid securityKey permission denied";
	private static final String ERROR_TO_MANY_SECURITY_KEYS = "System not found to many securityKey element";
	private static final String ERROR_FUNCTION_PERMISSION_DENIED = "Invalid function permission denied";
	private static final String ERROR_SELLING_COMPANY_PERMISSION_DENIED = "Unable to complete searchTours request: %s - SellingCompany: %s not allowed for this account";
	private static final String ERROR_UNSUPPORTED_SELLING_COMPANY = "Unsupported read selling company for Function:";

	private Map<FunctionType, String> functionElementMap = new HashMap<FunctionType, String>();

	@Inject
	private UserCCAPIDAO userCCAPIDAO;
	
//	private UserCCAPI mockUser=null;

	@PostConstruct
	public void init() {
		functionElementMap.put(FunctionType.GET_BROCHURE, "sellingCompanyCode");
		functionElementMap.put(FunctionType.GET_CONTINENTS_AND_COUNTRIES_VISITED, "sellingCompanies");
		functionElementMap.put(FunctionType.GET_TOUR_CATEGORIES, "sellingCompany");
		functionElementMap.put(FunctionType.GET_TOUR_DETAILS_FULL, "sellingCompany");
		functionElementMap.put(FunctionType.SEARCH_TOURS_AGGREGATED, "sellingCompanies");
		functionElementMap.put(FunctionType.SEARCH_TOURS_V1, "//*[local-name()='sellingCompanyCodes']/*[local-name()='string']");
		functionElementMap.put(FunctionType.SEARCH_TOURS_V3, "sellingCompanies");
		functionElementMap.put(FunctionType.TOUR_DETAILS_FULL, "sellingCompanyCode");
		
		
		// mock user to test performance load test -- to remove after test
//		mockUser=userCCAPIDAO.findByToken("token-xxx");
	}

	@Override
	public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
		logger.trace("TokenAuthInterceptor:handleRequest-start");
		WebServiceMessage msg = messageContext.getRequest();

		if (msg instanceof SoapMessage) {

			SoapMessage msgSoap = (SoapMessage) msg;

			if (msgSoap.getSoapBody().getSource() instanceof DOMSource) {

				String beanName = endpoint instanceof MethodEndpoint ? ((MethodEndpoint) endpoint).getBean().toString() : null;
				String namespace = StringUtils.substringBetween(msgSoap.toString(), "{", "}");
				String key = getKey(msgSoap.getDocument(), namespace);
				String function = getFunction(msgSoap.getDocument());

				FunctionType functionType = FunctionType.getValueBySoapName(function, beanName);
				List<String> companies = Lists.newArrayList();

				if (functionType != FunctionType.UPLOAD_TOUR_INFO) {
					companies = companieCodes(msgSoap.getDocument(), functionType, namespace);
				}

				authentication(key, functionType, companies);

			} else {
				logger.trace("TokenAuthInterceptor:handleRequest-end");
				throw new UnsupportedOperationException(String.format(ERROR_INCORRECT_BODY_TYPE, msgSoap.getSoapBody().getSource().getClass().getName()));
			}

		} else {
			logger.trace("TokenAuthInterceptor:handleRequest-end");
			throw new UnsupportedOperationException(String.format(ERROR_UNSUPPORTED_MESSAGE_CONTEXT, messageContext.getClass().getName()));
		}

		logger.trace("TokenAuthInterceptor:handleRequest-end");
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	private void authentication(String key, FunctionType function, List<String> companies) {

		// mock user to test performance load test -- to remove after test
		UserCCAPI user = userCCAPIDAO.findByToken(key);
//		UserCCAPI user = mockUser;

		if (user == null) {
			throw new TokenValidationException(ERROR_INVALID_SECURITY_KEY);
		}
		
		if (user.getEnabled()  == false) {
			throw new TokenValidationException(ERROR_INVALID_SECURITY_KEY);
		}

		if (AuthorityHelper.checkUserHasFunctionPermition(user, function) == false) {
			throw new TokenValidationException(ERROR_FUNCTION_PERMISSION_DENIED);
		}

		if (function != FunctionType.UPLOAD_TOUR_INFO) {
			try {
				AuthenticationHelper.companiesAuthentication(function, companies, user);
			} catch (CompaniesAuthenticationException e) {
				throw new TokenValidationException(BEAN_NAME_CCAPI_V1.equals(function.getBeanName()) ? String.format(ERROR_SELLING_COMPANY_PERMISSION_DENIED, e.getClass().getName(), e.getFirstInvalidSellingCompany()) : e.getMessage());
			}
		}

		UserCCAPIDetails usrDetails = new UserCCAPIDetails();
		usrDetails.setToken(key);

		Set<GrantedAuthority> grantedAuths = AuthorityHelper.getAllGrantedAuthoritiesForUser(user);
		Authentication auth = new UsernamePasswordAuthenticationToken(usrDetails, usrDetails.getToken(), grantedAuths);

		usrDetails.setUserDb(user);
		usrDetails.setAuthorities(grantedAuths);
		usrDetails.setAccountNonExpired(true);
		usrDetails.setAccountNonLocked(true);
		usrDetails.setCredentialsNonExpired(true);

		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private List<String> companieCodes(Document document, FunctionType functionType, String namespace) throws XPathExpressionException {

		List<String> list = Lists.newArrayList();

		String elementName = functionElementMap.get(functionType);

		if (elementName != null) {
			list = readContentOfElements(document, elementName, namespace);
		} else {
			throw new UnsupportedOperationException(ERROR_UNSUPPORTED_SELLING_COMPANY + functionType);
		}

		return list;
	}

	private List<String> readContentOfElements(Document document, String elementName, String namespace) throws XPathExpressionException {

		boolean isXPath = elementName.startsWith("//");

		NodeList nodeList;

		if (isXPath) {
			nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(elementName, document, XPathConstants.NODESET);
		} else {
			nodeList = document.getElementsByTagNameNS(namespace, elementName);
		}

		List<String> list = Lists.newArrayList();

		for (int x = 0; x < nodeList.getLength(); x++) {

			Node node = nodeList.item(x);

			if (StringUtils.isNotEmpty(node.getLocalName()) && (isXPath || elementName.equals(node.getLocalName()))) {
				list.add(node.getTextContent());
			}
		}

		return list;
	}

	private String getFunction(Document document) {

		NodeList nodeList = document.getElementsByTagNameNS(SOAP_NAMESPACE, "Body").item(0).getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {

			Node node = nodeList.item(i);

			if (StringUtils.isNotEmpty(node.getLocalName())) {
				return node.getLocalName();
			}
		}

		return null;
	}

	private String getKey(Document document, String namespace) throws XPathExpressionException {

		List<String> list = readContentOfElements(document, "securityKey", namespace);

		if (list.size() == 0) {
			throw new UnsupportedOperationException(ERROR_NO_SECURITY_KEY);
		} else if (list.size() > 1) {
			throw new UnsupportedOperationException(ERROR_TO_MANY_SECURITY_KEYS);
		}

		return list.get(0);
	}
}
