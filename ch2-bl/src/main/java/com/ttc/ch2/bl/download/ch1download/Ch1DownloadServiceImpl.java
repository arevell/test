package com.ttc.ch2.bl.download.ch1download;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.upload.UploadStatusService;
import com.ttc.ch2.bl.upload.UploadTourInfoService;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.upload.UploadStatus;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class Ch1DownloadServiceImpl implements Ch1DownloadService {

	private static Logger logger = LoggerFactory.getLogger(Ch1DownloadServiceImpl.class);

	private static final String TAG_TT_BEGIN = "<TT>";
	private static final String TAG_TT_END = "</TT>";

	private static final String SUFFIX_XML_LOWER = ".xml";
	private static final String SUFFIX_XML_UPPER = ".XML";

	private static final String SEPARATOR_URL_PATH = "/";
	private static final String SEPARATOR_A_HREF = "\"";

	private static final String INFO_DOWNLOAD_START = "Downloading [%s] - started";
	private static final String INFO_DOWNLOAD_PROGRESS = "Content Hub 1.0 Tour Info download - downloading tour: [%s] being: [%s/%s]";
	private static final String INFO_DOWNLOAD_END = "Downloading [%s] - finished";
	private static final String WARNING_DOWNLOAD_DISABLED = "CH1 download service is disabled in the config, ignoring download ...";
	private static final String ERROR_DOWNLOAD_FAILED = "TourInfo download from CH1 failed";
	private static final String ERROR_CLOSE_HTTP_CLIENT = "Cannot close http client";

	private static final Pattern PATTERN_TAG_TR = Pattern.compile("<tr(.*?)>(.*?)</tr>", Pattern.MULTILINE | Pattern.DOTALL);
	private static final Pattern PATTERN_TAG_A_HREF = Pattern.compile("<a(.*?)href(.*?)>(.*?)</a>");

	@Value("${ch1.tidownload.enabled}")
	private String ch1DownloadEnabled;

	@Value("${ch1.tidownload.base.url}")
	private String ch1DownloadBaseUrl;

	@Inject
	private ImportStatusService importStatusService;
	
	
	@Inject
	private UploadTourInfoService uploadTourInfoService;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private SellingCompanyDAO sellingCompanyDAO;
	
	@Inject
	private UploadStatusService uploadStatusService;
	
	@Inject
	private ContentRepositoryService contentRepositoryService;

	@Override
	@Deprecated
	public Map<String, String> downloadTIFromCH1(String brandCode) throws Ch1DownloadServiceException {

		logger.trace("Ch1DownloadServiceImpl:downloadTIfromCH1-start");

		if (!Boolean.parseBoolean(ch1DownloadEnabled)) {

			logger.warn(WARNING_DOWNLOAD_DISABLED);
			logger.trace("Ch1DownloadServiceImpl:downloadTIfromCH1-end");

			return null;
		}

		Map<String, String> codeXmlMap = new HashMap<String, String>();

		CloseableHttpClient httpClient = null;

		try {

			URL url = new URL(StringUtils.endsWith(ch1DownloadBaseUrl, SEPARATOR_URL_PATH) ? ch1DownloadBaseUrl : ch1DownloadBaseUrl + SEPARATOR_URL_PATH);

			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(new AuthScope(url.getHost(), url.getPort()), new UsernamePasswordCredentials(StringUtils.EMPTY, StringUtils.EMPTY));

			httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
			CloseableHttpResponse response = httpClient.execute(new HttpGet(url.toString() + brandCode));

			String htmlData = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

			response.close();

			Matcher matcherTr = PATTERN_TAG_TR.matcher(htmlData);

			int index = 0;
			int number = StringUtils.countMatches(htmlData, SUFFIX_XML_LOWER) / 2;

			while (matcherTr.find()) {

				Matcher matcherAHref = PATTERN_TAG_A_HREF.matcher(matcherTr.group());

				if (!matcherAHref.find()) {
					continue;
				}

				String link = StringUtils.substringBetween(matcherAHref.group(), SEPARATOR_A_HREF);
				String code = StringUtils.substringBetween(matcherAHref.group().toUpperCase(), TAG_TT_BEGIN, TAG_TT_END).replace(SUFFIX_XML_UPPER, StringUtils.EMPTY).trim();

				importStatusService.setupMessage(String.format(INFO_DOWNLOAD_PROGRESS, code, ++index, number), brandCode, TypeMsg.INF, ProcessLevel.CH1_TI_DOWNLOAD);

				response = httpClient.execute(new HttpGet(new URL(url, link).toString()));

				logger.trace(String.format(INFO_DOWNLOAD_START, code));

				codeXmlMap.put(code, EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));

				logger.trace(String.format(INFO_DOWNLOAD_END, code));

				response.close();
			}

		} catch (Exception e) {

			logger.error(ERROR_DOWNLOAD_FAILED, e);

			throw new Ch1DownloadServiceException(e);

		} finally {

			if (httpClient != null) {

				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error(ERROR_CLOSE_HTTP_CLIENT, e);
				}
			}
		}

		logger.trace("Ch1DownloadServiceImpl:downloadTIfromCH1-end");

		return codeXmlMap;
	}
	
	
	public void downloadTIFromCH1WithSaveToDB(String brandCode) throws Ch1DownloadServiceException {
		
		logger.trace("Ch1DownloadServiceImpl:downloadTIFromCH1WithSaveToDB-start");
		
		if (!Boolean.parseBoolean(ch1DownloadEnabled)) {			
			logger.warn(WARNING_DOWNLOAD_DISABLED);
			logger.trace("Ch1DownloadServiceImpl:downloadTIfromCH1-end");			
			return;			
		}		
		
		CloseableHttpClient httpClient = null;		
		try {			
			URL url = new URL(StringUtils.endsWith(ch1DownloadBaseUrl, SEPARATOR_URL_PATH) ? ch1DownloadBaseUrl : ch1DownloadBaseUrl + SEPARATOR_URL_PATH);
			
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(new AuthScope(url.getHost(), url.getPort()), new UsernamePasswordCredentials(StringUtils.EMPTY, StringUtils.EMPTY));
			
			httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
			CloseableHttpResponse response = httpClient.execute(new HttpGet(url.toString() + brandCode));
			
			String htmlData = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			
			response.close();
			
			Matcher matcherTr = PATTERN_TAG_TR.matcher(htmlData);
			
			int index = 0;
			int number = StringUtils.countMatches(htmlData, SUFFIX_XML_LOWER) / 2;
			
			Set<Long> toursSetAdded=Sets.newTreeSet();
			List<String> toursListNotAdded=Lists.newArrayList();
			
			// prepare data
			Brand brand = brandDAO.findByBrandCode(brandCode);			
			Map<String, SellingCompany> sellingCompaniesMap = new HashMap<String, SellingCompany>();			
			for (SellingCompany sellingCompany : sellingCompanyDAO.findByBrandCode(brandCode)) {
				sellingCompaniesMap.put(sellingCompany.getCode(), sellingCompany);
			}
			
			importStatusService.setupMessage(StringUtils.EMPTY, brandCode, TypeMsg.HDN, ProcessLevel.CH1_TI_PERSIST);
			uploadStatusService.initalizeNewProccess(brandCode);
			uploadStatusService.proccesingDescription(brandCode, "Start download from CH1", UploadStatus.ProcessLevel.CH1_TI_DOWNLOAD_FOR_TD.getLevel());
			while (matcherTr.find()) {
				Matcher matcherAHref = PATTERN_TAG_A_HREF.matcher(matcherTr.group());
				
				if (!matcherAHref.find()) {
					continue;
				}
				
				String link = StringUtils.substringBetween(matcherAHref.group(), SEPARATOR_A_HREF);
				String code = StringUtils.substringBetween(matcherAHref.group().toUpperCase(), TAG_TT_BEGIN, TAG_TT_END).replace(SUFFIX_XML_UPPER, StringUtils.EMPTY).trim();
			
				importStatusService.setupMessage(StringUtils.EMPTY, brandCode, TypeMsg.HDN, ProcessLevel.CH1_TI_PERSIST);
				uploadStatusService.setupMessage(brandCode, String.format(INFO_DOWNLOAD_PROGRESS, code, ++index, number), TypeMsg.INF);

				
				response = httpClient.execute(new HttpGet(new URL(url, link).toString()));
				
				logger.trace(String.format(INFO_DOWNLOAD_START, code));
				
				String xml=EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);				
				uploadTourInfoService.addTourInfoV1OnSynchronizationTDLevel(brandCode, code, xml, toursSetAdded, toursListNotAdded, brand, sellingCompaniesMap,index,number);
				
				logger.trace(String.format(INFO_DOWNLOAD_END, code));				
				response.close();
			}
									
			contentRepositoryService.clearTourInfo(toursSetAdded, brand);			
		} catch (Exception e) {			
			logger.error(ERROR_DOWNLOAD_FAILED, e);			
			throw new Ch1DownloadServiceException(e);
			
		} finally {
			
			if (httpClient != null) {				
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error(ERROR_CLOSE_HTTP_CLIENT, e);
				}
			}
			uploadStatusService.clearProccess(brandCode);
		}
		
		logger.trace("Ch1DownloadServiceImpl:downloadTIFromCH1WithSaveToDB-end");
	}

	@Override
	public boolean testCH1Download(String brandCode) {
		
		CloseableHttpClient httpClient = null;

		try {

			URL url = new URL(StringUtils.endsWith(ch1DownloadBaseUrl, SEPARATOR_URL_PATH) ? ch1DownloadBaseUrl : ch1DownloadBaseUrl + SEPARATOR_URL_PATH);

			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(new AuthScope(url.getHost(), url.getPort()), new UsernamePasswordCredentials(StringUtils.EMPTY, StringUtils.EMPTY));

			httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
			CloseableHttpResponse response = httpClient.execute(new HttpGet(url.toString() + brandCode));

			
			String htmlData = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			response.close();
			return htmlData.contains("/tour_info/");
		} catch (Exception e) {
			logger.error(ERROR_DOWNLOAD_FAILED, e);
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error(ERROR_CLOSE_HTTP_CLIENT, e);
				}
			}
		}

		return false;
	}
}
