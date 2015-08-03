package com.ttc.ch2.hibernate.jobs;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.common.exceptions.DaoException;
import com.ttc.ch2.dao.jobs.QuartzJobDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.messages.EmailHistory;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class QuartzJobDAOImpl extends BaseDao<QuartzJob, Long> implements QuartzJobDAO {

	@Override
	public List<QuartzJob> getJobsList(QueryCondition conditions, QuartzJob filter) {
		Search search = new Search();
		if(conditions != null) {
			search.setMaxResults(conditions.getPageSize());
			search.setFirstResult(conditions.getStartNumberItem());
			for(SortCondition sc: conditions.getSortConditions()) {
				if(sc.getDirection().equals(SortCondition.Direction.ASC)) {
					search.addSortAsc(sc.getColumnName());
				}
				else {
					search.addSortDesc(sc.getColumnName());
				}
			}
		}
		if(filter != null) {
			search.addFilter(getFilterFromExample(filter));
		}
		
		return search(search);
	}

	@Override
	public int getJobsCount(QuartzJob filter) {
		Search search = new Search();
		if(filter != null) {
			search.addFilter(getFilterFromExample(filter));
		}
		return count(search);
	}


	
	public QuartzJob findByExample(QuartzJob exemple)
	{
		Filter filter=getFilterFromExample(exemple);		
		Search search = new Search();
		search.addFilter(filter);
		List<QuartzJob> list=this.search(search);
		
		if(list.size()>1)
			throw new DaoException("To many record in QuartzJobDAOImpl->findByExample");
		
		return list.size() ==1 ? list.get(0) : null;
	}
}
