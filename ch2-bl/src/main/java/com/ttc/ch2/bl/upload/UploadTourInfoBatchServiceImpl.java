package com.ttc.ch2.bl.upload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipFile;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.xml.sax.SAXException;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteStreams;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.departure.TropicSynchronizeServiceException;
import com.ttc.ch2.bl.filecollect.FileCollectService;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.bl.lock.DbLocker;
import com.ttc.ch2.bl.lock.DbLocker.LockSql;
import com.ttc.ch2.bl.lock.Executor;
import com.ttc.ch2.bl.lock.ExecutorException;
import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.bl.report.ItinerarySegmentReportService;
import com.ttc.ch2.bl.report.ReconciliationReportService;
import com.ttc.ch2.bl.upload.ch1upload.Ch1UploadService;
import com.ttc.ch2.bl.upload.ch1upload.Ch1UploadServiceException;
import com.ttc.ch2.bl.upload.common.BrandPermissionChecker;
import com.ttc.ch2.bl.upload.common.JobExecutor;
import com.ttc.ch2.bl.upload.common.LogOperationHelper;
import com.ttc.ch2.bl.upload.common.OperationStatus;
import com.ttc.ch2.bl.upload.common.SellingPermissionChecker;
import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.bl.upload.common.tourinfogen.ITropicsV3TourInfoMapper;
import com.ttc.ch2.bl.upload.common.tourinfogen.TourInfoDataConsumer;
import com.ttc.ch2.bl.upload.validator.TiZipStreamValidator;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.common.StopBatchException;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.common.predicates.FindEntityByIdPredicate;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.dao.transfer.TourInfoTransferDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.XMLContentRepository;
import com.ttc.ch2.domain.upload.UploadStatus;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileSource;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.search.export.IndexSynchronizerService;
import com.ttc.ch2.search.export.IndexSynchronizerVO;
import com.ttsl.tourinfo._2010._08._2.TourInfo;

@Service
@Scope("prototype")
public class UploadTourInfoBatchServiceImpl implements UploadTourInfoBatchService 
{
	private static final Logger logger = LoggerFactory.getLogger(UploadTourInfoBatchServiceImpl.class);
	private static final Logger activityLogger = LoggerFactory.getLogger("ch2.activity.UploadTourInfoBatchServiceImpl");
	
	private static final String FILE_NAME_TEMPLATE = "%s.xml";

	private static final String INFO_SAVING_TOUR = "Content Hub 1.0 Tour Info download - saving tour: [%s] being: [%s/%s]";

	private static final String ERROR_NO_SELLING_COMPANY_FOR_BRAND = "Selling company: [%s] does not exist for brand: [%s]";
	private static final String ERROR_UPLOAD_TOUR_INFO = "Error uploading tour info - brand code: [%s], tour code: [%s]";

	
	
	@Value("${ch1.synchronize}")
	private String ch1Synchronize;
	
	@Value("${elastic.search.indexing}")
	private String elasticSearchIndexing;
	
	@Inject
	private UploadService uploadService;
	
	@Inject
	private Ch1UploadService ch1UploadService;
	
	@Inject 
	private TourInfoZipParserService tourInfoZipParserService; 

	@Inject
	private ImportStatusService importStatusService;

	@Inject
	private UploadStatusService uploadStatusService;
	
	@Inject
	private LockBrandService lockBrandService;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private TiZipStreamValidator tiValidator;
		
	@Inject
	private SellingCompanyDAO sellingCompanyDAO;
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject
	private ContentRepositoryService contentRepositoryService;
	
	@Inject
	private TourInfoDataConsumer tourInfoDataConsumer;
	
	@Inject
	private TourInfoTransferDAO tourInfoTransferDAO;
	
	@Inject
	private FileCollectService fileCollectService;

	@Inject
	private ItinerarySegmentReportService itinerarySegmentReportService;

	@Inject
	private ReconciliationReportService reconciliationReportService;
	
	@Inject
	private IndexSynchronizerService indexSynchronizerService;	
		
	@Inject
	private ApplicationContext ctx;

	private boolean lockUp=false;
	private boolean initialize=false;
	
	private OperationStatus opStatus;
	
