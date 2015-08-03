package com.ttc.ch2.bl.departure.common.tourdeparturegen;

import javax.xml.bind.JAXBException;

import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationDepartureInfo;

public interface TourDepartureDataConsumer {

	public String processTourDepartureV1(MarketVariationDepartureInfo tourDeparture, OperationStatus operationStatus) throws JAXBException;

	public String processTourDepartureV3(TourDeparturesType tourDeparture, OperationStatus operationStatus) throws JAXBException;
}
