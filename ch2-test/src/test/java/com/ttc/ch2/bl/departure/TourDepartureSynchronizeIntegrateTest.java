package com.ttc.ch2.bl.departure;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.domain.departure.TourDepartureHistory.TourDepartureStatus;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.scheduler.service.SchedulerForImportServiceImpl;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/testCtx.xml","classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TourDepartureSynchronizeIntegrateTest extends BaseTest{
	
	@Inject
	private TropicDepartureMainSynchronizeService service =null;
	
	@Inject
	private QuartzJobCh2Service service2;
	
	@Test
	public void synchronizeTest()
	{
		boolean exception=false;
		
		try{
		QuartzJobHistory h=SampleGenerator.generateSampleQuartzJobHistory(1);
		QuartzJob job=service2.findByName(SchedulerForImportServiceImpl.jobImportName,"BV");
		h.setQuartzJob(job);
		OperationStatus newOpStatus=new OperationStatus();
//		job.getQuartzJobHistory().add(h);
		OperationStatus opStatus=service.departureSynchronizeOperation(newOpStatus);
		Assert.assertNotNull(opStatus);
		Assert.assertTrue(opStatus.getStatus()==TourDepartureStatus.SUCCESS_OPERATION_END);
		}
		catch (Exception e) {
			exception=true;
			e.printStackTrace();
		}
		Assert.assertFalse(exception);
	}
}
