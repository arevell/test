package com.ttc.ch2.scheduler.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.elasticsearch.common.collect.Sets;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.ttc.ch2.bl.departure.ImportStatusService;
import com.ttc.ch2.bl.lock.DbLocker;
import com.ttc.ch2.bl.lock.Executor;
import com.ttc.ch2.bl.lock.ExecutorException;
import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.bl.upload.UploadStatusService;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.common.enums.CronExpresion;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.departure.ImportStatusDAO;
import com.ttc.ch2.dao.departure.TourDepartureHistoryDAO;
import com.ttc.ch2.dao.upload.UploadStatusDAO;
import com.ttc.ch2.dao.upload.UploadTourInfoDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.departure.ImportStatus;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.departure.TourDepartureHistory;
import com.ttc.ch2.domain.departure.TourDepartureHistory.TourDepartureStatus;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.jobs.QuartzJobHistory.JobHistoryStatus;
import com.ttc.ch2.domain.upload.UploadStatus;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.ch2.scheduler.common.ActiveJobException;
import com.ttc.ch2.scheduler.jobs.departuresynch.DepartureSynchronizeJob;


@Service
public class SchedulerForImportServiceImpl extends SchedulerServiceBase implements SchedulerForImportService 
{
	private static final Logger logger=LoggerFactory.getLogger(SchedulerForImportServiceImpl.class);
			
	private StatisticListenerQuartz statisticListenerQuartz;
	
	@Value("${reset.time}")
	private String resetTime;
	
	private int resetTimeMillisecoud;
	
	@Inject
	private LockBrandService lockBrandService;
	
	@Inject
	private UploadStatusService uploadStatusService;
	@Inject
	private ImportStatusService importStatusService;
	
	@Inject
	private TourDepartureHistoryDAO tourDepartureHistoryDAO;
		
	@Inject
	private ImportStatusDAO importStatusDAO;
	
	@Inject
	private UploadStatusDAO uploadStatusDAO;
	
	@Inject
	private UploadTourInfoDAO uploadTourInfoDAO;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private QuartzJobCh2Service quartzJobCh2Service;
		
	public void init() throws SchedulerServiceException{
		logger.trace("SchedulerServiceImpl.init-start");
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		statisticListenerQuartz=new StatisticListenerQuartz();
		
		try{
			resetTimeMillisecoud=Integer.parseInt(resetTime);
		}catch(NumberFormatException e){
			throw new SchedulerServiceException(e);
		}
		
		try {		
			logger.debug("SchedulerServiceImpl.init- configure job and trigger");
			
			schedulerLocal.addSchedulerListener(statisticListenerQuartz);
			setupJobFromDbForImport();			
		} catch (SchedulerException e) {
			throw new SchedulerServiceException(e);
		}
		logger.trace("SchedulerServiceImpl.init-end");
	}	
		
	@Override
	public List<JobVO> getJobList() throws SchedulerServiceException{
		logger.trace("SchedulerServiceImpl.getJobList-start");
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		List<JobVO>data=Lists.newArrayList();				
		try {
					for (String jobName : schedulerLocal.getJobNames(jobGroupName)) {					  													
					  Trigger[] triggers = schedulerLocal.getTriggersOfJob(jobName,jobGroupName);
					  Date nextFireTime=null;
					  if(triggers!=null && triggers.length>0){
						  nextFireTime = triggers[0].getNextFireTime();
					  }
					  
					  String brandCode=(String) schedulerLocal.getJobDetail(jobName, jobGroupName).getJobDataMap().get(DepartureSynchronizeJob.JobParams.BRAND_CODE.toString());
					  String name=(String) schedulerLocal.getJobDetail(jobName, jobGroupName).getJobDataMap().get(DepartureSynchronizeJob.JobParams.JOB_NAME_UI.toString());
					  QuartzJob job=quartzJobCh2Service.findByName(SchedulerForImportServiceImpl.jobImportName,brandCode);
					  					   
					  String user=job.getUser()!=null ? job.getUser().getUsername() : SCHEDULER_USER;					  
					  boolean needReset=resetNeed(brandCode);
					  boolean pbEnabled=needReset || stopJobAllowedOnly(brandCode);							
					  data.add(new JobVO(name, jobGroupName, nextFireTime,job.getJobStatus().toString(),user,brandCode,pbEnabled,needReset));					  
					  logger.debug("[jobName] : " + jobName + " [groupName] : " + jobGroupName + " - " + nextFireTime);					  				 
					}				 					
		} catch (SchedulerException e) {
			throw new SchedulerServiceException(e);
		}
		logger.trace("SchedulerServiceImpl.getJobList-end");
		return data;									
	}


