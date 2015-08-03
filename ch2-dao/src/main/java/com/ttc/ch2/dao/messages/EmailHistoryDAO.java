package com.ttc.ch2.dao.messages;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.messages.EmailHistory;

public interface EmailHistoryDAO extends GenericDAO<EmailHistory, Long > {

	public List<EmailHistory> getEmailHistoryList(QueryCondition conditions, EmailHistory filter,List<Brand> brands);
	
	public int getEmailHistoryCount(EmailHistory filter,List<Brand> brands);
	
	public EmailHistory findByExample(EmailHistory exemple);
}
