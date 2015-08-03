package com.ttc.ch2.bl.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.inject.Inject;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.exceptions.XMLMarshalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.report.ItinerarySegmentChecker;
import com.ttc.ch2.bl.upload.common.BrandPermissionChecker;
import com.ttc.ch2.bl.upload.common.ExtraPermissionChecker;
import com.ttc.ch2.bl.upload.common.LogOperationHelper;
import com.ttc.ch2.bl.upload.common.OperationStatus;
import com.ttc.ch2.bl.upload.common.SellingPermissionChecker;
import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.bl.upload.common.ValidatorSchemaHandler;
import com.ttc.ch2.bl.upload.common.tourinfogen.ITropicsV1TourInfoMapper;
import com.ttc.ch2.bl.upload.common.tourinfogen.TourInfoDataConsumer;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.common.EntityToIdTransform;
import com.ttc.ch2.common.predicates.FindEntityByIdPredicate;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.XMLContentRepository;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = TourInfoZipParserServiceException.class)
public class TourInfoZipParserServiceImpl implements TourInfoZipParserService {

	private static final Logger logger = LoggerFactory.getLogger(TourInfoZipParserServiceImpl.class);

	@Inject
	private TourInfoDataConsumer tourInfoDataConsumer;

	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;

	@Inject
	private ContentRepositoryService contentRepositoryService;

	@Inject
	private BrandDAO brandDAO;

	@Inject
	private SellingCompanyDAO sellingCompanyDAO;

	@Inject
	private UploadTourInfoJaxbCreator uploadTourInfoJaxbCreator;

	@Inject
	private UploadStatusService uploadStatusService;



