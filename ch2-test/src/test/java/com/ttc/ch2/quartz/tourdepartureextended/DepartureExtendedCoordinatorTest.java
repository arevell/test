package com.ttc.ch2.quartz.tourdepartureextended;

import java.util.EnumMap;
import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.JobCoordinator;
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.dao.RecReportDataDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.RecReportData;
import com.ttc.ch2.quartz.executionlisteners.InitializeImportDepartureJob;
import com.ttc.ch2.quartz.executionlisteners.ScheduleInstancePreparer;
import com.ttc.ch2.scheduler.common.DepartureExtendedCoordinator;
import com.ttc.ch2.search.export.IndexSynchronizerVO;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, ScheduleInstancePreparer.class,InitializeImportDepartureJob.class})
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml","classpath:/META-INF/spring/quartzTestCtx.xml"})
public class DepartureExtendedCoordinatorTest extends BaseTest {
	
	
	@Inject
	@Qualifier("schedulerFactoryBean")
	protected SchedulerFactoryBean schedulerFactory;
	

	@Inject
	private RecReportDataDAO recReportDataDAO;
	
	@Test
	public void insertDataTest() throws SchedulerException{	
		
		//prepare
		Brand b=new Brand();
		b.setCode("BV");
		b.setBrandName("BVUSAS");
		
		FileCollectVO fVo=new FileCollectVO();
		fVo.setBrand(b);
		fVo.setSellingCompanies(Lists.newArrayList(SampleGenerator.generateSellingCompany(b)));
		fVo.getToursListNotAdded().add("xxxx");
		fVo.getZipListNotAdded().add("xxxx");
		fVo.getZipListNotDeleted().add("xxxx");
				
		IndexSynchronizerVO iVo=new IndexSynchronizerVO();
		iVo.setIndexingAll(true);
		iVo.setUnexpectedErrorOccurred(false);
		iVo.getAggregatedIDsListNotAdded().add("yyyyy");
		iVo.getAggregatedIDsListNotDeleted().add("yyyyy");
		iVo.getDocumentIDsListNotAdded().add("yyyyy");
		iVo.getDocumentIDsListNotDeleted().add("yyyyy");
		
		EnumMap<JobCoordinator.Params,Object> params=new EnumMap<>(JobCoordinator.Params.class);
		params.put(JobCoordinator.Params.FILE_COLLECT_VO, fVo);
		params.put(JobCoordinator.Params.INDEX_VO, iVo);
		
		DepartureExtendedCoordinator coordinator=new DepartureExtendedCoordinator(b.getCode());
		
		try{
		//test
		coordinator.setup(params);
		
		//check
		List<RecReportData> list=recReportDataDAO.findAll();
		Assert.assertThat("RecReportData not exist in DB", list, Matchers.hasSize(Matchers.greaterThan(1)));
		
		boolean existIVO=false;
		boolean existFCVO=false;
		for (RecReportData recReportData : list) {
			
			if(RecReportData.Type.FileCollectVO==recReportData.getType()){
				existFCVO=true;
			}
			
			if(RecReportData.Type.IndexSearchingVO==recReportData.getType()){
				existIVO=true;
			}			
		}
		
		Assert.assertTrue("Expected FileCollectVO in RecReportData",existFCVO);
		Assert.assertTrue("Expected IndexSearchingVO in RecReportData",existIVO);
				 
		}
		finally{
			clear();
		}
	}
	
	
	private void clear(){
		
		String contentValue1="<string>yyyyy</string>";
		String contentValue2="<name>xxxxxx</name>";
		
		List<RecReportData> data=recReportDataDAO.findAll();
		for (RecReportData r: data) {
			if(r.getContent().contains(contentValue1) || r.getContent().contains(contentValue2)){
				recReportDataDAO.remove(r);
			}			
		}
	}
	
//	@Test
//	public void insertDataTestVer2() throws SchedulerException{	
//		
//		Brand b=new Brand();
//		b.setCode("BV");
//		b.setBrandName("BVUSAS");
//				
//		Scheduler s=schedulerFactory.getScheduler();
//		JobDetail d=new JobDetail(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString()+"_"+b.getCode(), SchedulerForDepExtImportService.jobGroupName,DepartureExtendedSynchronizeJob.class);
//		SimpleTrigger t=new SimpleTrigger(SchedulerForDepExtImportService.triggerName,SchedulerForDepExtImportService.triggerGroupName);
//		t.setStartTime(DateUtils.addSeconds(new Date(), 10));
//		t.setRepeatInterval(1000*60);
//		t.setRepeatCount(10);
//		s.scheduleJob(d, t);
//		s.start();
//
//	
//		FileCollectVO fVo=new FileCollectVO();
//		fVo.setBrand(b);
//		fVo.setSellingCompanies(Lists.newArrayList(SampleGenerator.generateSellingCompany(b)));
//		fVo.getToursListNotAdded().add("xxxx");
//		fVo.getZipListNotAdded().add("xxxx");
//		fVo.getZipListNotDeleted().add("xxxx");
//				
//		IndexSynchronizerVO iVo=new IndexSynchronizerVO();
//		iVo.getAggregatedIDsListNotAdded().add("yyyyy");
//		iVo.getAggregatedIDsListNotDeleted().add("yyyyy");
//		iVo.getDocumentIDsListNotAdded().add("yyyyy");
//		iVo.getDocumentIDsListNotDeleted().add("yyyyy");
//		
//		EnumMap<JobCoordinator.Params,Object> params=new EnumMap<>(JobCoordinator.Params.class);
//		params.put(JobCoordinator.Params.FILE_COLLECT_VO, fVo);
//		params.put(JobCoordinator.Params.INDEX_VO, iVo);
//		
//		DepartureExtendedCoordinator coordinator=new DepartureExtendedCoordinator(b.getCode());
//		
//		JobDetail jobDetail=null;
//		try{
//		//test
//		coordinator.setup(params);
//		
//		//check
//		 jobDetail=schedulerFactory.getScheduler().getJobDetail(QuartzJob.JobName.DepartureExtendedSynchronizeJob.toString()+"_"+b.getCode(), SchedulerForDepExtImportService.jobGroupName);
//		 Assert.assertNotNull("FILE_COLLECT_VO_MAP not exist in job dataMap",jobDetail.getJobDataMap().get(JobParams.FILE_COLLECT_VO_MAP.toString()));
//		 Assert.assertNotNull("INDEX_VO_MAP not exist in job dataMap",(jobDetail.getJobDataMap().get(JobParams.INDEX_VO_MAP.toString())));		 
//		 Assert.assertTrue("INDEX_VO_MAP has incorrect number of object expected 1 recived:"+((Map<Date,IndexSynchronizerVO>)jobDetail.getJobDataMap().get(JobParams.INDEX_VO_MAP.toString())).size(),((Map<Date,IndexSynchronizerVO>)jobDetail.getJobDataMap().get(JobParams.INDEX_VO_MAP.toString())).size()==1);
//		 Assert.assertTrue("FILE_COLLECT_VO_MAP has incorrect number of object expected 1 recived:"+((Map<Date,FileCollectVO>)jobDetail.getJobDataMap().get(JobParams.FILE_COLLECT_VO_MAP.toString())).size(),((Map<Date,FileCollectVO>)jobDetail.getJobDataMap().get(JobParams.FILE_COLLECT_VO_MAP.toString())).size()==1);
//		}
//		finally{
//			
//		}
//		
//	}

}
