package com.ttc.ch2.scheduler.jobs.departuresynch;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.departure.TropicDepartureMainSynchronizeService;
import com.ttc.ch2.bl.departure.TropicDepartureSynchronizeServiceImpl;
import com.ttc.ch2.bl.departure.TropicSynchronizeServiceException;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.departure.TourDepartureHistory.TourDepartureStatus;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
//import com.ttc.ch2.bl.departure.common.TDMessage;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.jobs.QuartzJobHistory.JobHistoryStatus;
import com.ttc.ch2.scheduler.common.DepartureExtendedCoordinator;
import com.ttc.ch2.scheduler.common.DepartureSynchronizeMessage;
import com.ttc.ch2.scheduler.common.ImportStatusChecker;
import com.ttc.ch2.scheduler.common.JobParams;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerForImportServiceImpl;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;
import com.ttc.ch2.statistics.BrandInfo;
import com.ttc.ch2.statistics.StatisticsBean;

public class DepartureSynchronizeJob extends QuartzJobBean{

	private static final Logger logger=LoggerFactory.getLogger(TropicDepartureSynchronizeServiceImpl.class);
		
	private static final int MAX_WAITING_COUNT = 60;
	private static final long MINUTE=1000*10*6; 
	
	private QuartzJobHistory history;
	
	private OperationStatus operationStatus;
	
	private String brandCode;
	
	private String user;
	
	private boolean processingByAnotherThread=false;
	
	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		
			logger.trace("DepartureSynchronizeJob:executeInternal-start");
			// default process has flag setap on true - make lock cause changing this flag
			processingByAnotherThread=true;
						
