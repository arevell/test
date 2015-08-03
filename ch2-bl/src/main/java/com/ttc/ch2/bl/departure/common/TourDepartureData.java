package com.ttc.ch2.bl.departure.common;

import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;

public class TourDepartureData {

	private String fileName;
	private String checkSum;
	private String xmlContentV1;
	private String xmlContentV3;
	private TourDeparturesType tourDeparturesType;

	public TourDepartureData(String xmlContentV3, String checkSum, String fileName, TourDeparturesType tourDeparturesType) {

		super();

		this.setXmlContentV3(xmlContentV3);
		this.setCheckSum(checkSum);
		this.setFileName(fileName);
		this.setTourDeparturesType(tourDeparturesType);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	public String getXmlContentV1() {
		return xmlContentV1;
	}

	public void setXmlContentV1(String xmlContentV1) {
		this.xmlContentV1 = xmlContentV1;
	}

	public String getXmlContentV3() {
		return xmlContentV3;
	}

	public void setXmlContentV3(String xmlContentV3) {
		this.xmlContentV3 = xmlContentV3;
	}

	public TourDeparturesType getTourDeparturesType() {
		return tourDeparturesType;
	}

	public void setTourDeparturesType(TourDeparturesType tourDeparturesType) {
		this.tourDeparturesType = tourDeparturesType;
	}

	public String getTourCode() {
		return tourDeparturesType.getTourCode();
	}
}