	@Override
	public SchedulerVO getSchedulerData() throws SchedulerServiceException {
		logger.trace("SchedulerServiceImpl.getSchedulerName-start");
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		try {						
			SchedulerVO vo=new SchedulerVO();
			vo.setSchedulerName(schedulerLocal.getSchedulerName());
			vo.setStatus(schedulerLocal.isStarted() ? SchedulerVO.Status.STARTED : SchedulerVO.Status.STOPED);
			vo.setStartDate(schedulerLocal.isInStandbyMode() ? null : statisticListenerQuartz.getCurrentDateStart());
			if(schedulerLocal.isInStandbyMode())
				vo.setStatus(SchedulerVO.Status.STAND_BY_MODE);
			
			logger.trace("SchedulerServiceImpl.getSchedulerName-end");
			return vo;
		} catch (SchedulerException e) {
			throw new SchedulerServiceException(e);
		}		
	}
		
	@Override	
	public void changeJobTime(final Date startTime,final String brandCode)  throws SchedulerServiceException
	{
		logger.trace("SchedulerServiceImpl.changeJobTime-start");
	
		if(startTime==null){
			throw new SchedulerServiceException("Start time of job is null");
		}
		if(startTime.before(new Date())){
			throw new SchedulerServiceException("Start time of job is set in the past. Please correct it.");
		}		
		
		try{		   			
			final List<String> brandsCode=Lists.newArrayList();
			if(StringUtils.isNotEmpty(brandCode)){
				brandsCode.add(brandCode);
			}
			else{
				for (Brand brand : brandDAO.findAll()) {
					brandsCode.add(brand.getCode());					
				};
			}
			
			for (String localBrandCode : brandsCode) {
				if(isActiveOrCancelledJob(localBrandCode)){					
					throw new SchedulerServiceException("System cannot change scheduled time of execution job till process is active. Please try again later or stop the Departure Import Job for "+localBrandCode);
				}
				
				if(resetNeed(localBrandCode)){
					throw new SchedulerServiceException("System cannot change scheduled time of execution job till job  it needs to be reset. Please click 'reset' button for Departure Import Job "+localBrandCode);
				}
			}
			DbLocker dbLocer=appCtx.getBean(DbLocker.class);										
			dbLocer.executeOperationWithWaitThread(new ChangeJobExecutor(startTime,brandsCode),DbLocker.LockSql.SCHEDULER_LOCK, WaitHelper.sleepTime);				
		} catch (ExecutorException e) {
			throw new SchedulerServiceException(e);
		}			
		logger.trace("SchedulerServiceImpl.changeJobTime-end");
	}
	
	private void interruptJob(String brandCode) throws SchedulerException {
		logger.trace("SchedulerServiceImpl:interruptJob-start");		
		if(isActiveJob(brandCode)){			
			QuartzJob job=quartzJobCh2Service.findByName(SchedulerForImportServiceImpl.jobImportName,brandCode);
			job.setJobStatus(JobStatus.Cancelled);
			quartzJobCh2Service.saveQuartzJob(job);			
		}
		logger.trace("SchedulerServiceImpl:interruptJob-end");
	}

	
	/**
	 * Method setup new trigger this job
	 * Method used in schedule job after execute main task*/
	public void setupCronJob(final boolean interrupt,final String brandCode) throws SchedulerServiceException{
		logger.trace("SchedulerServiceImpl.changeJobTime-start");				
			try{
				DbLocker dbLocer=appCtx.getBean(DbLocker.class);										
				dbLocer.executeOperationWithWaitThread(new Executor() {		
					public void execute() throws ExecutorException {
						setupCronJobWithoutTx(interrupt, brandCode);
					}
				},DbLocker.LockSql.SCHEDULER_LOCK, WaitHelper.sleepTime);					
			}	
			catch (ExecutorException e) {
					throw new SchedulerServiceException(e);
				}	
		logger.trace("SchedulerServiceImpl.changeJobTime-end");
	}
	
