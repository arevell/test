package com.ttc.ch2.dao;

import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.common.collect.Sets;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;

// this test may create locks on environment
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/testOnlyCtx.xml","classpath:/META-INF/spring/daoCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ContentRepositoryTest {
	
	
	@Inject
	private ContentRepositoryDAO dao;
	
	@Inject
	private BrandDAO brandDao;
		
	@Inject
	private SessionFactory sf;
	
//	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void deleteingEmptyCrTest(){
		
		//prepare
		StringBuilder sb=new StringBuilder();
		sb.append("select count(*) from ContentRepository r ");
		sb.append("inner join r.brands as B ");		
		sb.append("where r.tourInfoXMLMD5 is null and r.tourDepartureXMLMD5 is null ");		
		sb.append("and B.code=:brandCode");			
		Session session=sf.getCurrentSession();				
		Query q=session.createQuery(sb.toString());
		q.setParameter("brandCode","BV");	
		Long count=(Long) q.uniqueResult();
		//test
		int deletedCount=dao.deleteEmptyContentRepository("BV");
		
		//check
		Assert.assertEquals("Invalid deleted rows",(int)count.longValue(), deletedCount);		
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void clearCrTest(){
		
		//prepare
		Brand bv=brandDao.findByBrandCode("BV");
				
		StringBuilder sb=new StringBuilder();
		sb.append("select r.id from ContentRepository r ");
		sb.append("inner join r.brands as B ");		
		sb.append("where  r.repositoryStatus in :status ");		
		sb.append("and B.code=:brandCode");					
		Session session=sf.getCurrentSession();				
		Query q=session.createQuery(sb.toString());
		q.setParameter("brandCode","BV");	
		q.setParameterList("status",new Object[]{RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly});	
		List<Long> ids= (List<Long>) q.list();
		int count=ids.size()-1;
		if(count==0){
			System.out.println("nothing to test");
			return;
		}
				
		//test		
		List<ContentRepository> toClearData=dao.getContentRepositoriesElementsToClear(Sets.newHashSet(ids.get(0)),bv,RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly);		
		//check
		Assert.assertEquals("Invalid count rows to clear",(int)count, toClearData.size());
		for (ContentRepository cr : toClearData) {
			Assert.assertTrue("Invalid status of data",(cr.getRepositoryStatus()==RepositoryStatus.TIandTD || cr.getRepositoryStatus()==RepositoryStatus.TourDepartureOnly));				
		}
		
	}

}