	@Override
	public List<String> parseZipStream(OperationStatus opStatus,File fileZip) throws TourInfoZipParserServiceException {
		
		FileInputStream fileStream=null;
		FileInputStream fileStream2=null;
		ZipInputStream zipStream=null;
		ZipInputStream zipStream2=null;
        try
        {
        	fileStream=new FileInputStream(fileZip);        	
        	List<String> invalidToursListForItinerary=Lists.newArrayList();        	        
            zipStream = new ZipInputStream(fileStream,Charset.forName("ISO-8859-1"));            
        	Date now = new Date();          
			Unmarshaller jaxbUnmarshaller = uploadTourInfoJaxbCreator.createUnmarshaller(true);
            ZipEntry zipEntry;            
            boolean filesValid=true;            
            Set<Long> crRejectedMd5=Sets.newLinkedHashSet();
            Set<Long> crToSave=Sets.newLinkedHashSet();
            Date dStart=new Date();
            LogOperationHelper.logMessage(logger, opStatus,TourInfoMessages.CREATE_PRODUCTS_START);

            while((zipEntry = zipStream.getNextEntry())!=null) {
            	opStatus.addCountXml();           	
                StringBuilder tourInfoXMLBuffer = new StringBuilder();
                                
                //check zip entry name
                Iterator<String> itr=Splitter.on(File.separator).split(zipEntry.getName()).iterator();
                itr.next();
                if(zipEntry.getName().toLowerCase().endsWith(".xml")==false || itr.hasNext()){               
                	LogOperationHelper.logMessageForFile(logger, opStatus,zipEntry.getName(), TourInfoMessages.INCORRECT_FILE_NAME, zipEntry.getName() ,"file should has xml suffix or entry exist in directory");
                	filesValid=false;
                	continue;
                }
                	
                String productCodeFromFileName=StringUtils.substringBeforeLast(zipEntry.getName(), ".xml");
//                String productCodeFromFileName=zipEntry.getName().substring(0,zipEntry.getName().toLowerCase().indexOf(".xml"));
                
                LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.OPERATION_ON_FILE, zipEntry.getName(), zipEntry.getSize(), new Date(zipEntry.getTime()));
                                
                proccesingDescriptionSilent(opStatus.getBrandCode(),  "processing file:"+zipEntry.getName(),true);
                                
                readFileToString(tourInfoXMLBuffer,zipStream);              
                //logger.debug(tourInfoXMLBuffer.toString());
                OriginalTourInfoParseData data=parseOriginalTourInfoXml(tourInfoXMLBuffer, opStatus, productCodeFromFileName,jaxbUnmarshaller,zipEntry,true); 
//                if(data.valid()){
//                	TourInfo ti=data.originalTourInfo;
//                	if(data.valid() && validate(opStatus,ti, zipEntry,data.schemaValidatorHandler,productCodeFromFileName)==false || filesValid==false){
//                		filesValid=false;					
//                	}
//                }
                
                if(data.valid()){
    				if(!validate(opStatus,data.originalTourInfo, zipEntry,data.schemaValidatorHandler,productCodeFromFileName)){
    					filesValid=false;					
    				}
                }
                else{
                	filesValid=false;
                }                
				data.schemaValidatorHandler.setupDefault();								
            }// while first            
            jaxbUnmarshaller=null;
            
            LogOperationHelper.logMessage(logger, opStatus,TourInfoMessages.CREATE_PRODUCTS_END,DateHelper.calculateTime(dStart, new Date(),CalculateTimePattern.HMS));
            
            if(opStatus.getCountXml() == 0 || filesValid==false){
            	LogOperationHelper.logException(logger, opStatus, null, TourInfoMessages.INCORRECT_ZIP,opStatus.getUploadTourInfoFile().getName());
            	TourInfoZipParserServiceException e=new TourInfoZipParserServiceException(TourInfoMessages.getMessage(TourInfoMessages.INCORRECT_ZIP,opStatus.getUploadTourInfoFile().getName()));
            	e.setLoggedInHistory(true);
            	throw e;
            }
            
            IOUtils.closeQuietly(fileStream);
            fileStream=null;
                        
            Unmarshaller jaxbUnmarshallerWithoutSchemaValidate = uploadTourInfoJaxbCreator.createUnmarshaller(false);
            Date d1=new Date();
            LogOperationHelper.logMessage(logger, opStatus,TourInfoMessages.START_PERSISTS,crToSave.size());
            proccesingDescriptionSilent(opStatus.getBrandCode(), "Persist data",false);
           
        	fileStream2=new FileInputStream(fileZip);
            zipStream2 = new ZipInputStream(fileStream2,Charset.forName("ISO-8859-1"));            
            while((zipEntry = zipStream2.getNextEntry())!=null) {            	
                StringBuilder tourInfoXMLBuffer = new StringBuilder();          	
                
            	String productCodeFromFileName=StringUtils.substringBeforeLast(zipEntry.getName(), ".xml");
            	readFileToString(tourInfoXMLBuffer,zipStream2);    
            	
            	OriginalTourInfoParseData data=parseOriginalTourInfoXml(tourInfoXMLBuffer, opStatus, productCodeFromFileName,jaxbUnmarshallerWithoutSchemaValidate,zipEntry,false);
            	if(data.valid()){
                	TourInfo ti=data.originalTourInfo;
                  	
                	String contentXmlTourInfoVer1=generateOldTourInfo(opStatus,ti);

    				ContentRepository cr = contentRepositoryDAO.findByTourCode(ti.getTourCode(),opStatus.getBrandCode());    				
    				ContentRepository toUpdateOrSaveCr = createOrUpdateContentRepository(contentXmlTourInfoVer1, tourInfoXMLBuffer, zipEntry, ti, cr, productCodeFromFileName,opStatus,now);
    				
    				// need fixed
//    				uploadTourInfoFile.getContentRepositories().add(cr);
    				
    				
    				// expected null if Cr is rejected
    				if(toUpdateOrSaveCr==null){    					
    					if(cr!=null){
    						contentRepositoryDAO.evictEntity(cr);
    						crRejectedMd5.add(cr.getId());
    						LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.TI_CHECK_SUM_EXIST_IN_CR, cr.getTourCode());
        					opStatus.addRejectedXmlMd5();
    					}    					    					    					    				
    					continue;
    				}
    					
    				    				
    				//calculated status	    				
    				if (toUpdateOrSaveCr.getId() == null) {
    							toUpdateOrSaveCr.setRepositoryStatus(ContentRepository.RepositoryStatus.TourInfoOnly);
    				} else {
    		        if(toUpdateOrSaveCr.getRepositoryStatus() == ContentRepository.RepositoryStatus.Initial || toUpdateOrSaveCr.getRepositoryStatus() == ContentRepository.RepositoryStatus.Empty)
    								toUpdateOrSaveCr.setRepositoryStatus(ContentRepository.RepositoryStatus.TourInfoOnly);
    				else if(toUpdateOrSaveCr.getRepositoryStatus() !=  ContentRepository.RepositoryStatus.TourInfoOnly )
    								toUpdateOrSaveCr.setRepositoryStatus(ContentRepository.RepositoryStatus.TIandTD);
    		        }
    				proccesingDescriptionSilent(opStatus.getBrandCode(), "Persist data:"+toUpdateOrSaveCr.getTourCode(),false);
    				
    				//save and clear data
    		        contentRepositoryDAO.save(toUpdateOrSaveCr);
    		        contentRepositoryDAO.flush();
    		        crToSave.add(toUpdateOrSaveCr.getId());
//    		        contentRepositoryDAO.evictEntity(toUpdateOrSaveCr);
    		        contentRepositoryDAO.clearSession();        
//    				persistAllData(crToSave, opStatus,crRejectedMd5,uploadTourInfoFile.getBrand());
    				
    				// generate data to Itinerary raport used in anather part of process
    		        if (!new ItinerarySegmentChecker().checkItinerarySegment(ti.getItinerary())) {
    		        	invalidToursListForItinerary.add(ti.getTourCode());
    		        }    		        
            	}
            }// while second to save data in DB
                        