	private UploadTourInfoFile uploadTourInfoFile;
		
	
	/* (non-Javadoc)
	 * @see com.ttc.ch2.bl.upload.UploadTourInfoBatchService#invokeProcess(java.lang.String)
	 */
	public void invokeProcess(String brandCode,JobExecutor extraOperationFromScheduler){		
		logger.trace("UploadTourInfoBatchServiceImpl:invokeProcess-start");
		
		Preconditions.checkArgument(StringUtils.isNotEmpty(brandCode),"UploadTourInfoBatchServiceImpl:invokeProcess arg BrandCode is null");
		Preconditions.checkArgument(StringUtils.isNotEmpty(brandCode),"UploadTourInfoBatchServiceImpl:invokeProcess arg extraOperationFromScheduler is null");
		File temp=null;
		try{	
			
				// intialize data and make lock if can on brand
				InitializeExecutor initializeData=new InitializeExecutor(brandCode,extraOperationFromScheduler);
				DbLocker dblocker=ctx.getBean(DbLocker.class);
				try{
					dblocker.executeOperation(initializeData, LockSql.UPLOAD_LOCK);
				}
				catch(ExecutorException e){
					if(e.getCause()==null){
						throw new UploadServiceException(e);
					}
					else
						throw (Exception)e.getCause();
				}
			
				if (initialize== false || lockUp==false ) {
					return; 
				}
				
				// write data to temp file and valid again data
				temp = File.createTempFile("upload_tmp_file", ".tmp");
				FileOutputStream output=null;
				try{
				output=new FileOutputStream(temp);			
				ByteStreams.copy(new ByteArrayInputStream(uploadTourInfoFile.getZipData().getData()), output);
				}finally{
					if(output!=null){
						output.flush();
						IOUtils.closeQuietly(output);
					}
				}			
				byte data[]=FileUtils.readFileToByteArray(temp);	
				ByteArrayInputStream validStream=null;
				try{
					validStream=new ByteArrayInputStream(data);
					tiValidator.validZipStream(opStatus,uploadTourInfoFile,validStream);
				}
				finally{
					IOUtils.closeQuietly(validStream);				
				}
				
				setupMessage(opStatus,new ZipFile(temp));		

				// processing data - first part of processing
				List<String> tourCodeListForItineraryReport=Lists.newArrayList();
				tourCodeListForItineraryReport=tourInfoZipParserService.parseZipStream(opStatus,temp);
				
				// elasticsearch indexing
				proccesingDescriptionSilent(opStatus.getBrandCode(), "Create index", false,UploadStatus.ProcessLevel.INDEXING);				
				IndexSynchronizerVO indexSynchronizerVO = null;
				if (BooleanUtils.toBoolean(elasticSearchIndexing)) {
					try {
						indexSynchronizerVO = indexSynchronizerService.synchronize(ProcessName.UPLOAD, opStatus.getBrandCode());
						LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.INDEX_UPLOAD_SUCCESS);
					} catch (Exception e) {
						LogOperationHelper.logException(logger, opStatus, e, TourInfoMessages.UNEXPECTED_EXCEPTION);
					}
				} else {
					LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.INDEXING_UPLOAD_TOURN_OFF);
				}
				
				// outgoing archives
				proccesingDescriptionSilent(opStatus.getBrandCode(), "Outgoing archives processing", false,UploadStatus.ProcessLevel.OUTGOING_ARCHIVE);

				FileCollectVO fileCollectVO = null;
				try {
					fileCollectVO = fileCollectService.createLatestVersion(ProcessName.UPLOAD, opStatus.getBrandCode());
				} catch (Exception e) {
					LogOperationHelper.logException(logger, opStatus, e, TourInfoMessages.UNEXPECTED_EXCEPTION);
				}

				// create itinerary segment report
				proccesingDescriptionSilent(opStatus.getBrandCode(), "Itinerary Segment Report", false,UploadStatus.ProcessLevel.INTENERARY_RAPORT);
				String invalidTours = itinerarySegmentReportService.createItinerarySegmentReport(ProcessName.UPLOAD, opStatus.getBrandCode(), tourCodeListForItineraryReport);
				
				// clean unnecessary data from first part of processing
				tourCodeListForItineraryReport=null;
								
