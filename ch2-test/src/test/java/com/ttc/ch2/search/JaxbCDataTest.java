package com.ttc.ch2.search;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;

public class JaxbCDataTest {

	@Test
	public void cdataJaxbTest() throws JAXBException {
		JAXBContext jcTI = JAXBContext.newInstance(new Class[] { TourInfo.class });
		Unmarshaller jaxbTIUnmarshaller = jcTI.createUnmarshaller();
		Marshaller jaxbTIMarshaller = jcTI.createMarshaller();
		
		StreamSource tiXml = new StreamSource(JaxbCDataTest.class.getResourceAsStream("sample.xml"));
		TourInfo ti = (TourInfo) jaxbTIUnmarshaller.unmarshal(tiXml, TourInfo.class).getValue();
		
		jaxbTIMarshaller.marshal(ti, System.out);
	}
}