	private void setupCronJobWithoutTx(final boolean interrupt,final String brandCode) throws  ExecutorException{
		try{
			QuartzJob job=quartzJobCh2Service.findByName(jobImportName,brandCode);				
			Scheduler schedulerLocal=schedulerFactory.getScheduler();
			Trigger trigger = createCronTrigger(getImportTriggerName(brandCode), triggerGroupName, job.getCronExpresion(), job.getUser()!=null ? job.getUser().getUsername() : SCHEDULER_USER);	
			trigger.setJobName(getImportJobName(job.getBrandCode()));
			trigger.setJobGroup(jobGroupName);					
					if(interrupt){
						interruptJob(brandCode);
					}
					
					JobDetail detailJob=schedulerLocal.getJobDetail(trigger.getJobName(), trigger.getJobGroup());
					if(detailJob!=null)	{
						Trigger [] triggers=schedulerLocal.getTriggersOfJob(detailJob.getName(), detailJob.getGroup());
						if(triggers!=null && triggers.length==1 && triggers[0].getName().equals(getImportTriggerName(job.getBrandCode()))){
						detailJob.getJobDataMap().put(DepartureSynchronizeJob.JobParams.USER.toString(), SCHEDULER_USER);
						schedulerLocal.rescheduleJob(getImportTriggerName(brandCode), SchedulerForImportServiceImpl.triggerGroupName, trigger);
						}
						else{
							schedulerLocal.deleteJob(detailJob.getName(), detailJob.getGroup());
							schedulerLocal.scheduleJob(detailJob,trigger);	
						}
						
					}
					else{
						setupJobFromDbForImport(brandCode);
					}
			}catch(Exception e){
				throw new ExecutorException(e);
			}
	}
	
   public void setupNewCronExpression(CronExpresion expresion) throws SchedulerServiceException{
		
		try{
			DbLocker dbLocer=appCtx.getBean(DbLocker.class);										
			dbLocer.executeOperationWithWaitThread(new SetupNewCronExecutor(expresion),DbLocker.LockSql.SCHEDULER_LOCK, WaitHelper.sleepTime);					
		}	
		catch (ExecutorException e) {			
			if(e.getClass()!=null && e.getCause() instanceof ActiveJobException)
				throw new SchedulerServiceException(e.getCause());
			else
				throw new SchedulerServiceException();
		}	
	}
   
   public CronExpresion setupInconsistentCronExpresionIfNecessary() throws SchedulerServiceException{
	   
	   List<Brand> brands=brandDAO.findAll();
	   Set<CronExpresion> expresions=Sets.newLinkedHashSet();
	   for (Brand brand : brands) {
		   QuartzJob job=quartzJobCh2Service.findByName(jobImportName,brand.getCode());
		   CronExpresion calculatedCronExpresion=CronExpresion.findByExpresion(CronExpresion.TDI_HOUR06.getName(), job.getCronExpresion().substring(4));	
		   expresions.add(calculatedCronExpresion);
	   }
	   
	   if(expresions.size()==0)
		   throw new IllegalStateException("Incorrect cron time configuration for job Tour Departure Import");
	   
	   CronExpresion ce=expresions.iterator().next();
	   if(expresions.size()>1){		   	
		   setupNewCronExpression(ce);
	   }
	   
	   return ce;	   
   }
	
	
	private Trigger createCronTrigger(String triggerName, String triggerGroupName, String cronExpression,String user) throws SchedulerServiceException{
		logger.trace("SchedulerServiceImpl:createCronTrigger-start");
		CronTrigger trigger = super.createCronTrigger(triggerName, triggerGroupName, cronExpression);
		trigger.getJobDataMap().put(DepartureSynchronizeJob.JobParams.USER.toString(),user);
		logger.trace("SchedulerServiceImpl:createCronTrigger-start");
		return trigger;
	}
	
	
	private Trigger createSimpleTrigger(String triggerName, String triggerGroupName, Date startTime,String user) throws SchedulerServiceException{
		logger.trace("SchedulerServiceImpl:createSimpleTrigger-start");
		SimpleTrigger trigger = super.createSimpleTrigger(triggerName, triggerGroupName, startTime);		
		trigger.getJobDataMap().put(DepartureSynchronizeJob.JobParams.USER.toString(),user);		
		logger.trace("SchedulerServiceImpl:createSimpleTrigger-end");
		return trigger;
	}