				if (StringUtils.isNotBlank(invalidTours)) {
					LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.ITINERARY_SEGMENT_VALIDATION_WARNING, invalidTours);
				}

				
				try{
					proccesingDescriptionSilent(opStatus.getBrandCode(), "Reconciliation Report", false,UploadStatus.ProcessLevel.RECONCILIATION);
					reconciliationReportService.createReconciliationReport(ProcessName.UPLOAD, opStatus.getBrandCode(), indexSynchronizerVO, fileCollectVO);
				}catch(Exception e){					
					LogOperationHelper.logException(logger, opStatus, e, TourInfoMessages.UNEXPECTED_EXCEPTION);					
				}
								
				// upload to ch1
				proccesingDescriptionSilent(opStatus.getBrandCode(), "Upload data to CH1", false,UploadStatus.ProcessLevel.CH1_TI_DOWNLOAD);		
		        activityLogger.info("USER: {}  uploaded file:{}", opStatus.getWhoUploded().getUsername(), uploadTourInfoFile.getName());
				
				//TourInfo upload to CH1
				if(BooleanUtils.toBoolean(ch1Synchronize)){
					if (tourInfoTransferDAO.isUploadEnabled(opStatus.getBrandCode())) {
					ch1UploadService.uploadTItoCH1(uploadTourInfoFile.getName());
					LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.CH1_UPLOAD_SUCCESS);
					}
					else{
						LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.CH1_UPLOAD_DISABLED,brandCode);	
					}
				}else {
					LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.CH1_UPLOAD_TOURN_OFF);
				}
		}
		//exception after initialize
		catch(Ch1UploadServiceException e) {
			
			if(opStatus.isUploadStatusUpdated() && StringUtils.isNotEmpty(opStatus.getBrandCode())){
				setupMessageSilent( e.getMessage(),TypeMsg.ERR);
			}
			LogOperationHelper.logException(logger, opStatus, e, TourInfoMessages.CH1_UPLOAD_ERROR);
			throw new UploadServiceException("Error during sending file to CH1");
		}
