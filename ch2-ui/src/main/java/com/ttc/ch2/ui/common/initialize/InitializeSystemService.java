package com.ttc.ch2.ui.common.initialize;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Iterables;
import com.ttc.ch2.bl.SynchronizeLock;
import com.ttc.ch2.common.enums.SystemDirection;
import com.ttc.ch2.common.ordering.OrderingBrandByCode;
import com.ttc.ch2.common.predicates.Ch2ConfigValueFinder;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.Ch2ConfigValueDAO;
import com.ttc.ch2.dao.jobs.QuartzJobDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.Ch2ConfigValue;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.hibernate.DbUpdaterComponent;
import com.ttc.ch2.scheduler.service.QuartzSwitcher;
import com.ttc.ch2.scheduler.service.SchedulerForDepExtImportService;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerForSimpleJobsService;
import com.ttc.ch2.scheduler.service.SchedulerForUploadService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;
import com.ttc.ch2.statistics.JmxService;


public class InitializeSystemService {

	@Inject
	private DbUpdaterComponent dbUpdaterComponent;
	
	@Inject
	private SynchronizeLock synchronizeLock;
	
	@Inject
	private SchedulerForImportService schedulerForImportService;
	
	@Inject
	private SchedulerForDepExtImportService schedulerForQuickImportService;
	
	@Inject
	private SchedulerForUploadService schedulerForUploadService;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private QuartzJobDAO quartzJobDAO;
	
	@Inject
	private SchedulerForSimpleJobsService simpleJobsSchedulers;
	
	@Inject
	private QuartzSwitcher quartzSwitcher;
	
	@Inject
	private Ch2ConfigValueDAO ch2ConfigValueDAO;
	
	@Inject
	private JmxService jmxService;
	
	@Value("${common.production:true}")
	private String productionFlag;
	
	@PostConstruct
	public void init() throws UnsupportedEncodingException, SchedulerServiceException{	
		dbUpdaterComponent.updateStart(BooleanUtils.toBoolean(productionFlag));
		initQuartz();
		synchronizeLock.init();
		schedulerForImportService.init();
		schedulerForQuickImportService.init();
		schedulerForUploadService.init();
		simpleJobsSchedulers.init();
		jmxService.jmxRegister();
	}

	
	public void initQuartz()
	{		
		List<QuartzJob> list=quartzJobDAO.findAll();
		List<Brand> brands=brandDAO.findAll();
		Collections.sort(brands, new OrderingBrandByCode());
		
		List<Ch2ConfigValue> cfgs=ch2ConfigValueDAO.findAll();
		Ch2ConfigValue value=Iterables.find(cfgs, new Ch2ConfigValueFinder(Ch2ConfigValue.PropName.SystemDirection));
		SystemDirection systemDirection=SystemDirection.valueOf(value.getPropertyValue());
		
		int minute= 0;
		if (list.size() == 0) { 				
			for (Brand brand : brands) {
				
				// Import - long work all subtasks
				QuartzJob jobImport=new QuartzJob();
				jobImport.setJobName(QuartzJob.JobName.DepartureSynchronizeJob.toString());
				jobImport.setJobStatus(JobStatus.Inactive);
				jobImport.setNextFiringTime(null);
				jobImport.setGroupName(SchedulerForImportService.jobGroupName);
				jobImport.setUiName(String.format(SchedulerForImportService.JOB_DESC,brand.getCode()));

				jobImport.setCronExpresion(quartzSwitcher.calculateCronExpresionForDepartureImport(minute, systemDirection));
				jobImport.setBrandCode(brand.getCode());			
				quartzJobDAO.save(jobImport);
				
				minute=minute+5;
				
				// UPLOAD Tour Info 
				QuartzJob jobUpload=new QuartzJob();
				jobUpload.setJobName(QuartzJob.JobName.UploadTourInfoJob.toString());
				jobUpload.setJobStatus(JobStatus.Inactive);
				jobUpload.setNextFiringTime(null);
				jobUpload.setGroupName(SchedulerForUploadService.jobGroupName);	
				jobUpload.setCronExpresion(quartzSwitcher.calculateCronExpresionForTourInfoUpload());
				jobUpload.setBrandCode(brand.getCode());
				jobUpload.setUiName(String.format(SchedulerForUploadService.JOB_DESC,brand.getCode()));
				quartzJobDAO.save(jobUpload);
			}
			
			QuartzJob purger=new QuartzJob();
			purger.setGroupName(SchedulerForSimpleJobsService.SIMPLE_GROUP);	
			purger.setJobName(QuartzJob.JobName.AuditPurgeJob.toString());
			purger.setJobStatus(JobStatus.Inactive);
			purger.setNextFiringTime(null);
			purger.setCronExpresion(quartzSwitcher.calculateCronExpresionForAuditPurge());
			purger.setUiName(SchedulerForSimpleJobsService.JOB_DESC);
			quartzJobDAO.save(purger);		
		}
		
		minute= 0;
		QuartzJob jobSample=new QuartzJob();
		jobSample.setJobName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString());
		jobSample.setBrandCode(brands.get(0).getCode());		
		QuartzJob jobExist=quartzJobDAO.findByExample(jobSample);
		if(jobExist==null){
			for (Brand brand : brands) {
				// Import - quick work subtasks Reconciliation and CH1 download
				QuartzJob jobQuickImport=new QuartzJob();
				jobQuickImport.setJobName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString());
				jobQuickImport.setJobStatus(JobStatus.Inactive);
				jobQuickImport.setNextFiringTime(null);
				jobQuickImport.setGroupName(SchedulerForDepExtImportService.jobGroupName);	
				jobQuickImport.setCronExpresion(quartzSwitcher.calculateCronExpresionForDepartureExternal(minute, systemDirection));
				jobQuickImport.setBrandCode(brand.getCode());
				jobQuickImport.setUiName(String.format(SchedulerForDepExtImportService.JOB_DESC,brand.getCode()));
				quartzJobDAO.save(jobQuickImport);
			
				minute=minute+5;
			}			
		}
	}
	
}
