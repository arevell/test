package com.ttc.ch2.bl.departure;

import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.BooleanUtils;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Throwables;
import com.ttc.ch2.bl.departure.common.LogOperationHelper;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.bl.download.ch1download.Ch1DownloadService;
import com.ttc.ch2.bl.filecollect.FileCollectService;
import com.ttc.ch2.bl.filecollect.FileCollectServiceException;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.bl.filecollect.FileCollectVO.StatusOperation;
import com.ttc.ch2.bl.report.ReconciliationReportService;
import com.ttc.ch2.bl.report.ReconciliationReportServiceException;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.transfer.TourInfoTransferDAO;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.search.export.IndexSynchronizerService;
import com.ttc.ch2.search.export.IndexSynchronizerServiceException;
import com.ttc.ch2.search.export.IndexSynchronizerVO;

@Service
public class TropicExtendedDepartureSynchronizeServiceImpl implements TropicExtendedDepartureSynchronizeService {
	
	private static final Logger logger = LoggerFactory.getLogger(TropicExtendedDepartureSynchronizeServiceImpl.class);
	private static final CalculateTimePattern defaultTimePattern = CalculateTimePattern.HMS;
	
	@Value("${elastic.search.indexing}")
	private String elasticSearchIndexing;
		
	@Value("${ch1.tidownload.enabled}")
	private String ch1DownloadEnabled;
		
	@Inject
	private TourDepartureHistoryService tourDepartureHistoryService;
	
	@Inject
	private TourInfoTransferDAO tourInfoTransferDAO;
	
	@Inject
	private Ch1DownloadService ch1DownloadService;
	
	@Inject
	private ImportStatusService importStatusService;
		
	@Inject
	@Named("HabsReconciliationReportServiceImpl")
	private ReconciliationReportService reconciliationReportService;
	
	@Inject
	private IndexSynchronizerService indexSynchronizerService;
	
	@Inject
	private FileCollectService fileCollectService;
	
