package com.ttc.ch2.bl.report;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import com.google.common.collect.Lists;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompanies;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompany;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.departure.habs.HabsTourDepartureService;
import com.ttc.ch2.bl.departure.habs.HabsTourDepartureServiceException;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.bl.filecollect.FileCollectVO.StatusOperation;
import com.ttc.ch2.bl.message.MailSenderService;
import com.ttc.ch2.bl.message.MailSenderServiceException;
import com.ttc.ch2.bl.upload.UploadStatusService;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.messages.EmailAddressDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.messages.EmailAddress;
import com.ttc.ch2.domain.messages.EmailAddress.AddressType;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.search.export.IndexSynchronizerVO;
import com.wsout.habs.itropicsbuildws.WsTourWithSCVO;
import com.wsout.habs.itropicsbuildws.WsToursWithSCListVO;



@Service("HabsReconciliationReportServiceImpl")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class HabsReconciliationReportServiceImpl implements ReconciliationReportService {

	private final static String REPORT_MAIL_SUBJECT = "TourInfo - TourDepartures Reconciliation Report";
	private final static String REPORT_MAIL_FROM_ADDRESS = "ContentHub-%s@travcorp.com";
	private final static String REPORT_MAIL_BODY_EXTRA = "The following tour codes could not be validated:\n";
	private final static String REPORT_MAIL_BODY_EXTRA_DETAILS = "Selling company: %s tour code(s): %s\n";
	private final static String REPORT_MAIL_BODY =
			"Brand Code: %s                                                   \n" +
			"Tour Codes found in tour info but not in tour departure:         \n" +
			"No of files: %s                                                  \n" +
			"File names:                                                      \n" +
			"%s                                                               \n" +
			"                                                                 \n" +
			"Tour Codes found in tour departure but not in tour info:         \n" +
			"No of files: %s                                                  \n" +
			"File names:                                                      \n" +
			"%s                                                               \n" +
			"                                                                 \n" +
			"Tour Codes found in both tour info and tour departure:           \n" +
			"No of files: %s                                                  \n" +
			"File names:                                                      \n" +
			"%s                                                               \n" +
			"                                                                 \n" +
			"%s                                                               \n";

	private final static String HTML_REPORT_MAIL_BODY =
		"<!DOCTYPE html>									" +
		"	<html>											" +
		"		<head>										" +
		"			<style>									" +
		"				body								" +
		"				{									" +
		"					font-family: Segoe UI, Tahoma;	" +
		"				}									" +
		"				table								" +
		"				{									" +
		"					width: 600px;					" +
		"					border: 1px solid black;		" +
		"					border-collapse: collapse;		" +
		"				}									" +
		"				th, td								" +
		"				{									" +
		"					padding: 5px;					" +
		"				}									" +
		"				th									" +
		"				{									" +
		"					text-align: center;				" +
		"					border: 1px;	 				" +
		"					border-style: solid;			" +
		"				}									" +
		"				.info								" +
		"				{									" +
		"					background-color: #CCCCFF;		" +
		"				}									" +
		"				.success							" +
		"				{									" +
		"					background-color: #66FF33;		" +
		"				}									" +
		"				.warning							" +
		"				{									" +
		"					background-color: #FFCC00;		" +
		"				}									" +
		"				.error								" +
		"				{									" +
		"					background-color: #FF6600;		" +
		"				}									" +
		"			</style>								" +
		"		</head>										" +
		"		<body>										" +
		"			<div align=\"center\">					" +
		"				<b>Brand Code: %s</b>				" +
		"			</div>									" +
		"			</br>									" +
		"			%s										" +
		"			%s										" +
		"			%s										" +
		"			%s										" +
		"			%s										" +
		"			%s										" +
		"		</body>										" +
		"	</html>											";

	private final static String HTML_REPORT_MAIL_TABLE =
		"<table class=\"%s\" align=\"center\">	" +
		"	<tr>								" +
		"		<th><b>%s:</b> %s</th>			" +
		"	</tr>								" +
		"	%s									" +
		"</table>								" +
		"</br>									";

	private final static String HTML_REPORT_MAIL_ROW =
		"	<tr>			" +
		"		<td>%s</td>	" +
		"	</tr>			";

	private final static String HTML_REPORT_MAIL_LIST =
		"	%s	" +
		"	<ul>	" +
		"		%s	" +
		"	</ul>	";

	private final static String HTML_REPORT_MAIL_LIST_ITEM =
		"	<li>	" +
		"		%s	" +
		"	</li>	";

	private final static String ERROR_NO_BRAND = "Brand does not exist: %s";

	private static final String NODE_SELLING_COMPANIES = "SellingCompanies";

	private static final String INFO_TOUR_PROCESSING = "Reconciliation Report - processing tour: [%s] being: [%s/%s]";

	private static Logger logger = LoggerFactory.getLogger(HabsReconciliationReportServiceImpl.class); 
	
	@Value("${common.enviroment}")
	private String enviromentName;

	@Inject
	private BrandDAO brandDAO;

	@Inject
	private EmailAddressDAO emailAddressDAO;

	@Inject
	private ImportStatusService importStatusService;

	@Inject
	private UploadStatusService uploadStatusService;

	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject
	private HabsTourDepartureService tourDepartureService;

	@Inject
	private MailSenderService mailSenderService;


	@Override
	public void createReconciliationReport(ProcessName processName, String brandCode, IndexSynchronizerVO indexSynchronizerVO, FileCollectVO fileCollectVO) throws ReconciliationReportServiceException {

		ContentRepository filter = new ContentRepository();
		filter.getBrands().add(brandDAO.findByBrandCode(brandCode));

		List<Long> ids=contentRepositoryDAO.getContentRepositoriesIdsList(new QueryCondition(), filter,Lists.newArrayList(RepositoryStatus.TourInfoOnly, RepositoryStatus.TourDepartureOnly, RepositoryStatus.TIandTD));
		if (ids == null || ids.size() == 0) {
			return;
		}
				
		try {

			Map<String, List<String>> tourSellingCompaniesMap = new HashMap<String, List<String>>();

			WsToursWithSCListVO toursWithSCList = tourDepartureService.getToursWithSCList(brandCode);

			if (toursWithSCList != null && toursWithSCList.getTour() != null && toursWithSCList.getTour().size() > 0) {

				for (WsTourWithSCVO tourWithSC : toursWithSCList.getTour()) {
					tourSellingCompaniesMap.put(tourWithSC.getTourCode(), tourWithSC.getSellingCompany());
				}
			}

			Map<String, List<String>> sellingCompanyToursMap = new TreeMap<String, List<String>>();

			List<String> toursListTourInfo = new ArrayList<String>();
			List<String> toursListTourDeparture = new ArrayList<String>();
			List<String> toursListBoth = new ArrayList<String>();

			int index = 0;
		
			for (Long idCr: ids) {				
				ContentRepository contentRepository=contentRepositoryDAO.find(idCr);
				if (ProcessName.IMPORT.equals(processName)) {
					importStatusService.setupMessage(String.format(INFO_TOUR_PROCESSING, contentRepository.getTourCode(), ++index, ids.size()), brandCode, TypeMsg.INF, ProcessLevel.RECONCILIATION);
				} else if (ProcessName.UPLOAD.equals(processName)) {
					uploadStatusService.proccesingDescription(brandCode, String.format(INFO_TOUR_PROCESSING, contentRepository.getTourCode(), ++index, ids.size()), false);
				}

				switch (contentRepository.getRepositoryStatus()) {

				case TourInfoOnly:

					checkTourInfo(contentRepository, tourSellingCompaniesMap, sellingCompanyToursMap);
					toursListTourInfo.add(contentRepository.getTourCode());
					break;

				case TourDepartureOnly:

					toursListTourDeparture.add(contentRepository.getTourCode());
					break;

				case TIandTD:

					checkTourInfo(contentRepository, tourSellingCompaniesMap, sellingCompanyToursMap);
					toursListBoth.add(contentRepository.getTourCode());
					break;

				default:
					break;
				}
				
				contentRepositoryDAO.flush();
//				contentRepositoryDAO.evictEntity(contentRepository);
//				contentRepository.getXmlContentRepository().clear();
				contentRepositoryDAO.clearSession();
			}

			try {
				sendReconciliationReport(processName, brandCode, indexSynchronizerVO, fileCollectVO, toursListTourInfo, toursListTourDeparture, toursListBoth, sellingCompanyToursMap);
			}catch(MailSenderServiceException e) {
				logger.warn(MailSenderService.SMTP_ERROR, e);
			}

		} catch ( HabsTourDepartureServiceException | XMLStreamException | FactoryConfigurationError | JAXBException e) {
			throw new ReconciliationReportServiceException(e);
		}
	}

	private void checkTourInfo(ContentRepository contentRepository, Map<String, List<String>> tourSellingCompaniesMap, Map<String, List<String>> sellingCompanyToursMap) throws XMLStreamException, FactoryConfigurationError, JAXBException {

		if (contentRepository == null || contentRepository.getXmlContentRepository() == null || StringUtils.isBlank(contentRepository.getXmlContentRepository().get(0).getTourInfoXML())) {
			return;
		}

		XMLStreamReader streamReaderTI = XMLInputFactory.newFactory().createXMLStreamReader(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getTourInfoXML().getBytes(StandardCharsets.UTF_8)));

		do {
			streamReaderTI.next();
		} while (streamReaderTI.hasNext() && !(streamReaderTI.isStartElement() && NODE_SELLING_COMPANIES.equals(streamReaderTI.getLocalName())));

		Unmarshaller jaxbTIUnmarshaller = JAXBContext.newInstance(new Class[] { SellingCompanies.class }).createUnmarshaller();

		SellingCompanies sellingCompaniesTI = ((JAXBElement<SellingCompanies>) jaxbTIUnmarshaller.unmarshal(streamReaderTI, SellingCompanies.class)).getValue();

		streamReaderTI.close();

		if (sellingCompaniesTI == null || sellingCompaniesTI.getSellingCompany() == null) {
			return;
		}

		List<String> sellingCompaniesList = tourSellingCompaniesMap.get(contentRepository.getTourCode());

		for (SellingCompany sellingCompanyTI : sellingCompaniesTI.getSellingCompany()) {

			String sellingCompanyCode = sellingCompanyTI.getCode();

			if (sellingCompaniesList == null || !sellingCompaniesList.contains(sellingCompanyCode)) {

				List<String> toursList = sellingCompanyToursMap.containsKey(sellingCompanyCode) ? sellingCompanyToursMap.get(sellingCompanyTI.getCode()) : new ArrayList<String>();

				toursList.add(contentRepository.getTourCode());

				sellingCompanyToursMap.put(sellingCompanyCode, toursList);
			}
		}		
	}

	private void sendReconciliationReport(ProcessName processName, String brandCode, IndexSynchronizerVO indexSynchronizerVO, FileCollectVO fileCollectVO, List<String> toursListTourInfo, List<String> toursListTourDeparture, List<String> toursListBoth, Map<String, List<String>> sellingCompanyToursMap) throws MailSenderServiceException {

		Collections.sort(toursListTourInfo);
		Collections.sort(toursListTourDeparture);
		Collections.sort(toursListBoth);

		List<EmailAddress> emailAddressesList = emailAddressDAO.getEmailAddressesList(brandCode, AddressType.ConsolidationReportRecipient);
		
		String[] emailAddressesArray = new String[0];

		if (emailAddressesList != null) {

			for (EmailAddress address : emailAddressesList) {
				emailAddressesArray = (String[]) ArrayUtils.add(emailAddressesArray, address.getAddressText());
			}
		}

		Brand brand = brandDAO.findByBrandCode(StringUtils.trim(brandCode));
		Preconditions.checkState(brand != null, String.format(ERROR_NO_BRAND, brandCode));

		boolean isHtml = true;
		String mailBody = null;

		if (isHtml) {

			mailBody = preparteHtmlBody(brandCode, indexSynchronizerVO, fileCollectVO, toursListTourInfo, toursListTourDeparture, toursListBoth, sellingCompanyToursMap);

		} else {

			String extraBody = StringUtils.EMPTY;

			if (!sellingCompanyToursMap.isEmpty()) {

				extraBody = REPORT_MAIL_BODY_EXTRA;

				for (Entry<String, List<String>> entry : sellingCompanyToursMap.entrySet()) {

					Collections.sort(entry.getValue());
					extraBody += String.format(REPORT_MAIL_BODY_EXTRA_DETAILS, entry.getKey(), StringUtils.join(entry.getValue(), ", "));
				}
			}

			mailBody = String.format(
					REPORT_MAIL_BODY,
					brandCode,
					toursListTourInfo.size(),
					StringUtils.join(toursListTourInfo, "\n"),
					toursListTourDeparture.size(),
					StringUtils.join(toursListTourDeparture, "\n"),
					toursListBoth.size(),
					StringUtils.join(toursListBoth, "\n"),
					extraBody);
		}

		mailSenderService.sendMessage(
				processName,
				REPORT_MAIL_SUBJECT,
				mailBody,
				String.format(REPORT_MAIL_FROM_ADDRESS, enviromentName),
				isHtml,
				brand,
				emailAddressesArray);
	}

	private String preparteHtmlBody(String brandCode, IndexSynchronizerVO indexSynchronizerVO, FileCollectVO fileCollectVO, List<String> toursListTourInfo, List<String> toursListTourDeparture, List<String> toursListBoth, Map<String, List<String>> sellingCompanyToursMap) {

		StringBuilder stringBuilder = new StringBuilder();
		if (indexSynchronizerVO == null) {
			stringBuilder.append(String.format(HTML_REPORT_MAIL_TABLE, "error", "Elastic Search indexing", "error", String.format(HTML_REPORT_MAIL_ROW, "No information about idexed tours")));
		}		
		else if (indexSynchronizerVO.getDocumentCount()==0 && indexSynchronizerVO.getAggregatedCount() == 0 && indexSynchronizerVO.hasErrors()==false) {
			stringBuilder.append(String.format(HTML_REPORT_MAIL_TABLE, "warning", "Elastic Search indexing", "warning", String.format(HTML_REPORT_MAIL_ROW, "None of the tours has been indexed")));
		}
		else if (!indexSynchronizerVO.hasErrors()) {			
			stringBuilder.append(String.format(HTML_REPORT_MAIL_TABLE, "success", "Elastic Search indexing", "success", String.format(HTML_REPORT_MAIL_ROW, "Indexation completed successfully")));			
		} else {
			String documentIDsNotAdded = StringUtils.EMPTY;

			if (!indexSynchronizerVO.getDocumentIDsListNotAdded().isEmpty()) {

				for (String documentID : indexSynchronizerVO.getDocumentIDsListNotAdded()) {
					stringBuilder.append(String.format(HTML_REPORT_MAIL_LIST_ITEM, documentID));
				}

				documentIDsNotAdded = String.format(HTML_REPORT_MAIL_LIST, "Tour Codes not indexed:", stringBuilder.toString());
			}

			stringBuilder = new StringBuilder();

			String documentIDsNotDeleted = StringUtils.EMPTY;

			if (!indexSynchronizerVO.getDocumentIDsListNotDeleted().isEmpty()) {

				for (String documentID : indexSynchronizerVO.getDocumentIDsListNotDeleted()) {
					stringBuilder.append(String.format(HTML_REPORT_MAIL_LIST_ITEM, documentID));
				}

				documentIDsNotDeleted = String.format(HTML_REPORT_MAIL_LIST, "Tour indexes not deleted:", stringBuilder.toString());
			}

			stringBuilder = new StringBuilder();

			String aggregatedIDsNotAdded = StringUtils.EMPTY;

			if (!indexSynchronizerVO.getAggregatedIDsListNotAdded().isEmpty()) {

				for (String aggregatedID : indexSynchronizerVO.getAggregatedIDsListNotAdded()) {
					stringBuilder.append(String.format(HTML_REPORT_MAIL_LIST_ITEM, aggregatedID));
				}

				aggregatedIDsNotAdded = String.format(HTML_REPORT_MAIL_LIST, "Catalogued Tour Codes not aggregated:", stringBuilder.toString());
			}

			stringBuilder = new StringBuilder();

			String aggregatedIDsNotDeleted = StringUtils.EMPTY;

			if (!indexSynchronizerVO.getAggregatedIDsListNotDeleted().isEmpty()) {

				for (String aggregatedID : indexSynchronizerVO.getAggregatedIDsListNotDeleted()) {
					stringBuilder.append(String.format(HTML_REPORT_MAIL_LIST_ITEM, aggregatedID));
				}

				aggregatedIDsNotDeleted = String.format(HTML_REPORT_MAIL_LIST, "Catalogued Tour indexes not deleted:", stringBuilder.toString());
			}

			stringBuilder = new StringBuilder();

			stringBuilder.append(String.format(HTML_REPORT_MAIL_TABLE, "error", "Elastic Search indexing", "error", String.format(HTML_REPORT_MAIL_ROW, documentIDsNotAdded + documentIDsNotDeleted + aggregatedIDsNotAdded + aggregatedIDsNotDeleted)));
		}

		String tableIndexSynchronizer = stringBuilder.toString();


		stringBuilder = new StringBuilder();

		if (fileCollectVO == null || fileCollectVO.getStatusOperation()==StatusOperation.Cleared) {
			stringBuilder.append(String.format(HTML_REPORT_MAIL_TABLE, "warning", "File Collect creating", "warning", String.format(HTML_REPORT_MAIL_ROW, "None of the tours has been added to the repository")));			
		}		
		else if (fileCollectVO.getStatusOperation()==StatusOperation.NotExecuted  && fileCollectVO.hasErrors()==false) {
			stringBuilder.append(String.format(HTML_REPORT_MAIL_TABLE, "success", "File Collect", "success", String.format(HTML_REPORT_MAIL_ROW, "File Collect process has been skipped")));
		}		
		else if (!fileCollectVO.hasErrors()) {
			stringBuilder.append(String.format(HTML_REPORT_MAIL_TABLE, "success", "File Collect creating", "success", String.format(HTML_REPORT_MAIL_ROW, "All tours have been successfully added to the repository")));
		} else {

			String tourCodesNotAdded = StringUtils.EMPTY;

			if (!fileCollectVO.getToursListNotAdded().isEmpty()) {

				for (String tourCode : fileCollectVO.getToursListNotAdded()) {
					stringBuilder.append(String.format(HTML_REPORT_MAIL_LIST_ITEM, tourCode));
				}

				tourCodesNotAdded = String.format(HTML_REPORT_MAIL_LIST, "Tour Codes not added to the repository:", stringBuilder.toString());
			}

			stringBuilder = new StringBuilder();

			String zipFilesNotAdded = StringUtils.EMPTY;

			if (!fileCollectVO.getZipListNotAdded().isEmpty()) {

				for (String zipFile : fileCollectVO.getZipListNotAdded()) {
					stringBuilder.append(String.format(HTML_REPORT_MAIL_LIST_ITEM, zipFile));
				}

				zipFilesNotAdded = String.format(HTML_REPORT_MAIL_LIST, "Zip files not added to the repository:", stringBuilder.toString());
			}

			stringBuilder = new StringBuilder();

			String zipFilesNotDeleted = StringUtils.EMPTY;

			if (!fileCollectVO.getZipListNotDeleted().isEmpty()) {

				for (String zipFile : fileCollectVO.getZipListNotDeleted()) {
					stringBuilder.append(String.format(HTML_REPORT_MAIL_LIST_ITEM, zipFile));
				}

				zipFilesNotDeleted = String.format(HTML_REPORT_MAIL_LIST, "Zip files not deleted from the repository:", stringBuilder.toString());
			}

			stringBuilder = new StringBuilder();

			stringBuilder.append(String.format(HTML_REPORT_MAIL_TABLE, "error", "File Collect creating", "error", String.format(HTML_REPORT_MAIL_ROW, tourCodesNotAdded + zipFilesNotAdded + zipFilesNotDeleted)));
		}

		String tableFileCollect = stringBuilder.toString();


		stringBuilder = new StringBuilder();

		if (!sellingCompanyToursMap.isEmpty()) {

			for (Entry<String, List<String>> entry : sellingCompanyToursMap.entrySet()) {

				Collections.sort(entry.getValue());
				stringBuilder.append(String.format(HTML_REPORT_MAIL_ROW, String.format(REPORT_MAIL_BODY_EXTRA_DETAILS, entry.getKey(), StringUtils.join(entry.getValue(), ", "))));
			}
		}

		String tableTourInvalid = sellingCompanyToursMap.isEmpty() ? StringUtils.EMPTY : String.format(HTML_REPORT_MAIL_TABLE, "warning", "The following tour codes could not be validated", StringUtils.EMPTY, stringBuilder.toString());


		stringBuilder = new StringBuilder();

		for (String tourData : toursListTourInfo) {
			stringBuilder.append(String.format(HTML_REPORT_MAIL_ROW, tourData));
		}

		String tableTourInfo = String.format(HTML_REPORT_MAIL_TABLE, "info", "Tour Codes found in tour info but not in tour departure", "No of files: " + toursListTourInfo.size(), stringBuilder.toString());


		stringBuilder = new StringBuilder();

		for (String tourData : toursListTourDeparture) {
			stringBuilder.append(String.format(HTML_REPORT_MAIL_ROW, tourData));
		}

		String tableTourDeparture = String.format(HTML_REPORT_MAIL_TABLE, "info", "Tour Codes found in tour departure but not in tour info", "No of files: " + toursListTourDeparture.size(), stringBuilder.toString());


		stringBuilder = new StringBuilder();

		for (String tourData : toursListBoth) {
			stringBuilder.append(String.format(HTML_REPORT_MAIL_ROW, tourData));
		}

		String tableTourBoth = String.format(HTML_REPORT_MAIL_TABLE, "info", "Tour Codes found in both tour info and tour departure", "No of files: " + toursListBoth.size(), stringBuilder.toString());


		return String.format(HTML_REPORT_MAIL_BODY, brandCode, tableIndexSynchronizer, tableFileCollect, tableTourInvalid, tableTourInfo, tableTourDeparture, tableTourBoth);
	}
}
