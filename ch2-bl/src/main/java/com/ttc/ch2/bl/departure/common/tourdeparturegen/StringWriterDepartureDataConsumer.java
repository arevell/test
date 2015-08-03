package com.ttc.ch2.bl.departure.common.tourdeparturegen;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.bl.departure.common.LogOperationHelper;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.common.SchemaParam;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationDepartureInfo;

@Service
public class StringWriterDepartureDataConsumer implements TourDepartureDataConsumer {

	private static final Logger logger = LoggerFactory.getLogger(StringWriterDepartureDataConsumer.class);

	private static final String SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";

	private static final int BUFFER_SIZE = 1024;

	private JAXBContext jaxbContextV1;
	private JAXBContext jaxbContextV3;

	private com.ttsl.marketvariationdepartureinfo._2010._09._1.ObjectFactory objectFactoryV1;
	private com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.ObjectFactory objectFactoryV3;

	private Schema schemaV1;
	private Schema schemaV3;

	@PostConstruct
	public void init() throws JAXBException, SAXException {

		jaxbContextV1 = JAXBContext.newInstance(MarketVariationDepartureInfo.class);
		jaxbContextV3 = JAXBContext.newInstance(TourDeparturesType.class);

		objectFactoryV1 = new com.ttsl.marketvariationdepartureinfo._2010._09._1.ObjectFactory();
		objectFactoryV3 = new com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.ObjectFactory();

		schemaV1 = SchemaFactory.newInstance(SCHEMA_LANGUAGE).newSchema(getClass().getResource(SchemaParam.TD_1_1_0.getSchemaPath()));
		schemaV3 = SchemaFactory.newInstance(SCHEMA_LANGUAGE).newSchema(getClass().getResource(SchemaParam.TD_3_0_0.getSchemaPath()));
	}

	public String processTourDepartureV1(MarketVariationDepartureInfo tourDeparture, OperationStatus operationStatus) throws JAXBException {

		if (tourDeparture == null) {
			return null;
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(BUFFER_SIZE);

		ValidatorSchemaHandler validateHandler = new ValidatorSchemaHandler(operationStatus, tourDeparture.getMarketVariationCode());

		Marshaller jaxbMarshallerV1 = jaxbContextV1.createMarshaller();

		jaxbMarshallerV1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshallerV1.setSchema(schemaV1);
		jaxbMarshallerV1.setEventHandler(validateHandler);
		jaxbMarshallerV1.marshal(objectFactoryV1.createMarketVariationDepartureInfo(tourDeparture), outputStream);

		return validateHandler.noErrors ? new String(outputStream.toByteArray(), StandardCharsets.UTF_8) : null;
	}

	public String processTourDepartureV3(TourDeparturesType tourDeparture, OperationStatus operationStatus) throws JAXBException {

		if (tourDeparture == null) {
			return null;
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(BUFFER_SIZE);

		ValidatorSchemaHandler validateHandler = new ValidatorSchemaHandler(operationStatus, tourDeparture.getTourCode());

		Marshaller jaxbMarshallerV3 = jaxbContextV3.createMarshaller();

		jaxbMarshallerV3.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshallerV3.setSchema(schemaV3);
		jaxbMarshallerV3.setEventHandler(validateHandler);
		jaxbMarshallerV3.marshal(objectFactoryV3.createTourDepartures(tourDeparture), outputStream);

		return validateHandler.noErrors ? new String(outputStream.toByteArray(), StandardCharsets.UTF_8) : null;
	}

	class ValidatorSchemaHandler implements ValidationEventHandler {

		private OperationStatus operationStatus;
		private String productCode;
		private boolean noErrors = true;

		public ValidatorSchemaHandler(OperationStatus operationStatus, String productCode) {
			this.operationStatus = operationStatus;
			this.productCode = productCode;
		}

		@Override
		public boolean handleEvent(ValidationEvent event) {
			if (event.getSeverity() != ValidationEvent.WARNING) {
				ValidationEventLocator vel = event.getLocator();
				LogOperationHelper.logMessageForProduct(logger, operationStatus, productCode, TropicSynchronizeMessages.SCHEMA_VALIDATION_MARK,
						"Line:Col[" + vel.getLineNumber() + ":" + vel.getColumnNumber() + "]:" + event.getMessage());
				noErrors = false;
			}
			return true;
		}

		public boolean isNoErrors() {
			return noErrors;
		}
	}
}
