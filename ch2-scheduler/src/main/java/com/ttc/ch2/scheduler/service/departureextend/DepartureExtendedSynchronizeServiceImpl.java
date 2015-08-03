package com.ttc.ch2.scheduler.service.departureextend;

import java.util.Date;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.departure.TropicExtendedDepartureSynchronizeService;
import com.ttc.ch2.bl.departure.TropicSynchronizeServiceException;
import com.ttc.ch2.bl.departure.common.JobStatusChecker;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.jobs.QuartzJobDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.RecReportData;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.departure.TourDepartureHistory.TourDepartureStatus;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.jobs.QuartzJobHistory.JobHistoryStatus;
import com.ttc.ch2.scheduler.common.DepartureExtendedCoordinator;
import com.ttc.ch2.scheduler.common.DepartureExtendedSynchronizeStatusChecker;
import com.ttc.ch2.scheduler.common.DepartureSynchronizeMessage;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.scheduler.service.SchedulerForDepExtImportService;
import com.ttc.ch2.scheduler.service.SchedulerForImportServiceImpl;
import com.ttc.ch2.statistics.BrandInfo;
import com.ttc.ch2.statistics.StatisticsBean;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DepartureExtendedSynchronizeServiceImpl implements DepartureExtendedSynchronizeService{

	private static final Logger logger=LoggerFactory.getLogger(DepartureExtendedSynchronizeServiceImpl.class);
	
	
	// required field
	private String brandCode;	
	
	
	private boolean processingByAnotherThread=false;
	
	private String user;
	private QuartzJobHistory history;	
	private OperationStatus operationStatus;
	
	@Inject
	private SchedulerForDepExtImportService schedulerForDepExtImportService;
		
	@Inject
	private TropicExtendedDepartureSynchronizeService tropicExtendedDepartureSynchronizeService;
	
	@Inject
	private LockBrandService lockBrandService;
	
	@Inject
	private QuartzJobCh2Service quartzJobCh2Service; 
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private ImportStatusService importStatusService;
	
	@Inject
	private QuartzJobDAO quartzJobDAO;
	
	@Inject
	private StatisticsBean statisticsBean; 
		

	@Override
	public synchronized void interrupt() {		
		logger.trace("QuickDepartureSynchronizeServiceImpl:interrupt-start");	
		try{			
			setupUser(quartzJobCh2Service);
		if (history==null) {
			QuartzJob job=quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(),brandCode);
			job.setJobStatus(JobStatus.Inactive);
			Brand brand=brandDAO.findByBrandCode(brandCode);
			history=new QuartzJobHistory();			
			history.setExecutedBy(user);
			history.setQuartzJob(quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(),brandCode));
			history.setStartDate(new Date());				
			history.setBrand(brand);
			history.setQuartzJob(job);
			history.setStatus(JobHistoryStatus.Cancelled);
			quartzJobCh2Service.saveNewQuartzJobHistory(history);		
		}				

		QHComment comment=new QHComment();
		comment.setMessage(DepartureSynchronizeMessage.CANCEL_PROCESS.getMessage());
		comment.setMessageCode(DepartureSynchronizeMessage.CANCEL_PROCESS.getCode());
		comment.setModifiedTime(new Date());
		comment.setQuartzJobHistory(history);
		comment.setModifiedBy(user);
		history.getComments().add(comment);
		history.setStatus(JobHistoryStatus.Cancelled);
		history.getQuartzJob().setJobStatus(JobStatus.Cancelled);
		quartzJobCh2Service.mergeQuartzJobHistory(history);
		
		QuartzJob job=quartzJobDAO.find(history.getQuartzJob().getId());
		job.setJobStatus(JobStatus.Cancelled);
		quartzJobDAO.save(job);
		
		}
		catch(Exception e){
			saveError(e, operationStatus);
		}
		logger.trace("QuickDepartureSynchronizeServiceImpl:interrupt-end");
	}
	
	@Override
	public void processing(){			
		Preconditions.checkState(StringUtils.isNotBlank(brandCode),"QuickDepartureSynchronizeServiceImpl->processing field brandCode is required");
		
		// default process has flag setap on true - make lock cause changing this flag
		processingByAnotherThread=true;
				
		JobStatusChecker jobChecker=null;
		long statsSerial = BrandInfo.SERIAL_WAITING;
		try{
             setupUser(quartzJobCh2Service);
			 Preconditions.checkState(StringUtils.isNotBlank(user),"QuickDepartureSynchronizeServiceImpl->processing field user is required");
			
			 // lock proccess
			 jobChecker=new DepartureExtendedSynchronizeStatusChecker(brandCode);
			 if(lockBrand()==false){
				return;
			 }	
			 statsSerial = statisticsBean.registerStartExtendedTDImport(brandCode); 
		 	// prepare data to invoke operation		 			 
		    Stopwatch stopwatch=new Stopwatch();
		 	stopwatch.start();
		 	createNewQuartzJobHistory();
		 	createNewOperationStatus(jobChecker);
			// main operation on synchronize tourdeparture
			try {									
				  setupMessageSilent(TypeMsg.INF, "Start extended operation 'synchronization departure' for brandCode:"+brandCode, ProcessLevel.PREPARE);								
				  tropicExtendedDepartureSynchronizeService.departureSynchronizeOperation(operationStatus);
				}				
				catch (TropicSynchronizeServiceException e) {
						operationStatus = e.getOpStatus();
						saveError(e,operationStatus);
						logger.error("", e);
				} 
				catch (Exception e) {
						if(operationStatus!=null){
							operationStatus.setStatus(TourDepartureStatus.ERROR_OPERATION_END);
							saveError(e,operationStatus);
					      }
						  logger.error("", e);
					}
				finally {
					if (operationStatus != null) {
						stopwatch.stop();
						saveAdditionalInformation(history, operationStatus, stopwatch);
					}
				}
		} catch (Exception e) {				
			logger.error("",e);
		}
		finally{			
			// interrupt operation
			try{
				if(processingByAnotherThread==false && jobChecker!=null && StringUtils.isNotEmpty(brandCode) && jobChecker.isCancelProcess()){
					interrupt();
				}
			}
			catch(Exception e){
				logger.error("",e);
			}
						
			//external finish operation and release lock 	
			try{				
				if(processingByAnotherThread==false){
					
					if(operationStatus.needClearDataFromRecReportDataForIndex())
						schedulerForDepExtImportService.clearRecReportData(brandCode,RecReportData.Type.IndexSearchingVO);
						
					if(operationStatus.needClearDataFromRecReportDataForFileCollect())
						schedulerForDepExtImportService.clearRecReportData(brandCode,RecReportData.Type.FileCollectVO);
					
					clearStatusSilent(brandCode);
					clearQuartzJob(brandCode);
					lockBrandService.releaseLockBrand(brandCode, ProcessName.EXTENDED); // new tx						
				}
				
				statisticsBean.registerStopExtendedTDImport(brandCode, statsSerial);
			}
			catch(Exception e){
				logger.error("",e);
			}				
		}		
	}
	
	private boolean lockBrand() throws InterruptedException, TropicSynchronizeServiceException {				
		if(lockBrandService.lockBrand(brandCode, ProcessName.EXTENDED)){	
			processingByAnotherThread=false;
			setupMessageSilent(TypeMsg.INF, "Locked brand Code:"+brandCode, ProcessLevel.PREPARE);
			return true;
		}else{
			logOperationAfterLockFalseJob();			
			if(lockBrandService.isLockBrand(brandCode, ProcessName.UPLOAD)){
				clearStatusSilent(brandCode);	
			}			
			return false;
		}	
	}
	
	private void setupUser(QuartzJobCh2Service quartzJobCh2Service){
		if(StringUtils.isBlank(this.user)){
			QuartzJob job=quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(),brandCode);
		 	user=job.getUser()!=null ? job.getUser().getUsername() : SchedulerForImportServiceImpl.SCHEDULER_USER;
		}
	}
	
	private void createNewQuartzJobHistory(){
		Brand brand=brandDAO.findByBrandCode(brandCode);
		QuartzJob job=quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(),brandCode);
	 	job.setJobStatus(JobStatus.Active);
	 	
		history=new QuartzJobHistory();
		history.setExecutedBy(user);
		history.setQuartzJob(job);
		history.setStartDate(new Date());		
		history.setStatus(JobHistoryStatus.Processing);
		history.setBrand(brand);
		quartzJobCh2Service.saveNewQuartzJobHistory(history);		
	}
	
	private void createNewOperationStatus(JobStatusChecker jobChecker){
		operationStatus=new OperationStatus();		
		operationStatus.setCurrentBrand(brandCode);
		operationStatus.getTourDepartureHistory().setQuartzJobHistory(history);
		operationStatus.getTourDepartureHistory().setModifiedBy(user);
		operationStatus.setQuartzJobHistory(history);
		operationStatus.setUser(user);				
		operationStatus.setJobStatusChecker(jobChecker);
		operationStatus.setCrRejctedMd5ForBrand(new TreeSet<Long>());
		operationStatus.setCrUnsavedForBrand(new TreeSet<Long>());
		operationStatus.setCrSavedOrUpdateForBrand(new TreeSet<Long>());
		operationStatus.setIndexMap(schedulerForDepExtImportService.getIndexSynchronizerVOs(brandCode));
		operationStatus.setFileCollectMap(schedulerForDepExtImportService.getFileCollectVOs(brandCode));
		operationStatus.setJobName(QuartzJob.JobName.DepartureExtendedSynchronizeJob);
		operationStatus.setJobCoordinator(new DepartureExtendedCoordinator(brandCode));
	}
		
	private void logOperationAfterLockFalseJob(){
		Brand brand=brandDAO.findByBrandCode(brandCode);
		
		QuartzJobHistory history=new QuartzJobHistory();
		history.setExecutedBy(user);
		history.setQuartzJob(quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(),brandCode));
		history.setStartDate(new Date());
		history.setExecutionTime(0l);
		history.setBrand(brand);
		history.setStatus(JobHistoryStatus.Cancelled);	
				
		QHComment comment=new QHComment();
		comment.setMessage(DepartureSynchronizeMessage.REGISTRED_PROCESS_EXCEPTION_V2.getMessage());
		comment.setMessageCode(DepartureSynchronizeMessage.REGISTRED_PROCESS_EXCEPTION_V2.getCode());
		comment.setModifiedTime(new Date());
		comment.setQuartzJobHistory(history);
		comment.setModifiedBy(user);
		
		history.getComments().add(comment);
		
		quartzJobCh2Service.saveNewQuartzJobHistory(history);
	}
		
	private void saveError(Exception e,OperationStatus opStatus){
		
		if(history!=null){
			setupUser(quartzJobCh2Service);
			
			QHComment comment=new QHComment();
			comment.setMessage(DepartureSynchronizeMessage.COMMON_EXCEPTION.getMessage(e.getMessage()));
			comment.setMessageCode(DepartureSynchronizeMessage.COMMON_EXCEPTION.getCode());
			comment.setModifiedTime(new Date());
			comment.setQuartzJobHistory(history);
			comment.setModifiedBy(user);
			comment.setStackTrace(Throwables.getStackTraceAsString(e));
			history.getComments().add(comment);
			
			if(opStatus!=null){
				if(opStatus.isCancelProcess())
					history.setStatus(JobHistoryStatus.Cancelled);
				else
					history.setStatus(JobHistoryStatus.Fail);
				
				opStatus.setStatus(TourDepartureStatus.ERROR_OPERATION_END);
			}
			quartzJobCh2Service.mergeQuartzJobHistory(history);
		}
	}
	
	private void saveAdditionalInformation(QuartzJobHistory historyParam, OperationStatus opStatus,Stopwatch stopwatch){	
		
		switch (opStatus.getStatus()) {
		case SUCCESS_OPERATION_END:
			historyParam.setStatus(JobHistoryStatus.Success);
			break;
		case WARNING_OPERATION_END:
			historyParam.setStatus(JobHistoryStatus.Warning);
			break;
		case ERROR_OPERATION_END:
			historyParam.setStatus(JobHistoryStatus.Fail);
			break;
		default:
			break;
		}		
		historyParam.setExecutionTime(stopwatch.elapsed(TimeUnit.MILLISECONDS));
		boolean buildComment=false;
		StringBuilder sb=new StringBuilder();
		if(opStatus.getTourDepartureHistory()!=null && opStatus.getTourDepartureHistory().getId()!=null){
			sb.append("tour departure id-"+opStatus.getTourDepartureHistory().getId()).append(" ");
			buildComment=true;
		}
		if(opStatus.getQuartzJobHistory()!=null && opStatus.getQuartzJobHistory().getId()!=null){
			sb.append("quartz job id-"+opStatus.getQuartzJobHistory().getId()).append(" ");
			buildComment=true;
		}
		if(opStatus.getCrExport()!=null && opStatus.getCrExport().getId()!=null){
			sb.append("CRExport id-"+opStatus.getCrExport().getId()).append(" ");
			buildComment=true;
		}
		
		if(buildComment){
			QHComment comment=new QHComment();
			comment.setMessage(DepartureSynchronizeMessage.ADDITIONAL_INF.getMessage(sb.toString()));
			comment.setMessageCode(DepartureSynchronizeMessage.ADDITIONAL_INF.getCode());
			comment.setModifiedTime(new Date());
			comment.setQuartzJobHistory(historyParam);
			comment.setModifiedBy(opStatus.getUser());
			historyParam.getComments().add(comment);
		}	
		quartzJobCh2Service.mergeQuartzJobHistory(historyParam);
	}
	

	
	private void clearQuartzJob(String brandCode){
		QuartzJob job= quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(),brandCode);		
		job.setJobStatus(JobStatus.Inactive);
		quartzJobCh2Service.saveQuartzJob(job);	
	}
	

	private void setupMessageSilent(TypeMsg msgType,String message,ProcessLevel processLevel){		
		try{	
			importStatusService.setupMessage(message, brandCode, msgType,processLevel);
		}catch(Exception e)
		{
			logger.error("",e);
		}
	}
	
	private void clearStatusSilent(String brandCode) {
		try {
			importStatusService.clearStatus(brandCode);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	
	@Override
	public String getBrandCode() {
		return brandCode;
	}

	
	@Override
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}	
}
