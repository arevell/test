package com.ttc.ch2.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.domain.SellingCompany;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/testOnlyCtx.xml","classpath:/META-INF/spring/daoCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class SellingCompaniesTest {

	@Inject
	private SellingCompanyDAO sellingCompanyDAO;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void testSellingCompaniesDAO() {
		List<String> scl = new ArrayList<String>();
		scl.add("CHCANS");
		scl.add("CHJBGS");
		List<SellingCompany> scl2 = sellingCompanyDAO.findBySellingCompanyCodes(scl);
		assertEquals(scl.size(),scl2.size());		
	}
}
