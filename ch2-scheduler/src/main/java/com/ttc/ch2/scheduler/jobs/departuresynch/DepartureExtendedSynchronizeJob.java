package com.ttc.ch2.scheduler.jobs.departuresynch;

import org.apache.commons.lang.StringUtils;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.google.common.base.Preconditions;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.scheduler.common.JobParams;
import com.ttc.ch2.scheduler.service.departureextend.DepartureExtendedSynchronizeService;

public class DepartureExtendedSynchronizeJob extends QuartzJobBean implements InterruptableJob{

	private static final Logger logger=LoggerFactory.getLogger(DepartureExtendedSynchronizeJob.class);
	
	private String brandCode;
	
	
	private DepartureExtendedSynchronizeService departureExtendedSynchronizeService;
		
	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {		
		logger.trace("QuickDepartureSynchronizeJob:executeInternal-start");
			try{
				brandCode=(String) ctx.getJobDetail().getJobDataMap().get(JobParams.BRAND_CODE.toString());			 				
				Preconditions.checkState(StringUtils.isNotBlank(brandCode),"QuickDepartureSynchronizeJob->executeInternal param brandCode is required");				 					
				setupService();
				departureExtendedSynchronizeService.processing();			
			}
			catch(Exception e){
				logger.error("",e);
			}
			finally{				
			}
		logger.trace("QuickDepartureSynchronizeJob:executeInternal-end");		
	}
	
	public void interrupt() {
		
		logger.trace("QuickDepartureSynchronizeJob:interrupt-start");
		try{				 	
			Preconditions.checkState(StringUtils.isNotBlank(brandCode),"QuickDepartureSynchronizeJob->executeInternal param brandCode is required");
			setupService();
			departureExtendedSynchronizeService.interrupt();		
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{			
		}
		logger.trace("QuickDepartureSynchronizeJob:interrupt-end");
	}
	
	
	private synchronized void setupService(){		
		if(departureExtendedSynchronizeService==null){
		   departureExtendedSynchronizeService = SpringContext.getApplicationContext().getBean(DepartureExtendedSynchronizeService.class);
		   departureExtendedSynchronizeService.setBrandCode(brandCode);		
		}
	}
}
