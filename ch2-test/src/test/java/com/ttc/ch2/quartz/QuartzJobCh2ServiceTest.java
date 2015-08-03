package com.ttc.ch2.quartz;

import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.departure.TourDepartureHistoryDAO;
import com.ttc.ch2.dao.jobs.QuartzJobHistoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.jobs.QuartzJobHistory.JobHistoryStatus;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.scheduler.service.QuartzJobCh2ServiceException;
import com.ttc.test.helpservice.QuartzJobServiceHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzTestCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class QuartzJobCh2ServiceTest extends BaseTest {

	
	public static String brandCode="BV";
	
	public static String jobName=QuartzJob.JobName.DepartureSynchronizeJob.toString()+"_"+brandCode;
	
	@Inject
	private QuartzJobCh2Service serviceToTest;
	
	@Inject
	private QuartzJobHistoryDAO daoHistory;
		
	@Inject
	private TourDepartureHistoryDAO tdhDao;
	
	@Inject
	private QuartzJobServiceHelper quartzJobServiceHelper;
	
	@Inject 
	private BrandDAO brandDAO;
	
	
	private QuartzJobHistory prepare(){
		Brand b=brandDAO.findByBrandCode(brandCode);
		QuartzJobHistory qh=SampleGenerator.generateSampleQuartzJobHistory(1);	
		qh.setBrand(b);
		qh.getTourDepartureHistory().setBrand(b);
		qh.setQuartzJob(getQuartzJob());
		daoHistory.save(qh);
		tdhDao.save(qh.getTourDepartureHistory());
		tdhDao.flush();
		
		QuartzJobHistory result=daoHistory.find(qh.getId());		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getId()!=0l);
		Assert.assertTrue(result.getTourDepartureHistory().getId()!=0l);
		Assert.assertNotNull(result.getTourDepartureHistory().getQuartzJobHistory());
		Assert.assertTrue(result.getTourDepartureHistory().getQuartzJobHistory().getId()!=0l);
		return result;
	}
	
	@Test
	@Transactional
	public void positiveGetFullDataQuartzJobHistory() {
		//prepare
		QuartzJobHistory h=prepare();
		
		try{
		//test
		QuartzJobHistory result=serviceToTest.getFullDataQuartzJobHistory(h.getId());
		//check
			//lazy check		
			Assert.assertTrue(result.getComments().size()>0);
			Assert.assertNotNull(result.getTourDepartureHistory());
			Assert.assertTrue(result.getTourDepartureHistory().getComments().size()>0);
		}
		finally{
			daoHistory.remove(h);
			tdhDao.remove(h.getTourDepartureHistory());
		}
		
	}
	
	@Test
	public void negativeGetFullDataQuartzJobHistory() {
		//prepare
		boolean exceptionExist=false;
		//test
		try{
			serviceToTest.getFullDataQuartzJobHistory(null);
		}
		catch (QuartzJobCh2ServiceException exception) {
			if (exception.getCause() instanceof IllegalArgumentException) {
				exceptionExist = true;
			}
		}
		//check		
		Assert.assertTrue(exceptionExist);		
	}
	
	@Test
	public void negativeGetFullDataQuartzJobHistoryInvalidId() {
		//test		
		QuartzJobHistory result=serviceToTest.getFullDataQuartzJobHistory(-1l);		
		//check
		Assert.assertNull(result);		
	}
	
	@Test
	public void positiveFindByName() {
		//test
		QuartzJob job=serviceToTest.findByName(QuartzJob.JobName.DepartureSynchronizeJob.toString(),"BV");		
		//check
		Assert.assertNotNull(job);
	}
	
	@Test
	public void negativeFindByName() {
		//test
		QuartzJob job=serviceToTest.findByName(null,"BV");		
		//check
		Assert.assertNull(job);
		//test
		QuartzJob job2=serviceToTest.findByName("xxxx","BV");		
		//check
		Assert.assertNull(job2);
	}
	
	@Test
	@Transactional
	public void positiveSave() {
		
		//prepare
		QuartzJob srcJob=getQuartzJob();		
		Date newDate=DateUtils.addDays(new Date(), 2);
		srcJob.setNextFiringTime(newDate);
		//test
		serviceToTest.saveQuartzJob(srcJob);
		//check
		QuartzJob updatedJob=getQuartzJob();
		
		Assert.assertTrue(updatedJob.getNextFiringTime().getTime()==newDate.getTime());
	}
	
	@Test
	@Transactional
	public void negativeSaveNullParam() {
		
		//prepare
		boolean exceptionExist=false;
		//test
		try{
		serviceToTest.saveQuartzJob(null);
		}
		catch (QuartzJobCh2ServiceException exception) {
				if (exception.getCause() instanceof IllegalArgumentException) {
					exceptionExist = true;
				}
			}
		//check
		Assert.assertTrue(exceptionExist);
	}
	@Test
	@Transactional
	public void negativeSaveInvalidId() {
		
		//prepare
		boolean exceptionExist=false;
		QuartzJob srcJob=getQuartzJob();	
		srcJob.setId(-1l);
		//test
		try{
			serviceToTest.saveQuartzJob(srcJob);
		}
		catch (QuartzJobCh2ServiceException exception) {			
			exceptionExist = true;
			Assert.assertTrue(exception.getMessage().startsWith("Can not find job"));
		}
		//check
		Assert.assertTrue(exceptionExist);
	}
	
	@Test
	@Transactional
	public void positiveMergeQuartzJobHistory() {
		//prepare
		QuartzJobHistory h=prepare();
		h.setQuartzJob(getQuartzJob());
		try{
		h.setStatus(JobHistoryStatus.Fail);
		//test
		serviceToTest.mergeQuartzJobHistory(h);
		//check
		Assert.assertTrue(daoHistory.find(h.getId()).getStatus()==JobHistoryStatus.Fail);
		}
		finally{
			daoHistory.remove(h);
			tdhDao.remove(h.getTourDepartureHistory());			
		}
	}
	
	@Test
	@Transactional
	public void positiveSaveNewQuartzJobHistory() {
		//prepare
		QuartzJobHistory h=SampleGenerator.generateSampleQuartzJobHistory(1);
		h.setBrand(brandDAO.findByBrandCode(brandCode));
		h.setQuartzJob(getQuartzJob());
		//test
		serviceToTest.saveNewQuartzJobHistory(h);
		//check
		Assert.assertNotNull(daoHistory.find(h.getId()));
		Assert.assertNotNull(daoHistory.find(h.getId()).getQuartzJob());
		Assert.assertTrue(daoHistory.find(h.getId()).getQuartzJob().getJobName().equals(QuartzJob.JobName.DepartureSynchronizeJob.toString()));		
	}
	
	public QuartzJob getQuartzJob()
	{
		QuartzJob srcJob=quartzJobServiceHelper.getQuartzJob(brandCode);
		return srcJob;
	}
}
