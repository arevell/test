package com.ttc.ch2.bl.diag;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedRequest;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedResponse;
import com.ttc.ch2.bl.download.ch1download.Ch1DownloadService;
import com.ttc.ch2.bl.upload.ch1upload.Ch1UploadService;
import com.ttc.ch2.bl.upload.ch1upload.Ch1UploadServiceImpl;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.DBConfigDAO;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.search.services.SearchService;

@Service
public class DiagnosticImpl implements Diagnostic {

	private static final Logger logger = LoggerFactory.getLogger(DiagnosticImpl.class);
	
	@Inject 
	private SearchService searchService;
	
	@Inject
	private UserCCAPIDAO userDao;
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject 
	private DBConfigDAO dbConfigDAO;
	
	@Inject 
	private BrandDAO brandDAO;
	
	@Inject
	private Ch1DownloadService ch1DownloadService;
	
	@Inject
	private Ch1UploadService ch1UploadService;
	
	@Value("${ch2.es.health_check.token}")
	private String ccapiToken;
	
	@Value("${ch2.cluster.login_page}")
	private String ch2clusterPage;
	
	@Value("${ch2.es.health_check.sc}")
	private String sellingCompany;
	
	@Value("${endpoint.tropics_build_ws}")
	private String tropicsBuildWSEndpoint;
	
	private boolean isElasticSearchOK;
	private boolean isTropicsConnectivityOK;
	private boolean isCH1UploadConnectionOK;
	private boolean isCH1DownloadConnectionOK;
	private boolean isTempDirOK;
	private boolean isDBCodingSetOK;
	private boolean isClusterStateOK;
	private long matchingTIandTD;
	
	@PostConstruct
	public void init() {
		// we are interested in health check only during Tomcat start 
		if( System.getProperty("catalina.home") != null ) { 
			checkElasticSearch();
			checkTempDir();
			checkDBCoding();
			checkTropicsConnectivity();
			checkMatchingTIandTD();
			checkCH1UploadConnectionOK();
			checkCH1DownloadConnectionOK();
		}
	}
	
	@Override
	public boolean isElasticSearchOK() {
		return isElasticSearchOK;
	}

	@Override
	public boolean isTropicsConnectivityOK() {
		return isTropicsConnectivityOK;
	}

	
	@Override
	public boolean isTempDirOK() {
		return isTempDirOK;
	}

	@Override
	public boolean isDBCodingSetOK() {
		return isDBCodingSetOK;
	}

	@Override
	public boolean isClusterStateOK() {
		return isClusterStateOK;
	}

	@Override
	public long getMatchingTIandTD() {
		return matchingTIandTD;
	}

	@Override
	public boolean isCH1UploadConnectionOK() {
		return isCH1UploadConnectionOK;
	}

	@Override
	public boolean isCH1DownloadConnectionOK() {
		return isCH1DownloadConnectionOK;
	}

	@Override
	public void refreshStatus() {
		checkElasticSearch();
		checkClusterState();
		checkTempDir();
		checkDBCoding();
		checkTropicsConnectivity();
		checkMatchingTIandTD();
		checkCH1UploadConnectionOK();
		checkCH1DownloadConnectionOK();
	}	
	
	private void checkElasticSearch(){
		isElasticSearchOK = false;
		try {
			if(ccapiToken != null ) {
				GetContinentsAndCountriesVisitedRequest request = new GetContinentsAndCountriesVisitedRequest();
				request.getSellingCompanies().add(sellingCompany);
				request.setSecurityKey(ccapiToken);
				GetContinentsAndCountriesVisitedResponse response = searchService.getContinentsAndCountriesVisited(request);
				if( !(response == null || response.getContinentsAndCountries().size() == 0))   
					isElasticSearchOK = true;
			}
		}catch(Exception e) {
			logger.error("An error in isElasticSearchOK():", e);
		}
	}
		
	private void checkClusterState() {
		isClusterStateOK = false;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(ch2clusterPage);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {    
	            HttpEntity resEntity = response.getEntity();
	            String resp = new String(EntityUtils.toByteArray(resEntity));
	            isClusterStateOK =  resp.contains("Please login below");            
			}catch(Exception e) {
				throw e;
			}finally {
				response.close();
			}
		}catch(Exception e) {
			logger.error("An error in isTropicsConnectivityOK():", e);
		}
	}
	
	private void checkTempDir() {
		isTempDirOK = false;
		File temp = null;
		try {
			temp = File.createTempFile("temp_health_check", ".tmp");
			isTempDirOK= temp.canWrite() && temp.canRead();
		}catch(Exception e) {
			logger.error("An error in isTempDirOK():", e);
		}
		finally {
			if(temp !=null)
				temp.delete();
		}	
	}
	
	private void checkDBCoding() {
		isDBCodingSetOK = false;
		try {
			String charset=dbConfigDAO.getDBNLSparam("NLS_CHARACTERSET");
			isDBCodingSetOK = charset.contains("UTF");
		}catch(Exception e) {
			logger.error("An error in isDBCodingSetOK():", e);
		}
	}
	
	private void checkTropicsConnectivity() {
		isTropicsConnectivityOK = false;
		try {
		    CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(tropicsBuildWSEndpoint + "?wsdl");
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {    
	            HttpEntity resEntity = response.getEntity();
	            String resp = new String(EntityUtils.toByteArray(resEntity));
	            isTropicsConnectivityOK =  resp.contains("http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS");            
			}catch(Exception e) {
				throw e;
			}finally {
				response.close();
			}
		
		}catch(Exception e) {
			logger.error("Exception in isClusterStateOK()", e);
		}
	}
	
	private void checkMatchingTIandTD() {
		matchingTIandTD = 0l;
		List<Brand> brandList = brandDAO.findAll();
		for(int i=0; i<brandList.size(); i++) {
			long brandMatched = checkMatchingTIandTD(brandList.get(i));
			if(brandMatched == 0) {
				matchingTIandTD = 0l;
				break;
			}
			else {
				matchingTIandTD =+ brandMatched; 
			}
		}
	}
	
	private long checkMatchingTIandTD(Brand brand) {
		matchingTIandTD = 0l;
		try {
			ContentRepository cr = new ContentRepository();
			cr.setRepositoryStatus(RepositoryStatus.TIandTD);
			Set<Brand> brandSet = new HashSet<Brand>();
			brandSet.add(brand);
			cr.setBrands(brandSet);
			return contentRepositoryDAO.getContentRepositoriesCount(null, cr);
		}catch(Exception e) {
			logger.error("An error in getMatchingTIandTD():", e);
		}
		return 0l;
	}
	
	private void checkCH1UploadConnectionOK() {
		isCH1UploadConnectionOK = ch1UploadService.testUploadAvailability();
	}
	
	private void checkCH1DownloadConnectionOK() {
		List<Brand> brandList = brandDAO.findAll();
		isCH1DownloadConnectionOK = brandList.size() > 0 ? ch1DownloadService.testCH1Download(brandList.get(0).getCode()) : false;
		
	}

}