//		exception after initialize
		catch(TourInfoZipParserServiceException e)
		{
			logger.error("",e);
			if(!e.isLoggedInHistory())
				LogOperationHelper.logException(logger, opStatus, e, TourInfoMessages.UNEXPECTED_EXCEPTION);
			
			if(opStatus.isUploadStatusUpdated() && StringUtils.isNotEmpty(opStatus.getBrandCode())){
				try{
					setupMessageSilent( e.getMessage(),TypeMsg.ERR);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
			
			UploadServiceException ex=new UploadServiceException(e);
			ex.setOperationStatus(opStatus);
				logger.trace("UploadTourInfoServiceImpl:addUploadTourInfoFile-end");
			throw ex;
		}
//		exception after initialize
		catch (PermissionDeniedException e) {
			logger.error("",e);			
			UploadServiceException ex=new UploadServiceException(e.getMessage(),e);
			ex.setOperationStatus(opStatus);
			if(opStatus.isUploadStatusUpdated() && StringUtils.isNotEmpty(opStatus.getBrandCode())){
				setupMessageSilent( e.getMessage(),TypeMsg.ERR);
			}
			logger.trace("UploadTourInfoServiceImpl:addUploadTourInfoFile-end");
			throw ex;
		}
		catch(Exception e) {					
			logger.error("",e);
			if(lockUp){
				LogOperationHelper.logException(logger, opStatus, e, TourInfoMessages.UNEXPECTED_EXCEPTION);
			}
			UploadServiceException ex=new UploadServiceException(e);
			ex.setOperationStatus(opStatus);
			logger.trace("UploadTourInfoServiceImpl:addUploadTourInfoFile-end");
			throw ex;
		} 
		finally
		{			
			if(lockUp){
				try{
					updateTourInfoHistory(opStatus,uploadTourInfoFile);
					extraOperationFromScheduler.release();
					FileUtils.deleteQuietly(temp);
					uploadStatusService.clearProccess(brandCode);
				}
				finally{
					lockBrandService.releaseLockBrand(opStatus.getBrandCode(),ProcessName.UPLOAD);
					
				}	
			}
		}	
	}
		
	private boolean lockUploadForBrand(OperationStatus opStatus, UploadTourInfoFile uploadTourInfoFile) throws IOException, JAXBException, SAXException
	{
		logger.trace("UploadTourInfoServiceImpl:lockUploadForBrand-start");
		Preconditions.checkState(StringUtils.isNotBlank(opStatus.getBrandCode()), "UploadTourInfoServiceImpl->lockUploadForBrand opStatus should have brandCode");					
		String brandCode=opStatus.getBrandCode();
			if (lockBrandService.lockBrand(brandCode, ProcessName.UPLOAD)==false) {				
				logger.info("UploadTourInfoServiceImpl->lockUploadForBrand currently brand "+opStatus.getBrandCode()+" is locked");				
				return false;
			}
			else {
				LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.LOCK_UPLOAD_FOR_BRAND,brandCode);
				uploadTourInfoFile.setStatus(UploadTourInfoFileStatus.PROCESSING);
				opStatus.setStatus(UploadTourInfoFileStatus.PROCESSING);
				uploadService.mergeOnlyUploadTourInfoFile( uploadTourInfoFile);				
			}
			return true;
	}
	
	private void updateTourInfoHistory(OperationStatus opStatus, UploadTourInfoFile uploadTourInfoFile){
		
		try{
			LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.UPLOAD_END, DateHelper.calculateTime(opStatus.getStartOperation(), new Date(),CalculateTimePattern.HMS));
			opStatus.finish();			
			uploadService.mergeUploadTourInfoFileWithCr(uploadTourInfoFile,opStatus.getIdsCrSavedOrUpdated());
		}
		catch(Exception e){			
//			last chance to release resource 
			uploadTourInfoFile.getComments().clear();
			uploadTourInfoFile.getContentRepositories().clear();
			uploadTourInfoFile.setStatus(UploadTourInfoFileStatus.ERROR);
			uploadService.mergeUploadTourInfoFileWithCr(uploadTourInfoFile,opStatus.getIdsCrSavedOrUpdated());			
		}
		finally{
			if(opStatus.isUploadStatusUpdated() && StringUtils.isNotEmpty(opStatus.getBrandCode()))
        		uploadStatusService.clearProccess(opStatus.getBrandCode());
		}
	}
	
	private void setupMessage(OperationStatus opStatus,ZipFile zipFile) throws IOException{     
		uploadStatusService.updateCountProccess(zipFile.size(),opStatus.getBrandCode());
        String msg=String.format("File %s processing - upload by user %s",opStatus.getUploadTourInfoFile().getName(),opStatus.getWhoUploded().getUsername());                        
        setupMessageSilent( msg,TypeMsg.INF);
		opStatus.setUploadStatusUpdated(true);
    }
	
    private void setupMessageSilent(String message,TypeMsg msgType){		
		try{	
			uploadStatusService.setupMessage(opStatus.getBrandCode(),message,msgType);
		}catch(Exception e)
		{
			logger.error("",e);
		}
	}
    private void proccesingDescriptionSilent(String brandCode,String message,boolean updateCount,UploadStatus.ProcessLevel proccesLevel){		
    	try{	    		
    		if(proccesLevel!=null){
    			uploadStatusService.proccesingDescription(brandCode, message, proccesLevel.getLevel());
    		}
    		else{
    			uploadStatusService.proccesingDescription(brandCode, message, updateCount);	
    		}
		} catch (Exception e) {
    		logger.error("",e);
    	}
    }
    
    
    class  InitializeExecutor extends Executor {			
		
    	private boolean loadedData=false;
		private String brandCode;
		private JobExecutor extraOperationFromScheduler;
		
		
		public InitializeExecutor(String brandCode, JobExecutor extraOperationFromScheduler) {
			super();
			this.brandCode = brandCode;
			this.extraOperationFromScheduler=extraOperationFromScheduler;
		}

		@Override
		public void execute() throws ExecutorException {				
			//setup uploadTourInfoFile
			UploadTourInfoFile filter=new UploadTourInfoFile();
			filter.setStatus(UploadTourInfoFileStatus.PRE_PROCESSING);
			filter.setBrand(brandDAO.findByBrandCode(brandCode));
			List<UploadTourInfoFile> list=uploadService.getUploadTourInfoList(null, filter);
			if(list.size()==0){
				logger.info("UploadTourInfoBatchServiceImpl:invokeProcess nothing to do with brand:"+brandCode);
				initialize=false;				
			}else if(list.size()>1){
				throw new UploadServiceException("To many entities with status PRE_PROCESSING and brand code:"+brandCode);
			}else{				
				uploadTourInfoFile=uploadService.getFullData(list.get(0).getId());
				loadedData=true;
			}
			//setup operation status
			if(loadedData){
				if(uploadTourInfoFile.getSourceUploadFile()==UploadTourInfoFileSource.MANUAL){							
					BrandPermissionChecker checker=ctx.getBean(BrandPermissionChecker.class);
					checker.setUserGui((UserGui) uploadTourInfoFile.getUser());					
					opStatus=new OperationStatus(uploadTourInfoFile,checker);
					opStatus.setWhoUploded(uploadTourInfoFile.getUser());
				}
				else if(uploadTourInfoFile.getSourceUploadFile()==UploadTourInfoFileSource.API){
					 SellingPermissionChecker checker=ctx.getBean(SellingPermissionChecker.class);
					 checker.setUserCcapi((UserCCAPI) uploadTourInfoFile.getUser());
					 opStatus=new OperationStatus(uploadTourInfoFile,checker);
					 opStatus.setWhoUploded(uploadTourInfoFile.getUser());
				}
				
				opStatus.setBrandCode(brandCode);
				initialize=true;
				
				try{
				lockUp=lockUploadForBrand(opStatus,uploadTourInfoFile);
				if(lockUp){
					LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.UPLOAD_START);					
					extraOperationFromScheduler.init();				
					uploadStatusService.initalizeNewProccess(opStatus.getBrandCode());
					setupMessageSilent("Loked brand Code:"+brandCode ,TypeMsg.INF);				
					}
				}
				catch(Exception e){
					throw new ExecutorException(e);
				}						
			}
		}
	};
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<String> uploadTourInfo(String brandCode, Map<String, String> tourInfoV1CodeXmlMap) throws UploadServiceException {

		logger.trace("UploadTourInfoBatchServiceImpl:uploadTourInfo-start");

		if (tourInfoV1CodeXmlMap == null) {

			logger.trace("UploadTourInfoBatchServiceImpl:uploadTourInfo-end");
			return null;
		}

		Brand brand = brandDAO.findByBrandCode(brandCode);

		Map<String, SellingCompany> sellingCompaniesMap = new HashMap<String, SellingCompany>();

		for (SellingCompany sellingCompany : sellingCompanyDAO.findByBrandCode(brandCode)) {
			sellingCompaniesMap.put(sellingCompany.getCode(), sellingCompany);
		}

		Set<Long> toursSetAdded = new HashSet<Long>();
		List<String> toursListNotAdded = new ArrayList<String>();

		int index = 0;

		uploadStatusService.initalizeNewProccess(brandCode);
		uploadStatusService.updateCountProccess(tourInfoV1CodeXmlMap.size(), brandCode);

		for (Entry<String, String> entry : tourInfoV1CodeXmlMap.entrySet()) {

			try {

				String tourInfoCode = entry.getKey();
				String tourInfoXmlV1 = entry.getValue();

				uploadStatusService.setupMessage(brandCode, String.format(INFO_SAVING_TOUR, tourInfoCode, ++index, tourInfoV1CodeXmlMap.size()), TypeMsg.INF);
				uploadStatusService.proccesingDescription(brandCode, tourInfoCode, true);
				importStatusService.setupMessage(StringUtils.EMPTY, brandCode, TypeMsg.HDN, ProcessLevel.CH1_TI_PERSIST);

				TourInfo tourInfoV1 = tourInfoDataConsumer.processTourInfoV1(tourInfoXmlV1);
				com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo tourInfoV3 = ITropicsV3TourInfoMapper.map(tourInfoV1);

				String tourInfoXmlV3 = tourInfoDataConsumer.processTourInfoV3(tourInfoV3);

				if (tourInfoXmlV3 == null || tourInfoXmlV3.startsWith(TourInfoDataConsumer.ERROR_TAG)) {

					toursListNotAdded.add(tourInfoCode);
					logger.error(String.format(ERROR_UPLOAD_TOUR_INFO, brandCode, tourInfoCode), tourInfoXmlV3);

					continue;
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
					continue;
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

				toursListNotAdded.add(entry.getKey());
				logger.error(String.format(ERROR_UPLOAD_TOUR_INFO, brandCode, entry.getKey()), e);
				
				// I thing this exception should be throw need discussion with pawel
//				throw new UploadServiceException(e);
			}
		}
		contentRepositoryService.clearTourInfo(toursSetAdded, brand);

		uploadStatusService.clearProccess(brandCode);

		logger.trace("UploadTourInfoBatchServiceImpl:uploadTourInfo-end");

		return toursListNotAdded;
	}
	
	@Deprecated
	private void checkCancelOperation(OperationStatus opStatus) throws TropicSynchronizeServiceException {

		if (JobStatus.Cancelled==opStatus.getJobStatus()) {
			LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.CANCEL_PROCESS);
			StopBatchException e = new StopBatchException(TourInfoMessages.CANCEL_PROCESS.getMessage());
			throw e;
		}
		else if (JobStatus.Inactive==opStatus.getJobStatus()) {
			LogOperationHelper.logMessage(logger, opStatus, TourInfoMessages.INACTIVE_PROCESS);
			StopBatchException e = new StopBatchException(TourInfoMessages.INACTIVE_PROCESS.getMessage());
			throw e;
		}
	}
}