	private void setupJobFromDbForImport() throws SchedulerException, SchedulerServiceException{
		logger.trace("SchedulerServiceImpl:setupJobFromDb-start");
		for (QuartzJob job : quartzJobCh2Service.getJobsByGroupName(SchedulerForImportService.jobGroupName)) {
			setupJobFromDbForImport(job.getBrandCode());			
		}
		logger.trace("SchedulerServiceImpl:setupJobFromDb-end");
	}
	
	private void setupJobFromDbForImport(String brandCode) throws SchedulerException, SchedulerServiceException{
		logger.trace("SchedulerServiceImpl:setupJobFromDb-start");
			Scheduler schedulerLocal=schedulerFactory.getScheduler();
			QuartzJob job=quartzJobCh2Service.findByName(jobImportName, brandCode);
			JobDetail jobDetail=schedulerLocal.getJobDetail(getImportJobName(brandCode), jobGroupName);			
			if(jobDetail==null){
				JobDetailBean jobDetails= appCtx.getBean(jobImportName,JobDetailBean.class);
				jobDetails.setName(getImportJobName(brandCode));	
				jobDetails.getJobDataMap().put(DepartureSynchronizeJob.JobParams.BRAND_CODE.toString(), brandCode);
				jobDetails.getJobDataMap().put(DepartureSynchronizeJob.JobParams.JOB_NAME_UI.toString(), String.format(JOB_DESC, brandCode));
				jobDetails.getJobDataMap().put(DepartureSynchronizeJob.JobParams.USER.toString(), job.getUser()!=null ? job.getUser().getUsername() : SCHEDULER_USER);
				jobDetails.setRequestsRecovery(false);
				jobDetails.setDurability(true);
				jobDetails.setGroup(jobGroupName);
				
//				jobDetails.setVolatility(true);
//				DepartureSynchronizeJobListener jobListener=new DepartureSynchronizeJobListener(quartzJobCh2Service);
//				jobDetails.addJobListener(jobListener.getName());				
//				schedulerLocal.addJobListener(jobListener);			
				if(job.getNextFiringTime()!=null && job.getNextFiringTime().after(new Date())){
					schedulerLocal.scheduleJob(jobDetails,createSimpleTrigger(getImportTriggerName(brandCode),triggerGroupName,job.getNextFiringTime(),job.getUser()!=null ? job.getUser().getUsername() : SCHEDULER_USER));
				}
				else{			
					schedulerLocal.scheduleJob(jobDetails,createCronTrigger(getImportTriggerName(brandCode),triggerGroupName,job.getCronExpresion(),job.getUser()!=null ? job.getUser().getUsername() : SCHEDULER_USER));
				}	
			}		
		logger.trace("SchedulerServiceImpl:setupJobFromDb-end");
	}

	public void stopJob(final String brandCode) throws SchedulerServiceException{
		logger.trace("SchedulerServiceImpl:stopJob-start");
		if(resetAllowed(brandCode)){
			resetStateOfProcessUplodImport(brandCode);
		}
		else{		
			boolean allowedStop=false;
				try {
					allowedStop = stopJobAllowedOnly(brandCode);
				} catch (SchedulerException e1) {
					throw new SchedulerServiceException(e1);
				}
				if(allowedStop){
					try{
						// send interrupt event 		
						DbLocker dbLocer=appCtx.getBean(DbLocker.class);										
						dbLocer.executeOperationWithWaitThread(new Executor() {		
							public void execute() throws ExecutorException {
							try{
								interruptJob(brandCode);														
								}catch(Exception e){
									throw new ExecutorException(e);
								}
							}
						},DbLocker.LockSql.SCHEDULER_LOCK, WaitHelper.sleepTime);
					}catch(ExecutorException e){
						throw new SchedulerServiceException(e.getCause());
					}
				}else{
					throw new SchedulerServiceException("Job cannot be stopped. Probably import process is in critical section.");
				}			
		}
		logger.trace("SchedulerServiceImpl:stopJob-end");
	}
	
	

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void resetStateOfProcessUplodImport(final String brandCode) throws SchedulerServiceException{
		
		try{
				if(resetAllowed(brandCode)==false){
					throw new SchedulerServiceException("System can not be reset at the current state. Probably process import or upload just activated.");
				}						
				// send interrupt event 		
				DbLocker dbLocer=appCtx.getBean(DbLocker.class);
				dbLocer.executeOperation(new ResetStateExecutor(brandCode), DbLocker.LockSql.SCHEDULER_LOCK);
		}
		catch (Exception e){
			throw new SchedulerServiceException(e);
		}
	}
		
