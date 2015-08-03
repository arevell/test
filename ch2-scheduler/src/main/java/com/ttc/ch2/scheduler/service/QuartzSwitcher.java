package com.ttc.ch2.scheduler.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.ttc.ch2.common.enums.CronExpresion;
import com.ttc.ch2.common.enums.SystemDirection;
import com.ttc.ch2.common.predicates.Ch2ConfigValueFinder;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.Ch2ConfigValueDAO;
import com.ttc.ch2.dao.jobs.QuartzJobDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.Ch2ConfigValue;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;

@Service
public class QuartzSwitcher {
	
	@Inject
	private SchedulerForImportService schedulerForImportService; 
	
	@Inject
	private  SchedulerForDepExtImportService schedulerForDepExtImportService;
	
	@Inject
	private Ch2ConfigValueDAO ch2ConfigValueDAO;
	
	@Inject
	private QuartzJobDAO quartzJobDao;
	
	@Inject
	private BrandDAO brandDao;
		
	@Value("${scheduler.habs.departureimport.cron}")
	private String habsDepartureImportCronExpresion;
	
	@Value("${scheduler.habs.departureexternal.cron}")
	private String habsDepartureExternalCronExpresion;
	
	@Value("${scheduler.tropics.departureimport.cron}")
	private String tropicsDepartureImportCronExpresion;
	
	@Value("${scheduler.tropics.departureexternal.cron}")
	private String tropicsDepartureExternalCronExpresion;
	
	@Value("${scheduler.audti.purge.cron}")
	private String auditPurgeCronExpresion;
	
	@Value("${scheduler.tourInfo.upload.cron}")
	private String tourInfoUploadCronExpresion;
	

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void switchToSystem(SystemDirection systemDirection) throws SchedulerServiceException{
					
		QuartzJob jobSample=new QuartzJob();

		int minute=0;
		for (Brand brand: brandDao.findAll()) {			
			jobSample.setBrandCode(brand.getCode());
			
			jobSample.setJobName(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString());								
			QuartzJob jobExist1=quartzJobDao.findByExample(jobSample);
			
			if(jobExist1!=null){				
				jobExist1.setCronExpresion(calculateCronExpresionForDepartureExternal(minute,systemDirection));
				quartzJobDao.save(jobExist1);
				if(jobExist1.getJobStatus()==JobStatus.Inactive){
					schedulerForDepExtImportService.setupCronJob(true, brand.getCode());
				}
			}
						
			jobSample.setJobName(QuartzJob.JobName.DepartureSynchronizeJob.toString());		
			QuartzJob jobExist2=quartzJobDao.findByExample(jobSample);			
			if(jobExist2!=null){				
				jobExist2.setCronExpresion(calculateCronExpresionForDepartureImport(minute,systemDirection));
				quartzJobDao.save(jobExist2);
								
				if(jobExist2.getJobStatus()==JobStatus.Inactive && (jobExist2.getNextFiringTime()==null || jobExist2.getNextFiringTime().before(new Date()))){
					schedulerForImportService.setupCronJob(true, brand.getCode());
				}
			}
			minute=minute+5;
		}
		
		List<Ch2ConfigValue> cfgs=ch2ConfigValueDAO.findAll();
		Ch2ConfigValue value=Iterables.find(cfgs, new Ch2ConfigValueFinder(Ch2ConfigValue.PropName.SystemDirection));
		value.setPropertyValue(systemDirection.toString());
		ch2ConfigValueDAO.save(value);
	}
	

	public String calculateCronExpresionForDepartureExternal(int minute,SystemDirection systemDirection){
		
		String expresion=null;
		String format=null;
		switch (systemDirection) {
		case HABS:			
			format=StringUtils.defaultIfBlank(habsDepartureExternalCronExpresion, CronExpresion.TDI_HOUR06.getExpresion());			
			expresion=MessageFormat.format(format, minute);
			break;
		case TROPICS:	
			format=StringUtils.defaultIfBlank(tropicsDepartureExternalCronExpresion, CronExpresion.TDI_HOUR06.getExpresion());			
			expresion=MessageFormat.format(format, minute);
			break;

		default:
			throw new IllegalStateException("Cron expresion can not setup");			
		}
		return expresion;
	}
	
	public String calculateCronExpresionForDepartureImport(int minute,SystemDirection systemDirection){
		
		String expresion=null;
		String format=null;
		switch (systemDirection) {
		case HABS:			
			format=StringUtils.defaultIfBlank(habsDepartureImportCronExpresion, CronExpresion.TDE_HOUR02.getExpresion());			
			expresion=MessageFormat.format(format, minute);
			break;
		case TROPICS:	
			format=StringUtils.defaultIfBlank(tropicsDepartureImportCronExpresion, CronExpresion.TDI_HOUR06.getExpresion());			
			expresion=MessageFormat.format(format, minute);
			break;
			
		default:
			throw new IllegalStateException("Cron expresion can not setup");			
		}
		return expresion;
	}
	
	public String calculateCronExpresionForAuditPurge(){			
		return StringUtils.defaultIfBlank(auditPurgeCronExpresion,CronExpresion.AUDIT_PURGE.getExpresion());
	}
	public String calculateCronExpresionForTourInfoUpload(){			
		return StringUtils.defaultIfBlank(tourInfoUploadCronExpresion,CronExpresion.TI_MINUTE03.getExpresion());
	}
		
}
