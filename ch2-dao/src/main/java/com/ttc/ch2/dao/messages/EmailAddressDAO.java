package com.ttc.ch2.dao.messages;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.messages.EmailAddress;
import com.ttc.ch2.domain.messages.EmailAddress.AddressType;

public interface EmailAddressDAO extends GenericDAO<EmailAddress, Long> {

	List<EmailAddress> getEmailAddressesList(String brandCode, AddressType addressType);
}
