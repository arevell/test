package com.ttc.ch2.scheduler.service;

import java.util.List;

import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;

public interface QuartzJobCh2Service {
	
	// view history 
	public List<QuartzJobHistory> getJobsHistoryList(QueryCondition conditions, QuartzJobHistory filter) throws QuartzJobCh2ServiceException;	
	public int getJobsCount(QuartzJobHistory filter) throws QuartzJobCh2ServiceException;
		
	// import
	public void saveNewQuartzJobHistory(QuartzJobHistory quartzJobHistory) throws QuartzJobCh2ServiceException;	
	public void mergeQuartzJobHistory(QuartzJobHistory quartzJobHistory);
		
	public void saveQuartzJob(QuartzJob job);
	
	public QuartzJob findByName(String name,String brandCode);
	public List<QuartzJob> getJobsByGroupName(String name); 
	public QuartzJobHistory getFullDataQuartzJobHistory(Long id);
}
