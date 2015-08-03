package com.ttc.ch2.hibernate.jobs;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.jobs.QuartzJobHistoryDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class QuartzJobHistoryDAOImpl extends BaseDao<QuartzJobHistory, Long> implements QuartzJobHistoryDAO {


	@Override
	public List<QuartzJobHistory> getJobsHistoryList(QueryCondition conditions,	QuartzJobHistory filter) {
		Search search = new Search();
		setupSortCondition(search, conditions);
		setupSearchCondition(search, filter);		
		return search(search);
	}

	@Override
	public int getJobsCount(QuartzJobHistory filter) {
		Search search = new Search();
		setupSearchCondition(search, filter);
		return count(search);
	}
	
	
	private void setupSearchCondition(Search search,QuartzJobHistory filter)
	{
		if(filter != null) {
			Date startDate=filter.getStartDate();
			if(startDate!=null){
			search.addFilterGreaterOrEqual("startDate", startDate);
			}				
			
			if(filter.getStatus()!=null)
			{
				search.addFilterEqual("status", filter.getStatus());
			}
			
			if(StringUtils.isNotEmpty(filter.getExecutedBy())) {
				search.addFilterLike("executedBy", "%" + filter.getExecutedBy()+"%");
			}		
			
			if(filter.getBrand()!=null){
				search.addFilterEqual("brand", filter.getBrand());
			}
		}
	}

	
}
