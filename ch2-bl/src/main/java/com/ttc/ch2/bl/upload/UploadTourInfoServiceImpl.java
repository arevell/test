package com.ttc.ch2.bl.upload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteStreams;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoRequest;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoResponse;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.upload.common.BrandPermissionChecker;
import com.ttc.ch2.bl.upload.common.LogOperationHelper;
import com.ttc.ch2.bl.upload.common.OperationStatus;
import com.ttc.ch2.bl.upload.common.SellingPermissionChecker;
import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.bl.upload.common.tourinfogen.ITropicsV3TourInfoMapper;
import com.ttc.ch2.bl.upload.common.tourinfogen.TourInfoDataConsumer;
import com.ttc.ch2.bl.upload.validator.BLMT;
import com.ttc.ch2.bl.upload.validator.TiZipStreamValidator;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.predicates.FindEntityByIdPredicate;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.TIBlobData;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.XMLContentRepository;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileSource;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.util.ext.NotBlankPropertyChecker;
import com.ttc.util.ext.NullPropertyChecker;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.Severity;
import com.ttc.util.validation.AllPassValidator;
import com.ttc.util.validation.Checker;
import com.ttc.util.validation.Validator;
import com.ttc.util.ws.MessagesUtil;
import com.ttsl.tourinfo._2010._08._2.TourInfo;

@Service
@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
public class UploadTourInfoServiceImpl implements UploadTourInfoService{

	private static final Logger logger=LoggerFactory.getLogger(UploadTourInfoServiceImpl.class);

	@Inject
	private UploadService uploadService;
		
	@Inject
	private TiZipStreamValidator tiValidator;
	
	@Inject
	private ApplicationContext ctx;
		
	@Inject 
	private UserCCAPIDAO userCCAPIDAO;
	
	private Validator<Object> validator;
	{
	    List<Checker<Object>> list = new ArrayList<>();
	    
	    NullPropertyChecker checker1 = new NullPropertyChecker();
	    checker1.setSubjectName("request parameter");
	    list.add(checker1);
	    
	    NullPropertyChecker checker3 = new NotBlankPropertyChecker();
	    checker3.setPrerequisite(checker1);
	    checker3.setSubjectName("file name");
	    checker3.setPropertyName("fileName");
	    list.add(checker3);
	    
	    NullPropertyChecker checker4 = new NullPropertyChecker();
	    checker4.setPrerequisite(checker1);
	    checker4.setSubjectName("input stream parameter");
	    checker4.setPropertyName("fileData.inputStream");
	    list.add(checker4);
	    
	    validator = new AllPassValidator<>(list);    
	}
        

