package com.ttc.ch2.bl.view;

import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TourMatchingViewMergeXmlTest extends BaseViewTest{

	
	@Inject
	ContentRepositoryService contentRepositoryService;
	
	@Inject
	TourMatchingViewService tourMachingView;
	
	@Test
	@Transactional
	public void  positiveSampleTest() throws Exception
	{			
		Source src=Whitebox.invokeMethod(new TourMatchingViewServiceImpl(), "generateSourceDocument",new Object[]{SampleGenerator.generateSampleXml(),SampleGenerator.generateSampleXml()});		
		Assert.assertNotNull(src);
//		printOutput(src);
	}
	
	@Test
	@Transactional
	public void  positiveTest() throws Exception
	{
		ContentRepository cr=getCr();
		TourMatchingViewServiceImpl obj=getTargetObject(tourMachingView, TourMatchingViewServiceImpl.class);
		Source src=Whitebox.invokeMethod(obj, "generateSourceDocument",new Object[]{cr.getXmlContentRepository().get(0).getOldTourInfoXML(),cr.getXmlContentRepository().get(0).getOldTourDepartureXML()});
		Assert.assertNotNull(src);
//		printOutput(src);
	}
	
	@Test
	@Transactional
	public void  positiveForNewVersionTest() throws Exception
	{		
		ContentRepository cr=getCr();
		TourMatchingViewServiceImpl obj=getTargetObject(tourMachingView, TourMatchingViewServiceImpl.class);
		Source src=Whitebox.invokeMethod(obj, "generateSourceDocument",new Object[]{cr.getXmlContentRepository().get(0).getTourInfoXML(),cr.getXmlContentRepository().get(0).getTourDepartureXML()});
		Assert.assertNotNull(src);
//		printOutput(src);
	}


	
	
	

}
