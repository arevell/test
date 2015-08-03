package com.ttc.ch2.bl.view;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.domain.tour.ContentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/testCtx.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TourMatchingViewGenerateXmlTest extends BaseViewTest{

	@Inject
	TourMatchingViewService tourMachingView;
		
	@Test(expected=IllegalArgumentException.class)
	public void  nullParamTestVer01() throws Exception
	{
		tourMachingView.getTransformateContentView(null,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullParamTestVer02() throws Exception
	{
		tourMachingView.getTransformateContentView("","");
	}
		
	@Test(expected=TourMatchingViewException.class)
	public void inccorectTourcodeTest() throws Exception
	{
		tourMachingView.getTransformateContentView("wwww","xx");
	}
	
	@Test
	@Transactional
	public void  positiveTest() throws Exception
	{
		//prepare
		ContentRepository cr=getCr();
		TourMatchingViewServiceImpl obj=getTargetObject(tourMachingView, TourMatchingViewServiceImpl.class);		
		//test
		String src=Whitebox.invokeMethod(obj, "generate",new Object[]{cr});
		//when
		Assert.assertNotNull(src);
//		System.out.println(src);
	}
	
	
	
	@Test
	@Transactional
	public void  positiveForNewVersionTest() throws Exception
	{
		//prepare
		ContentRepository cr=getCr();
		TourMatchingViewServiceImpl obj=getTargetObject(tourMachingView, TourMatchingViewServiceImpl.class);
		//test
		String src=Whitebox.invokeMethod(obj, "generateNew",new Object[]{cr});
		//when
		Assert.assertNotNull(src);
//		System.out.println(src);
		
//		FileUtils.write(new File("D:/view.html"), src);
	}
	
	
}