	public boolean stopJobAllowedOnly(String brandCode) throws SchedulerException{
		logger.trace("SchedulerServiceImpl:stopJobAllowedOnly-start");
		
		Preconditions.checkArgument(StringUtils.isNotBlank(brandCode),"SchedulerServiceImpl:stopJobAllowedOnly arg brand code is null");		
		boolean result=false;
		Date current=new Date();
		boolean jobActive=isActiveJob(brandCode);
		
		boolean lockExist=lockBrandService.isLockBrand(brandCode,ProcessName.IMPORT);		
		ImportStatus importStatus=importStatusDAO.getImportStatusByBrandCode(brandCode);
		
		QuartzJobHistory eHistory=new QuartzJobHistory();
		eHistory.setStatus(JobHistoryStatus.Processing);
		List<QuartzJobHistory> list=quartzJobCh2Service.getJobsHistoryList(null, eHistory);
		
		result=jobActive && lockExist && list.size()>0 && importStatus!=null && importStatus.getProcessLevel().getLevel()<ProcessLevel.PERSIST.getLevel();   		
		if (result) {
			Date importStatusDate=DateUtils.addMilliseconds(importStatus.getDateUpdate(),resetTimeMillisecoud);
			result=current.before(importStatusDate);
		}						
		return result;
	}
	
	public boolean uploadActive(String brandCode){
		logger.trace("SchedulerServiceImpl:uploadActive-start");
		Preconditions.checkArgument(StringUtils.isNotBlank(brandCode),"SchedulerServiceImpl:uploadActive arg brand code is null");	
		boolean result=false;
		Date current=new Date();
		boolean lockExist=lockBrandService.isLockBrand(brandCode,ProcessName.IMPORT);
		UploadStatus uploadStatus=uploadStatusDAO.getUploadStatusByBrandCode(brandCode);
		
		UploadTourInfoFile eUploadTourInfoFile=new UploadTourInfoFile();
		eUploadTourInfoFile.setStatus(UploadTourInfoFileStatus.PROCESSING);
		List<UploadTourInfoFile> tiHistoryList=uploadTourInfoDAO.getUploadTourInfoList(null, eUploadTourInfoFile);
		
		result=lockExist && tiHistoryList.size()>0 && uploadStatus!=null;
		if(result){			
			Date uploadStatusDate=DateUtils.addMilliseconds(uploadStatus.getDateUpdate(),resetTimeMillisecoud);
			result=current.before(uploadStatusDate);
		}
						
		logger.trace("SchedulerServiceImpl:uploadActive-end");
		return result;
	}
	
