package com.ttc.test.helpservice;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.outgoingarchives.OutgoingArchivesService.OutgoingArchivesVersion;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.filecollect.FileCollectDAO;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.filecollect.FileCollect;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.user.UserCCAPI;


@Component
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class UriBuilder {

	@Inject
	private UserCCAPIDAO dao;
	
	@Inject 
	private ContentRepositoryService contentRepositoryService;
	
	@Value("${rest.test.host}")
	private String host;
	
	@Value("${rest.test.app}")
	private String app;
	
	@Value("${outgoing.archives.tour.data.v3.latest.directory}")
	private String outgoingArchivesTDLatestDirectoryV1;
	
	@Value("${outgoing.archives.tour.data.v3.latest.directory}")
	private String outgoingArchivesTDLatestDirectoryV3;
	
	@Value("${content.repository.path}")
	private String contentRepositoryDirectory;
	
	@Inject
	private FileCollectDAO fileCollectDAO; 
	
	@Inject
	private BrandDAO brandDAO;
	
	public String getHostApp()
	{
		return host+app;
	}
	
	public  String getUriCr(String path,String token,String version)
	{		
		UriCrData d=new UriCrData(path,version);
		d.token=token;
		String uriLocal=d.createUri();	
		return uriLocal;
	}
	
	public  String getUriOutgoingArchive(String path,String token,String version)
	{		
		UriOutgoingArchiveData d=new UriOutgoingArchiveData(path,version);
		d.token=token;
		String uriLocal=d.createUri();	
		return uriLocal;
	}
	
	class UriCrData
	{
		String version=null;
		String uri;
		String brand;
		String file;
		String token;
		String uriLocal;
		
				
		 public UriCrData(String uri,String version) {
			this.uri=uri;
			this.version=version;
		}
		 
		 public UriCrData(String uri) {
			 this(uri, "");
		 }

		String createUri()
		{
			setupBrandAndFile();
			setupToken();
			String uriLocal=host+app+uri+brand;
			if(StringUtils.isNotEmpty(version))
			uriLocal+="/"+version;
			
			uriLocal+="/"+file+"?token="+token;
			return uriLocal;
		}
		
		private void setupToken(){
		
//			String token="token-xxx";
			UserCCAPI u=dao.findByToken(token);
			if(u==null || u.getEnabled()==false)
				throw new UnsupportedOperationException("Test need data in DB user ccapi with token"+token);					
		}
		
		private void setupBrandAndFile()
		{
			RepositoryStatus rsToFind=null;
			if(uri.contains("tour_info"))
				rsToFind=RepositoryStatus.TourInfoOnly;
			else if(uri.contains("tour_departure"))
					rsToFind=RepositoryStatus.TourDepartureOnly;
			
			ContentRepository filter=new ContentRepository();
//			filter.setTourCode("LA3SN10");			
			List<ContentRepository> lCr=contentRepositoryService.getContentRepositoriesList(new QueryCondition(0, 1),filter ,RepositoryStatus.TIandTD,rsToFind);
			
				if (lCr.size() > 0) {
					ContentRepository cr=null;
					try{
					cr=lCr.get(0);
					brand=cr.getBrands().iterator().next().getCode();
					file=cr.getTourCode()+".xml";
					}catch(Exception e){
						throw new UnsupportedOperationException("Test has problem with extract data for rest URI tourCode:"+ cr!=null ? cr.getTourCode() : "[not available]");							
					}
				}
				else{
					throw new UnsupportedOperationException("Test need data in DB brand related with content repository");
				}
			
		}	
	}
	
	
	class UriOutgoingArchiveData
	{
		String version=null;
		String uri;
		String file;
		String token;
		String uriLocal;
				
		 public UriOutgoingArchiveData(String uri,String version) {
			this.uri=uri;
			this.version=version;
		}
		 
		 public UriOutgoingArchiveData(String uri) {
			 this(uri, "");
		 }

		String createUri()
		{			
			setupToken();
			setupFile();
			String uriLocal=host+app+uri;
			if(StringUtils.isNotEmpty(version))
				uriLocal+=version+"/";
			uriLocal+=file+"?token="+token;
			return uriLocal;
		}
		
		private void setupToken(){
		
			UserCCAPI u=dao.findByToken(token);
			if(u==null || u.getEnabled()==false)
				throw new UnsupportedOperationException("Test need data in DB user ccapi with token"+token);			
		}
		
		private void setupFile()
		{
			String pathVersion = OutgoingArchivesVersion.V1.equals(StringUtils.defaultIfBlank(version,"V1")) ? outgoingArchivesTDLatestDirectoryV1 : outgoingArchivesTDLatestDirectoryV3;
			File pathToLastVersionRepository=new File(contentRepositoryDirectory+pathVersion);
			
			String sellingCompanyCode=null;
			for (Brand brand : brandDAO.findAll()) {
				List<FileCollect> list=  fileCollectDAO.getFileCollectList(brand.getCode());
				if(list.size()>0){
					sellingCompanyCode=list.get(0).getSellingCompany().getCode();
					break;
				}
			}
			
			if(StringUtils.isEmpty(sellingCompanyCode))
				throw new UnsupportedOperationException("Test need data in outgoing archive repository");
			
			this.file=sellingCompanyCode+".zip";			
		}
		
	}
}
