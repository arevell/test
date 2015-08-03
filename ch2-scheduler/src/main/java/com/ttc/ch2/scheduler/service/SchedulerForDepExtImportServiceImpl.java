package com.ttc.ch2.scheduler.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.elasticsearch.common.collect.Maps;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.XStream;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.bl.lock.DbLocker;
import com.ttc.ch2.bl.lock.Executor;
import com.ttc.ch2.bl.lock.ExecutorException;
import com.ttc.ch2.dao.RecReportDataDAO;
import com.ttc.ch2.domain.RecReportData;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.scheduler.common.JobParams;
import com.ttc.ch2.search.export.IndexSynchronizerVO;


@Service
public class SchedulerForDepExtImportServiceImpl extends SchedulerServiceBase implements SchedulerForDepExtImportService{

	private static final Logger logger = LoggerFactory.getLogger(SchedulerForDepExtImportServiceImpl.class);

	@Inject
	private RecReportDataDAO recReportDataDAO;
	
	
	public void init() throws SchedulerServiceException{
		logger.trace("SchedulerForDepExtImportServiceImpl:init-start");	
		try {		
			logger.debug("SchedulerForDepExtImportServiceImpl:init.init- configure job and trigger");
			 setupJobFromDb();
		} catch (SchedulerException e) {
			throw new SchedulerServiceException(e);
		}
		logger.trace("SchedulerForDepExtImportServiceImpl:init-end");
	}	
	
	
	private void setupJobFromDb() throws SchedulerException, SchedulerServiceException{
		logger.trace("SchedulerForDepExtImportServiceImpl:setupJobFromDbForUpload-start");
		for (QuartzJob job : quartzJobCh2Service.getJobsByGroupName(jobGroupName)) {
			setupJobFromDb(job);			
		}
		logger.trace("SchedulerForDepExtImportServiceImpl:setupJobFromDbForUpload-end");
	}
	
	private void setupJobFromDb(QuartzJob job) throws SchedulerException, SchedulerServiceException{
		logger.trace("SchedulerForDepExtImportServiceImpl:setupJobFromDbFor-start");
			Scheduler schedulerLocal=schedulerFactory.getScheduler();
			String brandCode=job.getBrandCode();
			JobDetail jobDetail=schedulerLocal.getJobDetail(getJobName(brandCode), jobGroupName);
			if(jobDetail==null){
				JobDetailBean jobDetails= appCtx.getBean(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(),JobDetailBean.class);
				jobDetails.setName(getJobName(brandCode));	
				jobDetails.getJobDataMap().put(JobParams.BRAND_CODE.toString(), brandCode);	
				jobDetails.getJobDataMap().put(JobParams.JOB_NAME_UI.toString(), String.format(JOB_DESC, brandCode));
				jobDetails.setRequestsRecovery(false);
				jobDetails.setDurability(true);	
				jobDetails.setGroup(jobGroupName);
				Trigger trigger=null;
				if(job.getNextFiringTime()==null){
					trigger=createCronTrigger(getTriggerName(brandCode),triggerGroupName,job.getCronExpresion());
				}
				else{
					trigger=createSimpleTrigger(getTriggerName(brandCode),triggerGroupName, job.getNextFiringTime());	
				}
					
				trigger.setPriority(1);
				schedulerLocal.scheduleJob(jobDetails,trigger);
			}		
		logger.trace("SchedulerForDepExtImportServiceImpl:setupJobFromDbFor-end");
	}

	private String getJobName(String brandCode){
		return QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString()+"_"+brandCode;
	}
	