	public boolean checkResetForUpload(String brandCode){
		logger.trace("SchedulerServiceImpl:resetPermissionForUpload-start");		
		Preconditions.checkArgument(StringUtils.isNotBlank(brandCode),"SchedulerServiceImpl:stopJobAllowedOnly arg brand code is null");
		boolean result=false;	
		Date current=new Date();
		boolean lockExist=lockBrandService.isLockBrand(brandCode,ProcessName.UPLOAD);	
		UploadStatus uploadStatus=uploadStatusDAO.getUploadStatusByBrandCode(brandCode);
		
		if(lockExist){
			if(uploadStatus!=null){
				Date uploadStatusDate=DateUtils.addMilliseconds(uploadStatus.getDateUpdate(),resetTimeMillisecoud);
				if(current.before(uploadStatusDate)){
					result=false;
				}
				else{
					logger.info("Reset needed Upload status not change");
					result=true;
				}
			}else{
				logger.info("Reset needed lock exist but upload status not exist");
				result=true;
			}
		}else{			
			UploadTourInfoFile eUploadTourInfoFile=new UploadTourInfoFile();
			eUploadTourInfoFile.setStatus(UploadTourInfoFileStatus.PROCESSING);
			eUploadTourInfoFile.setBrand(brandDAO.findByBrandCode(brandCode));
			List<UploadTourInfoFile> tiHistoryList=uploadTourInfoDAO.getUploadTourInfoList(null, eUploadTourInfoFile);
			if(tiHistoryList.size()>0){
				logger.info("Reset needed Lock not exist but status is incorrect");
				result= true;
			}			
		}
		logger.trace("SchedulerServiceImpl:resetPermissionForUpload-end");
		return result;		
	}
	
	public boolean checkResetForImport(String brandCode){
		logger.trace("SchedulerServiceImpl:resetPermissionForImport-start");
		
		Preconditions.checkArgument(StringUtils.isNotBlank(brandCode),"SchedulerServiceImpl:resetPermissionForImport arg brand code is null");
		boolean result=false;	
		Date current=new Date();
		boolean lockExist=lockBrandService.isLockBrand(brandCode,ProcessName.IMPORT);	
		ImportStatus importStatus=importStatusDAO.getImportStatusByBrandCode(brandCode);
		
		if(lockExist){
			if(importStatus!=null){
				Date uploadStatusDate=DateUtils.addMilliseconds(importStatus.getDateUpdate(),resetTimeMillisecoud);
				if(current.before(uploadStatusDate)){
					result=false;
				}
				else{
					logger.info("Reset needed Import status not change");
					result=true;
				}
			}else{
				logger.info("Reset needed lock exist but import status not exist");
				result=true;
			}
		}else{			
			QuartzJobHistory eQuartzJobHistory=new QuartzJobHistory();
			eQuartzJobHistory.setStatus(QuartzJobHistory.JobHistoryStatus.Processing);
			eQuartzJobHistory.setBrand(brandDAO.findByBrandCode(brandCode));
			List<QuartzJobHistory> qHistoryList=quartzJobCh2Service.getJobsHistoryList(null, eQuartzJobHistory);
			if(qHistoryList.size()>0){
				logger.info("Reset needed Lock not exist but status is incorrect");
				result= true;
			}			
			
			QuartzJob job=quartzJobCh2Service.findByName(jobImportName,brandCode);
			if(JobStatus.Active==job.getJobStatus() || JobStatus.Cancelled==job.getJobStatus()){
				logger.info("Reset needed Lock not exist but status job is incorrect");
				result= true;
			}	
		
			boolean lockExistOnUpload=lockBrandService.isLockBrand(brandCode,ProcessName.UPLOAD);
			if(importStatus!=null && lockExistOnUpload==false){
				logger.info("Reset needed Lock not exist but snapshot exist");
				result= true;
			}			
		}
		
		logger.trace("SchedulerServiceImpl:resetPermissionForImport-end");
		return result;		
	}
	
	
 public boolean resetNeed(String brandCode){
	logger.trace("SchedulerServiceImpl:resetNeed-start");
	 
	boolean needResetImport=checkResetForImport(brandCode);
	boolean needResetUpload=checkResetForUpload(brandCode);
		
	logger.trace("SchedulerServiceImpl:resetNeed-end");
	return needResetUpload || needResetImport;
 }
	
	
	// when import is blocked and upload is blocked
	// when import is blocked and upload dont exist
	// when import dont exist and upload is blocked
	// when import dont exist and upload dont exist but status process is incosistency
	public boolean resetAllowed(String brandCode) throws SchedulerServiceException{
		
		try{
			boolean result=false;			
			boolean needResetImport=checkResetForImport(brandCode);
			boolean needResetUpload=checkResetForUpload(brandCode);
			
			if(needResetUpload || needResetImport){
				// this situation should not happen by semafor
				if(needResetImport && needResetUpload){
					result=true;
				}			
				// import work
				else if(needResetUpload && stopJobAllowedOnly(brandCode)){
					result=false;
				}
				// upload work
				else if(needResetImport && uploadActive(brandCode)){
					result=false;
				}
				else{
					// process need restart and work is not active
					result = true;
				}
			}
			return result;	
		}catch(Exception e){
			throw new SchedulerServiceException(e);
		}
	}
		
