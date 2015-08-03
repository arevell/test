package com.ttc.ch2.hibernate.messages;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.messages.EmailAddressDAO;
import com.ttc.ch2.domain.messages.EmailAddress;
import com.ttc.ch2.domain.messages.EmailAddress.AddressType;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class EmailAddressDAOImpl extends BaseDao<EmailAddress, Long> implements EmailAddressDAO {

	@Override
	public List<EmailAddress> getEmailAddressesList(String brandCode, AddressType addressType) {

		Search search = new Search();
		search.addFilterEqual("brand.code", brandCode);
		search.addFilterEqual("addressType", addressType);

		return search(search);
	}
}
