package com.ttc.ch2.dao;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.common.collect.Sets;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/testOnlyCtx.xml","classpath:/META-INF/spring/daoCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@FixMethodOrder(MethodSorters.JVM)
public class ContentRepositoryTest {
	
	
	@Inject
	private ContentRepositoryDAO dao;
	
	@Inject
	private BrandDAO brandDao;
		
	@Inject
	private SessionFactory sf;
	
	@Test
	@Ignore(" this test may create locks on environment")
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

	@Test
	@Ignore(" this test may create locks on environment")
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public void extendIdListTest(){
		Long ids[] = {20584l,20586l,20588l};
		List<Long> idsret = dao.getExtendedCRIdsforSearchToursAggregated(Arrays.asList(ids), "IV");
		System.out.println(idsret);	
	}
	

	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void findByTourCodes_notourcodes(){
		List<ContentRepository> list = dao.findByTourCodes(Collections.<String>emptyList(), "TT");
		assertThat(list, empty());
	}
	

	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void findByTourCodes_half_of_ELEMENTS_IN_CLAUSE(){
		testLimit(0.5, "half of limit");
	}

	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void findByTourCodes_doubled_ELEMENTS_IN_CLAUSE(){	
		testLimit(2.0, "doubled limit");	
	}
	
	private void testLimit(double part, String message) {
		String hql = "select r.tourCode from ContentRepository r inner join r.brands as B inner join r.brands as B where  r.repositoryStatus in :status and B.code=:brandCode";
		
		Session session=sf.getCurrentSession();				
		Query q=session.createQuery(hql);
		q.setParameter("brandCode","TT");	
		q.setParameterList("status",new Object[]{RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly});
		@SuppressWarnings("unchecked") List<String> tourCodes  = q.list();
		
		List<String> tours = new ArrayList<>(tourCodes);
		int limit = (int)( BaseDao.MAX_ELEMENTS_INSIDE_SQL_IN_CLAUSE * part);
		assumeThat(message, limit, lessThan(tours.size()));
		tours.retainAll(tours.subList(0, limit));
		
		List<ContentRepository> list = dao.findByTourCodes(tours, "TT");
		assertThat(list, notNullValue());
		assertThat(list, not(empty()));
	}
	
}
