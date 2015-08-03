package com.ttc.ch2.bl.report;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Itinerary;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ItinerarySegment;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.message.MailSenderService;
import com.ttc.ch2.bl.message.MailSenderServiceException;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.messages.EmailAddressDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.messages.EmailAddress;
import com.ttc.ch2.domain.messages.EmailAddress.AddressType;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ItinerarySegmentReportServiceImpl implements ItinerarySegmentReportService {

	private final static String REPORT_MAIL_SUBJECT = "TourInfo: Itinerary Segment Validation Report";
	private final static String REPORT_MAIL_FROM_ADDRESS = "ContentHub-%s@travcorp.com";
	private final static String REPORT_MAIL_BODY =
			"Brand Code: %s                                                   \n" +
			"Files with invalid ItinerarySegment(s) StartDay/Duration values: \n" +
			"No of files: %s                                                  \n" +
			"File names:                                                      \n" +
			"%s                                                               \n";

	private final static String ERROR_NO_BRAND = "Brand does not exist: %s";

	private static final String NODE_ITINERARY = "Itinerary";

	private static Logger logger = LoggerFactory.getLogger(ItinerarySegmentReportServiceImpl.class);
	
	@Value("${common.enviroment}")
	private String enviromentName;

	@Inject
	private BrandDAO brandDAO;

	@Inject
	private EmailAddressDAO emailAddressDAO;

	@Inject
	private ContentRepositoryService contentRepositoryService;

	@Inject
	private MailSenderService mailSenderService;

    /**This method need a lot of memory. iterate on contentRepository */
	public String createItinerarySegmentValidationReport(ProcessName processName, String brandCode) throws ItinerarySegmentReportException {

		ContentRepository filter = new ContentRepository();
		filter.getBrands().add(brandDAO.findByBrandCode(brandCode));

		List<ContentRepository> contentRepositoryList = contentRepositoryService.getContentRepositoriesList(new QueryCondition(), filter, RepositoryStatus.TourInfoOnly, RepositoryStatus.TIandTD);

		if (contentRepositoryList == null || contentRepositoryList.size() == 0) {
			return null;
		}

		List<String> invalidToursList = new ArrayList<String>();

		try {
			ItinerarySegmentChecker checker=new ItinerarySegmentChecker();
			for (ContentRepository contentRepository : contentRepositoryList) {

				XMLStreamReader streamReaderTI = XMLInputFactory.newFactory().createXMLStreamReader(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getTourInfoXML().getBytes(StandardCharsets.UTF_8)));

				do {
					streamReaderTI.next();
				} while (streamReaderTI.hasNext() && !(streamReaderTI.isStartElement() && NODE_ITINERARY.equals(streamReaderTI.getLocalName())));

				Unmarshaller jaxbTIUnmarshaller = JAXBContext.newInstance(new Class[] { Itinerary.class }).createUnmarshaller();

				Itinerary itinerary = ((JAXBElement<Itinerary>) jaxbTIUnmarshaller.unmarshal(streamReaderTI, Itinerary.class)).getValue();

				streamReaderTI.close();

				if (!checker.checkItinerarySegment(itinerary)) {
					invalidToursList.add(contentRepository.getTourCode());
				}
			}

			if (invalidToursList.size() == 0) {
				return null;
			}

			try {
				sendItinerarySegmentValidationReport(processName, invalidToursList, brandCode);
			}catch(MailSenderServiceException e) {
				logger.warn(MailSenderService.SMTP_ERROR, e);
			}

		} catch (XMLStreamException | FactoryConfigurationError | JAXBException  e) {
			throw new ItinerarySegmentReportException(e);
		}

		return StringUtils.join(invalidToursList, "\n");
	}

	public String createItinerarySegmentValidationReport(ProcessName processName, String brandCode, List<TourInfo> tourInfoList) throws ItinerarySegmentReportException {

		if (tourInfoList == null || tourInfoList.size() == 0) {
			return null;
		}

		List<String> invalidToursList = new ArrayList<String>();

		ItinerarySegmentChecker itinerarySegmentChecker = new ItinerarySegmentChecker();
		for (TourInfo tourInfo : tourInfoList) {			
			if (!itinerarySegmentChecker.checkItinerarySegment(tourInfo.getItinerary())) {
				invalidToursList.add(tourInfo.getTourCode());
			}
		}

		if (invalidToursList.size() == 0) {
			return null;
		}

		try {
			sendItinerarySegmentValidationReport(processName, invalidToursList, brandCode);
		} catch (MailSenderServiceException e) {
			//throw new ItinerarySegmentReportException(e);
			logger.warn(MailSenderService.SMTP_ERROR, e);
		}

		return StringUtils.join(invalidToursList, "\n");
	}
	
	public String createItinerarySegmentReport(ProcessName processName, String brandCode, List<String> invalidToursList) throws ItinerarySegmentReportException {
		
		if (invalidToursList == null || invalidToursList.size() == 0) {
			return null;
		}
						
		try {
			sendItinerarySegmentValidationReport(processName, invalidToursList, brandCode);
		} catch (MailSenderServiceException e) {
			//throw new ItinerarySegmentReportException(e);
			logger.warn(MailSenderService.SMTP_ERROR, e);
		}
		
		return StringUtils.join(invalidToursList, "\n");
	}

	

	private void sendItinerarySegmentValidationReport(ProcessName processName, List<String> invalidToursList, String brandCode) throws MailSenderServiceException {

		Collections.sort(invalidToursList);

		List<EmailAddress> emailAddressesList = emailAddressDAO.getEmailAddressesList(brandCode, AddressType.ItineraryReportRecipient);

		String[] emailAddressesArray = new String[0];

		if (emailAddressesList != null) {

			for (EmailAddress address : emailAddressesList) {
				emailAddressesArray = (String[]) ArrayUtils.add(emailAddressesArray, address.getAddressText());
			}
		}

		Brand brand = brandDAO.findByBrandCode(StringUtils.trim(brandCode));
		Preconditions.checkState(brand != null, String.format(ERROR_NO_BRAND, brandCode));

		mailSenderService.sendMessage(
				processName,
				REPORT_MAIL_SUBJECT,
				String.format(REPORT_MAIL_BODY, brandCode, invalidToursList.size(), StringUtils.join(invalidToursList, "\n")),
				String.format(REPORT_MAIL_FROM_ADDRESS, enviromentName),
				false,
				brand,
				emailAddressesArray);
	}
}