	@Override
	public OperationStatus departureSynchronizeOperation(OperationStatus opStatus) throws TropicSynchronizeServiceException {				
		
		try {
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.SYNCHRONIZE_START);			
			initialTourDepartureHistory(opStatus);		// new tx				
			executeOperation(opStatus);
		}
		catch (TropicSynchronizeServiceException e) {
			if(opStatus.isCancelOrInactiveProcess()==false){
				throw e;
			}				
		}
		finally {					
			opStatus.finish();			
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.SYNCHRONIZE_END, DateHelper.calculateTime(opStatus.getStartOperation(), opStatus.getEndOperation(),CalculateTimePattern.HMS));
			tourDepartureHistoryService.saveTourDepartureHistory(opStatus.getTourDepartureHistory(),opStatus.getCrSavedOrUpdateForBrand()); // new tx			
		}
		return opStatus;
	}

	private void initialTourDepartureHistory(OperationStatus opStatus){
		try{
			tourDepartureHistoryService.saveInitialTourDepartureHistory(opStatus.getTourDepartureHistory()); // new tx
		}
		catch(Exception e){
			logger.error("",e);
			QHComment comment=new QHComment();
			comment.setMessage("Unexpected problem occured");
			comment.setMessageCode("ERR-1000");
			comment.setModifiedTime(new Date());
			comment.setQuartzJobHistory(opStatus.getQuartzJobHistory());
			comment.setModifiedBy(opStatus.getUser());
			comment.setStackTrace(Throwables.getStackTraceAsString(e));
			opStatus.getQuartzJobHistory().getComments().add(comment);
			throw e;
		}
	}
	
	public void executeOperation(OperationStatus opStatus) throws TropicSynchronizeServiceException{
		try{
			
			// download TI from CH1 to CH2
			executeDownloadFromCH1IfEnabled(opStatus);
			
			executeElasticSearchIndexingIfEnabled(opStatus);			
			// FileCollect, Outgoing archives when CH1 download is enabled
			executeFileCollectIfEnabled(opStatus);
							
			generateReconciliationReport(opStatus);
		}
		catch(Exception e){
			LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);
			TropicSynchronizeServiceException ex = new TropicSynchronizeServiceException(e);
			ex.setOpStatus(opStatus);
			throw ex;
		}		
	}
	
	private void generateReconciliationReport(OperationStatus opStatus) throws ReconciliationReportServiceException{
		// reconcilation report
		Date start=new Date();
		try{
		prepareIndexingMapForReconciliationReport(opStatus);
		IndexSynchronizerVO indexSynchronizerVO=indexSynchronizerService.getStateOfIndex(opStatus.getIndexMap(),opStatus.getCurrentBrand());
		FileCollectVO fileCollectVO=margeFileCollectVO(opStatus.getFileCollectMap());
		setupMessageSilent(opStatus,  TypeMsg.INF, TropicSynchronizeMessages.RECONCILIATION_GEN.createMessage(opStatus.getCurrentBrand()),ProcessLevel.RECONCILIATION);
		reconciliationReportService.createReconciliationReport(ProcessName.IMPORT, opStatus.getCurrentBrand(), indexSynchronizerVO, fileCollectVO);
		}
		finally{
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.RECONCILIATION_GEN_END, opStatus.getCurrentBrand(),DateHelper.calculateTime(start, new Date(), CalculateTimePattern.HMS));
		}
	}
	
	private void executeDownloadFromCH1IfEnabled(OperationStatus opStatus) {
		if(Boolean.parseBoolean(ch1DownloadEnabled)){
			if (tourInfoTransferDAO.isDownloadEnabled(opStatus.getCurrentBrand())) {			
				Date startDateTIUpload = new Date();
				try {
					LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOUR_INFO_CH1_DOWNLOAD_START, opStatus.getCurrentBrand());								
					ch1DownloadService.downloadTIFromCH1WithSaveToDB(opStatus.getCurrentBrand());							
				} catch (Exception e) {
					logger.error("",e);
					LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);
				}
				finally{
					LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOUR_INFO_CH1_DOWNLOAD_END, opStatus.getCurrentBrand(), DateHelper.calculateTime(startDateTIUpload, new Date(), CalculateTimePattern.HMS));
				}
			}
			else{
				LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOUR_INFO_CH1_DOWNLOAD_DISABLED,opStatus.getCurrentBrand());		
			}				
		} else {
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.TOUR_INFO_CH1_DOWNLOAD_DISABLED_CFG,opStatus.getCurrentBrand());			
		}
	}
	
	private void executeElasticSearchIndexingIfEnabled(OperationStatus opStatus) throws TropicSynchronizeServiceException{
		if (BooleanUtils.toBoolean(elasticSearchIndexing)) {			
			if((Boolean.parseBoolean(ch1DownloadEnabled)  && tourInfoTransferDAO.isDownloadEnabled(opStatus.getCurrentBrand())) || foundIndexationErrors(opStatus)){						
					Date start=new Date();
					IndexSynchronizerVO syncOut=new IndexSynchronizerVO(true);
					try{												
						setupMessageSilent(opStatus,  TypeMsg.INF, TropicSynchronizeMessages.INDEXING_START.createMessage(opStatus.getCurrentBrand()),ProcessLevel.INDEXING);
						LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEXING_START, opStatus.getCurrentBrand());
						
						//full indexing
						indexSynchronizerService.synchronize(ProcessName.IMPORT, opStatus.getCurrentBrand(),syncOut);						
						if(syncOut.hasErrors()) {
								throw new IndexSynchronizerServiceException(IndexSynchronizerService.ES_EXCEPTION_MSG);
						}						
						LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEX_SUCCESS);
					} catch (IndexSynchronizerServiceException e) {
						logger.error("",e);
						LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.UNEXPECTED_EXCEPTION);
					}
					finally{
						// update Rec_Report_data on DB
						opStatus.getJobCoordinator().setupIndexingResult(syncOut);												
						opStatus.getIndexMap().put(new Date(), syncOut);
					    LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEXING_END, opStatus.getCurrentBrand(), DateHelper.calculateTime(start, new Date(), CalculateTimePattern.HMS));
					}						
			}
		} else {
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INDEXING_TOURN_OFF);
		}
	}
	
	private void executeFileCollectIfEnabled(OperationStatus opStatus){
			if((Boolean.parseBoolean(ch1DownloadEnabled) && tourInfoTransferDAO.isDownloadEnabled(opStatus.getCurrentBrand())) || foundIndexationErrors(opStatus) || foundFileCollectErrors(opStatus)){	
				Date startDateOutgoingArchives = new Date();
				LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.OUTGOING_ARCHIVE_FOR_BRAND, opStatus.getCurrentBrand());
				setupMessageSilent(opStatus,  TypeMsg.INF, TropicSynchronizeMessages.OUTGOING_ARCHIVE_FOR_BRAND.createMessage(opStatus.getCurrentBrand()),ProcessLevel.OUTGOING_ARCHIVE);
				FileCollectVO fileCollectVO=new FileCollectVO();
				try {
					fileCollectService.createLatestVersion(ProcessName.IMPORT, opStatus.getCurrentBrand(),fileCollectVO);					
									
				} catch (FileCollectServiceException e) {
					LogOperationHelper.logDefaultException(logger, opStatus, new Date(), e, TropicSynchronizeMessages.OUTGOING_ARCHIVE_EXCEPTION, opStatus.getCurrentBrand());
				}
				finally{
					// update Rec_Report_data on DB
					opStatus.getJobCoordinator().setupFileCollectResult(fileCollectVO);
					opStatus.getFileCollectMap().put(new Date(), fileCollectVO);	
				}
				LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.OUTGOING_ARCHIVE_FOR_BRAND_END, opStatus.getCurrentBrand(), DateHelper.calculateTime(startDateOutgoingArchives, new Date(), defaultTimePattern));				
			}
	}
	
	/*if full indexing was invoke than IndexMap should have only last IndexSynchronizerVO*/
	private void prepareIndexingMapForReconciliationReport(OperationStatus opStatus){
		
		Map<Date,IndexSynchronizerVO> map=opStatus.getIndexMap();		
		if(map.isEmpty()==false){
			Set<Date> keys=Sets.newTreeSet(map.keySet());
			LinkedList<Date> keysList=Lists.newLinkedList(keys);
			Date lastKey=keysList.getLast();
			
			IndexSynchronizerVO vo=map.get(lastKey);
			if(vo.getIndexingAll()){
				map.clear();
				map.put(lastKey, vo);
			}	
		}
	}
	
	private boolean foundIndexationErrors(OperationStatus opStatus) {
		for (IndexSynchronizerVO vo: opStatus.getIndexMap().values()) {
			if(vo.hasErrors())
				return true;
		}
		return false;
	}
	
	private boolean foundFileCollectErrors(OperationStatus opStatus) {
		for (FileCollectVO vo: opStatus.getFileCollectMap().values()) {
			if(vo.hasErrors())
				return true;
		}
		return false;
	}
		
	protected FileCollectVO margeFileCollectVO(Map<Date, FileCollectVO> map){				
		FileCollectVO result=new FileCollectVO();
		result.setStatusOperation(StatusOperation.NotExecuted);
		if(map!=null && map.isEmpty()==false){
			
			TreeSet<Date> keys=Sets.newTreeSet(map.keySet());		
			// last version indexsynchronizerVO
			FileCollectVO localVO=map.get(keys.last());
				
			result.setBrand(localVO.getBrand());
			result.setSellingCompanies(localVO.getSellingCompanies());
			result.getToursListNotAdded().clear();
			result.getToursListNotAdded().addAll(localVO.getToursListNotAdded());
			
			result.getZipListNotAdded().clear();
			result.getZipListNotAdded().addAll(localVO.getZipListNotAdded());
			
			result.getZipListNotDeleted().clear();
			result.getZipListNotDeleted().addAll(localVO.getZipListNotDeleted());
				
			result.setStatusOperation(localVO.getStatusOperation());			
		}
		return result;
	}
	
	private void setupMessageSilent(OperationStatus opStatus, TypeMsg msgType, String message, ProcessLevel processLevel) {
		try {
			importStatusService.setupMessage(message, opStatus.getCurrentBrand(), msgType, processLevel);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
//	private void checkCancelOperationx(OperationStatus opStatus,String extraMsg) throws TropicSynchronizeServiceException {
//
//		JobStatus status=opStatus.getJobStatus();
//		if (JobStatus.Cancelled==status) {
//			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.CANCEL_PROCESS,extraMsg);
//			TropicSynchronizeServiceException e = new TropicSynchronizeServiceException(TropicSynchronizeMessages.CANCEL_PROCESS.getMessage());
//			throw e;
//		}
//		else if (JobStatus.Inactive==status) {
//			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.INACTIVE_PROCESS,extraMsg);
//			TropicSynchronizeServiceException e = new TropicSynchronizeServiceException(TropicSynchronizeMessages.INACTIVE_PROCESS.getMessage());
//			throw e;
//		}
//	}

}
