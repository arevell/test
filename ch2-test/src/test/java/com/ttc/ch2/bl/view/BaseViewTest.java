package com.ttc.ch2.bl.view;

import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.junit.Assert;
import org.junit.Assume;

import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.common.BaseTest;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;

public class BaseViewTest extends BaseTest{

	
	@Inject
	protected ContentRepositoryService contentRepositoryService;

	protected ContentRepository getCr(){
		
		List<ContentRepository> list=contentRepositoryService.getContentRepositoriesList(new QueryCondition(0,1), new ContentRepository(), RepositoryStatus.TIandTD);		
		if(list.size()==0)
			Assume.assumeTrue("Test need data in DB on table content repository",false);
		
		ContentRepository cr=list.get(0);		
		Assert.assertNotNull(cr);
		return cr;
	}
	
	protected void printOutput(Source src)
	{
		try {
			  Transformer transformer = TransformerFactory.newInstance().newTransformer();
			  StreamResult result = new StreamResult(new StringWriter());			  
			  transformer.transform(src, result);
			  System.out.println(result.getWriter().toString());
			} catch(TransformerException ex) {
			  ex.printStackTrace();			  
			}
	}
}
