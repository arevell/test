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
		tiCommentDAO.save(SampleGenerator.generateSampleTIComment(0));
		tdCommentDAO.save(SampleGenerator.generateSampleTDComment(0));
		creCommentDAO.save(SampleGenerator.generateSampleCREComment(0));
		qhCommentDAO.save(SampleGenerator.generateSampleQHComment(0));
		
	}
	
	@Test
	public void testFilterTI()
	{
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
	
	@Test
	public void testCountTI()
	{
		//prepare
		tiCommentDAO.save(SampleGenerator.generateSampleTIComment(0));
		TIComment filter=new TIComment();	
		//test
		int count=service.getCommentCount(filter,null,TypeComment.TIComment);
		//check		
		Assert.assertTrue(count>0);
	}
	
	@Test
	public void testFilterTD()
	{
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
	
	@Test
	public void testCountTD()
	{
		//prepare
		tdCommentDAO.save(SampleGenerator.generateSampleTDComment(0));
		TDComment filter=new TDComment();	
		//test
		int count=service.getCommentCount(filter,null,TypeComment.TDComment);
		//check		
		Assert.assertTrue(count>0);
	}
	@Test
	public void testFilterQH()
	{
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
	
	@Test
	public void testCountQH()
	{
		//prepare
		qhCommentDAO.save(SampleGenerator.generateSampleQHComment(0));
		QHComment filter=new QHComment();	
		//test
		int count=service.getCommentCount(filter,null,TypeComment.QHComment);
		//check		
		Assert.assertTrue(count>0);
	}
	@Test
	public void testFilterCRE()
	{
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
	
	@Test
	public void testCountCRE()
	{
		//prepare
		creCommentDAO.save(SampleGenerator.generateSampleCREComment(0));
		CREComment filter=new CREComment();	
		//test
		int count=service.getCommentCount(filter,null,TypeComment.CREComment);
		//check		
		Assert.assertTrue(count>0);
	}
}
