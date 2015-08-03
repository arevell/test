package com.ttc.ch2.quartz;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.impl.jdbcjobstore.JobStoreTX;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;
import com.ttc.ch2.scheduler.service.SchedulerForImportServiceImpl;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class InitializeQuartzTest extends BaseTest {

	@Inject
	@Qualifier("schedulerFactoryBean")
	private SchedulerFactoryBean schedulerFactory;
	
	@Inject
	private BrandDAO brandDAO; 
	
	
	@Test
	public void initializeTest() throws SchedulerServiceException, SchedulerException {		
		Assert.assertTrue(JobStoreTX.class.equals(schedulerFactory.getScheduler().getMetaData().getJobStoreClass()));
		Assert.assertTrue(schedulerFactory.getScheduler().getMetaData().isJobStoreClustered());
		
		for (Brand brand : brandDAO.findAll()) {
			JobDetail jobdetail=schedulerFactory.getScheduler().getJobDetail(SchedulerForImportService.jobImportName+"_"+brand.getCode(), SchedulerForImportServiceImpl.jobGroupName);		
			Assert.assertNotNull(jobdetail);	
		}
		
		
	}
	
}