	private String getImportJobName(String brandCode){
		return jobImportName+"_"+brandCode;
	}
	
	private String getImportTriggerName(String brandCode){
		return triggerImportName+"_"+brandCode;
	}
	
	private boolean isActiveJob(String brandCode)
	{
		QuartzJob job=quartzJobCh2Service.findByName(SchedulerForImportServiceImpl.jobImportName,brandCode);
		return JobStatus.Active==job.getJobStatus();
	}
	
	private boolean isActiveOrCancelledJob(String brandCode)
	{
		QuartzJob job=quartzJobCh2Service.findByName(SchedulerForImportServiceImpl.jobImportName,brandCode);
		return JobStatus.Active==job.getJobStatus() || JobStatus.Cancelled==job.getJobStatus();
	}
	
	
	class SetupNewCronExecutor extends Executor{

		private CronExpresion localExpresion;
		
		public SetupNewCronExecutor(CronExpresion localExpresion) {
			super();
			this.localExpresion = localExpresion;
		}

		@Override
		public void execute() throws ExecutorException {
			try{
				List<Brand> brands=brandDAO.findAll();
				String activeJob=null;
				for (Brand brand : brands) {
						if (isActiveJob(brand.getCode())) {
						activeJob=brand.getCode();
						break;
					}
				}
				
				if(StringUtils.isNotEmpty(activeJob)){
					throw new ActiveJobException("Job for brand:"+activeJob+" is active");
				}																	
				int minute= 0;
				boolean changed=false;
				for (Brand brand : brands) {						
					String brandCode=brand.getCode();
					QuartzJob job=quartzJobCh2Service.findByName(jobImportName,brandCode);
					CronExpresion calculatedCronExpresion=CronExpresion.findByExpresion(CronExpresion.TDI_HOUR06.getName(), job.getCronExpresion().substring(4));						
					if(calculatedCronExpresion.equals(localExpresion)==false){							
						job.setCronExpresion(localExpresion.getExpresion(minute));
						job.setUser(null);
						job.setNextFiringTime(null);
						quartzJobCh2Service.saveQuartzJob(job);
						changed=true;
					}											
					minute=minute+5;
				}
				
				if(changed){
					for (Brand brand : brands) {
						setupCronJobWithoutTx(false, brand.getCode());
					}
				}
				
			}catch(Exception e){
				throw new ExecutorException(e);
			}
			
		}		
	}
	
	class ResetStateExecutor extends Executor{

		private String brandCode;
		
		public ResetStateExecutor(String brandCode) {
			super();
			this.brandCode = brandCode;
		}

