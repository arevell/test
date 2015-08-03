package com.ttc.ch2.bl.departure;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import junit.framework.Assert;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.google.common.base.Joiner;
import com.ttc.ch2.bl.upload.UploadServiceException;
import com.ttc.ch2.bl.upload.common.tourinfogen.ITropicsV1TourInfoMapper;
import com.ttc.ch2.bl.upload.common.tourinfogen.StringWriterTourInfoDataConsumer;
import com.ttc.ch2.common.SchemaParam;
import com.ttsl.tourinfo._2010._08._2.TourInfo;

public class TourInfoV2MapingTest {

	@Test
	public void mapingTest() throws JAXBException, SAXException
	{
		JAXBContext jaxbContext = JAXBContext.newInstance(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo.class);
		Unmarshaller jaxbUnmarshaller =jaxbContext.createUnmarshaller();
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema"); 
		Schema schema = sf.newSchema(StringWriterTourInfoDataConsumer.class.getResource(SchemaParam.TI_3_0_0.getSchemaPath()));         
		jaxbUnmarshaller.setSchema(schema);
		Object tourInfo=jaxbUnmarshaller.unmarshal(TourInfoV2MapingTest.class.getResourceAsStream("sample_tourinfo_3.0.xml"));
		
		TourInfo ti=ITropicsV1TourInfoMapper.map((com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo) tourInfo);		
		Assert.assertNotNull(ti);
	}
	
	@Test
	public void mapingValidationTest() throws JAXBException,UploadServiceException, SAXException
	{
		JAXBContext jaxbContext = JAXBContext.newInstance(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo.class);
		Unmarshaller jaxbUnmarshaller =jaxbContext.createUnmarshaller();
		Object tourInfo=jaxbUnmarshaller.unmarshal(TourInfoV2MapingTest.class.getResourceAsStream("sample_tourinfo_3.0.xml"));				
		TourInfo ti=ITropicsV1TourInfoMapper.map((com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo) tourInfo);		
		Assert.assertNotNull(ti);
		
		StringWriterTourInfoDataConsumer consumer=new StringWriterTourInfoDataConsumer();
		consumer.init();
		String xml = consumer.processTourInfoV1(ti);
		boolean valid = (xml != null && !xml.startsWith(StringWriterTourInfoDataConsumer.ERROR_TAG)); 
		if(!valid){
			System.out.println(xml);
		}
				
		Assert.assertEquals(true, valid);
	}
}