            LogOperationHelper.logMessage(logger, opStatus,TourInfoMessages.END_PERSISTS,DateHelper.calculateTime(d1, new Date(),CalculateTimePattern.HMS));
            
            // clear TOURINFO
        	Set<Long> crNotCleared=Sets.newHashSet(crToSave);
	        crNotCleared.addAll(crRejectedMd5);
	        crRejectedMd5.clear();
	        opStatus.setIdsCrSavedOrUpdated(crToSave);
//	        crToSave.clear();
	    	contentRepositoryService.clearTourInfo(crNotCleared,opStatus.getUploadTourInfoFile().getBrand());		
            return invalidToursListForItinerary;
        } 
        catch (TourInfoZipParserServiceException e) {
			throw e;
		} catch (PermissionDeniedException e) {
			throw e;
		} catch (Exception e) {
			throw new TourInfoZipParserServiceException(e);
		}
        finally{
			IOUtils.closeQuietly(fileStream);
			IOUtils.closeQuietly(fileStream2);
			IOUtils.closeQuietly(zipStream);
			IOUtils.closeQuietly(zipStream2);
		}
	}

	@Deprecated
	private void persistAllData(List<ContentRepository> crToSave,OperationStatus opStatus,Set<Long> crRejectedMd5,Brand brand){
	
			if (crToSave.size() > 0) {
	    		Date d1=new Date();
	        	LogOperationHelper.logMessage(logger, opStatus,TourInfoMessages.START_PERSISTS,crToSave.size());
	        	for (ContentRepository cr : crToSave) {
	        		
					if (cr.getId() == null) {
						cr.setRepositoryStatus(ContentRepository.RepositoryStatus.TourInfoOnly);
					} else {
	        			if(cr.getRepositoryStatus() == ContentRepository.RepositoryStatus.Initial || cr.getRepositoryStatus() == ContentRepository.RepositoryStatus.Empty)
							cr.setRepositoryStatus(ContentRepository.RepositoryStatus.TourInfoOnly);
						else if(cr.getRepositoryStatus() !=  ContentRepository.RepositoryStatus.TourInfoOnly )
							cr.setRepositoryStatus(ContentRepository.RepositoryStatus.TIandTD);
	        		}
					proccesingDescriptionSilent(opStatus.getBrandCode(), "Persist data:"+cr.getTourCode(),false);
	        		contentRepositoryDAO.save(cr);
	        		contentRepositoryDAO.flush();	        		            		
				}
	        	LogOperationHelper.logMessage(logger, opStatus,TourInfoMessages.END_PERSISTS,DateHelper.calculateTime(d1, new Date(),CalculateTimePattern.HMS));
	    	}
			
			
			
	    	Set<Long> crNotCleared=Sets.newHashSet((Iterables.transform(crToSave, new EntityToIdTransform())));
	        crNotCleared.addAll(crRejectedMd5);
	        crRejectedMd5.clear();
	        crToSave.clear();
	        
	        
	        
	    	contentRepositoryService.clearTourInfo(crNotCleared,brand);
	    	
            //int deleteCount=contentRepositoryService.deleteEmptyContentRepository(opStatus.getBrandCode());
            //opStatus.setDeleteCount(deleteCount);
	}
	
	
	private ContentRepository createOrUpdateContentRepository(String contentXmlTourInfoVer1,StringBuilder tourInfoXMLBuffer,ZipEntry zipEntry, TourInfo ti,
			                                                  ContentRepository cr,String productCodeFromFileName,OperationStatus opStatus,Date now) throws UnsupportedEncodingException{
		
		
//		String tourInfoXMLBuffer.toString();
		String tiXml = tourInfoXMLBuffer.toString();
		byte[] tiXmlBytes = tiXml.getBytes("UTF-8");
		
		if(cr == null) {
			LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.INSERT_TOUR_INFO,ti.getTourCode());
			logger.debug("Executing insert operation for ContentRepository Table");
			cr = new ContentRepository();
//			cr.setUploadTourInfoFile(Sets.newHashSet(uploadTourInfoFile));
			cr.setTourInfoModified(new Date());
			
			// setup relation  brand<->CR
			Brand brand=brandDAO.findByBrandCode(opStatus.getUploadTourInfoFile().getBrand().getCode());	
			cr.setBrands(Sets.newHashSet(brand));
			
			// setup relation  Selling company<->CR
			List<String> sclist =Lists.newArrayList(Iterables.transform(ti.getSellingCompanies().getSellingCompany(), new SellingCompanyToCodeXmlTransformer()));
			List<SellingCompany> sellingCompaniesList = sellingCompanyDAO.findBySellingCompanyCodes(sclist);
			Set<SellingCompany> sellingCompanies = Sets.newHashSet(sellingCompaniesList);
			cr.setSellingCompanies(sellingCompanies);
			
			cr.setStatus(ContentRepository.Status.TourInfoUpload);
		
			cr.setTourInfoModified(now);
			cr.setTiFileName(zipEntry.getName());
			cr.setTourCode(ti.getTourCode());
			cr.setCataloguedTourCode(ti.getCataloguedTour().getCode());
			XMLContentRepository xmlContent = new XMLContentRepository();
			xmlContent.setContentRepository(cr);
			cr.setXmlContentRepository(Lists.newArrayList(xmlContent));					
			xmlContent.setTourInfoXML(tiXml);
			cr.setTourInfoXMLMD5(DigestUtils.md5DigestAsHex(tiXmlBytes));
			cr.setTourInfoXMLSize(new Long(tiXmlBytes.length));
			
			xmlContent.setOldTourInfoXML(contentXmlTourInfoVer1);					
			cr.setOldTourInfoXMLSize(new Long(contentXmlTourInfoVer1.getBytes("UTF-8").length));					
			opStatus.addInsert();
			return cr;
											
		}
		else {
			logger.debug("Preparing for update TourInfo_XML");
			if(cr.getTourInfoXMLMD5()== null || !cr.getTourInfoXMLMD5().equals(DigestUtils.md5DigestAsHex(tiXmlBytes))) {
				LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.UPDATE_TOUR_INFO,ti.getTourCode());
//				cr.getUploadTourInfoFile().add(uploadTourInfoFile);				
				cr.setTiFileName(zipEntry.getName());
				cr.setCataloguedTourCode(ti.getCataloguedTour().getCode());
				cr.getXmlContentRepository().get(0).setTourInfoXML(tiXml);
				cr.setTourInfoXMLSize(new Long(tiXmlBytes.length));
				cr.setStatus(ContentRepository.Status.TourInfoUpload);
				cr.setTourInfoModified(new Date());
				cr.setTourInfoSource(ContentRepository.DataSource.CH2);

				// setup relation  brand<->CR						
				Brand brand=brandDAO.findByBrandCode(opStatus.getUploadTourInfoFile().getBrand().getCode());	

				if(Iterables.tryFind(cr.getBrands(), new FindEntityByIdPredicate(brand.getId())).isPresent()==false)
				{
					cr.getBrands().add(brand);
				}

				// setup relation  Selling company<->CR						
				List<String> sclist =Lists.newArrayList(Iterables.transform(ti.getSellingCompanies().getSellingCompany(), new SellingCompanyToCodeXmlTransformer()));
				List<SellingCompany> sellingCompaniesList = sellingCompanyDAO.findBySellingCompanyCodes(sclist);
				Set<SellingCompany> sellingCompanies = Sets.newHashSet(sellingCompaniesList);
				for(SellingCompany sc: sellingCompanies) {						
					if(Iterables.tryFind(cr.getSellingCompanies(), new FindEntityByIdPredicate(sc.getId())).isPresent()==false){							
						cr.getSellingCompanies().add(sc);
					}	
				}
				cr.setTourInfoXMLMD5(DigestUtils.md5DigestAsHex(tiXmlBytes));						
				cr.getXmlContentRepository().get(0).setOldTourInfoXML(contentXmlTourInfoVer1);
				cr.setOldTourInfoXMLSize(new Long(contentXmlTourInfoVer1.getBytes("UTF-8").length));
				
				opStatus.addUpdate();
				return cr;
			}
			else {
				// to reject CR
				return null;
			}
		}		
	}
	

	private OriginalTourInfoParseData parseOriginalTourInfoXml(StringBuilder tourInfoXMLBuffer,OperationStatus opStatus,String productCodeFromFileName,Unmarshaller jaxbUnmarshaller,ZipEntry zipEntry,boolean useSchemaHandler){		
		OriginalTourInfoParseData data=new OriginalTourInfoParseData();		
         try{ 
         	 data.originalTourInfo= (TourInfo) jaxbUnmarshaller.unmarshal(new StringReader(tourInfoXMLBuffer.toString()));
         	 if(useSchemaHandler) {
         		 data.schemaValidatorHandler=(ValidatorSchemaHandler) jaxbUnmarshaller.getEventHandler();
	         	 
	         	 if(data.schemaValidatorHandler.isNoErrors()==false) {
	         		List<String> schemaLogsError=data.schemaValidatorHandler.getLogs();
	    			for (String xsdMsg : schemaLogsError) {
	    				LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName,  TourInfoMessages.SCHEMA_VALIDATE_ON_FILE,zipEntry.getName(),xsdMsg);	
	    			}	
	         	 }
	         }
         }
         catch(UnmarshalException e){
        	 data.parseValid=false;
         	 String detailMsg=StringUtils.defaultIfBlank(e.getMessage(), "no data");
         	 
         	 if(e.getLinkedException() instanceof XMLMarshalException){
         		 detailMsg=e.getLinkedException().getMessage();
         	 }      
         	 
         	 LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName,  TourInfoMessages.SCHEMA_VALIDATE_ON_FILE,zipEntry.getName(),detailMsg);
         	 if(data.schemaValidatorHandler!=null)
         		data.schemaValidatorHandler.setupDefault();                	 
         	 
          }
          catch(Exception e){
        	 data.parseValid=false;
         	 String detailMsg=StringUtils.defaultIfBlank(e.getMessage(), "no data");                	                	
         	 LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName,  TourInfoMessages.SCHEMA_VALIDATE_ON_FILE,zipEntry.getName(),detailMsg);
         	 if(data.schemaValidatorHandler!=null)
         		data.schemaValidatorHandler.setupDefault();                	          	 
          }
          return data;
	}
	
	private boolean validate(OperationStatus opStatus, TourInfo ti,ZipEntry zipEntry, ValidatorSchemaHandler schemaValidatorHandler, String productCodeFromFileName) throws TourInfoZipParserServiceException{
		
		LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.VALIDATION_FILE, zipEntry.getName());
		
		boolean  valid=true;
				
		if(zipEntry.getName().endsWith(".xml")==false)
		{
			LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.INCORRECT_FILE_NAME, zipEntry.getName() ,"file should has xml surfix");
			valid&=false;
		}
		
		String tourCodeFromFileName=Splitter.on(".").split(zipEntry.getName()).iterator().next();
		if(tourCodeFromFileName.length()>10)
		{
			LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.INCORRECT_FILE_NAME, zipEntry.getName() ,"tourCode lenght greater than 10 character");
			valid&=false;
		}
				
				
		if(schemaValidatorHandler.isNoErrors())
		{			
			if(!zipEntry.getName().toUpperCase().equals(ti.getTourCode() + ".XML") ) {			
				LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.TI_FILENAME_CHECK, ti.getTourCode(), zipEntry.getName());
				valid&=false;
			}	
			
			// valid brands
				Brand brand=brandDAO.findByBrandCode(ti.getBrandCode());
				if(brand == null) {
					LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.BRAND_DONT_EXIST,ti.getBrandCode(), ti.getTourCode());		
					valid&=false;
				}
				
				if(!opStatus.getUploadTourInfoFile().getBrand().getCode().equals(ti.getBrandCode())) {
					LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.DIFFERENT_BRAND,brand.getCode(),ti.getBrandCode(), ti.getTourCode());		
					valid&=false;
				}				
			List<String> sclist =Lists.newArrayList(Iterables.transform(ti.getSellingCompanies().getSellingCompany(), new SellingCompanyToCodeXmlTransformer()));						
			// check extra permision
				if(opStatus.getExtraPermissionChecker().checkerFor()==ExtraPermissionChecker.CheckerType.BRAND)
				{	
					((BrandPermissionChecker)opStatus.getExtraPermissionChecker()).setBrandCode(ti.getBrandCode());
					if(opStatus.getExtraPermissionChecker().checkPermission()==false)
					{
						LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.PERMISSION_DENIED_BRAND, opStatus.getUploadTourInfoFile().getBrand().getCode(),zipEntry.getName());
						throw new  PermissionDeniedException(TourInfoMessages.getMessage(TourInfoMessages.PERMISSION_DENIED_BRAND, opStatus.getUploadTourInfoFile().getBrand().getCode(),zipEntry.getName()));
					}
				}
				else if(opStatus.getExtraPermissionChecker().checkerFor()==ExtraPermissionChecker.CheckerType.SELLING_COMPANIES)
				{
					//setup companies for check
					((SellingPermissionChecker)opStatus.getExtraPermissionChecker()).setSellingCompanies(Sets.newHashSet(sclist));					
					if(opStatus.getExtraPermissionChecker().checkPermission()==false)
					{					
						String companies=Joiner.on(",").join(((SellingPermissionChecker)opStatus.getExtraPermissionChecker()).getListCompaniesWithoutAuthority());
						LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.PERMISSION_DENIED_SELLING_COPANIES, companies,zipEntry.getName());
						throw new  PermissionDeniedException(TourInfoMessages.getMessage(TourInfoMessages.PERMISSION_DENIED_SELLING_COPANIES, companies,zipEntry.getName()));
					}
				}
				else
				{
					throw new UnsupportedOperationException("ExtraPermissionChecker has unssuported typ of check:"+opStatus.getExtraPermissionChecker().checkerFor());
				}
			
			
			
			List<SellingCompany> sellingCompaniesList = sellingCompanyDAO.findBySellingCompanyCodes(sclist);
			if(sellingCompaniesList.size() != sclist.size()) {
				
				LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.SELLING_COMPANIES_DONT_EXIST, ti.getTourCode());
				valid&=false;
			}
									
			Set<String> sellingCompanyCodeFormBrand =Sets.newHashSet(Iterables.transform(opStatus.getUploadTourInfoFile().getBrand().getSellingCompanies(), new SellingCompanyToCodeDomainTransformer()));	
			Set<String> sellingCompaniesOutsideBrand=Sets.difference(Sets.newHashSet(sclist), sellingCompanyCodeFormBrand);						
			if(sellingCompaniesOutsideBrand.size()>0)
			{
				LogOperationHelper.logMessageForFile(logger, opStatus,productCodeFromFileName, TourInfoMessages.SELLING_COMPANIES_DONT_EXIST_IN_BRAND,Joiner.on(",").join(sellingCompaniesOutsideBrand),ti.getBrandCode());
				valid&=false;
			}
		}
		else {		
			// Here is incorrect schema
			valid&=false;
		}
		return valid;
	}

	private String generateOldTourInfo(OperationStatus opStatus,TourInfo ti) throws TourInfoZipParserServiceException
	{	
		String xmlContent=null;
		try
		{
			LogOperationHelper.logMessageForFile(logger, opStatus,ti.getTourCode(), TourInfoMessages.MAPPING_FILE,ti.getTourCode());
			com.ttsl.tourinfo._2010._08._2.TourInfo tourInfover240= ITropicsV1TourInfoMapper.map(ti);

			LogOperationHelper.logMessageForFile(logger, opStatus,ti.getTourCode(), TourInfoMessages.CREATE_OLD_TOUR_INFO, ti.getTourCode());

			xmlContent = tourInfoDataConsumer.processTourInfoV1(tourInfover240);

			if (xmlContent == null || xmlContent.startsWith(TourInfoDataConsumer.ERROR_TAG)) {
				throw new TourInfoZipParserServiceException("Generate TourInfo ver 2.4.0 -[tour code:" + ti.getTourCode() + "] validation schema error:" + xmlContent);
			}
		}
		catch(TourInfoZipParserServiceException e)
		{
			throw e;
		}
		catch(Exception e)
		{						
			throw new TourInfoZipParserServiceException("Generate TourInfo ver 2.4.0 - for tour code:"+ti.getTourCode(),e);
		}
		return xmlContent;
	}

	private void proccesingDescriptionSilent(String brandCode,String message,boolean updateCount){		
	    	try{	
	    		uploadStatusService.proccesingDescription(brandCode, message, updateCount);
			} catch (Exception e) {
	    		logger.error("",e);
	    	}
	    }
	  
	private void readFileToString(StringBuilder tourInfoXMLBuffer,InputStream stream) throws UnsupportedEncodingException, IOException{
		
		byte[] buffer = new byte[2048];
		 int len = 0;
         while ((len = stream.read(buffer)) > 0) {
           	tourInfoXMLBuffer.append(new String(buffer,0, len, "UTF-8")); // xml files have to be coded in UTF-8
           }  
		
	}
	
	class SellingCompanyToCodeDomainTransformer implements Function<SellingCompany, String>
	{
		@Override
		public String apply(SellingCompany input) {			
			return input.getCode();
		}	
	}

	class SellingCompanyToCodeXmlTransformer implements Function<com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompany, String>
	{
		@Override
		public String apply(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompany input) {			
			return input.getCode();
		}	
	}
		
	class OriginalTourInfoParseData{
		TourInfo originalTourInfo;
		boolean  parseValid=true;
		ValidatorSchemaHandler schemaValidatorHandler=null;
		
		public  boolean valid(){
			return  originalTourInfo!=null && parseValid;
		}
	}
}
