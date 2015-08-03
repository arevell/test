package com.ttc.ch2.ui.mvc;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ttc.util.messages.Severity;

public class ExceptionXmlConverterTest {
	
	/**
	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<error>
	    <message>
	        <interpolatedMessage>Test Message</interpolatedMessage>
	        <messageType>
	            <code>401</code>
	            <description>Test Message</description>
	            <severity>ERROR</severity>
	        </messageType>
	    </message>
	    <status>FAILURE</status>
	</error>
	**/
	
	@Test
	public void testConverterStatusCode() throws Exception{
		//prepare
		ExceptionXmlConverter testedObject=new ExceptionXmlConverter();		
//		test
		String contentXml=testedObject.convertToXmlByJaxb(401,"Test Message", Severity.ERROR);		
		//check
		Assert.assertNotNull(contentXml);
		Assert.assertTrue(contentXml.contains("<error>"));
		Assert.assertTrue(contentXml.contains("Test Message"));
		Assert.assertTrue(contentXml.contains("<code>401</code>"));
		Assert.assertTrue(contentXml.contains("<status>FAILURE</status>"));		
	}
	
	@Test
	public void testConverterException() throws Exception{
		//prepare
		ExceptionXmlConverter testedObject=new ExceptionXmlConverter();	
		Exception e=new Exception("Tested Exception");
//		test
		String contentXml=testedObject.convertToXmlByJaxb(e);		
		//check
		Assert.assertNotNull(contentXml);
		Assert.assertTrue(contentXml.contains("<error>"));
		Assert.assertTrue(contentXml.contains(e.getMessage()));
		Assert.assertTrue(contentXml.contains("<code>500</code>"));
		Assert.assertTrue(contentXml.contains("<status>FAILURE</status>"));		
	}
	
	@Test
	public void testConverterParams() throws Exception{
		//prepare
		ExceptionXmlConverter testedObject=new ExceptionXmlConverter();
		List<String> msgs=Lists.newArrayList("message:1","message:2","message:3");		
		Map<String,Object> params=Maps.newHashMap();
		params.put("code",503);
		params.put("description","Param Description");
		params.put("exception_msg", msgs);

//		test
		String contentXml=testedObject.convert(params);		
		//check
		Assert.assertNotNull(contentXml);
		Assert.assertTrue(contentXml.contains("<error>"));
		Assert.assertTrue(contentXml.contains(ObjectUtils.toString(params.get("description"))));
		Assert.assertTrue(contentXml.contains(ObjectUtils.toString(params.get("code"))));
		Assert.assertTrue(contentXml.contains("<status>FAILURE</status>"));
		
		for (String msg : msgs) {
			Assert.assertTrue(contentXml.contains("<description>"+msg+"</description>"));	
		}
		
	}

}
