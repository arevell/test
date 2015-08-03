package com.ttc.ch2.bl.messages;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.bl.message.MessagesService;
import com.ttc.ch2.common.AuthenticatedExecutionPreparer;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.messages.EmailHistoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.messages.EmailHistory;





@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, AuthenticatedExecutionPreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class MessagesTest extends BaseTest{

	@Inject
	private EmailHistoryDAO dao;
	
	@Inject
	private BrandDAO brandDao;
	
	@Inject
	private MessagesService messagesService;
		
	private void checkMessage() {
		EmailHistory eEmailHistory=new EmailHistory();
		eEmailHistory.setSubject("subject:1");		
		EmailHistory finded=dao.findByExample(eEmailHistory);
		if (finded == null) {
			Brand brand=brandDao.findAll().get(0);			
			dao.save(SampleGenerator.generateEmailHistory(1,brand));
		}
	}
	
	private void clear(){
		EmailHistory eEmailHistory=new EmailHistory();
		eEmailHistory.setSubject("subject:1");		
		EmailHistory finded=dao.findByExample(eEmailHistory);
		if (finded != null) {						
			dao.remove(finded);
		}
	}
	
	@Test
	public void testFilter()
	{
		try{
		//prepare
		checkMessage();
		QueryCondition condition=new QueryCondition(0,10);
		EmailHistory filter=new EmailHistory();
		filter.setSubject("subject:1");		
		//test
		List<EmailHistory> list=messagesService.getEmailHistoryList(condition, filter);
		//check
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		}
		finally{
			clear();
		}
	}
	
	@Test
	public void testCount()
	{
		try{
		//prepare
		checkMessage();
		EmailHistory filter=new EmailHistory();
		filter.setSubject("subject:1");		
		//test
		int count=messagesService.getEmailHistoryCount(filter);
		//check		
		Assert.assertTrue(count>0);
		}
		finally{
			clear();
		}
	}
}
