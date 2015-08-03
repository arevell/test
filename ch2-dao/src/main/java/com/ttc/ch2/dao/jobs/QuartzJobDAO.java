package com.ttc.ch2.dao.jobs;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.jobs.QuartzJob;

public interface QuartzJobDAO extends GenericDAO<QuartzJob, Long> {

	List<QuartzJob> getJobsList(QueryCondition conditions, QuartzJob filter);
	
	int getJobsCount(QuartzJob filter);
		
	public QuartzJob findByExample(QuartzJob exemple);
	
}
