package com.ttc.ch2.ui.common.initialize;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.ttc.ch2.bl.SynchronizeLock;
import com.ttc.ch2.common.enums.CronExpresion;
import com.ttc.ch2.common.ordering.OrderingBrandByCode;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.jobs.QuartzJobDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.hibernate.DbUpdaterComponent;
import com.ttc.ch2.scheduler.service.SchedulerForSimpleJobsService;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerForUploadService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;


public class InitializeSystemService {

	@Inject
	private DbUpdaterComponent dbUpdaterComponent;
	
	@Inject
	private SynchronizeLock synchronizeLock;
	
	@Inject
	private SchedulerForImportService schedulerForImportService;
	
	@Inject
	private SchedulerForUploadService schedulerForUploadService;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private QuartzJobDAO quartzJobDAO;
	
	@Inject
	private SchedulerForSimpleJobsService simpleJobsSchedulers;
	
	
	@PostConstruct
	public void init() throws UnsupportedEncodingException, SchedulerServiceException
	{
		dbUpdaterComponent.updateStart();
		initQuartz();
		synchronizeLock.init();
		schedulerForImportService.init();
		schedulerForUploadService.init();
		simpleJobsSchedulers.init();
	}

	
	public void initQuartz()
	{		
		List<QuartzJob> list=quartzJobDAO.findAll();			
		int minute= 0;
		if (list.size() == 0) { 		
			List<Brand> brands=brandDAO.findAll();
			Collections.sort(brands, new OrderingBrandByCode());
			for (Brand brand : brands) {
				QuartzJob jobImport=new QuartzJob();
				jobImport.setJobName(SchedulerForImportService.jobImportName);
				jobImport.setJobStatus(JobStatus.Inactive);
				jobImport.setNextFiringTime(null);
				jobImport.setGroupName(SchedulerForImportService.jobGroupName);	

				jobImport.setCronExpresion(CronExpresion.TDI_HOUR06.getExpresion(minute));
				jobImport.setBrandCode(brand.getCode());			
				quartzJobDAO.save(jobImport);
				minute=minute+5;
				
				QuartzJob jobUpload=new QuartzJob();
				jobUpload.setJobName(SchedulerForUploadService.jobUploadName);
				jobUpload.setJobStatus(JobStatus.Inactive);
				jobUpload.setNextFiringTime(null);
				jobUpload.setGroupName(SchedulerForUploadService.jobGroupName);	
				jobUpload.setCronExpresion(CronExpresion.TI_MINUTE03.getExpresion());
				jobUpload.setBrandCode(brand.getCode());			
				quartzJobDAO.save(jobUpload);
			}
			
			QuartzJob purger=new QuartzJob();
			purger.setGroupName(SchedulerForSimpleJobsService.SIMPLE_GROUP);	
			purger.setJobName(SchedulerForSimpleJobsService.AUDIT_PURGER);
			purger.setJobStatus(JobStatus.Inactive);
			purger.setNextFiringTime(null);
			purger.setCronExpresion(CronExpresion.AUDIT_PURGE.getExpresion());
			quartzJobDAO.save(purger);		
		}		
	}
	
}
