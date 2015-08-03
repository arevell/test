package com.ttc.ch2.brox.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.activation.DataHandler;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.IllegalDataException;
import org.jdom.JDOMException;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import com.ttc.ch2.api.ccapi.v3.GetBrochureRequest;
import com.ttc.ch2.api.ccapi.v3.GetBrochureResponse;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.upload.validator.BLMT;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.util.ext.EmptyPropertyChecker;
import com.ttc.util.ext.NotBlankPropertyChecker;
import com.ttc.util.ext.NullPropertyChecker;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.Severity;
import com.ttc.util.validation.AllPassValidator;
import com.ttc.util.validation.Checker;
import com.ttc.util.validation.Validator;
import com.ttc.util.ws.MessagesUtil;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.ArrayOfMarketVariationPricing;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationPricing;
import com.ttsl.tourinfo._2010._08._2.SellingCompanies;
import com.ttsl.tourinfo._2010._08._2.SellingCompany;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class BrochureServiceImpl implements BrochureService {

	private static Logger logger = LoggerFactory.getLogger(BrochureServiceImpl.class);
	private static final Logger activityLogger = LoggerFactory.getLogger("ch2.activity.BrochureService");

	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
	private static final SimpleDateFormat dateFormatterNumerical = new SimpleDateFormat("dd.MM.yyyy");

	private static final int BUFFER_SIZE = 1024;

	private static final String ELEMENT_SELLING_COMPANIES_TI = "SellingCompanies";
	private static final String ELEMENT_SELLING_COMPANY_TI = "SellingCompany";
	private static final String ELEMENT_SELLING_COMPANIES_TD = "MarketVariationPricings";
	private static final String ELEMENT_SELLING_COMPANY_TD = "MarketVariationPricing";
	private static final String ELEMENT_BROCHURE = "brochure";
	private static final String ELEMENT_TITLE = "title";
	private static final String ELEMENT_AGENT = "agent";
	private static final String ELEMENT_IMAGE = "image";
	private static final String ELEMENT_TEXT = "text";
	private static final String ATTRIBUTE_SELLING_COMPANY_CODE_TI = "Code";
	private static final String ATTRIBUTE_SELLING_COMPANY_CODE_TD = "SellingCompanyCode";
	private static final String ATTRIBUTE_SELLING_COMPANY = "SellingCompany";
	private static final String ATTRIBUTE_CURRENT_DATE = "currentDate";
	private static final String ATTRIBUTE_CURRENT_DATE_NUMERICAL = "currentDateNumerical";
	private static final String PATH_TERMS = "/bookend/%s/terms.%s.xml";
	private static final String PATH_FRONTISPIECE = "/bookend/%s/frontispiece.%s.xml";
	private static final String PATH_STYLESHEETS = "/stylesheets/%s/stylesheet.xsl";
	private static final String PATH_FOP_CONFIGURATION = "/fopConfig.xml";
	private static final String PATH_FONTS = "/fonts/";
	private static final String PATH_BOOKEND = "/bookend/%s/";
	private static final String ERROR_NO_COMMON_SELLING_COMPANY = "Could not find common selling company for TourInfo and TourDeparture with code: [%s]";
	private static final String ERROR_NO_TOUR_INFO = "Could not find TourInfo for the given tour: [%s]";
	private static final String ERROR_NO_TOUR_INFO_SELLING_COMPANY = "Given selling company is not valid for the tour: [%s] - TourInfo does not contain the selling company";
	private static final String ERROR_NO_TOUR_DEPARTURE = "Could not find TourDeparture for the given tour: [%s]";
	private static final String ERROR_NO_TOUR_DEPARTURE_SELLING_COMPANY = "Given selling company is not valid for the tour: [%s] - TourDeparture does not contain the selling company";
	private static final String ERROR_NO_TOUR_DATA = "Could not find either TourInfo or TourDeparture for the given tour: [%s]";

	public static final String ERROR_NO_REQUESTED_PARAMETER_DEFINED = "The requested parameter [%s] has not been defined";
	public static final String ERROR_INCORRECT_COMPANY = "Incorrect selling company code: [%s]";
	public static final String ERROR_INCORRECT_BRAND = "Incorrect brand code: [%s] for selling company code: [%s]";

	@Inject
	private ContentRepositoryService contentRepositoryService;

	private Validator<Object> validator;
	{
		List<Checker<Object>> list = new ArrayList<>();
		NullPropertyChecker checker = new NullPropertyChecker();
		checker.setSubjectName("request parameter");
		list.add(checker);

		NullPropertyChecker checker1 = new EmptyPropertyChecker();
		checker1.setPrerequisite(checker);
		checker1.setPropertyName("tour");
		checker1.setSubjectName("tour");
		list.add(checker1);

		NullPropertyChecker checker2 = new NotBlankPropertyChecker();
		checker2.setPrerequisite(checker);
		checker2.setSubjectName("sellingCompanyCode");
		checker2.setPropertyName("sellingCompanyCode");
		list.add(checker2);

		NullPropertyChecker checker3 = new NotBlankPropertyChecker();
		checker3.setPrerequisite(checker);
		checker3.setSubjectName("brandCode");
		checker3.setPropertyName("brandCode");
		list.add(checker3);

		validator = new AllPassValidator<Object>(list);
	}

	URIResolver resolver = new URIResolver() {
		public Source resolve(String href, String base) throws TransformerException {
			return new StreamSource(getClass().getResourceAsStream("/" + href));
		}
	};


	@Override
	public GetBrochureResponse getBrochure(GetBrochureRequest brochureParameters) {

		GetBrochureResponse response = new GetBrochureResponse();
		Collection<Message> messages = validate(brochureParameters);

		MessagesUtil.assignSoapMessageContext(response, messages);

		try {

			if (MessagesUtil.severity(messages) == Severity.ERROR) {

				response.setSuccessful(false);
				return response;
			}

			try {

				byte[] resultData = getBrochureInternal(brochureParameters);

				response.setFileData(new DataHandler(resultData, "application/octet-stream"));
				response.setSuccessful(true);

				activityLogger.info("USER: {}  called getBrochure()", SecurityHelper.getLoginSilent());

			} catch (JDOMException | IOException | ConfigurationException | SAXException | TransformerException | IllegalDataException e) {

				logger.error("", e);

				Message message = MessageBuilder.newMessage(BLMT.SYSTEM_ERROR)
						.withSubject("Exception", e.getMessage())
						.build();

				MessagesUtil.assignSoapMessageContext(response, message);

				response.setSuccessful(false);
			}

		} catch (Throwable th) {

			logger.error("", th);

			Message message = MessageBuilder.newMessage(BLMT.SYSTEM_ERROR)
					.withSubject("Exception", th.getMessage())
					.build();

			MessagesUtil.assignSoapMessageContext(response, message);

			response.setSuccessful(false);
		}

		return response;
	}

	@Override
	public byte[] getBrochureData(GetBrochureRequest brochureParameters) throws BrochureServiceException {

		validateParameters(brochureParameters);

		if (BrochureService.ANY_CODE.equals(brochureParameters.getSellingCompanyCode())) {
			brochureParameters.setSellingCompanyCode(getCommonSellingCompany(brochureParameters.getTour().get(0), brochureParameters.getBrandCode()));
		}

		try {
			return getBrochureInternal(brochureParameters);
		} catch (JDOMException | IOException | ConfigurationException | SAXException | TransformerException e) {
			throw new BrochureServiceException(e);
		}
	}

	protected Collection<Message> validate(GetBrochureRequest request) {

		Collection<Message> messages = validator.validate(request);

		return messages;
	}

	private byte[] getBrochureInternal(GetBrochureRequest brochureParameters) throws BrochureServiceException, JDOMException, IOException, ConfigurationException, SAXException, TransformerException {

		String brochureTitle = brochureParameters.getTitle();

		Document documentBrochure = new Document(new Element(ELEMENT_BROCHURE));
		documentBrochure.getRootElement().addContent((new Element(ELEMENT_TITLE)).addContent(brochureTitle));

		String agentText = brochureParameters.getAgentText();
		String agentImage = brochureParameters.getAgentImage();

		if (StringUtils.isNotBlank(agentText) || StringUtils.isNotBlank(agentImage)) {

			Element elementAgent = new Element(ELEMENT_AGENT);

			if (StringUtils.isNotBlank(agentText)) { elementAgent.addContent(new Element(ELEMENT_IMAGE).addContent(agentImage)); }
			if (StringUtils.isNotBlank(agentText)) { elementAgent.addContent(new Element(ELEMENT_TEXT).addContent(agentText)); }

			documentBrochure.getRootElement().addContent(elementAgent);
		}

		String brandCode = brochureParameters.getBrandCode();
		String sellingCompanyCode = brochureParameters.getSellingCompanyCode();

		documentBrochure.getRootElement().setAttribute(ATTRIBUTE_SELLING_COMPANY, sellingCompanyCode);

		SAXBuilder builder = new SAXBuilder();

		byte[] resultData = null;

		Document documentFrontispiece = builder.build(getClass().getResourceAsStream(String.format(PATH_FRONTISPIECE, brandCode, sellingCompanyCode)));
		Document documentTerms = builder.build(getClass().getResourceAsStream(String.format(PATH_TERMS, brandCode, sellingCompanyCode)));

		documentBrochure.getRootElement().addContent(documentFrontispiece.getRootElement().detach());
		documentBrochure.getRootElement().addContent(documentTerms.getRootElement().detach());

		for (String tourCode : brochureParameters.getTour()) {

			ContentRepository contentRepository = contentRepositoryService.findByTourCode(tourCode, brandCode);

			if (contentRepository == null || (StringUtils.isBlank(contentRepository.getXmlContentRepository().get(0).getOldTourInfoXML()) && StringUtils.isBlank(contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML()))) {
				throw new BrochureServiceException(String.format(ERROR_NO_TOUR_DATA, tourCode));
			} else if (StringUtils.isBlank(contentRepository.getXmlContentRepository().get(0).getOldTourInfoXML())) {
				throw new BrochureServiceException(String.format(ERROR_NO_TOUR_INFO, tourCode));
			} else if (StringUtils.isBlank(contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML())) {
				throw new BrochureServiceException(String.format(ERROR_NO_TOUR_DEPARTURE, tourCode));
			}

			Document documentTourInfo = builder.build(new StringReader(contentRepository.getXmlContentRepository().get(0).getOldTourInfoXML()));

			if (!checkSellingCompany(documentTourInfo, ELEMENT_SELLING_COMPANY_TI, ATTRIBUTE_SELLING_COMPANY_CODE_TI, sellingCompanyCode)) {
				throw new BrochureServiceException(String.format(ERROR_NO_TOUR_INFO_SELLING_COMPANY, tourCode));
			}

			documentBrochure.getRootElement().addContent(documentTourInfo.getRootElement().detach());

			Document documentTourDeparture = builder.build(new StringReader(contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML()));

			if (!checkSellingCompany(documentTourDeparture, ELEMENT_SELLING_COMPANY_TD, ATTRIBUTE_SELLING_COMPANY_CODE_TD, sellingCompanyCode)) {
				throw new BrochureServiceException(String.format(ERROR_NO_TOUR_DEPARTURE_SELLING_COMPANY, tourCode));
			}

			documentBrochure.getRootElement().addContent(documentTourDeparture.getRootElement().detach());
		}

		resultData = convertToPDF(documentBrochure, brandCode, brochureTitle);

		return resultData;
	}

	private byte[] convertToPDF(Document document, String brandCode, String brochureTitle) throws ConfigurationException, SAXException, IOException, TransformerException {

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setURIResolver(resolver);

		Transformer transformer = transformerFactory.newTransformer(new StreamSource(getClass().getResourceAsStream(String.format(PATH_STYLESHEETS, brandCode))));
		transformer.setParameter(ATTRIBUTE_CURRENT_DATE, dateFormatter.format(new Date()));
		transformer.setParameter(ATTRIBUTE_CURRENT_DATE_NUMERICAL, dateFormatterNumerical.format(new Date()));

		URL urlFont = getClass().getResource(PATH_FONTS);
		URL urlBookend = getClass().getResource(String.format(PATH_BOOKEND, brandCode));

		FopFactory fopFactory = FopFactory.newInstance();
		fopFactory.setFontBaseURL(urlFont.getProtocol() + ":" + urlFont.getPath());
		fopFactory.setUserConfig(new DefaultConfigurationBuilder().build(getClass().getResourceAsStream(PATH_FOP_CONFIGURATION)));

		FOUserAgent userAgent = fopFactory.newFOUserAgent();
		userAgent.setBaseURL(urlBookend.getProtocol() + ":" + urlBookend.getPath());
		userAgent.setTitle(brochureTitle);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(BUFFER_SIZE);

		Source source = new StreamSource(new StringReader(new XMLOutputter().outputString(document)));
		Result result = new SAXResult(fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outputStream).getDefaultHandler());

		transformer.transform(source, result);

		return outputStream.toByteArray();
	}

	private void validateParameters(GetBrochureRequest brochureParameters) throws BrochureServiceException {

		if (brochureParameters.getTour() == null || brochureParameters.getTour().size() == 0) {
			throw new BrochureServiceException(String.format(ERROR_NO_REQUESTED_PARAMETER_DEFINED, "tour"));
		}

		if (StringUtils.isBlank(brochureParameters.getSellingCompanyCode())) {
			throw new BrochureServiceException(String.format(ERROR_NO_REQUESTED_PARAMETER_DEFINED, "sellingCompanyCode"));
		}

		if (StringUtils.isBlank(brochureParameters.getBrandCode())) {
			throw new BrochureServiceException(String.format(ERROR_NO_REQUESTED_PARAMETER_DEFINED, "brandCode"));
		}
	}

	private boolean checkSellingCompany(Document document, String sellingCompanyElement, String sellingCompanyAttribute, String sellingCompanyCode) {

		@SuppressWarnings("unchecked")
		Iterator<Element> iterator = document.getDescendants(new ElementFilter(sellingCompanyElement));

		while (iterator != null && iterator.hasNext()) {

			if (sellingCompanyCode.equals(iterator.next().getAttributeValue(sellingCompanyAttribute))) {
				return true;
			}
		}

		return false;
	}

	private String getCommonSellingCompany(String tourCode, String brandCode) throws BrochureServiceException {

		ContentRepository contentRepository = contentRepositoryService.findByTourCode(tourCode, brandCode);

		if (contentRepository != null &&
			StringUtils.isNotBlank(contentRepository.getXmlContentRepository().get(0).getOldTourInfoXML()) &&
			StringUtils.isNotBlank(contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML())) {

			try {

				XMLStreamReader streamReaderTI = XMLInputFactory.newFactory().createXMLStreamReader(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getOldTourInfoXML().getBytes(StandardCharsets.UTF_8)));
				XMLStreamReader streamReaderTD = XMLInputFactory.newFactory().createXMLStreamReader(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML().getBytes(StandardCharsets.UTF_8)));

				do {
					streamReaderTI.next();
				} while (streamReaderTI.hasNext() && !(streamReaderTI.isStartElement() && ELEMENT_SELLING_COMPANIES_TI.equals(streamReaderTI.getLocalName())));

				do {
					streamReaderTD.next();
				} while (streamReaderTD.hasNext() && !(streamReaderTD.isStartElement() && ELEMENT_SELLING_COMPANIES_TD.equals(streamReaderTD.getLocalName())));

				Unmarshaller jaxbTIUnmarshaller = JAXBContext.newInstance(new Class[] { SellingCompanies.class }).createUnmarshaller();
				Unmarshaller jaxbTDUnmarshaller = JAXBContext.newInstance(new Class[] { ArrayOfMarketVariationPricing.class }).createUnmarshaller();

				SellingCompanies sellingCompaniesTI = ((JAXBElement<SellingCompanies>) jaxbTIUnmarshaller.unmarshal(streamReaderTI, SellingCompanies.class)).getValue();
				ArrayOfMarketVariationPricing sellingCompaniesTD = ((JAXBElement<ArrayOfMarketVariationPricing>) jaxbTDUnmarshaller.unmarshal(streamReaderTD, ArrayOfMarketVariationPricing.class)).getValue();

				streamReaderTI.close();
				streamReaderTD.close();

				for (SellingCompany sellingCompanyTI : sellingCompaniesTI.getSellingCompany()) {
					for (MarketVariationPricing sellingCompanyTD : sellingCompaniesTD.getMarketVariationPricing()) {
						if (sellingCompanyTI.getCode().equals(sellingCompanyTD.getSellingCompanyCode())) {
							return sellingCompanyTI.getCode();
						}
					}
				}

			} catch (JAXBException | XMLStreamException | FactoryConfigurationError e) {
				throw new BrochureServiceException(e);
			}
		}

		throw new BrochureServiceException(String.format(ERROR_NO_COMMON_SELLING_COMPANY, tourCode));
	}
}
