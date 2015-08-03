package com.ttc.ch2.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.RecReportDataDAO;
import com.ttc.ch2.domain.RecReportData;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class RecReportDataDAOImpl extends BaseDao<RecReportData,  Long> implements RecReportDataDAO {


	
	public List<RecReportData> getList(RecReportData exemple)
	{
		Filter filter=getFilterFromExample(exemple);		
		Search search = new Search();
		search.addFilter(filter);
		List<RecReportData> list=this.search(search);		
		return list;
	}
}