	private String getTriggerName(String brandCode){
		return triggerName+"_"+brandCode;
	}
	
	
	/**
	 * Method setup new trigger this job
	 * Method used in schedule job after execute main task*/
	public void setupCronJob(final boolean interrupt, final String brandCode) throws SchedulerServiceException{
		logger.trace("SchedulerServiceImpl.changeJobTime-start");				
			try{
				DbLocker dbLocer=appCtx.getBean(DbLocker.class);										
				dbLocer.executeOperationWithWaitThread(new Executor() {		
					public void execute() throws ExecutorException {
					try{
						QuartzJob job=quartzJobCh2Service.findByName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString(),brandCode);				
						Scheduler schedulerLocal=schedulerFactory.getScheduler();
						Trigger trigger = createCronTrigger(getTriggerName(brandCode), triggerGroupName, job.getCronExpresion());	
						trigger.setJobName(getJobName(job.getBrandCode()));
						trigger.setJobGroup(jobGroupName);	
						
						if(interrupt){
							interruptJob(brandCode,QuartzJob.JobName.DepartureExtendedSynchronizeJob);
						}
						
								JobDetail detailJob=schedulerLocal.getJobDetail(trigger.getJobName(), trigger.getJobGroup());
								if(detailJob!=null)	{
									Trigger [] triggers=schedulerLocal.getTriggersOfJob(detailJob.getName(), detailJob.getGroup());
									if(triggers!=null && triggers.length==1 && triggers[0].getName().equals(getTriggerName(job.getBrandCode()))){
									detailJob.getJobDataMap().put(JobParams.USER.toString(), SCHEDULER_USER);
									schedulerLocal.rescheduleJob(getTriggerName(brandCode), SchedulerForImportServiceImpl.triggerGroupName, trigger);
									}
									else{
										schedulerLocal.deleteJob(detailJob.getName(), detailJob.getGroup());
										schedulerLocal.scheduleJob(detailJob,trigger);	
									}									
								}
								else{
									setupJobFromDb(job);
								}
						}catch(Exception e){
							throw new ExecutorException(e);
						}
					}
				},DbLocker.LockSql.SCHEDULER_LOCK, WaitHelper.sleepTime);					
			}	
			catch (ExecutorException e) {
					throw new SchedulerServiceException(e);
				}	
		logger.trace("SchedulerServiceImpl.changeJobTime-end");
	}
	
	@Deprecated
	@SuppressWarnings("unchecked")
	public void insertToContextData(String brandCode,IndexSynchronizerVO indexSynchronizerVO,FileCollectVO fileCollectVO){
		
		try{
		Scheduler schedulerLocal=schedulerFactory.getScheduler();
		JobDetail jobDetail=schedulerLocal.getJobDetail(getJobName(brandCode), jobGroupName);
		if(jobDetail==null){
			throw new IllegalStateException("System cant find job:"+getJobName(brandCode));
		}
		
		Date curr=new Date();
		boolean replaceJob=false;
			if(fileCollectVO!=null){
				Map<Date,FileCollectVO> fileCollectVOs=null;
				if(jobDetail.getJobDataMap().containsKey(JobParams.FILE_COLLECT_VO_MAP.toString())){				
					fileCollectVOs=((Map<Date, FileCollectVO>) jobDetail.getJobDataMap().get(JobParams.FILE_COLLECT_VO_MAP.toString()));				
				}
				else{
					fileCollectVOs=Maps.newHashMap();				
				}
				
				fileCollectVOs.put(curr, fileCollectVO);
				jobDetail.getJobDataMap().put(JobParams.FILE_COLLECT_VO_MAP.toString(),fileCollectVOs);
				replaceJob=true;
			}
			if(indexSynchronizerVO!=null){
				Map<Date,IndexSynchronizerVO> indexSynchronizerVOs=null;
				if(jobDetail.getJobDataMap().containsKey(JobParams.INDEX_VO_MAP.toString())){		
					indexSynchronizerVOs=(Map<Date, IndexSynchronizerVO>) jobDetail.getJobDataMap().get(JobParams.INDEX_VO_MAP.toString());				
				}
				else{
					indexSynchronizerVOs=Maps.newHashMap();				
				}
				
				indexSynchronizerVOs.put(curr, indexSynchronizerVO);
				jobDetail.getJobDataMap().put(JobParams.INDEX_VO_MAP.toString(),indexSynchronizerVOs);
				replaceJob=true;
			}
			
			if(replaceJob){
				schedulerLocal.addJob(jobDetail, true);
			}
		}
		catch(Exception e){
			logger.error("",e);
		}		
	}
	
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void setupRecReportData(String brandCode,IndexSynchronizerVO indexSynchronizerVO,FileCollectVO fileCollectVO){
		
		try{
			Date curr=new Date();
			if(indexSynchronizerVO!=null){
				RecReportData r=new RecReportData();
				r.setBrandCode(brandCode);
				r.setInsertDate(curr);
				r.setType(RecReportData.Type.IndexSearchingVO);
				
				XStream xstream = new XStream();
				xstream.alias("IndexSynchronizerVO", IndexSynchronizerVO.class);
				String xml = xstream.toXML(indexSynchronizerVO);
				r.setContent(xml);
				
				recReportDataDAO.save(r);
			}
			if(fileCollectVO!=null){
				RecReportData r=new RecReportData();
				r.setBrandCode(brandCode);
				r.setInsertDate(curr);
				r.setType(RecReportData.Type.FileCollectVO);
				
				XStream xstream = new XStream();
				xstream.alias("FileCollectVO", FileCollectVO.class);
				String xml = xstream.toXML(fileCollectVO);
				r.setContent(xml);				
				recReportDataDAO.save(r);
			}
		}
		catch(Exception e){			
			logger.error("",e);
		}			
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void clearRecReportData(String brandCode){
			
		try{			
			RecReportData e=new RecReportData();
			e.setBrandCode(brandCode);
			
			for (RecReportData r : recReportDataDAO.getList(e)) {
				recReportDataDAO.remove(r);
			}
		}
		catch(Exception e){
				logger.error("",e);
		}	
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void clearRecReportData(String brandCode,RecReportData.Type type){
		
		try{			
			RecReportData e=new RecReportData();
			e.setBrandCode(brandCode);
			e.setType(type);
			
			for (RecReportData r : recReportDataDAO.getList(e)) {
				recReportDataDAO.remove(r);
			}
		}
		catch(Exception e){
			logger.error("",e);
		}	
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public List<RecReportData> getRecReportDataList(String brandCode,RecReportData.Type type){		
		try{			
			RecReportData e=new RecReportData();
			e.setBrandCode(brandCode);
			e.setType(type);
			return recReportDataDAO.getList(e);
		}
		catch(Exception e){
			logger.error("",e);
			return null;
		}	
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public Map<Date, IndexSynchronizerVO> getIndexSynchronizerVOs(String brandCode){
		 List<RecReportData> list=getRecReportDataList(brandCode, RecReportData.Type.IndexSearchingVO);		 
		 if(list==null)
			 return null;
		 
		 Map<Date, IndexSynchronizerVO> result=Maps.newHashMap();
		 XStream xstream = new XStream();
		 xstream.alias("IndexSynchronizerVO", IndexSynchronizerVO.class);			
		 for (RecReportData recReportData : list) {			 
			 IndexSynchronizerVO o = (IndexSynchronizerVO) xstream.fromXML(recReportData.getContent()); 			 
			 result.put(recReportData.getInsertDate(), o);
		 }
		 return result;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public Map<Date, FileCollectVO> getFileCollectVOs(String brandCode){
		List<RecReportData> list=getRecReportDataList(brandCode, RecReportData.Type.FileCollectVO);		 
		if(list==null)
			return null;
		
		Map<Date, FileCollectVO> result=Maps.newHashMap();
		XStream xstream = new XStream();
		xstream.alias("FileCollectVO", FileCollectVO.class);			
		for (RecReportData recReportData : list) {			 
			FileCollectVO o = (FileCollectVO) xstream.fromXML(recReportData.getContent()); 			 
			result.put(recReportData.getInsertDate(), o);
		}
		return result;
	}
}