			LockBrandService lockBrandService  = null;
			ImportStatusChecker jobChecker =null;
			StatisticsBean statisticsBean = SpringContext.getApplicationContext().getBean(StatisticsBean.class);
			long statsSerial=BrandInfo.SERIAL_WAITING;
			try {				
				
			 //prepare beans	
			 lockBrandService  = SpringContext.getApplicationContext().getBean(LockBrandService.class);
			 TropicDepartureMainSynchronizeService tropicDepartureMainSynchronizeService = SpringContext.getApplicationContext().getBean(TropicDepartureMainSynchronizeService.class);
			 QuartzJobCh2Service quartzJobCh2Service = SpringContext.getApplicationContext().getBean(QuartzJobCh2Service.class);			 
			 BrandDAO brandDAO = SpringContext.getApplicationContext().getBean(BrandDAO.class);			
			 
			
			 // read params
			 brandCode=(String) ctx.getJobDetail().getJobDataMap().get(JobParams.BRAND_CODE.toString());			 
			 Preconditions.checkState(StringUtils.isNotBlank(brandCode),"DepartureSynchronizeJob->executeInternal param brandCode is required");
			 
			 // lock proccess
			 jobChecker=new ImportStatusChecker(brandCode,SpringContext.getApplicationContext());
			 if(lockBrand(jobChecker,lockBrandService,brandCode)==false){
				return;
			 }
			 statsSerial = statisticsBean.registerStartTDImport(brandCode);	
			 // prepare data to invoke operation
			 	Brand brand=brandDAO.findByBrandCode(brandCode);			 
			 	Date start=new Date();
								
			 	QuartzJob job=quartzJobCh2Service.findByName("DepartureSynchronizeJob",brandCode);
			 	job.setJobStatus(JobStatus.Active);
			 	String localUser=getUser(quartzJobCh2Service);
				history=new QuartzJobHistory();
				history.setExecutedBy(localUser);
				history.setQuartzJob(job);
				history.setStartDate(new Date());		
				history.setStatus(JobHistoryStatus.Processing);
				history.setBrand(brand);
				quartzJobCh2Service.saveNewQuartzJobHistory(history);		
				
				operationStatus=new OperationStatus();
				
				operationStatus.setCurrentBrand(brandCode);
				operationStatus.getTourDepartureHistory().setQuartzJobHistory(history);
				operationStatus.getTourDepartureHistory().setModifiedBy(localUser);
				operationStatus.setQuartzJobHistory(history);
				operationStatus.setUser(localUser);				
				operationStatus.setJobStatusChecker(jobChecker);
				operationStatus.setJobCoordinator(new DepartureExtendedCoordinator(brandCode));
				operationStatus.setJobName(QuartzJob.JobName.DepartureSynchronizeJob);
				
				
				
				// main operation on synchronize tourdeparture
								try {									
									setupMessageSilent(TypeMsg.INF, "Start operation 'import tour departure' for brand:"+brandCode, ProcessLevel.PREPARE);
									tropicDepartureMainSynchronizeService.departureSynchronizeOperation(operationStatus);
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
										if(operationStatus!=null){											
											saveAdditionalInformation(history,operationStatus,start);											
										}
								}
			} catch (Exception e) {				
				logger.error("",e);
			}
			finally{
				
				// interrupt operation
				try{
					if(processingByAnotherThread==false && jobChecker!=null && StringUtils.isNotEmpty(jobChecker.getBrandCode()) && jobChecker.isCancelProcess()){
						interrupt();
					}
				}
				catch(Exception e){
					logger.error("",e);
				}
				
				// setup next job
					try {						
						manageNextJob(lockBrandService);
					} catch (SchedulerServiceException e) {
						logger.error("",e);
					}
									
					//release lock
					try{
						if(lockBrandService!=null && processingByAnotherThread==false){
						lockBrandService.releaseLockBrand(brandCode, ProcessName.IMPORT); // new tx
						}
					}
					catch(Exception e){
						logger.error("",e);
					}
				statisticsBean.registerStopTDImport(brandCode, statsSerial);
			}
		logger.trace("DepartureSynchronizeJob:executeInternal-end");			
	}
	
	public void interrupt() {
		
		logger.trace("DepartureSynchronizeJob:interrupt-start");	
		try{
		QuartzJobCh2Service quartzJobCh2Service = SpringContext.getApplicationContext().getBean(QuartzJobCh2Service.class);
		BrandDAO brandDAO = SpringContext.getApplicationContext().getBean(BrandDAO.class);
		String localUser=getUser(quartzJobCh2Service);
		if (history==null) {
			QuartzJob job=quartzJobCh2Service.findByName("DepartureSynchronizeJob",brandCode);
			job.setJobStatus(JobStatus.Inactive);
			Brand brand=brandDAO.findByBrandCode(brandCode);
			history=new QuartzJobHistory();			
			history.setExecutedBy(localUser);
			history.setQuartzJob(quartzJobCh2Service.findByName("DepartureSynchronizeJob",brandCode));
			history.setStartDate(new Date());				
			history.setBrand(brand);
			history.setQuartzJob(job);			
			quartzJobCh2Service.saveNewQuartzJobHistory(history);		
		}				

		QHComment comment=new QHComment();
		comment.setMessage(DepartureSynchronizeMessage.CANCEL_PROCESS.getMessage());
		comment.setMessageCode(DepartureSynchronizeMessage.CANCEL_PROCESS.getCode());
		comment.setModifiedTime(new Date());
		comment.setQuartzJobHistory(history);
		comment.setModifiedBy(localUser);
		history.getComments().add(comment);
		history.setStatus(JobHistoryStatus.Cancelled);
		quartzJobCh2Service.mergeQuartzJobHistory(history);
		}
		catch(Exception e){
			saveError(e, operationStatus);
		}
		logger.trace("DepartureSynchronizeJob:interrupt-end");
	}
		
	
	private void proccessStartBeforLog()
	{
		QuartzJobCh2Service quartzJobCh2Service = SpringContext.getApplicationContext().getBean(QuartzJobCh2Service.class);
		BrandDAO brandDAO = SpringContext.getApplicationContext().getBean(BrandDAO.class);
		Brand brand=brandDAO.findByBrandCode(brandCode);
		QuartzJobHistory history=new QuartzJobHistory();
		String localUser=getUser(quartzJobCh2Service);
		history.setExecutedBy(localUser);
		history.setQuartzJob(quartzJobCh2Service.findByName("DepartureSynchronizeJob",brandCode));
		history.setStartDate(new Date());
		history.setExecutionTime(0l);
		history.setBrand(brand);
		history.setStatus(JobHistoryStatus.Cancelled);	
				
		QHComment comment=new QHComment();
		comment.setMessage(DepartureSynchronizeMessage.REGISTRED_PROCESS_EXCEPTION.getMessage());
		comment.setMessageCode(DepartureSynchronizeMessage.REGISTRED_PROCESS_EXCEPTION.getCode());
		comment.setModifiedTime(new Date());
		comment.setQuartzJobHistory(history);
		comment.setModifiedBy(localUser);
		
		history.getComments().add(comment);
		
		quartzJobCh2Service.saveNewQuartzJobHistory(history);
	}
	
	private void manageNextJob(LockBrandService lockBrandService) throws SchedulerServiceException{
		
		QuartzJobCh2Service quartzJobCh2Service = SpringContext.getApplicationContext().getBean(QuartzJobCh2Service.class);
		SchedulerForImportService schedulerService = SpringContext.getApplicationContext().getBean(SchedulerForImportService.class);
		
		if(processingByAnotherThread==false){		
			// Currently job are finished and configure cron job
			QuartzJob job= quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureSynchronizeJob.toString(),brandCode);
			job.setNextFiringTime(null);
			job.setUser(null);
			job.setJobStatus(JobStatus.Inactive);
			quartzJobCh2Service.saveQuartzJob(job);	
			schedulerService.setupCronJob(false,brandCode);
			clearStatusSilent(brandCode);
			
		}
		else if(processingByAnotherThread==true && lockBrandService.isLockBrand(brandCode, ProcessName.IMPORT)==false){
			
			QuartzJob job= quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureSynchronizeJob.toString(),brandCode);
			if(job.getNextFiringTime()==null || job.getNextFiringTime().before(new Date())){
				job.setNextFiringTime(null);
				job.setUser(null);
				job.setJobStatus(JobStatus.Inactive);
				quartzJobCh2Service.saveQuartzJob(job);	
				schedulerService.setupCronJob(false,brandCode);				
			}			
		}
	}
	
	private void saveError(Exception e,OperationStatus opStatus){
		
		QuartzJobCh2Service quartzJobCh2Service = SpringContext.getApplicationContext().getBean(QuartzJobCh2Service.class);
		if(history!=null){
			QHComment comment=new QHComment();
			comment.setMessage(DepartureSynchronizeMessage.COMMON_EXCEPTION.getMessage(e.getMessage()));
			comment.setMessageCode(DepartureSynchronizeMessage.COMMON_EXCEPTION.getCode());
			comment.setModifiedTime(new Date());
			comment.setQuartzJobHistory(history);
			comment.setModifiedBy(getUser(quartzJobCh2Service));
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
	
	private void saveAdditionalInformation(QuartzJobHistory historyParam, OperationStatus opStatus,Date start){	
		
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
		historyParam.setExecutionTime(new Date().getTime()-start.getTime());
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

		QuartzJobCh2Service quartzJobCh2Service = SpringContext.getApplicationContext().getBean(QuartzJobCh2Service.class);	
		quartzJobCh2Service.mergeQuartzJobHistory(historyParam);
	}
	
	
	private boolean lockBrand(ImportStatusChecker jobChecker, LockBrandService lockBrandService,String brandCode) throws InterruptedException, TropicSynchronizeServiceException {
				
		boolean lock=false;
		int count=MAX_WAITING_COUNT;	
		do{
			if(lockBrandService.lockBrand(brandCode, ProcessName.IMPORT)){
				lock=true;
				processingByAnotherThread=false;
				setupMessageSilent(TypeMsg.INF, "Locked brand Code:"+brandCode, ProcessLevel.PREPARE);
				break;
			}
			else{					
				if(jobChecker.isCancelProcess() || lockBrandService.isLockBrand(brandCode, ProcessName.IMPORT) || lockBrandService.isLockBrand(brandCode, ProcessName.EXTENDED))
					break;
				else{
					setupMessageSilent(TypeMsg.INF, "Wait for semaphore release:"+brandCode, ProcessLevel.PREPARE);
					Thread.sleep(MINUTE);
					count--;	
				}
			}			
		}while(count>0);
		
		if(lock==false){			
			proccessStartBeforLog();			
			if(lockBrandService.isLockBrand(brandCode, ProcessName.UPLOAD)){
				clearStatusSilent(brandCode);	
			}
		}		
		return lock;
	}
	
	private void setupMessageSilent(TypeMsg msgType,String message,ProcessLevel processLevel){
		
		try{
			ImportStatusService importStatusService = SpringContext.getApplicationContext().getBean(ImportStatusService.class);	
			importStatusService.setupMessage(message, brandCode, msgType,processLevel);
		}catch(Exception e)
		{
			logger.error("",e);
		}
	}
	private void clearStatusSilent(String brandCode) {

		try {
			ImportStatusService importStatusService = SpringContext.getApplicationContext().getBean(ImportStatusService.class);
			importStatusService.clearStatus(brandCode);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	private String getUser(QuartzJobCh2Service quartzJobCh2Service){
		if(StringUtils.isBlank(this.user)){
		QuartzJob job=quartzJobCh2Service.findByName("DepartureSynchronizeJob",brandCode);
	 	user=job.getUser()!=null ? job.getUser().getUsername() : SchedulerForImportServiceImpl.SCHEDULER_USER;
		}
		return this.user;
	}
	
	
	
	
}
