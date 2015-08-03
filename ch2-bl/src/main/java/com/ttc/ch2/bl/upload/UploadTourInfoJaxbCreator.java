package com.ttc.ch2.bl.upload;

import java.io.StringReader;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.ttc.ch2.bl.upload.common.ValidatorSchemaHandler;
import com.ttc.ch2.common.SchemaParam;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;

@Component
public class UploadTourInfoJaxbCreator {

	
	private JAXBContext jaxbContext;
	
	@PostConstruct
	private void init() throws JAXBException
	{
		jaxbContext = JAXBContext.newInstance(TourInfo.class);
	}
	
	
	public Unmarshaller createUnmarshaller(boolean schemaValidate) throws JAXBException, SAXException
	{		
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();		
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema"); 
		Schema schema = sf.newSchema(TourInfoZipParserServiceImpl.class.getResource(SchemaParam.TI_3_0_0.getSchemaPath()));  
				
		if(schemaValidate){
			jaxbUnmarshaller.setSchema(schema);
			jaxbUnmarshaller.setEventHandler(new ValidatorSchemaHandler());
		}
		return jaxbUnmarshaller;
	}
	
	
	@Deprecated
	private Schema createSchemaMix() throws SAXException
	{
		URL xsdUrlA = TourInfo.class.getResource("TourInfo.3.0.xsd");
		URL xsdUrlB = TourInfo.class.getResource("location_lists.2.xsd");
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		
		String W3C_XSD_TOP_ELEMENT =
		"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
		   + "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0\" elementFormDefault=\"qualified\">\n"
		   + "<xs:include schemaLocation=\"" +xsdUrlA.getPath() +"\"/>\n"
		   + "<xs:include schemaLocation=\"" +xsdUrlB.getPath() +"\"/>\n"
		   +"</xs:schema>";
		//System.out.println(W3C_XSD_TOP_ELEMENT);
		Schema schema = schemaFactory.newSchema(new StreamSource(new StringReader(W3C_XSD_TOP_ELEMENT), "xsdTop"));
		return schema;
	}
}
