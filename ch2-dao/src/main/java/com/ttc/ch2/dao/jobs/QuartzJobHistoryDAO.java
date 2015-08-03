package com.ttc.ch2.dao.jobs;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;

public interface QuartzJobHistoryDAO extends GenericDAO<QuartzJobHistory, Long> {

	List<QuartzJobHistory> getJobsHistoryList(QueryCondition conditions, QuartzJobHistory filter);
	
	int getJobsCount(QuartzJobHistory filter);
	
}
