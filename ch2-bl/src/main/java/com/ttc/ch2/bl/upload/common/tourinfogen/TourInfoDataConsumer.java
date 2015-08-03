package com.ttc.ch2.bl.upload.common.tourinfogen;

import javax.xml.bind.JAXBException;

public interface TourInfoDataConsumer {

	public static final String ERROR_TAG = "<error>";

	public String processTourInfoV1(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfo) throws JAXBException;

	public String processTourInfoV3(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo tourInfo) throws JAXBException;

	public com.ttsl.tourinfo._2010._08._2.TourInfo processTourInfoV1(String tourInfoXml) throws JAXBException;
}
