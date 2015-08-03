package com.ttc.ch2.bl.upload.ch1upload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class Ch1UploadServiceImpl implements Ch1UploadService {

	private static Logger logger = LoggerFactory.getLogger(Ch1UploadServiceImpl.class); 
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;

	@Inject
	private BrandDAO brandDAO;
	
	@Value("${ch1.tiupload.hostname}")
	private String ch1UploadHostname;
	@Value("${ch1.tiupload.enabled}")
	private String ch1UploadEnabled;
	@Value("${ch1.tiupload.path}")
	private String ch1UploadPath;
	@Value("${ch1.tiupload.protocol}")
	private String ch1UploadProtocol;
	@Value("${ch1.tiupload.port}")
	private String ch1UploadPort;
	@Value("#{'${ch1.tiupload.brands}'.split(',')}")
	private List<String> ch1UploadBrandCodes;
	@Value("#{'${ch1.tiupload.users}'.split(',')}")
	private List<String> ch1UploadUsers;
	@Value("#{'${ch1.tiupload.passwords}'.split(',')}")
	private List<String> ch1UploadPasswords;
	
	@Override
	public void uploadTItoCH1(String fileName) {
		CloseableHttpClient httpclient = null;
		try {
			//Properties props = new Properties();
			//props.load(Ch1UploadServiceImpl.class.getResourceAsStream("/META-INF/ch1TourInfoUpload.properties"));
			if(Boolean.parseBoolean(ch1UploadEnabled) && StringUtils.isNotBlank(fileName) && fileName.length() > 2) {
				String brandCode = fileName.substring(0, 2);
				Brand brand = brandDAO.findByBrandCode(brandCode);
				ContentRepository filter = new ContentRepository(); 
				filter.setBrands(Sets.newHashSet(brand));
				List<RepositoryStatus> statuses = Lists.newArrayList(RepositoryStatus.TIandTD,RepositoryStatus.TourInfoOnly);
							
//				List<ContentRepository> crList = contentRepositoryDAO.getContentRepositoriesList(null, filter,statuses);
				
				List<Long> ids=contentRepositoryDAO.getContentRepositoriesIdsList(null, filter, statuses);
				
				ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
				ZipOutputStream zos = new ZipOutputStream(baoStream);
				for(Long id: ids) {
					ContentRepository cr=contentRepositoryDAO.find(id);
					zos.putNextEntry(new ZipEntry(cr.getTourCode()+".xml"));
					String tiXml = cr.getXmlContentRepository().get(0).getOldTourInfoXML().replace("http://www.ttc.com/ch2/api/ccapi/v1/TourInfo/2010/08/2.4", "http://www.ttsl.com/TourInfo/2010/08/2.4");
					zos.write(tiXml.getBytes("UTF-8"));
					zos.closeEntry();
					
//					contentRepositoryDAO.evictEntity(cr);
					contentRepositoryDAO.clearSession();
				}
				zos.close();
				
				int brandIdx=ch1UploadBrandCodes.indexOf(brandCode);
				String uploadErrorMessage=null;
				CredentialsProvider credsProvider = new BasicCredentialsProvider();
		        credsProvider.setCredentials(
		                new AuthScope(ch1UploadHostname, Integer.parseInt(ch1UploadPort)),
		                new UsernamePasswordCredentials(ch1UploadUsers.get(brandIdx), ch1UploadPasswords.get(brandIdx)));
				httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
				HttpPost httppost = new HttpPost(ch1UploadProtocol + ch1UploadHostname + ":" + 
						ch1UploadPort+ch1UploadPath);
				HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody("fileUpload", baoStream.toByteArray(), ContentType.create("application/zip"), fileName).build();
				httppost.setEntity(reqEntity);
				CloseableHttpResponse response = httpclient.execute(httppost);
				try {    
		            HttpEntity resEntity = response.getEntity();
		            String resp = new String(EntityUtils.toByteArray(resEntity));
		            if(resp.contains("<div class=\"message\">Upload success")) {
		            	logger.info("Upload to CH1 was successful");
		            }
		            else {
		            	uploadErrorMessage = "Error during upload, CH1 response: "+ resp;
		            	logger.error(uploadErrorMessage);
		            	throw new Ch1UploadServiceException(uploadErrorMessage);
		            }
		            
				}catch(Exception e) {
					uploadErrorMessage = "Cannot transfer ZIP file to CH1"+e;
					logger.error(uploadErrorMessage,e);
					throw new Ch1UploadServiceException("Error during upload ZIP file to CH1, please check log file for details...",e);
					
				}finally {
					response.close();
				}
			}
			else {
				logger.warn("CH1 upload service is disabled in the config, ignoring upload...");
			}
		}catch(IOException e) {
			logger.error("TourInfo upload to CH1 failed: ", e);
			throw new Ch1UploadServiceException(e);
		}finally {
			try {
				if(httpclient != null)
					httpclient.close();
			}catch(IOException e) {
				logger.error("Cannot close http client: " + e);
			}
		}
	}

	private boolean testUploadAvailability(String brandCode) {
		CloseableHttpClient httpclient = null;
		try {
			int brandIdx = ch1UploadBrandCodes.indexOf(brandCode);
			if(Boolean.parseBoolean(ch1UploadEnabled) && brandIdx >= 0 ) {
				CredentialsProvider credsProvider = new BasicCredentialsProvider();
		        credsProvider.setCredentials(
		                new AuthScope(ch1UploadHostname, Integer.parseInt(ch1UploadPort)),
		                new UsernamePasswordCredentials(ch1UploadUsers.get(brandIdx), ch1UploadPasswords.get(brandIdx)));
				httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
				
				HttpGet httpGet = new HttpGet(ch1UploadProtocol + ch1UploadHostname + ":" + 
						ch1UploadPort+ch1UploadPath.replace("/upload", "")+"/index");
				
				CloseableHttpResponse response = httpclient.execute(httpGet);
				try {    
		            HttpEntity resEntity = response.getEntity();
		            String resp = new String(EntityUtils.toByteArray(resEntity));
		            return resp.contains("Tour Content File Upload");
				}catch(Exception e) {
					throw e;
				}finally {
					response.close();
				}
			}
		}catch(Exception e) {
			logger.error("Exception in testUploadAvailability()", e);
		}finally {
		try {
			if(httpclient != null)
				httpclient.close();
		}catch(IOException e) {
			logger.error("Cannot close http client: " + e);
		}
	}	
		return false;
	}



	@Override
	public boolean testUploadAvailability() {
		List<Brand> brandsList = brandDAO.findAll();
		for(int i=0; i<brandsList.size();i++) {
			if(!testUploadAvailability(brandsList.get(i).getCode()))
				return false;
		}
		return true;
	}
	
}
