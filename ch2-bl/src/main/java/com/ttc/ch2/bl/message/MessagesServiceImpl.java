package com.ttc.ch2.bl.message;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.dao.messages.EmailHistoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.messages.EmailHistory;
import com.ttc.ch2.domain.user.UserGui;

@Service
public class MessagesServiceImpl implements MessagesService {

	@Inject
	private EmailHistoryDAO emailHistoryDAO;

	public List<EmailHistory> getEmailHistoryList(QueryCondition conditions, EmailHistory filter) throws MessagesServiceException {
		try {
			List<Brand> brands=Lists.newArrayList();
			if(SecurityHelper.hasAuthority(Role.BRAND)){
				UserGui userGui=SecurityHelper.getUserGuiPrincipal().getUserDb();
				brands.addAll(userGui.getBrands());
			}
			return emailHistoryDAO.getEmailHistoryList(conditions, filter,brands);
		} catch (Exception e) {
			throw new MessagesServiceException(e);
		}
	}

	public int getEmailHistoryCount(EmailHistory filter) throws MessagesServiceException {
		try {
			List<Brand> brands=Lists.newArrayList();
			if(SecurityHelper.hasAuthority(Role.BRAND)){
				UserGui userGui=SecurityHelper.getUserGuiPrincipal().getUserDb();
				brands.addAll(userGui.getBrands());
			}
			return emailHistoryDAO.getEmailHistoryCount(filter,brands);
		} catch (Exception e) {
			throw new MessagesServiceException(e);
		}
	}

	@Override
	public void saveEmailHistory(EmailHistory emailHistory) {
		emailHistoryDAO.save(emailHistory);
	}
}
