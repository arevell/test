package com.ttc.ch2.scheduler.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.dao.jobs.QuartzJobDAO;
import com.ttc.ch2.dao.jobs.QuartzJobHistoryDAO;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.user.User;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class QuartzJobCh2ServiceImpl implements QuartzJobCh2Service{

	private static final Logger logger = LoggerFactory.getLogger(QuartzJobCh2ServiceImpl.class);
	@Inject
	private QuartzJobDAO quartzDao;
	@Inject
	private QuartzJobHistoryDAO quartzJobHistoryDAO;
	
	@Inject
	private UserService userService;

	@Override
	public List<QuartzJobHistory> getJobsHistoryList(QueryCondition conditions,	QuartzJobHistory filter) throws QuartzJobCh2ServiceException {
		logger.trace("QuartzJobCh2ServiceImpl:getJobsHistoryList-start");
		try {
			logger.trace("QuartzJobCh2ServiceImpl:getJobsHistoryList-end");
			return quartzJobHistoryDAO.getJobsHistoryList(conditions, filter);
		}
		catch (Exception e) {
			logger.trace("QuartzJobCh2ServiceImpl:getJobsHistoryList-end");
			throw new QuartzJobCh2ServiceException(e);
		}
	}

	@Override
	public int getJobsCount(QuartzJobHistory filter)throws QuartzJobCh2ServiceException {
		logger.trace("QuartzJobCh2ServiceImpl:getJobsCount-start");
		try {
			logger.trace("QuartzJobCh2ServiceImpl:getJobsCount-end");
			return quartzJobHistoryDAO.getJobsCount(filter);
		} catch (Exception e) {
			logger.trace("QuartzJobCh2ServiceImpl:getJobsCount-end");
			throw new QuartzJobCh2ServiceException(e);
		}
	}

	@Override
	public void saveNewQuartzJobHistory(QuartzJobHistory quartzJobHistory)throws QuartzJobCh2ServiceException {		
		logger.trace("QuartzJobCh2ServiceImpl:saveNewQuartzJobHistory-start");
		try {
				QuartzJob job=quartzDao.find(quartzJobHistory.getQuartzJob().getId());
				job.setJobStatus(quartzJobHistory.getQuartzJob().getJobStatus());
				quartzJobHistory.setQuartzJob(job);
				quartzJobHistoryDAO.save(quartzJobHistory);
				quartzJobHistoryDAO.flush();
		} catch (Exception e) {
			logger.trace("QuartzJobCh2ServiceImpl:saveNewQuartzJobHistory-end");
			throw new QuartzJobCh2ServiceException(e);
		}
		logger.trace("QuartzJobCh2ServiceImpl:saveNewQuartzJobHistory-end");
	}
	
	
	public void mergeQuartzJobHistory(QuartzJobHistory quartzJobHistory) {
		logger.trace("QuartzJobCh2ServiceImpl:addNewQuartzJobHistory-start");
		try {			
			QuartzJobHistory localHistory=quartzJobHistoryDAO.find(quartzJobHistory.getId());			
			for (QHComment comment : quartzJobHistory.getComments()) {
				if (comment.getId() == null) {					
					localHistory.getComments().add(comment);
					comment.setQuartzJobHistory(localHistory);
				}
			}			
			localHistory.setExecutedBy(quartzJobHistory.getExecutedBy());
			localHistory.setExecutionTime(quartzJobHistory.getExecutionTime());
			localHistory.setStartDate(quartzJobHistory.getStartDate());
			localHistory.setStatus(quartzJobHistory.getStatus());
									
			quartzJobHistoryDAO.save(localHistory);
			quartzJobHistoryDAO.flush();			
		} catch (Exception e) {
			logger.trace("QuartzJobCh2ServiceImpl:addNewQuartzJobHistory-end");
			logger.error("",e);
			throw new QuartzJobCh2ServiceException(e);
		}
		logger.trace("QuartzJobCh2ServiceImpl:addNewQuartzJobHistory-end");
	}
	

	public QuartzJob findByName(String name,String brandCode)
	{
		logger.trace("QuartzJobCh2ServiceImpl:findByName-start");
		try{
			if(StringUtils.isBlank(name))
				return null;

		QuartzJob eJob=new QuartzJob();
		eJob.setJobName(name);
		eJob.setBrandCode(brandCode);
		logger.trace("QuartzJobCh2ServiceImpl:findByName-end");
		return quartzDao.findByExample(eJob);
		}
		catch (Exception e) {
				logger.trace("QuartzJobCh2ServiceImpl:findByName-end");
				throw new QuartzJobCh2ServiceException(e);
		}		
	}
	
	public void saveQuartzJob(QuartzJob job)
	{
		logger.trace("QuartzJobCh2ServiceImpl:save-start");
		try {
			Preconditions.checkArgument(job!=null, "QuartzJobCh2ServiceImpl-> arg job is null");
			if(job.getId()==null){
			quartzDao.save(job);
			}
			else
			{
				QuartzJob db=quartzDao.find(job.getId());
				if(db!=null)
				{
					db.setCronExpresion(job.getCronExpresion());
					db.setGroupName(job.getGroupName());
					db.setJobName(job.getJobName());
					db.setJobStatus(job.getJobStatus()==null ? JobStatus.Inactive : job.getJobStatus());
					db.setNextFiringTime(job.getNextFiringTime());
					db.setBrandCode(job.getBrandCode());
					
					if(job.getUser()!=null){
						User user=userService.findUserByName(job.getUser().getUsername());
						db.setUser(user);
					}
					else{
						db.setUser(null);
					}
	
					quartzDao.save(db);
					quartzDao.flush();
				}
				else{
					throw new QuartzJobCh2ServiceException("Can not find job by id:"+job.getId());
				}
			}
		} 
		catch (QuartzJobCh2ServiceException e) {
			logger.error("",e);
			logger.trace("QuartzJobCh2ServiceImpl:save-end");
			throw e;
		}catch (Exception e) {
			logger.error("",e);
			logger.trace("QuartzJobCh2ServiceImpl:save-end");
			throw new QuartzJobCh2ServiceException(e);
		} 
		logger.trace("QuartzJobCh2ServiceImpl:save-end");
	}
	
	
	public QuartzJobHistory getFullDataQuartzJobHistory(Long id)
	{
		logger.trace("QuartzJobCh2ServiceImpl:getFullDataQuartzJobHistory-start");		
		QuartzJobHistory jobHistory=null;
		try {
			Preconditions.checkArgument(id!=null, "QuartzJobCh2ServiceImpl->arg id is null");
			
			jobHistory=quartzJobHistoryDAO.find(id);
			if (jobHistory != null) {
				if(jobHistory.getTourDepartureHistory()!=null){
					for (TDComment comment : jobHistory.getTourDepartureHistory().getComments()) {
						comment.getId();
					}			
				}
				
				for (QHComment comment : jobHistory.getComments()) {
					comment.getId();
				}
			}
		} catch (Exception e) {
			logger.trace("QuartzJobCh2ServiceImpl:getFullDataQuartzJobHistory-end");
			throw new QuartzJobCh2ServiceException(e);
		}
		
		logger.trace("QuartzJobCh2ServiceImpl:getFullDataQuartzJobHistory-end");
		return jobHistory;
	}

	@Override
	public List<QuartzJob> getJobsByGroupName(String groupName) {
		logger.trace("QuartzJobCh2ServiceImpl:getJobsByName-start");
		
		try{			
			QuartzJob eQuartzJob=new QuartzJob();
			eQuartzJob.setGroupName(groupName);			
			List<QuartzJob> list=quartzDao.getJobsList(null, eQuartzJob);
		
		logger.trace("QuartzJobCh2ServiceImpl:getJobsByName-end");
		return list;
		}catch(Exception e){
			logger.error("",e);
			logger.trace("QuartzJobCh2ServiceImpl:getJobsByName-start");
			throw new QuartzJobCh2ServiceException(e);
		}		
	}	
}
