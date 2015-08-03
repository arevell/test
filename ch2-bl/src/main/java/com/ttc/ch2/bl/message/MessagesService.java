package com.ttc.ch2.bl.message;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.messages.EmailHistory;

public interface MessagesService {

	List<EmailHistory> getEmailHistoryList(QueryCondition conditions, EmailHistory filter) throws MessagesServiceException;

	int getEmailHistoryCount(EmailHistory filter) throws MessagesServiceException;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	void saveEmailHistory(EmailHistory emailHistory);
}
