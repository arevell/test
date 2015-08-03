package com.ttc.ch2.bl.messages;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.bl.message.CommentsService;
import com.ttc.ch2.common.AuthenticatedExecutionPreparer;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.dao.comment.CRECommentDAO;
import com.ttc.ch2.dao.comment.QHCommentDAO;
import com.ttc.ch2.dao.comment.TDCommentDAO;
import com.ttc.ch2.dao.comment.TICommentDAO;
import com.ttc.ch2.dao.comment.TypeComment;
import com.ttc.ch2.domain.comment.CREComment;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.QueryCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, AuthenticatedExecutionPreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class CommentsTest extends BaseTest{

	private static final Logger logger = LoggerFactory.getLogger(CommentsTest.class);
	@Inject 
	private SessionFactory sf;
	
	@Inject
	private CommentsService service;
	
	@Inject
	private QHCommentDAO qhCommentDAO;
	@Inject
	private CRECommentDAO creCommentDAO;
	@Inject
	private TICommentDAO tiCommentDAO;
	@Inject
	private TDCommentDAO tdCommentDAO;

	@Test
	public void initDB()
	{
		try{
		tiCommentDAO.save(SampleGenerator.generateSampleTIComment(0));
		tdCommentDAO.save(SampleGenerator.generateSampleTDComment(0));
		creCommentDAO.save(SampleGenerator.generateSampleCREComment(0));
		qhCommentDAO.save(SampleGenerator.generateSampleQHComment(0));
		}
		finally{
			clearDB();
		}
		
	}
	
	@Test
	public void testFilterTI()
	{
		try{
		//prepare
		tiCommentDAO.save(SampleGenerator.generateSampleTIComment(0));
		QueryCondition condition=new QueryCondition(0,10);
		TIComment filter=new TIComment();	
		//test
		List<Comment> list=service.getCommentList(condition, filter,null, TypeComment.TIComment);
		//check
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		}
		finally{
			clearDB();
		}
	}
	
	@Test
	public void testCountTI()
	{
		try{
		//prepare
		tiCommentDAO.save(SampleGenerator.generateSampleTIComment(0));
		TIComment filter=new TIComment();	
		//test
		int count=service.getCommentCount(filter,null,TypeComment.TIComment);
		//check		
		Assert.assertTrue(count>0);
		}
		finally{
			clearDB();
		}
	}
	
	@Test
	public void testFilterTD()
	{
		try{
		//prepare
		tdCommentDAO.save(SampleGenerator.generateSampleTDComment(0));
		QueryCondition condition=new QueryCondition(0,10);
		TDComment filter=new TDComment();	
		//test
		List<Comment> list=service.getCommentList(condition, filter,null, TypeComment.TDComment);
		//check
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		}
		finally{
			clearDB();
		}
	}
	
	@Test
	public void testCountTD()
	{
		try{
		//prepare
		tdCommentDAO.save(SampleGenerator.generateSampleTDComment(0));
		TDComment filter=new TDComment();	
		//test
		int count=service.getCommentCount(filter,null,TypeComment.TDComment);
		//check		
		Assert.assertTrue(count>0);
		}
		finally{
			clearDB();
		}
	}
	@Test
	public void testFilterQH()
	{
		try{	
		//prepare
		qhCommentDAO.save(SampleGenerator.generateSampleQHComment(0));
		QueryCondition condition=new QueryCondition(0,10);
		QHComment filter=new QHComment();	
		//test
		List<Comment> list=service.getCommentList(condition, filter,null, TypeComment.QHComment);
		//check
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		}
		finally{
			clearDB();
		}
	}
	
	@Test
	public void testCountQH()
	{
		try{
		//prepare
		qhCommentDAO.save(SampleGenerator.generateSampleQHComment(0));
		QHComment filter=new QHComment();	
		//test
		int count=service.getCommentCount(filter,null,TypeComment.QHComment);
		//check		
		Assert.assertTrue(count>0);
		}finally
		{
			clearDB();
		}
	}
	@Test
	public void testFilterCRE()
	{
		try{
		//prepare
		creCommentDAO.save(SampleGenerator.generateSampleCREComment(0));
		QueryCondition condition=new QueryCondition(0,10);
		CREComment filter=new CREComment();	
		//test
		List<Comment> list=service.getCommentList(condition, filter,null, TypeComment.CREComment);
		//check
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		}
		finally
		{
			clearDB();
		}
	}
	
	@Test
	public void testCountCRE()
	{
		try{
		//prepare
		creCommentDAO.save(SampleGenerator.generateSampleCREComment(0));
		CREComment filter=new CREComment();	
		//test
		int count=service.getCommentCount(filter,null,TypeComment.CREComment);
		//check		
		Assert.assertTrue(count>0);
		}
		finally{
			clearDB();
		}
	}
	
	
	private void clearDB(){
		
		String hql="select c from %s c where c.message like :msg ";
		Session s=null;
		Transaction tx=null;
		try{
				s=sf.openSession();
				tx=s.getTransaction();
				tx.begin();
				{
				Query q=s.createQuery(String.format(hql + "and c.tourDepartureHistory is null", "TDComment"));
				q.setParameter("msg", "message:%");				
					List<TDComment> list=q.list();			
					for (TDComment comment : list) {
						tdCommentDAO.remove(comment);
					}
				}
				
				{
					Query q=s.createQuery(String.format(hql+"and c.uploadTourInfoFile is null", "TIComment"));
					q.setParameter("msg", "message:%");	
					List<TIComment> list=q.list();			
					for (TIComment comment : list) {
						tiCommentDAO.remove(comment);
					}
				}
				
				{
					Query q=s.createQuery(String.format(hql+ "and c.contentRepositoryExport is null", "CREComment"));
					q.setParameter("msg", "message:%");	
					List<CREComment> list=q.list();			
					for (CREComment comment : list) {
						creCommentDAO.remove(comment);
					}
				}
				{
					Query q=s.createQuery(String.format(hql + "and c.quartzJobHistory is null", "QHComment"));
					q.setParameter("msg", "message:%");	
					List<QHComment> list=q.list();			
					for (QHComment comment : list) {
						qhCommentDAO.remove(comment);
					}
				}
				
				tx.commit();
		}
		catch (Exception e) {
			if(tx!=null)
				tx.rollback();
			logger.error("",e);
		}
		finally{
			if(s!=null){				
				s.flush();
				s.close();
			}
		}
		
	}
}