	public OperationStatus addManualUploadTourInfoFile(UploadTourInfoFile uploadTourInfoFile, InputStream inputStream,UserGui user) throws UploadServiceException
	{
		logger.trace("UploadTourInfoServiceImpl:addManualUploadTourInfoFile-start");
		
		Preconditions.checkArgument(uploadTourInfoFile!=null,"UploadTourInfoServiceImpl.addManualUploadTourInfoFile arg UploadTourInfoFile is null");
		Preconditions.checkArgument(uploadTourInfoFile.getName()!=null,"UploadTourInfoServiceImpl.addManualUploadTourInfoFile arg UploadTourInfoFile.name is null");
		Preconditions.checkArgument(inputStream!=null,"UploadTourInfoServiceImpl.addApiUploadTourInfoFile arg Stream is null");
		Preconditions.checkArgument(user!=null,"UploadTourInfoServiceImpl.addApiUploadTourInfoFile user is null");
		
		Date now = new Date();
		uploadTourInfoFile.setDateUpload(now);
		
		uploadTourInfoFile.setSourceUploadFile(UploadTourInfoFileSource.MANUAL);
		uploadTourInfoFile.setUser(user);
		uploadTourInfoFile.setStatus(UploadTourInfoFileStatus.PRE_PROCESSING);
				
		BrandPermissionChecker checker=ctx.getBean(BrandPermissionChecker.class);
		checker.setUserGui(user);
		OperationStatus opStatus=new OperationStatus(uploadTourInfoFile,checker);
		opStatus.setWhoUploded(user);
		
		logger.trace("UploadTourInfoServiceImpl:addManualUploadTourInfoFile-end");
		
		return addUploadTourInfoFile(opStatus,uploadTourInfoFile, inputStream);
	}

	
	public UploadTourInfoResponse addApiUploadTourInfoFile(UploadTourInfoRequest request) throws UploadServiceException {
	    logger.trace("UploadTourInfoServiceImpl:addApiUploadTourInfoFile-start");
	    UploadTourInfoResponse response = new UploadTourInfoResponse();
	    
	    Collection<Message> messages = validate(request);
	    MessagesUtil.assignContext(response, messages);
	    if (MessagesUtil.severity(messages) == Severity.ERROR) {
	        response.setSuccessful(false);
	        return response;
	    }
	    
        UploadTourInfoFile uploadTourInfoFile = new UploadTourInfoFile();
        uploadTourInfoFile.setName(request.getFileName());        
        try {
            InputStream inputStream = request.getFileData().getInputStream();
            
            Date now = new Date();			
            uploadTourInfoFile.setSourceUploadFile(UploadTourInfoFileSource.API);		
            uploadTourInfoFile.setStatus(UploadTourInfoFileStatus.PRE_PROCESSING);            
            uploadTourInfoFile.setDateUpload(now);
            
            
            String securitykey=request.getSecurityKey();
            UserCCAPI user=userCCAPIDAO.findByToken(securitykey);
            SellingPermissionChecker checker=ctx.getBean(SellingPermissionChecker.class);
            checker.setUserCcapi(user);
            OperationStatus opStatus=new OperationStatus(uploadTourInfoFile,checker);
            opStatus.setWhoUploded(user);
            uploadTourInfoFile.setUser(user);

            
            OperationStatus operationStatus = addUploadTourInfoFile(opStatus,uploadTourInfoFile, inputStream);
            if(operationStatus.getLastError()!=null){
            	Message message = MessageBuilder.newMessage(BLMT. UPLOAD_TOUR_INFO).withSubject("Exception", operationStatus.getLastErrorMessage()).build();
                MessagesUtil.assignContext(response, message);
            }
            
        } catch (UploadServiceException e) {
            logger.error("", e);
            Message message = MessageBuilder.newMessage(BLMT. SYSTEM_ERROR).withSubject("Exception", e.getMessage()).build();
            MessagesUtil.assignContext(response, message);
        }catch (IOException e) {
        	logger.error("", e);
            Message message = MessageBuilder.newMessage(BLMT. SYSTEM_ERROR).withSubject("Exception", e.getMessage()).build();
            MessagesUtil.assignContext(response, message);
		}
        
//        response.setSuccessful(MessagesUtil.severity(messages) != Severity.ERROR);
        
        logger.trace("UploadTourInfoServiceImpl:addApiUploadTourInfoFile-end");
        return response;
	}
		