		@Override
		public void execute() throws ExecutorException {
			try{
				QuartzJob job=quartzJobCh2Service.findByName(jobImportName,brandCode);				
				Scheduler schedulerLocal=schedulerFactory.getScheduler();		
				interruptJob(brandCode);
				
				Thread.sleep(WaitHelper.sleepTime);
				
				//clear data uplod
				Brand brand=brandDAO.findByBrandCode(brandCode);				
				uploadStatusService.clearProccess(brandCode);
				UploadTourInfoFile eFilterTI=new UploadTourInfoFile();
				eFilterTI.setBrand(brand);
				eFilterTI.setStatus(UploadTourInfoFileStatus.PROCESSING);
				List<UploadTourInfoFile> listUplod=uploadTourInfoDAO.getUploadTourInfoList(null, eFilterTI);
				if(listUplod.size()>0){
					for (UploadTourInfoFile uploadTourInfoFile : listUplod) {
						uploadTourInfoFile.setStatus(UploadTourInfoFileStatus.FAIL);
					}
				}
				
				//clear data import
				QuartzJobHistory eFilter=new QuartzJobHistory();
				eFilter.setStatus(JobHistoryStatus.Processing);	
				eFilter.setBrand(brand);
				List<QuartzJobHistory> historyProcessingRows=quartzJobCh2Service.getJobsHistoryList(null, eFilter);
				if(historyProcessingRows.size()>0){
					for (QuartzJobHistory quartzJobHistory : historyProcessingRows) {
						quartzJobHistory.setStatus(JobHistoryStatus.Cancelled);				
					}
				}
				
				TourDepartureHistory eFilterTD=new TourDepartureHistory(); 
				eFilterTD.setStatus(TourDepartureStatus.OPERATION_IN_PROGESS);
				eFilterTD.setBrand(brand);
				List<TourDepartureHistory> list=tourDepartureHistoryDAO.getTourDepartureHistoryByExemple(eFilterTD);
				if(list.size()>0){
					for (TourDepartureHistory tourDepartureHistory : list) {
						tourDepartureHistory.setStatus(TourDepartureStatus.ERROR_OPERATION_END);
						tourDepartureHistoryDAO.save(tourDepartureHistory);
					}
				}
		
				job.setUser(null);
				job.setJobStatus(JobStatus.Inactive);
				quartzJobCh2Service.saveQuartzJob(job);		
				
				importStatusService.clearStatus(brandCode);
															
				try{
					String jobName=getImportJobName(brandCode);							
					boolean isDeleted=schedulerLocal.deleteJob(jobName, jobGroupName);	
					Thread.sleep(WaitHelper.sleepTime);
				}
				catch(Exception e)
				{
					logger.error("",e);
				}
				setupJobFromDbForImport(brandCode);
				
				// release lock on brand
				lockBrandService.releaseLockBrand(brandCode);
				
			}catch(Exception e){
				throw new ExecutorException(e);
			}			
		}		
	}
	
	class ChangeJobExecutor extends Executor{

		private Date startTime;
		private List<String> brandsCode;
		
		public ChangeJobExecutor(Date startTime, List<String> brandsCode) {
			super();
			this.startTime = startTime;
			this.brandsCode = brandsCode;
		}

		@Override
		public void execute() throws ExecutorException {
			try {								
				for (String brandCodeLoc : brandsCode) {
					final Scheduler schedulerLocal=schedulerFactory.getScheduler();
					QuartzJob job=quartzJobCh2Service.findByName(SchedulerForImportServiceImpl.jobImportName,brandCodeLoc);
					Trigger trigger = createSimpleTrigger(getImportTriggerName(brandCodeLoc), triggerGroupName, startTime, job.getUser()!=null ? job.getUser().getUsername() : SCHEDULER_USER);	
					trigger.setJobName(getImportJobName(brandCodeLoc));
					trigger.setJobGroup(jobGroupName);									
					job.setNextFiringTime(startTime);
					job.setBrandCode(brandCodeLoc);
					job.setUser(SecurityHelper.getUserGuiPrincipal().getUserDb());									
					quartzJobCh2Service.saveQuartzJob(job);															
					interruptJob(brandCodeLoc);									
					JobDetail detailJob=schedulerLocal.getJobDetail(trigger.getJobName(), trigger.getJobGroup());									
					if(detailJob!=null)	{
						Trigger [] triggers=schedulerLocal.getTriggersOfJob(detailJob.getName(), detailJob.getGroup());
						if(triggers!=null && triggers.length==1 && triggers[0].getName().equals(getImportTriggerName(brandCodeLoc))){
						detailJob.getJobDataMap().put(DepartureSynchronizeJob.JobParams.USER.toString(), SecurityHelper.getUserGuiPrincipal().getUserDb().getUsername());
						schedulerLocal.rescheduleJob(getImportTriggerName(brandCodeLoc), SchedulerForImportServiceImpl.triggerGroupName, trigger);
						}
						else{
							schedulerLocal.deleteJob(detailJob.getName(), detailJob.getGroup());
							schedulerLocal.scheduleJob(detailJob,trigger);	
						}
					}
					else{
						setupJobFromDbForImport(brandCodeLoc);
					}			
				}
			} catch (SchedulerException e) {
				throw new ExecutorException(e);
			}		
			catch( SchedulerServiceException e){
				throw new ExecutorException(e);
			}
			
		}		
	}
}
