package com.ttc.ch2.hibernate.messages;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.common.exceptions.DaoException;
import com.ttc.ch2.dao.messages.EmailHistoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.messages.EmailHistory;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class EmailHistoryDAOImpl extends BaseDao<EmailHistory,  Long> implements EmailHistoryDAO {

	@Override
	public List<EmailHistory> getEmailHistoryList(QueryCondition conditions, EmailHistory filter,List<Brand> brands) {
		Search search = new Search();
		setupSortCondition(search, conditions);
		setupSearchCondition(search, filter,brands);		
		return search(search);
	}
	
	protected void setupSearchCondition(Search search, EmailHistory filter,List<Brand> brands){
		if(filter != null) {		
			if(filter.getMessageDeliveryTime()!=null){
			search.addFilterGreaterOrEqual("messageDeliveryTime", filter.getMessageDeliveryTime());
			}				
			
			if (filter.getProccessName()!= null) {
				search.addFilterEqual("proccessName", filter.getProccessName());
			}
			
			if (filter.getStatus() != null) {
				search.addFilterEqual("status", filter.getStatus());
			}
			
			if(StringUtils.isNotEmpty(filter.getSubject())) {
				search.addFilterILike("subject", "%" + filter.getSubject()+"%");
			}
			
			if(brands!=null && brands.size()>0) {				
				search.addFilterIn("brand", brands);
			}
		}		
	}

	@Override
	public int getEmailHistoryCount(EmailHistory filter,List<Brand> brands) {
		Search search = new Search();
		setupSearchCondition(search, filter,brands);
		return count(search);
	}
	
	public EmailHistory findByExample(EmailHistory exemple)
	{
		Filter filter=getFilterFromExample(exemple);		
		Search search = new Search();
		search.addFilter(filter);
		List<EmailHistory> list=this.search(search);
		
		if(list.size()>1)
			throw new DaoException("To many record in EmailHistoryDAOImpl->findByExample");
		
		return list.size() ==1 ? list.get(0) : null;
	}
	
}
