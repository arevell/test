package com.ttc.ch2.bl.upload.common.tourinfogen;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.marshaller.NioEscapeHandler;
import com.ttc.ch2.common.SchemaParam;

@Service
public class StringWriterTourInfoDataConsumer implements TourInfoDataConsumer {

	private static final Logger logger = LoggerFactory.getLogger(StringWriterTourInfoDataConsumer.class);

	private static final String SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";

	private JAXBContext jaxbContextV1;
	private JAXBContext jaxbContextV3;

	private Schema schemaV1;
	private Schema schemaV3;

	@PostConstruct
	public void init() throws JAXBException, SAXException {

		jaxbContextV1 = JAXBContext.newInstance(com.ttsl.tourinfo._2010._08._2.TourInfo.class);
		jaxbContextV3 = JAXBContext.newInstance(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo.class);

		schemaV1 = SchemaFactory.newInstance(SCHEMA_LANGUAGE).newSchema(getClass().getResource(SchemaParam.TI_2_4_0.getSchemaPath()));
		schemaV3 = SchemaFactory.newInstance(SCHEMA_LANGUAGE).newSchema(getClass().getResource(SchemaParam.TI_3_0_0.getSchemaPath()));
	}

	@Override
	public String processTourInfoV1(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfo) throws JAXBException {

		if (tourInfo == null) {
			return null;
		}

		StringWriter stringWriter = new StringWriter();

		ValidatorSchemaHandler validateHandler = new ValidatorSchemaHandler();

		Marshaller jaxbMarshallerV3 = jaxbContextV1.createMarshaller();

		jaxbMarshallerV3.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshallerV3.setProperty(CharacterEscapeHandler.class.getName(), new NioEscapeHandler(StandardCharsets.UTF_8.name()));
		jaxbMarshallerV3.setSchema(schemaV1);
		jaxbMarshallerV3.setEventHandler(validateHandler);
		jaxbMarshallerV3.marshal(tourInfo, stringWriter);

		return validateHandler.noErrors ? stringWriter.toString() : ERROR_TAG + StringUtils.join(validateHandler.getLogs(), "\n");
	}

	@Override
	public String processTourInfoV3(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo tourInfo) throws JAXBException {

		if (tourInfo == null) {
			return null;
		}

		StringWriter stringWriter = new StringWriter();

		ValidatorSchemaHandler validateHandler = new ValidatorSchemaHandler();

		Marshaller jaxbMarshallerV3 = jaxbContextV3.createMarshaller();

		jaxbMarshallerV3.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshallerV3.setProperty(CharacterEscapeHandler.class.getName(), new NioEscapeHandler(StandardCharsets.UTF_8.name()));
		jaxbMarshallerV3.setSchema(schemaV3);
		jaxbMarshallerV3.setEventHandler(validateHandler);
		jaxbMarshallerV3.marshal(tourInfo, stringWriter);

		return validateHandler.noErrors ? stringWriter.toString() : ERROR_TAG + StringUtils.join(validateHandler.getLogs(), "\n");
	}

	public com.ttsl.tourinfo._2010._08._2.TourInfo processTourInfoV1(String tourInfoXml) throws JAXBException {

		ValidatorSchemaHandler validateHandler = new ValidatorSchemaHandler();

		Unmarshaller jaxUnmarshallerV1 = jaxbContextV1.createUnmarshaller();

		jaxUnmarshallerV1.setSchema(schemaV1);
		jaxUnmarshallerV1.setEventHandler(validateHandler);

		com.ttsl.tourinfo._2010._08._2.TourInfo tourInfo = (com.ttsl.tourinfo._2010._08._2.TourInfo) jaxUnmarshallerV1.unmarshal(new ByteArrayInputStream(tourInfoXml.getBytes(StandardCharsets.UTF_8)));

		return tourInfo;
	}

	class ValidatorSchemaHandler implements ValidationEventHandler {

		private List<String> logs = Lists.newArrayList();
		private boolean noErrors = true;

		@Override
		public boolean handleEvent(ValidationEvent event) {
			if (event.getSeverity() != ValidationEvent.WARNING) {
				ValidationEventLocator vel = event.getLocator();
				String msg = "Line:Col[" + vel.getLineNumber() + ":" + vel.getColumnNumber() + "]:" + event.getMessage();
				logger.error(msg);
				logs.add(msg);
				noErrors = false;
			}
			return true;
		}

		public boolean isNoErrors() {
			return noErrors;
		}

		public List<String> getLogs() {
			return logs;
		}
	}
}