	protected OperationStatus addUploadTourInfoFile(OperationStatus opStatus, UploadTourInfoFile uploadTourInfoFile, InputStream inputStream) throws UploadServiceException {						
		logger.trace("UploadTourInfoServiceImpl:addUploadTourInfoFile-start");
		tiValidator.validateZipUplodPermission(uploadTourInfoFile);
		LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.PRE_UPLOAD_START);
		File temp=null;
		try {		
			temp = File.createTempFile("upload_tmp_file", ".tmp");
			FileOutputStream output=null;
			try{
			output=new FileOutputStream(temp);			
			ByteStreams.copy(inputStream, output);
			}finally{
				if(output!=null){
					output.flush();
					IOUtils.closeQuietly(output);
				}
			}
			
			byte data[]=FileUtils.readFileToByteArray(temp);	
			ByteArrayInputStream validStream=null;
			try{
				tiValidator.validZipExcludeZipBomb(new ByteArrayInputStream(data));
				tiValidator.validZipStream(opStatus,uploadTourInfoFile,new ByteArrayInputStream(data));
				tiValidator.searchDuplicateUploadFileName(uploadTourInfoFile, opStatus);
			}
			finally{
				IOUtils.closeQuietly(validStream);				
			}			
			TIBlobData zipData=new TIBlobData();
			zipData.setData(data);
			uploadTourInfoFile.setZipData(zipData);			
		}				
		catch (UploadServiceException e) {
			logger.error("",e);			
			e.setOperationStatus(opStatus);
			logger.trace("UploadTourInfoServiceImpl:addUploadTourInfoFile-end");			
		 throw e;
		}		
		catch(Exception e) {			
			LogOperationHelper.logException(logger, opStatus, e, TourInfoMessages.UNEXPECTED_EXCEPTION);			
			UploadServiceException ex=new UploadServiceException(e);
			ex.setOperationStatus(opStatus);
			logger.trace("UploadTourInfoServiceImpl:addUploadTourInfoFile-end");
			throw ex;
		} 
		finally
		{		
			updateTourInfoHistory(opStatus,uploadTourInfoFile);				
			FileUtils.deleteQuietly(temp);	
		}		
		logger.trace("UploadTourInfoServiceImpl:addUploadTourInfoFile-end");
		return opStatus;		
	}
	
	
	private void updateTourInfoHistory(OperationStatus opStatus, UploadTourInfoFile uploadTourInfoFile){
		
		try{
			LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.PRE_UPLOAD_END, DateHelper.calculateTime(opStatus.getStartOperation(), new Date(),CalculateTimePattern.HMS));
			opStatus.finish();			
			// if brand not exist this entity will not present on upload page because there is default brand filter on data 
			// uploadTourInfoFile is not saved to databases if brand is null  
			if(opStatus.getStatus()==UploadTourInfoFileStatus.PRE_PROCESSING)
				uploadService.addPreProcesingUploadTourInfo(uploadTourInfoFile);
		}
		catch(Exception e){			
//			last chance to release resource 
			uploadTourInfoFile.getComments().clear();
			uploadTourInfoFile.getContentRepositories().clear();
			uploadTourInfoFile.setStatus(UploadTourInfoFileStatus.ERROR);
			uploadService.mergeOnlyUploadTourInfoFile(uploadTourInfoFile);			
		}		
	}
	

	
	protected Collection<Message> validate(UploadTourInfoRequest request) {
	    Collection<Message> messages = validator.validate(request);	                
	    return messages;
	}
	
	// upload  	
	private static final String FILE_NAME_TEMPLATE = "%s.xml";	
	private static final String INFO_SAVING_TOUR = "Content Hub 1.0 Tour Info download - saving tour: [%s] being: [%s/%s]";
	private static final String ERROR_NO_SELLING_COMPANY_FOR_BRAND = "Selling company: [%s] does not exist for brand: [%s]";
	private static final String ERROR_UPLOAD_TOUR_INFO = "Error uploading tour info - brand code: [%s], tour code: [%s]";
	
	@Inject
	private UploadStatusService uploadStatusService;
	
	@Inject
	private ImportStatusService importStatusService;
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
		
	@Inject
	private TourInfoDataConsumer tourInfoDataConsumer;
	
	public boolean addTourInfoV1OnSynchronizationTDLevel(String brandCode,String tourCode,String tourInfoV1Xml,Set<Long> toursSetAdded,List<String> toursListNotAdded,Brand brand,Map<String, SellingCompany> sellingCompaniesMap,int index, int number) throws UploadServiceException {

		logger.trace("UploadTourInfoServiceImpl:addTourInfoV1OnSynchronizationTDLevel-start");
		if (tourInfoV1Xml == null) {
				logger.trace("UploadTourInfoServiceImpl:addTourInfoV1OnSynchronizationTDLevel-end");
			return false;
		}
				
			try {

				String tourInfoCode = tourCode;
				String tourInfoXmlV1 = tourInfoV1Xml;
				uploadStatusService.setupMessage(brandCode, String.format(INFO_SAVING_TOUR, tourInfoCode,index,number), TypeMsg.INF);
				uploadStatusService.proccesingDescription(brandCode, tourInfoCode, true);

				TourInfo tourInfoV1 = tourInfoDataConsumer.processTourInfoV1(tourInfoXmlV1);
				com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo tourInfoV3 = ITropicsV3TourInfoMapper.map(tourInfoV1);

				String tourInfoXmlV3 = tourInfoDataConsumer.processTourInfoV3(tourInfoV3);

				if (tourInfoXmlV3 == null || tourInfoXmlV3.startsWith(TourInfoDataConsumer.ERROR_TAG)) {
					toursListNotAdded.add(tourInfoCode);
					logger.error(String.format(ERROR_UPLOAD_TOUR_INFO, brandCode, tourInfoCode), tourInfoXmlV3);
					logger.trace("UploadTourInfoServiceImpl:addTourInfoV1OnSynchronizationTDLevel-end");
					return false;
				}

				String tourInfoMD5 = DigestUtils.md5DigestAsHex(tourInfoXmlV3.getBytes(StandardCharsets.UTF_8));

				ContentRepository contentRepository = contentRepositoryDAO.findByTourCode(tourInfoCode, brandCode);

				if (contentRepository == null) {

					contentRepository = new ContentRepository();

					XMLContentRepository xmlContentRepository = new XMLContentRepository();
					xmlContentRepository.setContentRepository(contentRepository);

					contentRepository.setTourCode(tourInfoCode);
					contentRepository.setXmlContentRepository(new ArrayList<XMLContentRepository>());
					contentRepository.getXmlContentRepository().add(xmlContentRepository);

				} else if (tourInfoMD5.equals(contentRepository.getTourInfoXMLMD5())) {

					toursSetAdded.add(contentRepository.getId());
					logger.trace("UploadTourInfoServiceImpl:addTourInfoV1OnSynchronizationTDLevel-end");
					return false;
				}

				contentRepository.setCataloguedTourCode(tourInfoV3.getCataloguedTour().getCode());
				contentRepository.setTiFileName(String.format(FILE_NAME_TEMPLATE, tourInfoCode));
				contentRepository.setTourInfoModified(new Date());
				contentRepository.setTourInfoSource(ContentRepository.DataSource.CH1);
				contentRepository.setTourInfoXMLMD5(tourInfoMD5);
				contentRepository.setTourInfoXMLSize(Long.valueOf(tourInfoXmlV3.getBytes(StandardCharsets.UTF_8).length));
				contentRepository.setOldTourInfoXMLSize(Long.valueOf(tourInfoXmlV1.getBytes(StandardCharsets.UTF_8).length));
				contentRepository.setStatus(ContentRepository.Status.TourInfoUpload);
				contentRepository.getXmlContentRepository().get(0).setTourInfoXML(tourInfoXmlV3);
				contentRepository.getXmlContentRepository().get(0).setOldTourInfoXML(tourInfoXmlV1);
				contentRepository.setRepositoryStatus(ContentRepository.RepositoryStatus.TourDepartureOnly.equals(contentRepository.getRepositoryStatus())
						|| ContentRepository.RepositoryStatus.TIandTD.equals(contentRepository.getRepositoryStatus()) ? ContentRepository.RepositoryStatus.TIandTD : ContentRepository.RepositoryStatus.TourInfoOnly);

				if (!Iterables.tryFind(contentRepository.getBrands(), new FindEntityByIdPredicate(brand.getId())).isPresent()) {
					contentRepository.getBrands().add(brand);
				}

				for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompany sellingCompanyXml : tourInfoV3.getSellingCompanies().getSellingCompany()) {

					SellingCompany sellingCompanyDB = sellingCompaniesMap.get(sellingCompanyXml.getCode());

					if (sellingCompanyDB == null) {
						throw new UploadServiceException(String.format(ERROR_NO_SELLING_COMPANY_FOR_BRAND, sellingCompanyXml.getCode(), brandCode));
					}

					if (!Iterables.tryFind(contentRepository.getSellingCompanies(), new FindEntityByIdPredicate(sellingCompanyDB.getId())).isPresent()) {
						contentRepository.getSellingCompanies().add(sellingCompanyDB);
					}
				}

				contentRepositoryDAO.save(contentRepository);
				contentRepositoryDAO.flush();
				toursSetAdded.add(contentRepository.getId());
				contentRepositoryDAO.evictEntity(contentRepository);

			} catch (Exception e) {

				toursListNotAdded.add(tourCode);
				logger.error(String.format(ERROR_UPLOAD_TOUR_INFO, brandCode, tourCode), e);
				logger.trace("UploadTourInfoServiceImpl:addTourInfoV1OnSynchronizationTDLevel-end");
				throw new UploadServiceException(e);
			}
		logger.trace("UploadTourInfoServiceImpl:addTourInfoV1OnSynchronizationTDLevel-end");
		return true;
	}	
}


